package org.zyf.javabasic.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @program: zyfboot-javabasic
 * @description: AutoComment
 * @author: zhangyanfeng
 * @create: 2024-08-11 19:58
 **/
public class BlogCommentAutomation {
    public static void main(String[] args) {
        // 设置ChromeDriver路径
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // 初始化WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // 打开网站
            driver.get("https://blog.csdn.net/login");

            // 等待页面加载完成
            WebDriverWait wait = new WebDriverWait(driver, 10);

            // 输入用户名
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
            usernameField.sendKeys("18586128339");

            // 输入密码
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("csdn20142014");

            // 点击登录按钮
            WebElement loginButton = driver.findElement(By.name("login"));
            loginButton.click();

            // 等待登录完成并跳转到博客页面
            wait.until(ExpectedConditions.urlToBe("https://blog.csdn.net/xiaofeng10330111?type=blog"));

            // 打开某篇博客文章
            driver.get("https://zyfcodes.blog.csdn.net/article/details/105148032");

            // 输入评论
            WebElement commentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("comment")));
            commentField.sendKeys("This is an automated comment!");

            // 提交评论
            WebElement submitButton = driver.findElement(By.name("submit"));
            submitButton.click();

            // 打印提交成功信息
            System.out.println("Comment submitted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭浏览器
            driver.quit();
        }
    }
}
