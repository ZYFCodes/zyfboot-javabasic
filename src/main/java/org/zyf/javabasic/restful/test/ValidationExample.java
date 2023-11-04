package org.zyf.javabasic.restful.test;

import com.google.common.collect.Lists;
import org.zyf.javabasic.restful.model.Address;
import org.zyf.javabasic.restful.model.UserRegistrationForm;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: 验证示例代码
 * @author: zhangyanfeng
 * @create: 2023-10-29 19:46
 **/
public class ValidationExample {
    public static void main(String[] args) {
        // 创建验证器工厂和验证器
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // 创建一个 User 对象进行验证
        UserRegistrationForm user = new UserRegistrationForm();
        user.setUsername(null); // 这里设置了一个 null 值来触发 @NotNull 错误
        user.setPassword("P@ssw0rd");
        user.setEmail("invalid_email"); // 无效的邮箱地址
        user.setFullName("A");
        user.setAge(17); // 年龄不满足 @Min 规则
        user.setBirthDate(new Date(3000, 1, 1)); // 未来的日期
        user.setAgreeToTerms(false); // 没有同意使用条款
        user.setPrice(new BigDecimal("123.4567")); // 小数位数过多
        user.setCreditCardNumber("1234-5678-9012-3456"); // 无效的信用卡号
        user.setWebsiteURL("not_a_url"); // 无效的 URL
        user.setPercentage(150.0); // 超出范围
        user.setFutureDate(new Date(3020, 1, 1)); // 未来的日期
        user.setPastOrPresentDateTime(LocalDateTime.now().plusDays(1)); // 未来的日期

        // 嵌套校验 - 创建 Address 对象
        Address address = new Address();
        address.setStreet(null); // 未提供街道
        address.setCity("Sample City"); // 设置城市
        address.setZipCode("12345"); // 设置邮政编码
        user.setAddress(address);

        user.setPhoneNumber("1234567");

        List<String> phoneNumbers = Lists.newArrayList("1234567890", "9876543210", "invalid");
        user.setPhoneNumbers(phoneNumbers);

        List<String> addressContents = Lists.newArrayList("abc", null, "def");
        user.setAddressContents(addressContents);


        // 使用验证器验证 User 对象
        Set<ConstraintViolation<UserRegistrationForm>> violations = validator.validate(user);

        if (violations.isEmpty()) {
            System.out.println("用户对象验证通过，可以提交注册。");
        } else {
            System.out.println("用户对象验证失败，错误如下：");
            for (ConstraintViolation<UserRegistrationForm> violation : violations) {
                System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
            }
        }
    }
}
