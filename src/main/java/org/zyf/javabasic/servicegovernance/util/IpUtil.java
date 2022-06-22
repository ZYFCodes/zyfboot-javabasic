package org.zyf.javabasic.servicegovernance.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankuai.inf.octo.mns.listener.IIdcChangeListener;
import com.sankuai.inf.octo.mns.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.zyf.javabasic.servicegovernance.constants.Consts;
import org.zyf.javabasic.servicegovernance.model.Idc;
import org.zyf.javabasic.servicegovernance.thread.ScheduleTaskFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/22  23:08
 */
public class IpUtil {
    private static final Logger LOG = LoggerFactory.getLogger(IpUtil.class);
    private static final String IP_REGEX = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";


    private static final Object lock = new Object();
    private static final Object idcXmlLock = new Object();
    private static final Object idcxmlInitFromRemoteLock = new Object();
    private static boolean isInitSGAgentIDCXml = false;
    private static boolean idcXmlValid = false;
    private static boolean isIdcCacheInit = false;
    private static String UNKNOWN = "unknown";

    // internal IDC cache, get idc info for special ip
    private static List<IDC> idcCache = new ArrayList<IDC>();
    private static long idcLastModifiedTime = 0;
    // the exposed idc class in idl-common
    private static List<Idc> idcxml = new ArrayList<Idc>();
    private static boolean isIdcxmlInitFromRemote = false;
    private static Map<Integer, IDC> idcMap = null;
    private static final ReentrantReadWriteLock idcMaplock = new ReentrantReadWriteLock();
    private static Set<Integer> maskSet = null;
    /**
     *idc文件监听器
     */
    private volatile static Set<IIdcChangeListener> idcChangeListeners = Collections.newSetFromMap(new ConcurrentHashMap<IIdcChangeListener, Boolean>());
    private static ExecutorService idcListenerExecutor = Executors.newFixedThreadPool(1, new ScheduleTaskFactory("MnsInvoker-Idc-Schedule"));
    private static final Object idcListenerLock = new Object();

