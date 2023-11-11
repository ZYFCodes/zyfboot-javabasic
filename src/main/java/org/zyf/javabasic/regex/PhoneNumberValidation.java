package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 手机号的格式可以表示为 1[3456789]\d{9}
 * @author: zhangyanfeng
 * @create: 2023-11-05 13:59
 **/
public class PhoneNumberValidation {
    public static void main(String[] args) {
        String regex = "1[3456789]\\d{9}";

        String[] phoneNumbers = {
                "13912345678",
                "18887654321",
                "12345678901",
                "135",
                "1891234",
                "158888888888"
        };

        for (String phoneNumber : phoneNumbers) {
            boolean isMatch = validatePhoneNumber(phoneNumber, regex);
            System.out.println(phoneNumber + " is a valid phone number: " + isMatch);
        }
    }

    private static boolean validatePhoneNumber(String phoneNumber, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
