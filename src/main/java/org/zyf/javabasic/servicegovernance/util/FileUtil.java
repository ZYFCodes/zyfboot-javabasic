package org.zyf.javabasic.servicegovernance.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/21  20:59
 */
public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {

    }

    public static String readStringFromFile(String path) {
        File file = null;
        InputStream in = null;
        BufferedReader br = null;
        String line = "";
        StringBuilder sb = new StringBuilder();
        try {
            file = new File(path);
            in = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        } catch (Exception e) {
            //ignore the error.
        } finally {
            if (null != br) {
                try {
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    in.close();
                } catch (IOException e) {
                    LOG.debug("readStringFromFile, finally:", e);
                }
            }
        }
        return StringUtils.trim(sb.toString());
    }

    public static void writeString2File(String path, String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write(content, 0, content.length());
        } catch (IOException e) {
            LOG.debug("writeString2File", e);
        } finally {
            if (null != fw) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    LOG.debug(e.getMessage(), e);
                }
            }
        }
    }


}

