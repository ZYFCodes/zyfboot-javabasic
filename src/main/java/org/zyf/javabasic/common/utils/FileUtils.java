package org.zyf.javabasic.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/8/26  14:12
 */
public class FileUtils {

    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr.trim()).append(",");
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static String readFileContent2(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr.trim()).append("@@@ZYF@@@");
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static void writeToFile(String data, String fileNamePath) {
        byte[] sourceByte = data.getBytes();
        FileOutputStream outStream = null;
        if (null != sourceByte) {
            try {
                //文件路径（路径+文件名）
                File file = new File(fileNamePath);
                if (!file.exists()) {
                    //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                //文件输出流将数据写入文件
                outStream = new FileOutputStream(file);
                outStream.write(sourceByte);
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                // do something
            } finally {
            }
        }
    }

    public static long getFileSize(String fileNamePath) throws IOException {
        Path imageFilePath = Paths.get(fileNamePath);
        FileChannel imageFileChannel = FileChannel.open(imageFilePath);

        return imageFileChannel.size();
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtils.deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtils.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    private static volatile Set<String> rpcInfos = new HashSet<>();

    public static void main(String[] args) throws IOException {
//        String adress = "/Users/yanfengzhang/Downloads/zyfurl.txt";
//        System.out.println(readFileContent(adress));
//        System.out.println(getFileSize(adress));
//
//        for (int i = 0; i < 10; i++) {
//            writeToFile(String.valueOf(i), "/Users/yanfengzhang/Downloads/zyf112222222222.txt");
//        }

//
//        String beafore = readFileContent("/Users/yanfengzhang/Downloads/zyfurlTotal.txt");
//        String beafore1 = beafore.substring(0, beafore.length() - 1).replaceAll("\\{", "");
//        String beafore2 = beafore1.replaceAll("\\}", "");
//        System.out.println(beafore2);
//
//        Map<String, String> result = Splitter.on(",").withKeyValueSeparator("=").split(beafore2);


        for (int i = 0; i < 15; i++) {
            rpcInfos.add("rpcInvocation.getServiceInterface().getName()" + i + ":" + "rpcInvocation.getMethod().getName()" + i);
            writeToFile(rpcInfos.toString(), "/Users/yanfengzhang/Downloads/rpcInfo.txt");
        }

        for (int i = 12; i < 17; i++) {
            rpcInfos.add("rpcInvocation.getServiceInterface().getName()" + i + ":" + "rpcInvocation.getMethod().getName()" + i);
            writeToFile(rpcInfos.toString(), "/Users/yanfengzhang/Downloads/rpcInfo.txt");
        }
    }
}