    static {
        CommonUtil.mnsCommonSchedule.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                File file = new File(Consts.sg_idcFile);
                if (file.exists() && idcLastModifiedTime != file.lastModified()) {
                    try {
                        List<IDC> idcs = new ArrayList<IDC>();
                        boolean ret = initSGAgentIDCXml(Consts.sg_idcFile, idcs);
                        if (ret) {
                            final List<IDC> oldIdcs = getIdcCache();
                            final List<IDC> newIdcs = idcs;
                            if (!idcs.isEmpty() && idcDiff(oldIdcs, newIdcs)) {
                                if (isIdcCacheInit) {
                                    LOG.info("start to trigger idc listener, size: {}", idcChangeListeners.size());
                                    idcListenerExecutor.submit(new Runnable() {
                                        @Override
                                        public void run() {
                                            for(IIdcChangeListener listener: idcChangeListeners) {
                                                try {
                                                    //listener.changed(oldIdcs, newIdcs);
                                                } catch (Exception e) {
                                                    LOG.error("an error invokes inside the listener callback.", e);
                                                }
                                            }
                                        }
                                    });
                                }
                                setIdcCache(idcs);
                                setIdcxmlWithIDCs(idcs);
                                refreshIdcData(idcs);


                                LOG.info("success to reload {}", Consts.sg_idcFile);
                                for (IDC idc : idcs) {
                                    LOG.info("region = {}, center = {}, idc = {}, ip = {}", idc.getRegion(), idc.getCenter(), idc.getIdc(), idc.getIp());
                                }
                                idcLastModifiedTime = file.lastModified();
                                idcXmlValid = true;
                            } else {
                                LOG.info("the {} is empty or not changed, idc size: {}", Consts
                                        .sg_idcFile, idcs.size());
                            }
                        } else {
                            LOG.error("fail to parse {}", Consts.sg_idcFile);
                        }
                    } catch (Exception e) {
                        LOG.error("fail to reload idc file: {}", Consts.sg_idcFile, e);
                    }
                }
            }
        }, 0, 5, TimeUnit.MINUTES);
    }


    IpUtil() {
    }

    static void refreshIdcData(List<IDC> idcs) {
        Map<Integer, IDC> idcTmp = new HashMap<Integer, IDC>();
        Set<Integer> maskTmp = new HashSet<Integer>();
        for (IDC idc : idcs) {
            idcTmp.put(idc.getIpMaskValue(), idc);
            maskTmp.add(idc.getIntMask());
        }
        idcMaplock.writeLock().lock();
        idcMap = idcTmp;
        maskSet = maskTmp;
        idcMaplock.writeLock().unlock();
    }

    static boolean idcDiff(final List<IDC> oldData, final List<IDC> newData) {
        if (null == oldData || null == newData) {
            LOG.warn("fail to compare idc, oldData or newData is null.");
            return false;
        }
        if (oldData.size() != newData.size()) {
            LOG.info("idcDiff returns true, oldData size: {}, newData size: {}", oldData.size(), newData.size());
            return true;
        } else {
            boolean isChanged = false;
            for(IDC oldIDC: oldData) {
                if (!newData.contains(oldIDC)) {
                    LOG.info("idcDiff returns true, IDC is not the same one, old IDC: {}, new IDCList: {}",
                            oldIDC, newData);
                    isChanged = true;
                    break;
                }
            }
            return isChanged;
        }
    }

    public static Set<IIdcChangeListener> getIdcChangeListeners() {
        return idcChangeListeners;
    }

    static void addIdcChangeListener(IIdcChangeListener listener) {
        if (!idcChangeListeners.contains(listener)) {
            synchronized (idcListenerLock) {
                if (!idcChangeListeners.contains(listener)) {
                    idcChangeListeners.add(listener);
                }
            }
        }
    }

    static void removeIdcChangeListener(IIdcChangeListener listener) {
        if (idcChangeListeners.contains(listener)) {
            synchronized (idcListenerLock) {
                if (idcChangeListeners.contains(listener)) {
                    idcChangeListeners.remove(listener);
                }
            }
        }
    }

    static void setIdcCache(List<IDC> idcs) {
        synchronized (lock) {
            idcCache = idcs;
            isIdcCacheInit = true;
        }
    }

    public static List<IDC> getIdcCache() {
        synchronized (lock) {
            return idcCache;
        }
    }

    private static void setIdcxml(List<Idc> idcXmlNew) {
        synchronized (idcXmlLock) {
            idcxml = idcXmlNew;
        }
    }

    private static void setIdcxmlWithIDCs(List<IDC> idcs) {
        List<Idc> idcXmlNew = new ArrayList<Idc>();
        for (IDC idc : idcs) {
            Idc idcXmlItem = new Idc();
            idcXmlItem.setRegion(idc.getRegion());
            idcXmlItem  .setCenter(idc.getCenter());
            idcXmlItem  .setIdc(idc.getIdc());
            idcXmlNew.add(idcXmlItem);
        }
        setIdcxml(idcXmlNew);
    }

    static List<Idc> getAllIdcs() {
        List<Idc> ret = null;
        synchronized (idcXmlLock) {
            ret = idcxml;
        }
        if (ret.isEmpty()) {
            if (!isIdcXmlValid() && !isIdcxmlInitFromRemote) {
                LOG.info("fail to get idc from local and never try remote, now try remote for the first time");
                synchronized (idcxmlInitFromRemoteLock) {
                    if (!isIdcxmlInitFromRemote) {
                        getIdcXmlFromRemote();
                    }
                }
            }
            // re-get the idcs
            synchronized (idcXmlLock) {
                ret = idcxml;
            }
        }
        return ret;
    }

    private static void getIdcXmlFromRemote() {
        String res = HttpUtil.get(Consts.getIdcXmlApi);
        if (StringUtils.isEmpty(res)) {
            return;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(res);

            int ret = json.path("ret").intValue();
            if (200 != ret) {
                JsonNode msgNode = json.path("msg");
                LOG.warn("failed to get idc xml from remote, errCode: {}, errMsg: {} unknown error.", ret , (null != msgNode ? msgNode.textValue() : ""));
                return;
            }
            List<Map<String, String>> dataTemp = mapper.readValue(json.path("data").toString(), new TypeReference<List<Map<String, String>>>() {
            });
            List<Idc> idcs = new ArrayList<Idc>();
            for (Map<String, String> item : dataTemp) {
                Idc idc = new Idc();
                idc.setRegion(item.get("region"));
                idc.setCenter(item.get("center"));
                idc.setIdc(item.get("idc"));
                idcs.add(idc);
            }

            if (!idcs.isEmpty()) {
                // success to init idcxml from remote
                setIdcxml(idcs);
                isIdcxmlInitFromRemote = true;
            }

        } catch (Exception e) {
            LOG.warn("faild to parse idc result from remote", e);
        }
    }

    static boolean initSGAgentIDCXml(String filePath, List<IDC> idcResult) {
        boolean ret = false;
        for(int tries = 0 ;tries < 3; tries++) {
            try {
                // make sure that the cache is empty.
                idcResult.clear();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setIgnoringElementContentWhitespace(true);
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document xml = builder.parse(new File(filePath));
                NodeList regionList = xml.getElementsByTagName("Region");

                for (int regionIter = 0; regionIter < regionList.getLength(); ++regionIter) {
                    Element region = (Element) regionList.item(regionIter);

                    NodeList regionNames = region.getElementsByTagName("RegionName");
                    String regionName = regionNames.getLength() > 0 ? regionNames.item(0).getFirstChild().getNodeValue() : UNKNOWN;

                    NodeList idcs = region.getElementsByTagName("IDC");
                    for (int idcIter = 0; idcIter < idcs.getLength(); ++idcIter) {
                        Element idc = (Element) idcs.item(idcIter);

                        NodeList idcNames = idc.getElementsByTagName("IDCName");
                        String idcName = idcNames.getLength() > 0 ? idcNames.item(0).getFirstChild().getNodeValue() : UNKNOWN;

                        NodeList centerNames = idc.getElementsByTagName("CenterName");
                        String centerName = centerNames.getLength() > 0 ? centerNames.item(0).getFirstChild().getNodeValue() : UNKNOWN;

                        NodeList items = idc.getElementsByTagName("Item");
                        for (int itemIter = 0; itemIter < items.getLength(); ++itemIter) {
                            Element item = (Element) items.item(itemIter);
                            NodeList ips = item.getElementsByTagName("IP");
                            NodeList masks = item.getElementsByTagName("MASK");
                            if (ips.getLength() <= 0 || masks.getLength() <= 0) {
                                //idc.xml is error
                                continue;
                            }

                            String idcIp = ips.item(0).getFirstChild().getNodeValue();
                            String idcMask = masks.item(0).getFirstChild().getNodeValue();
                            IDC idcItem = new IDC();
                            idcItem.setRegion(regionName);
                            idcItem.setIdc(idcName);
                            idcItem.setCenter(centerName);
                            idcItem.setIp(idcIp);
                            idcItem.setMask(idcMask);

                            Idc idcinfo = new Idc();
                            idcinfo.setIdc(idcName);
                            idcinfo.setRegion(regionName);
                            idcinfo.setCenter(centerName);
                            idcItem.setIdcinfo(idcinfo);

                            idcItem.init();
                            idcResult.add(idcItem);
                        }
                    }
                }
                ret = true;
                break;
            } catch (Exception e) {
                //ignore the error while offline
                LOG.warn("failed to read local idc file: {}, tries: {}", filePath, tries, e);
                ret = false;
            } finally {
                isInitSGAgentIDCXml = true;
            }
        }

        return ret;
    }

    static boolean isIdcXmlValid() {
        if (!isInitSGAgentIDCXml) {
            synchronized (lock) {
                if (!isInitSGAgentIDCXml) {
                    List<IDC> idcs = new ArrayList<IDC>();
                    idcXmlValid = initSGAgentIDCXml(Consts.sg_idcFile, idcs);
                    if (idcXmlValid) {
                        setIdcCache(idcs);
                        setIdcxmlWithIDCs(idcs);
                        refreshIdcData(idcs);
                    }
                }
            }
        }
        return idcXmlValid;
    }

    static int convertMaskToInt(String mask) {
        String[] vnum = mask.split("\\.");
        if (4 != vnum.length) {
            return -1;
        }

        int iMask = 0;
        for (int i = 0; i < vnum.length; ++i) {
            iMask += (Integer.parseInt(vnum[i]) << ((3 - i) * 8));
        }
        return iMask;
    }

    static int getIpv4Value(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return -1;
        }
        String[] vcIp = ip.split("\\.");
        if (4 != vcIp.length) {
            return -1;
        }

        int address = 0;
        int filteNum = 0xFF;
        for (int i = 0; i < 4; ++i) {

            int pos = i * 8;
            if (!NumberUtils.isDigits(vcIp[3 - i])) {
                return -1;
            }
            int vIp = -1;
            try {
                vIp = Integer.parseInt(vcIp[3 - i]);
            } catch (NumberFormatException e) {
                //invalid num.
                return -1;
            }

            if (vIp > 255 || vIp < 0) {
                return -1;
            }
            address |= ((vIp << pos) & (filteNum << pos));
        }
        return address;
    }

    private static Idc handleIdcInfoFromLocal(String ip) {
        Idc resIdc = new Idc();
        List<IDC> idcsTemp = null;
        synchronized (lock) {
            idcsTemp = idcCache;
        }

        for (IDC idc : idcsTemp) {
            if (idc.isSameIDC(ip)) {
                resIdc.setRegion(idc.getRegion());
                resIdc.setCenter(idc.getCenter());
                resIdc.setIdc(idc.getIdc());
                return resIdc;
            }
        }
        resIdc.setIdc(UNKNOWN);resIdc.setRegion(UNKNOWN);resIdc.setCenter(UNKNOWN);
        return resIdc;
    }

    private static Idc handleIdcInfoFromLocalNew(String ip) {
        try {
            idcMaplock.readLock().lock();
            for (Integer mask : maskSet) {
                IDC curIDC = idcMap.get(mask & getIpv4Value(ip));
                if (curIDC != null && curIDC.getIntMask() == mask) {
                    return curIDC.getIdcinfo();
                }
            }
            return getDefaultUnknownIdc();
        } catch (Exception e) {
            LOG.error("failed to handle idcinfo, ip: {}", ip, e);
            return getDefaultUnknownIdc();
        } finally {
            idcMaplock.readLock().unlock();
        }
    }

    private static Idc getDefaultUnknownIdc() {
        Idc resIdc = new Idc();
        resIdc.setIdc(UNKNOWN);
        resIdc.setRegion(UNKNOWN);resIdc.setCenter(UNKNOWN);
        return resIdc;
    }

    public static Map<String, Idc> getIdcInfoFromLocal(List<String> ips) {
        Map<String, Idc> resIdcInfo = new HashMap<String, Idc>();
        if (!isIdcXmlValid() || null == ips) {
            return resIdcInfo;
        }
        for (String ip : ips) {
            if (null != resIdcInfo.get(ip)) {
                //if this ip was already parsed, ignore.
                continue;
            }
            Idc idc = IpUtil.handleIdcInfoFromLocal(ip);
            if (null != idc) {
                resIdcInfo.put(ip, idc);
            }
        }
        return resIdcInfo;
    }

    public static Map<String, Idc> getIdcInfoFromLocal(List<String> ips, List<String> localCanotParseIps) {
        Map<String, Idc> resIdcInfo = new HashMap<String, Idc>();
        for (String ip : ips) {
            if (null != resIdcInfo.get(ip)) {
                //if this ip was already parsed, ignore.
                continue;
            }
            Idc idc = IpUtil.handleIdcInfoFromLocalNew(ip);
            if (null != idc) {
                resIdcInfo.put(ip, idc);
            } else {
                localCanotParseIps.add(ip);
            }
        }
        return resIdcInfo;
    }


    static Map<String, Idc> getIdcInfoFromRemote(List<String> ips) {
        String param = convertList2PostList(ips);
        String res = HttpUtil.post(Consts.getIdcInfoApi, param);
        if (StringUtils.isEmpty(res)) {
            return null;
        }
        Map<String, Idc> result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(res);

            int ret = json.path("ret").intValue();
            if (200 != ret) {
                LOG.warn("failed to get idc info from remote, errCode: " + ret + ", errMsg: " + json
                        .path("msg").textValue());
                return null;
            }
            Map<String, Map<String, Object>> dataTemp = mapper.readValue(json.path("data").toString(), new TypeReference<Map<String, Map<String, Object>>>() {
            });
            result = new HashMap<String, Idc>();
            for (Map.Entry<String, Map<String, Object>> item : dataTemp.entrySet()) {
                Idc idc = new Idc();
                idc.setRegion((String) item.getValue().get("region"));
                idc.setCenter((String) item.getValue().get("center"));
                idc.setIdc((String) item.getValue().get("idc"));
                result.put(item.getKey(), idc);
            }
        } catch (Exception e) {
            LOG.warn("faild to parse idc result from remote", e);
        }
        return result;
    }

    private static String convertList2PostList(List<String> param) {
        return (null == param || param.isEmpty()) ? null : "ips=" + StringUtils.join(param, "&ips=");
    }


    //because of the use of regular expression matching, be careful of its performance while you call it with high concurrency.
    public static boolean checkIP(String ip) {
        return StringUtils.isNotEmpty(ip) && ip.matches(IP_REGEX);
    }

    public static class IDC {
        private String region;
        private String idc;
        private String center;
        private String ip;
        private String mask;

        private int intMask;
        private int ipMaskValue;
        private boolean isInit = false;
        private Object lock = new Object();

        private Idc idcinfo;

        private void init() {
            if (!isInit) {
                synchronized (lock) {
                    if (!isInit) {
                        intMask = convertMaskToInt(mask);
                        ipMaskValue = intMask & getIpv4Value(ip);
                        isInit = true;
                    }
                }
            }
        }

        public boolean isSameIDC(String ip) {
            init();
            return ipMaskValue == (intMask & getIpv4Value(ip));
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getIdc() {
            return idc;
        }

        public void setIdc(String idc) {
            this.idc = idc;
        }

        public String getCenter() {
            return center;
        }

        public void setCenter(String center) {
            this.center = center;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setMask(String mask) {
            this.mask = mask;
        }

        public String getMask() {
            return mask;
        }

        public int getIntMask() {
            return intMask;
        }

        public void setIntMask(int intMask) {
            this.intMask = intMask;
        }

        public int getIpMaskValue() {
            return ipMaskValue;
        }

        public void setIpMaskValue(int ipMaskValue) {
            this.ipMaskValue = ipMaskValue;
        }

        public Idc getIdcinfo() {
            return idcinfo;
        }

        public void setIdcinfo(Idc idcinfo) {
            this.idcinfo = idcinfo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            IDC idc1 = (IDC) o;


            if (!StringUtils.equals(region, idc1.region)) {
                return false;
            }
            if (!StringUtils.equals(idc, idc1.idc)) {
                return false;
            }
            if (!StringUtils.equals(center, idc1.center)) {
                return false;
            }
            if (!StringUtils.equals(ip, idc1.ip)) {
                return false;
            }
            if (!StringUtils.equals(mask, idc1.mask)) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public int hashCode() {
            int result = 0;
            if (null != region) {
                result = region.hashCode();
            }
            if (null != idc) {
                result = 31 * result + idc.hashCode();
            }
            if (null != center) {
                result = 31 * result + center.hashCode();
            }
            if (null != ip) {
                result = 31 * result + ip.hashCode();
            }
            if (null != mask) {
                result = 31 * result + mask.hashCode();
            }
            return result;
        }

        @Override
        public String toString() {
            return "IDC{" +
                    "region='" + region + '\'' +
                    ", idc='" + idc + '\'' +
                    ", center='" + center + '\'' +
                    ", ip='" + ip + '\'' +
                    ", mask='" + mask + '\'' +
                    ", intMask=" + intMask +
                    ", ipMaskValue=" + ipMaskValue +
                    ", isInit=" + isInit +
                    '}';
        }
    }
}

