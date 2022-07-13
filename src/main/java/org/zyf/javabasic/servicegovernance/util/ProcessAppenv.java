package org.zyf.javabasic.servicegovernance.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyf.javabasic.servicegovernance.constants.Consts;
import org.zyf.javabasic.servicegovernance.enums.HostEnv;
import org.zyf.javabasic.servicegovernance.exception.MnsException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/21  19:51
 */
public class ProcessAppenv {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessAppenv.class);

    static final String OLD_ENV_KEY = "deployenv";
    static final String NEW_ENV_KEY = "env";

    static final String SWIMLANE_KEY = "swimlane";
    static final String CELL_KEY = "cell";

    private static final Map<String, HostEnv> valueMapHostEnv = initValueMapHostEnv();

    private static boolean appenvIsExist = false;

    // loadAppenvFromFile will not return null
    private static final Properties appenv = loadAppenvFromFile();

    private static boolean appenvIsExistV2 = false;

    private static Properties appenvV2 = null;

    private static final boolean validAppenv = checkEnv(NEW_ENV_KEY) || checkEnv(OLD_ENV_KEY);

    private static final HostEnv hostEnv = handleHostEnv();

    private static final Object appenvV2Lock = new Object();

    private static Map<String, HostEnv> initValueMapHostEnv() {
        Map<String, HostEnv> ret = new HashMap<String, HostEnv>();
        ret.put("prod", HostEnv.PROD);
        ret.put("product", HostEnv.PROD);
        ret.put("staging", HostEnv.STAGING);
        ret.put("prelease", HostEnv.PPE);
        ret.put("ppe", HostEnv.PPE);
        ret.put("test", HostEnv.TEST);
        ret.put("qa", HostEnv.TEST);
        ret.put("dev", HostEnv.DEV);
        ret.put("alpha", HostEnv.DEV);
        return ret;
    }


    private static Properties loadAppenvFromFile() {
        Properties props = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(Consts.PIGEON_ENV_FILE);
            props.load(in);
            appenvIsExist = true;
        } catch (FileNotFoundException e) {
            appenvIsExist = false;
            LOG.debug(Consts.PIGEON_ENV_FILE + " does not exist");
        } catch (IOException e) {
            LOG.debug("Failed to load config from" + Consts.PIGEON_ENV_FILE, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    LOG.debug("Failed to close " + Consts.PIGEON_ENV_FILE, e);
                }
            }
        }
        return props;
    }


    public static String loadAppenvFromFileWithEx(String key) throws Exception {
        if (appenvV2 == null) {
            synchronized (appenvV2Lock) {
                if (appenvV2 == null) {
                    Properties props = new Properties();
                    InputStream in = null;
                    try {
                        in = new FileInputStream(Consts.PIGEON_ENV_FILE);
                        props.load(in);
                        appenvIsExistV2 = true;
                    } catch (FileNotFoundException e) {
                        appenvIsExistV2 = false;
                        LOG.error("{} does not exist", Consts.PIGEON_ENV_FILE);
                        throw new MnsException(Consts.PIGEON_ENV_FILE + " does not exist");
                    } catch (Exception e) {
                        LOG.error("Failed to load config from {}", Consts.PIGEON_ENV_FILE, e);
                        throw new MnsException("Unknown Reason: " + e.getMessage());
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (Exception e) {
                                LOG.error("Failed to close {}", Consts.PIGEON_ENV_FILE, e);
                                throw new MnsException("Unknown Reason: " + e.getMessage());
                            }
                        }
                    }
                    appenvV2 = props;
                }
            }
        }
        String value = CommonUtil.trim(appenvV2.getProperty(key));
        if ("".equals(value)) {
            throw new MnsException("cell cannot be empty");
        }
        return value;
    }


    private static HostEnv handleHostEnv() {
        if (isAppenvIsExist()) {
            // appenv exist
            if (checkEnv(NEW_ENV_KEY)) {
                return strToHostEnv(CommonUtil.trim(appenv.getProperty(NEW_ENV_KEY)));
            } else if (checkEnv(OLD_ENV_KEY)) {
                return strToHostEnv(CommonUtil.trim(appenv.getProperty(OLD_ENV_KEY)));
            }
        } else {
            // appenv does not exist
            if (!ProcessInfoUtil.isMac()) {
                // If it isn't a mac machine, there will generally be appenv. warn log
                LOG.warn("{} does not exist.", Consts.PIGEON_ENV_FILE);
            }
        }
        return HostEnv.DEV;
    }

    private static boolean checkEnv(String key) {
        boolean ret = false;
        if (isAppenvIsExist()) {
            String value = CommonUtil.trim(appenv.getProperty(key));
            ret = !CommonUtil.isBlankString(value) && isEnvValid(value);
        } else {
            ret = false;
        }

        return ret;
    }

    static Properties getAppenv() {
        return appenv;
    }

    static HostEnv getHostEnv() {
        return hostEnv;
    }

    static boolean isValidAppenv() {
        return validAppenv;
    }

    private static boolean isEnvValid(String envStr) {
        return valueMapHostEnv.containsKey(envStr);
    }

    private static HostEnv strToHostEnv(String envStr) {
        return isEnvValid(envStr) ? valueMapHostEnv.get(envStr) : HostEnv.DEV;
    }

    static boolean isAppenvIsExist() {
        return appenvIsExist;
    }
}
