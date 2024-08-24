package org.zyf.javabasic.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: zyfboot-javabasic
 * @description: GettingStartedRemote
 * @author: zhangyanfeng
 * @create: 2024-08-18 13:03
 **/
public class GettingStartedRemote {
    public static void main(String[] args) throws MalformedURLException {

        WebDriver driver = new RemoteWebDriver(

                new URL("http://114.0.5735.90"),

                new ChromeOptions());

        driver.get("https://blog.csdn.net/xiaofeng10330111?spm=1010.2135.3001.5343");

        driver.quit();

    }
}
