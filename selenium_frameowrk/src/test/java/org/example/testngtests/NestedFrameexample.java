package org.example.testngtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class NestedFrameexample {

    WebDriver driver;

    @BeforeTest
    public void beforetest(){
        WebDriverManager.chromedriver().driverVersion("121.0.6167.185").clearDriverCache().setup();

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
    public void nestedFrame(){
        driver.get("https://demoqa.com/nestedframes");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame1"));

        String framecontent = driver.findElement(By.xpath("//body")).getText();

        System.out.println(framecontent);

        WebElement childframe  = driver.findElement(By.xpath("//iframe[@srcdoc]"));

        driver.switchTo().frame(childframe);

        String childframecontent = driver.findElement(By.xpath("//body")).getText();

        System.out.println(childframecontent);

//        driver.switchTo().defaultContent();

        driver.switchTo().parentFrame();
    }
}
