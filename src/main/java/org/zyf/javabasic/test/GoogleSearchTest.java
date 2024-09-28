package org.zyf.javabasic.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @program: zyfboot-javabasic
 * @description: GoogleSearchTest
 * @author: zhangyanfeng
 * @create: 2024-08-18 17:35
 **/
public class GoogleSearchTest {
    public static void main(String[] args) throws InterruptedException {
        // 设置 ChromeDriver 可执行文件的路径
        System.setProperty("webdriver.chrome.driver", "/Users/zyf/Downloads/chromedriver-mac-arm64/chromedriver");

        // 设置 chrome 驱动路径
        WebDriver driver = new ChromeDriver();
        // 加载页面
        driver.get("https://www.jd.com/");
        WebElement element = driver.findElement(By.id("key"));
        element.sendKeys("");
        element.sendKeys(Keys.ENTER);
        Thread.sleep(2000L);
        // 登录
        element = driver.findElement(By.id("loginname"));
        element.sendKeys("17391904797");
        element = driver.findElement(By.id("nloginpwd"));
        element.sendKeys("!QAZ2wsx");
        element = driver.findElement(By.id("loginsubmit"));
        element.submit();
        Thread.sleep(60000L);
        // 登录
        element = driver.findElement(By.id("key"));
        element.clear();
        element.sendKeys("农夫山泉");
        element.sendKeys(Keys.ENTER);
        driver.quit();
    }
}
