package org.zyf.javabasic.servicegovernance.util;

import com.sankuai.inf.octo.mns.listener.IIdcChangeListener;
import com.sankuai.inf.octo.mns.util.MnsCatUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyf.javabasic.servicegovernance.constants.Consts;
import org.zyf.javabasic.servicegovernance.enums.HostEnv;
import org.zyf.javabasic.servicegovernance.exception.MnsException;
import org.zyf.javabasic.servicegovernance.model.Idc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 服务治理中心对应当前机器基本信息工具类
 * @date 2022/6/21  23:31
 */
public class ProcessInfoUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessInfoUtil.class);
    private static final List<String> ignoreNiNames = initIgnoreNiNames();
    private static final String localIpV4 = getIpV4();
    private static final HostEnv appEnv = ProcessAppenv.getHostEnv();
    /*octo inner env*/
    private static final String octoEnvStr = parseOctoEnv();
    private static String localHostName = getSystemInfoByCommand(new String[]{"hostname"});
    private static final boolean isMac = checkMacOsName() || CommonUtil.containsIgnoreCase(localHostName, "macbook") || CommonUtil.containsIgnoreCase(localHostName, "mac.local");
    private static boolean isOnline = !isMac && isOnlineHost(localIpV4);

    // new unified env
    private static final HostEnv hostEnv = parseHostEnv();

    private static final String swimlane = readAppenv(ProcessAppenv.SWIMLANE_KEY);

    private static final String cell = readAppenv(ProcessAppenv.CELL_KEY);

    private static final String grayRealeasePrefix = "gray-release-";

    private static String UNKNOWN = "unknown";

    static {
        MnsCatUtil.logJarVersionCatMonitor();
    }

    private ProcessInfoUtil() {

    }

    /**
     * ignore:
     * vnic(virtual network interface controller)
     * docker
     * vmnet （vmware）
     * vmbox, vbox (virtual box)
     */
    private static List<String> initIgnoreNiNames() {
        List<String> ret = new ArrayList<String>();
        ret.add("vnic");
        ret.add("docker");
        ret.add("vmnet");
        ret.add("vmbox");
        ret.add("vbox");
        return ret;
    }

    private static boolean isIgnoreNI(String niName) {
        for (String item : ignoreNiNames) {
            if (CommonUtil.containsIgnoreCase(niName, item)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkMacOsName() {
        String osName = System.getProperty("os.name");
        LOG.info("os.name = {}", osName);
        return CommonUtil.containsIgnoreCase(osName, "mac os");
    }

    private static HostEnv parseHostEnv() {
        HostEnv ret = HostEnv.DEV;
        if (ProcessAppenv.isValidAppenv()) {
            ret = appEnv;
        } else {
            if (isOnline) {
                if ("prod".equals(octoEnvStr)) {
                    ret = HostEnv.PROD;
                } else if ("stage".equals(octoEnvStr)) {
                    ret = HostEnv.STAGING;
                } else {
                    ret = HostEnv.PROD;
                }
            } else {
                if ("stage".equals(octoEnvStr)) {
                    ret = HostEnv.PPE;
                } else if ("test".equals(octoEnvStr)) {
                    ret = HostEnv.TEST;
                } else {
                    ret = HostEnv.DEV;
                }
            }
        }
        return ret;
    }

    public static HostEnv getHostEnv() {
        return hostEnv;
    }

    public static String getSwimlane() {
        return swimlane;
    }

    public static String getCell() {
        return cell;
    }


    /**
     * return  cell property from appenv file
     *
     * @return string
     * @throws Exception specifically:
     *                   Throws FileNotFoundException when appenv file is not exist;
     *                   Throws MnsException when cell is an empty string;
     *                   Other cases throw Exception with message;
     */
    public static String getCellWithEx() throws Exception {
        return ProcessAppenv.loadAppenvFromFileWithEx(ProcessAppenv.CELL_KEY);
    }

    public static boolean isGrayRelease() {
        return !StringUtils.isEmpty(cell) && cell.startsWith(grayRealeasePrefix);
    }

    private static String readAppenv(String key) {
        String value = ProcessAppenv.getAppenv().getProperty(key);
        return CommonUtil.trim(value);
    }

    private static String getIpV4() {
        String ip = "";
        Enumeration<NetworkInterface> networkInterface;
        try {
            networkInterface = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            LOG.error("fail to get network interface information.", e);
            return ip;
        }
        Set<String> ips = new HashSet<String>();
        while (networkInterface.hasMoreElements()) {
            NetworkInterface ni = networkInterface.nextElement();
            // 忽略虚拟网卡的IP,docker 容器的IP
            String niName = (null != ni) ? ni.getName() : "";
            if (isIgnoreNI(niName)) {
                continue;
            }

            Enumeration<InetAddress> inetAddress = null;
            try {
                if (null != ni) {
                    inetAddress = ni.getInetAddresses();
                }
            } catch (Exception e) {
                LOG.debug("fail to get ip information.", e);
            }
            while (null != inetAddress && inetAddress.hasMoreElements()) {
                InetAddress ia = inetAddress.nextElement();
                if (ia instanceof Inet6Address) {
                    continue; // ignore ipv6
                }
                String thisIp = ia.getHostAddress();
                // 排除 回送地址
                if (!ia.isLoopbackAddress() && !thisIp.contains(":") && !"127.0.0.1".equals(thisIp)) {
                    ips.add(thisIp);
                    if (CommonUtil.isBlankString(ip)) {
                        ip = thisIp;
                    }
                }
            }
        }

        // 为新办公云主机绑定了两个IP, 只用其 10 段IP
        if (ips.size() >= 2) {
            for (String str : ips) {
                if (str.startsWith("10.")) {
                    ip = str;
                    break;
                }
            }
        }

        if (CommonUtil.isBlankString(ip)) {
            LOG.error("cannot get local ip.");
            ip = "";
        }

        return ip;
    }

    public static String getLocalIpV4() {
        return localIpV4;
    }

    public static boolean isLocalHostOnline() {
        return isOnline;
    }

    private static boolean isOfflineHostNameSuffix(String hostName) {
        return CommonUtil.containsIgnoreCase(hostName, ".office.mos") || CommonUtil.containsIgnoreCase(hostName, ".corp.sankuai.com");
    }

    @Deprecated
    public static boolean isOnlineHost(String ip) {
        String host = getHostInfoByIp(ip);
        //default is offline
        boolean online = false;
        if (isOfflineHostNameSuffix(host)) {
            online = false;
        } else if (CommonUtil.containsIgnoreCase(host, ".sankuai.com")) {
            online = true;
        } else if (isPigeonEnvOnline()) {
            online = true;
        }
        return online;
    }

    //note recommended to use, only for mns-invoker itself.
    public static boolean isMac() {
        return isMac;
    }

    public static String getHostInfoByIp(String ip) {
        return getSystemInfoByCommand(new String[]{"host", ip});
    }


    public static String getHostNameInfoByIp() {
        return localHostName;
    }

    private static String getSystemInfoByCommand(String[] command) {
        String result = "";
        BufferedReader read = null;
        InputStream in = null;
        Process pro = null;
        try {
            pro = Runtime.getRuntime().exec(command);
            pro.waitFor();
            in = pro.getInputStream();
            read = new BufferedReader(new InputStreamReader(in));
            result = read.readLine();
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
        } finally {
            try {
                if (null != pro) {
                    pro.destroy();
                }
                if (null != read) {
                    read.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                LOG.debug(e.getMessage(), e);
            }
        }
        return result;
    }

    public static boolean isPigeonEnvOnline() {
        return HostEnv.PROD == appEnv || HostEnv.STAGING == appEnv;
    }


    // xml格式有问题, 解析失败; 所以用字符串方式处理.
    public static String getOctoEnv() {
        return octoEnvStr;
    }

    private static String parseOctoEnv() {
        String env = "prod";
        String envFileContennt = FileUtil.readStringFromFile(Consts.sg_envFile);
        if (envFileContennt.contains("/mns/sankuai/prod")) {
            env = "prod";
        } else if (envFileContennt.contains("/mns/sankuai/stage")) {
            env = "stage";
        } else if (envFileContennt.contains("/mns/sankuai/test")) {
            env = "test";
        } else if (ProcessAppenv.isValidAppenv()) {
            switch (appEnv) {
                case PROD:
                    env = "prod";
                    break;
                case STAGING:
                    env = "stage";
                    break;
                case PPE:
                    env = "stage";
                    break;
                case DEV:
                    env = "prod";
                    break;
                case TEST:
                    env = "test";
                    break;
            }
        }
        return env;
    }

    public static String getEnvStr() {
        String env = "prod";
        if (ProcessAppenv.isValidAppenv()) {
            switch (appEnv) {
                case PROD:
                    env = "prod";
                    break;
                case STAGING:
                    env = "stage";
                    break;
                case PPE:
                    env = "stage";
                    break;
                case DEV:
                    env = "prod";
                    break;
                case TEST:
                    env = "test";
                    break;
                default:
                    LOG.error("invalid env config option.");
            }
        }
        return env;
    }

    public static Properties loadFromXmlFile() throws IOException {
        Properties props = new Properties();
        InputStream in = null;

        try {
            in = new FileInputStream(Consts.sg_envFile);
            props.loadFromXML(in);
        } catch (FileNotFoundException e) {
            LOG.debug(Consts.sg_envFile + " does not exist");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    LOG.debug("fail to close " + Consts.sg_envFile, e);
                }
            }
        }
        return props;
    }

    private static Idc getDefaultUnknownIdc() {
        Idc defaultItem = new Idc();
        defaultItem.setIdc(UNKNOWN);
        defaultItem.setCenter(UNKNOWN);
        defaultItem.setRegion(UNKNOWN);
        return defaultItem;
    }

    public static Idc getIdcInfoFromIdc(String idc) {
        List<Idc> idcs = getIdcs();
        LOG.info("get idcInfo from idc, idc size:{}", idcs.size());
        for (Idc idcItem : idcs) {
            if (StringUtils.equals(idc, idcItem.getIdc())) {
                return idcItem;
            }
        }
        return getDefaultUnknownIdc();
    }

    public static List<Idc> getIdcInfoFromCenter(String center) {
        List<Idc> idcList = new ArrayList<Idc>();
        List<Idc> idcs = getIdcs();
        LOG.info("get idcInfo from center, idc size:{}", idcs.size());
        for (Idc idcItem : idcs) {
            if (StringUtils.equals(center, idcItem.getCenter())) {
                idcList.add(idcItem);
            }
        }
        if (idcList.isEmpty()) {
            Idc defaultItem = getDefaultUnknownIdc();
            idcList.add(defaultItem);
        }

        return idcList;
    }

    public static List<Idc> getIdcs() {
        return IpUtil.getAllIdcs();
    }

    public static Map<String, Idc> getIdcInfo(List<String> ips) throws MnsException {
        Map<String, Idc> resIdcInfo = new HashMap<String, Idc>();
        if (null == ips || ips.isEmpty()) {
            return resIdcInfo;
        }
        List<String> localCanotParseIps = new ArrayList<String>();
        if (IpUtil.isIdcXmlValid()) {
            resIdcInfo.putAll(IpUtil.getIdcInfoFromLocal(ips, localCanotParseIps));
        } else {
            localCanotParseIps.addAll(ips);
        }
        if (!localCanotParseIps.isEmpty()) {
            Map<String, Idc> remoteRet = IpUtil.getIdcInfoFromRemote(localCanotParseIps);
            if (null != remoteRet) {
                resIdcInfo.putAll(remoteRet);
            } else {
                throw new MnsException("fail to get idc info.");
            }
        }
        return resIdcInfo;
    }

    public static void addIdcListener(IIdcChangeListener listener) {
        if (null == listener) {
            LOG.warn("idcChangeListener can not be null");
            return;
        }
        IpUtil.addIdcChangeListener(listener);
    }

    public static void removeIdcListener(IIdcChangeListener listener) {
        if (null == listener) {
            LOG.warn("idcChangeListener can not be null");
            return;
        }
        IpUtil.removeIdcChangeListener(listener);
    }


    /**
     * @return
     * @deprecated
     */
    @Deprecated
    public static String getHostName() {
        return localHostName;
    }

    /**
     * @param ip
     * @return
     * @deprecated
     */
    @Deprecated
    public static String getHostName(String ip) {
        if (CommonUtil.isBlankString(ip) || !ip.startsWith("10.")) {
            return ip;
        }

        String processRet = null;
        Process process = null;
        InputStream processIn = null;
        BufferedReader reader = null;
        try {
            process = Runtime.getRuntime().exec("host " + ip);
            processIn = process.getInputStream();
            reader = new BufferedReader(new InputStreamReader(processIn));

            processRet = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                processRet += line;
            }
        } catch (IOException e) {
            LOG.debug(e.getMessage(), e);
        } finally {
            if (process != null) {
                process.destroy();
            }
            try {
                if (processIn != null) {
                    processIn.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LOG.debug(e.getMessage(), e);
            }
        }
        if (processRet != null) {
            processRet = processRet.trim();
            int pos = processRet.lastIndexOf(" ");
            //廊坊机房：lf.sankuai.com  大兴机房：dx.sankuai.com  其他 xxx.sankuai.com
            //211.37.32.10.in-addr.arpa domain name pointer dx-mobile-mtthrift-monitor01.dx.sankuai.com.
            if (pos > 0 && processRet.endsWith(".sankuai.com.")) {
                String[] str = processRet.split("\\s");
                String endStr = str[str.length - 1];
                int end = endStr.indexOf(".");
                return endStr.substring(0, end);
            }
        }
        return ip;
    }

    /**
     * @return
     */
    public static int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            LOG.debug("getPid:", e);
            return -1;
        }
    }

    /**
     * @return
     * @deprecated
     */
    @Deprecated
    public static String getIpPid() {
        return getLocalIpV4() + ":pid" + getPid();
    }

    /**
     * @param servers
     * @return
     * @deprecated
     */
    @Deprecated
    public static List<String> serviceGroupingByDCLocation(
            List<String> servers) {
        return serviceGroupingByDCLocation(servers, true);
    }

    /**
     * @param servers
     * @param enableRemoteServer
     * @return
     * @deprecated
     */
    @Deprecated
    public static List<String> serviceGroupingByDCLocation(List<String> servers,
                                                           boolean enableRemoteServer) {
        List<String> localDCServers = new ArrayList<String>();
        List<String> remoteDCServers = new ArrayList<String>();

        String prefix = getLocalIpV4Prefix();

        // TODO: server status ?
        for (String server : servers) {
            String[] address = server.split(":");
            if (address.length != 2) {
                continue;
            }

            if (server.startsWith(prefix)) {
                localDCServers.add(server);
            } else {
                remoteDCServers.add(server);
            }
        }

        if (!localDCServers.isEmpty()) {
            return localDCServers;
        } else if (!enableRemoteServer) {
            return new ArrayList<String>();
        } else if (!remoteDCServers.isEmpty()) {
            return remoteDCServers;
        } else {
            return servers;
        }
    }

    /**
     * @return
     * @deprecated
     */
    @Deprecated
    private static String getLocalIpV4Prefix() {
        String localAddress = ProcessInfoUtil.getLocalIpV4();
        String[] ipSegs = localAddress.split("\\.");
        return ipSegs[0] + "." + ipSegs[1] + ".";

    }

    /**
     * @param remoteIp
     * @return
     * @deprecated
     */
    @Deprecated
    public static boolean isSameDC(String remoteIp) {
        String prefix = getLocalIpV4Prefix();
        return remoteIp.startsWith(prefix);

    }


    @Deprecated
    /**
     * @deprecated
     */
    public static String getLocalIpV4(String ip) {
        return localIpV4;
    }


}