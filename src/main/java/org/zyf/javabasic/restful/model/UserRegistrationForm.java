package org.zyf.javabasic.restful.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;
import org.zyf.javabasic.restful.annotations.EachElementNotNull;
import org.zyf.javabasic.restful.annotations.ValidPhoneNumber;
import org.zyf.javabasic.restful.annotations.ValidPhoneNumbers;

import javax.validation.constraints.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 简单的用户注册表单
 * @author: zhangyanfeng
 * @create: 2023-10-29 19:43
 **/
@Data
public class UserRegistrationForm {
    @NotNull(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空且必须包含非空白字符")
    private String password;

    @NotEmpty(message = "邮箱地址不能为空")
    @Email(message = "请输入有效的电子邮件地址")
    private String email;

    @Size(min = 2, max = 30, message = "姓名长度必须在2到30个字符之间")
    private String fullName;

    @Min(value = 18, message = "年龄必须大于等于18")
    @Max(value = 120, message = "年龄必须小于等于120")
    private int age;

    @Past(message = "出生日期必须在过去")
    private Date birthDate;

    @AssertTrue(message = "必须同意使用条款")
    private boolean agreeToTerms;

    @Digits(integer = 3, fraction = 2, message = "价格必须是一个有效的数字，整数部分最多3位，小数部分最多2位")
    private BigDecimal price;

    @CreditCardNumber(message = "无效的信用卡号")
    private String creditCardNumber;

    @URL(message = "请输入有效的URL")
    private String websiteURL;

    @DecimalMin(value = "0.0", message = "值必须大于等于0")
    @DecimalMax(value = "100.0", message = "值必须小于等于100")
    private double percentage;

    @Future(message = "日期必须在未来")
    private Date futureDate;

    @PastOrPresent(message = "日期必须在过去或现在")
    private LocalDateTime pastOrPresentDateTime;

    @Positive(message = "值必须是正数")
    private int positiveNumber;

    @NegativeOrZero(message = "值必须是负数或零")
    private int negativeOrZeroNumber;

    @NotEmpty(message = "电话号码列表不能为空")
    @Size(min = 1, message = "至少提供一个电话号码")
    @ValidPhoneNumbers
    private List<String> phoneNumbers;

    @ValidPhoneNumber
    private String phoneNumber;

    @EachElementNotNull(message = "地址信息内容中不能含有空数据")
    private List<String> addressContents;

    @Valid
    private Address address; // 嵌套校验
}
