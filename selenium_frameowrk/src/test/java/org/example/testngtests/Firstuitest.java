package org.example.testngtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;
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

    @Test(dependsOnMethods = "test2")
    public void test3(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("user1");
        driver.findElement(By.id("last-name")).sendKeys("user2");
        driver.findElement(By.id("postal-code")).sendKeys("7676464");

        driver.findElement(By.id("continue")).click();

        WebElement price_level = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
        wait.until(ExpectedConditions.visibilityOf(price_level));

        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        Assert.assertEquals(items.size(), 3, "Expected total 3 items to present but actually "+items.size()+" are present.");

        List<Double> price_list = new ArrayList<>();
        for (int i = 1; i <= items.size(); i++) {
            String price = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])["+i+"]")).getText();
            price = price.replaceAll("\\$", "");
            price_list.add(Double.parseDouble(price));
        }

        String tax = driver.findElement(By.className("summary_tax_label")).getText();
        tax = tax.split("\\$")[1];
        Double tax_double = Double.parseDouble(tax);

        Double total = price_list.stream().mapToDouble(Double::doubleValue).sum() + tax_double;

        String total_actual = driver.findElement(By.xpath("//div[contains(@class, 'summary_total_label')]")).getText();
        total_actual = total_actual.split("\\$")[1];

        Double actual_price_ui = Double.parseDouble(total_actual);

        Assert.assertEquals(total, actual_price_ui, "Expected total price to be "+total+" but in UI "+total_actual+" is present");


    }

    @AfterTest
    public void aftertest(){
       driver.quit();
    }

}
