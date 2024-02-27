package org.example.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Commonfunctions {

    public static void click(By locator, WebDriver driver){
       try{
           WebElement element = driver.findElement(locator);
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           wait.until(ExpectedConditions.presenceOfElementLocated(locator));
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
           element.click();
       }catch (Exception e){
           System.err.println("Unexpected Error: "+e.getMessage());
       }
    }


}
