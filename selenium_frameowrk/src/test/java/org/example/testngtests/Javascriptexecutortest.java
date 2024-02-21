package org.example.testngtests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Javascriptexecutortest {

    WebDriver driver;

    @BeforeTest
    public void beforetest(){
//        WebDriverManager.chromedriver().clearDriverCache().setup();
        String rootpath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootpath+"/src/test/resources/driver/chromedriver.exe");

        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void test_scroll() throws InterruptedException {
        driver.get("https://www.tutorialspoint.com/index.htm");
        WebElement element = driver.findElement(By.xpath("//*[text()='About us']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void test_scroll_by_pixel() throws InterruptedException {
        driver.get("https://www.tutorialspoint.com/index.htm");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)","");
        Thread.sleep(3000);
        driver.close();
    }
}
