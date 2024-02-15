package org.example.testngtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Firstuitest {

    WebDriver driver;

    @BeforeTest
    public void beforetest(){
        WebDriverManager.chromedriver().clearDriverCache().setup();

        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }


    @Test
    public void test1(){
        String title = driver.getTitle();
        System.out.println("The title of the website is: "+title);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();
    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        Map<String, String> item_price_collection = new HashMap<>();

        for (int i = 1; i <=3 ; i++) {

            String product_name = driver.findElement(By.xpath("(//div[@id='inventory_container']//div[@class='inventory_item'])["+i+"]//div[@class='inventory_item_name ']")).getText();

            String product_price = driver.findElement(By.xpath("(//div[@id='inventory_container']//div[@class='inventory_item'])["+i+"]//div[@class='inventory_item_price']")).getText();
            item_price_collection.put(product_name, product_price);
            String add_to_cart_xpath = "(//div[@id='inventory_container']//div[@class='inventory_item'])["+i+"]//button";
            driver.findElement(By.xpath(add_to_cart_xpath)).click();
        }

        String items_in_cart = driver.findElement(By.className("shopping_cart_badge")).getText();
        int total_items = Integer.parseInt(items_in_cart);

        System.out.println("Total items in cart: "+items_in_cart);

        Assert.assertEquals(total_items, 3);

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> items = driver.findElements(By.xpath("//div[@class='cart_list']//div[@class='cart_item']"));

        Assert.assertEquals(items.size(), 3, "Expected total 3 items to present but actually "+items.size()+" are present.");

        for (int i = 1; i <= items.size() ; i++) {
            String item_name = driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_name']")).getText();

            String item_price = driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_price']")).getText();

            String expected_price = item_price_collection.get(item_name);

            if(expected_price == null){
                Assert.fail("Expected product "+item_name+" to be present in cart but not present");
            }else{
                Assert.assertEquals(item_price, expected_price, "Actual & expected price don't match");
            }
        }

    }

    @AfterTest
    public void aftertest(){
       driver.quit();
    }

}
