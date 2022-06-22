package org.zyf.javabasic.servicegovernance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyf.javabasic.servicegovernance.thread.ScheduleTaskFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/21  23:57
 */
public class CommonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

    // please do not change it to multi thread.
    public static ScheduledExecutorService mnsCommonSchedule = Executors.newSingleThreadScheduledExecutor(new ScheduleTaskFactory("MnsInvoker-Schedule"));

    private CommonUtil() {

    }

    public static BufferedInputStream wrapToStream(byte[] data) {
        return new BufferedInputStream(new ByteArrayInputStream(data));
    }

    public static boolean isBlankString(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean isEmtpy(List<?> list) {
        return null == list || list.isEmpty();
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        return null != str && null != searchStr && str.toLowerCase().contains(searchStr);
    }

    static String trim(String str) {
        return null == str ? null : str.trim();
    }

    public static String toJsonString(Object object) {
        try {
            return (new ObjectMapper()).writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.warn("Failed to convert to json, return null. ", e);
        }

        return null;
    }
}

