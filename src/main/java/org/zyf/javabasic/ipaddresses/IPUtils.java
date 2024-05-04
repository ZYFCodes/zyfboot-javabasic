package org.zyf.javabasic.ipaddresses;

/**
 * @program: zyfboot-javabasic
 * @description: 使用位运算和位掩码来进行IPv4地址和长整型数值的转换
 * @author: zhangyanfeng
 * @create: 2024-05-03 00:08
 **/
public class IPUtils {
    public static long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        return (Long.parseLong(parts[0]) << 24) +
                (Long.parseLong(parts[1]) << 16) +
                (Long.parseLong(parts[2]) << 8) +
                Long.parseLong(parts[3]);
    }

    public static String longToIp(long longIp) {
        StringBuilder sb = new StringBuilder();
        sb.append((longIp >>> 24) & 0xFF).append(".");
        sb.append((longIp >>> 16) & 0xFF).append(".");
        sb.append((longIp >>> 8) & 0xFF).append(".");
        sb.append(longIp & 0xFF);
        return sb.toString();
    }

    public static void main(String[] args) {
        String ip1 = "10.122.28.76";
        long ip1ToLong = ipToLong(ip1);
        System.out.println("IPv4地址 \"" + ip1 + "\" 转换为长整型数值的结果是：" + ip1ToLong);

        String ip2 = "10.168.0.45";
        long ip2ToLong = ipToLong(ip2);
        System.out.println("IPv4地址 \"" + ip2 + "\" 转换为长整型数值的结果是：" + ip2ToLong);

        long longIp = 197958752L;
        String longIpToIp = longToIp(longIp);
        System.out.println("长整型数值 \"" + longIp + "\" 转换为IPv4地址的结果是：" + longIpToIp);
    }
}
