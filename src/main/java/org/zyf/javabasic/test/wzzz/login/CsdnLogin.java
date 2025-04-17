package org.zyf.javabasic.test.wzzz.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @program: zyfboot-javabasic
 * @description: CsdnLogin
 * @author: zhangyanfeng
 * @create: 2025-04-16 08:29
 **/
public class CsdnLogin {
    public static void main(String[] args) {
        // 配置 ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/zyf/Downloads/chromedriver-mac-arm64/chromedriver");  // 更新为你本地的 chromedriver 路径

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            // 打开 CSDN 登录页面
            driver.get("https://passport.csdn.net/account/login");

            // 显式等待，直到用户名输入框可见
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[autocomplete='username']")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[autocomplete='current-password']")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.base-button")));

            // 输入用户名和密码，点击登录
            usernameField.sendKeys("15560826537");  // 替换为你的用户名
            passwordField.sendKeys("csdn20142014");  // 替换为你的密码
            loginButton.click();

            // 获取 cookies
            Thread.sleep(5000);  // 等待页面加载
            driver.manage().getCookies().forEach(cookie -> {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();  // 关闭浏览器
        }
    }
}
