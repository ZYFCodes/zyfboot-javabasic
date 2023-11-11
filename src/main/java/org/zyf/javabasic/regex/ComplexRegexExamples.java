package org.zyf.javabasic.regex;

import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 一些复杂正则匹配的举例
 * @author: zhangyanfeng
 * @create: 2023-11-05 16:57
 **/
public class ComplexRegexExamples {
    public static void main(String[] args) {
        // 1. 匹配有效的 IPv4 地址
        String ipv4Regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        String ipv4Address = "192.168.1.1";
        boolean isIPv4Valid = ipv4Address.matches(ipv4Regex);
        System.out.println("1. IPv4 Address is Valid: " + isIPv4Valid);

        // 2. 匹配有效的时间（HH:MM:SS）
        String timeRegex = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
        String time = "15:30:45";
        boolean isTimeValid = time.matches(timeRegex);
        System.out.println("2. Time is Valid: " + isTimeValid);

        // 3. 匹配有效的 HTML标签
        String htmlTagRegex = "<([a-zA-Z][a-zA-Z0-9]*)\\b[^>]*>.*?</\\1>";
        String htmlText = "<p>This is a <b>bold</b> statement.</p>";
        boolean isHtmlValid = htmlText.matches(htmlTagRegex);
        System.out.println("3. HTML is Valid: " + isHtmlValid);

        // 4. 匹配有效的域名
        String domainRegex = "^(https?|ftp)://[a-zA-Z0-9.-]+(:\\d+)?(/\\S*)?$";
        String domain = "https://www.example.com";
        boolean isDomainValid = domain.matches(domainRegex);
        System.out.println("4. Domain is Valid: " + isDomainValid);

        // 5. 匹配有效的文件路径（Unix和Windows）
        String filePathRegex = "^(?:[A-Za-z]:)?[\\/\\\\](?:[^<>:\"/\\\\|?*]*)+$";
        String filePath = "/usr/local/bin/script.sh";
        boolean isFilePathValid = filePath.matches(filePathRegex);
        System.out.println("5. File Path is Valid: " + isFilePathValid);

        // 6. 匹配有效的MAC地址
        String macAddressRegex = "^[0-9A-Fa-f]{2}[:-]([0-9A-Fa-f]{2}[:-]){4}[0-9A-Fa-f]{2}$";
        String macAddress = "00:1A:2B:3C:4D:5E";
        boolean isMacAddressValid = macAddress.matches(macAddressRegex);
        System.out.println("6. MAC Address is Valid: " + isMacAddressValid);

        // 7. 匹配有效的美国社会安全号码 (SSN)
        String ssnRegex = "^(?!000|666|9\\d\\d)\\d{3}-(?!00)\\d{2}-(?!0000)\\d{4}$";
        String ssn = "123-45-6789";
        boolean isSsnValid = ssn.matches(ssnRegex);
        System.out.println("7. SSN is Valid: " + isSsnValid);

        // 8. 匹配有效的十六进制颜色码
        String hexColorRegex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        String hexColor = "#FFA500";
        boolean isHexColorValid = hexColor.matches(hexColorRegex);
        System.out.println("8. Hex Color is Valid: " + isHexColorValid);

        // 9. 匹配有效的URL，包括http和https
        String urlRegex = "^(https?|ftp)://[a-zA-Z0-9.-]+(:\\d+)?(/\\S*)?$";
        String url = "http://www.example.com";
        boolean isUrlValid = url.matches(urlRegex);
        System.out.println("9. URL is Valid: " + isUrlValid);

        // 10. 匹配有效的邮政编码（美国）
        String zipCodeRegex = "^[0-9]{5}(-[0-9]{4})?$";
        String zipCode = "90210";
        boolean isZipCodeValid = zipCode.matches(zipCodeRegex);
        System.out.println("10. Zip Code is Valid: " + isZipCodeValid);

        // 11. 匹配有效的货币金额（带2位小数）
        String currencyRegex = "^[+-]?[0-9]{1,3}(?:,?[0-9]{3})*(?:\\.[0-9]{2})?$";
        String currency = "$1,234.56";
        boolean isCurrencyValid = currency.matches(currencyRegex);
        System.out.println("11. Currency is Valid: " + isCurrencyValid);

        // 12. 匹配有效的国际电话号码
        String internationalPhoneRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        String internationalPhoneNumber = "+1 123 456 7890";
        boolean isInternationalPhoneNumberValid = internationalPhoneNumber.matches(internationalPhoneRegex);
        System.out.println("12. International Phone Number is Valid: " + isInternationalPhoneNumberValid);

        // 13. 匹配有效的IPv6 地址
        String ipv6Regex = "^(?:[0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4}$";
        String ipv6Address = "2001:0db8:85a3:0000:0000:8a2e:0370:7334";
        boolean isIPv6Valid = ipv6Address.matches(ipv6Regex);
        System.out.println("13. IPv6 Address is Valid: " + isIPv6Valid);

        // 14. 匹配有效的车牌号（美国）
        String licensePlateRegex = "^[A-Z0-9]{1,7}$";
        String licensePlate = "ABC1234";
        boolean isLicensePlateValid = licensePlate.matches(licensePlateRegex);
        System.out.println("14. License Plate is Valid: " + isLicensePlateValid);

        // 15. 匹配有效的日期和时间（yyyy-MM-dd HH:mm:ss）
        String dateTimeRegex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        String dateTime = "2023-11-04 15:30:45";
        boolean isDateTimeValid = dateTime.matches(dateTimeRegex);
        System.out.println("15. Date and Time is Valid: " + isDateTimeValid);

        // 16. 匹配复杂的邮件地址
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String emailAddress = "user.name@example.co.uk";
        boolean isEmailValid = emailAddress.matches(emailRegex);
        System.out.println("16. Email Address is Valid: " + isEmailValid);

        // 17. 匹配多行注释块
        String commentBlockRegex = "/\\*(?:(?!\\*/).)*\\*/";
        String commentBlock = "/* This is\na multi-line\ncomment block. */";
        boolean isCommentBlockValid = commentBlock.matches(commentBlockRegex);
        System.out.println("17. Comment Block is Valid: " + isCommentBlockValid);

        // 18. 匹配Markdown链接
        String markdownLinkRegex = "\\[([^\\]]+)\\]\\(([^\\)]+)\\)";
        String markdownText = "Visit [OpenAI](https://www.openai.com) for more information.";
        boolean isMarkdownLinkValid = markdownText.matches(markdownLinkRegex);
        System.out.println("18. Markdown Link is Valid: " + isMarkdownLinkValid);

        // 19. 匹配复杂的HTML文本
        String complexHtmlRegex = "<[a-zA-Z][^>]*>(.*?)</[a-zA-Z]>";
        String complexHtml = "<div class='content'>This is a <b>bold</b> paragraph.</div>";
        boolean isComplexHtmlValid = Pattern.compile(complexHtmlRegex, Pattern.DOTALL).matcher(complexHtml).matches();
        System.out.println("19. Complex HTML is Valid: " + isComplexHtmlValid);

        // 20. 匹配多种日期格式（dd/MM/yyyy 或 yyyy-MM-dd）
        String dateRegex = "(\\d{2}/\\d{2}/\\d{4})|(\\d{4}-\\d{2}-\\d{2})";
        String mixedDates = "Dates: 11/04/2023, 2023-11-04, 04/11/23";
        boolean areMixedDatesValid = mixedDates.matches(dateRegex);
        System.out.println("20. Mixed Dates are Valid: " + areMixedDatesValid);

        // 21. 匹配引号括起来的文本
        String quotedTextRegex = "\"(.*?)\"";
        String quotedText = "This is a \"quoted text\" example.";
        boolean isQuotedTextValid = Pattern.compile(quotedTextRegex).matcher(quotedText).find();
        System.out.println("21. Quoted Text is Valid: " + isQuotedTextValid);

        // 22. 匹配包含特殊字符的密码（大小写字母、数字、特殊字符）
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        String password = "P@ssw0rd";
        boolean isPasswordValid = password.matches(passwordRegex);
        System.out.println("22. Password is Valid: " + isPasswordValid);

    }
}
