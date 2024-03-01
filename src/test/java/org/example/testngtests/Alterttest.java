package org.example.testngtests;

import org.example.utilities.Commonfunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Alterttest {

    WebDriver driver;

    @BeforeTest
    public void beforetest(){
//        WebDriverManager.chromedriver().clearDriverCache().setup();

        String rootpath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootpath+"/src/test/resources/driver/chromedriver.exe");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory",  System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator+"downloads");
        chromePrefs.put("download.prompt_for_download", false);

        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--disable-extensions");
        ops.addArguments("--headless=new");
        ops.setExperimentalOption("prefs", chromePrefs);
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @Test
    public void test_alerts_only_accept() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

        Commonfunctions.click(By.id("alertButton"), driver);

        Thread.sleep(2000);
        System.out.println(driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();

    }

    @Test
    public void test_alerts_confirmation() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

        Commonfunctions.click(By.id("confirmButton"), driver);

        Thread.sleep(1000);
        System.out.println(driver.switchTo().alert().getText());

        driver.switchTo().alert().dismiss();

    }

    @Test
    public void test_alerts_text() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

        Commonfunctions.click(By.id("promtButton"), driver);

        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        Thread.sleep(1000);
        alert.sendKeys("Hi there");
        alert.accept();

    }
    @Test
    public void test_select_class() throws Exception {
        driver.get("https://demoqa.com/select-menu");

        WebElement selectcolor = driver.findElement(By.xpath("//select[@id='oldSelectMenu']"));

        Select se = new Select(selectcolor);

        //se.selectByIndex(1);
//        se.selectByVisibleText("Purple");
        se.selectByValue("6");


        //se.deselectAll();
    }

    @Test
    public void test_sliders() throws InterruptedException {
        driver.get("https://demoqa.com/slider");
        Actions actions = new Actions(driver);
        WebElement slider = driver.findElement(By.xpath("//input[contains(@class,'range-slider')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(slider));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",slider);


        actions.click(slider).build().perform();
        Thread.sleep(1000);

        int slider_value = Integer.parseInt(slider.getAttribute("value"));
        System.out.println("Default slider value: "+slider_value);
        int des_slider_value = 35;

        if(slider_value > des_slider_value){
            for (int i = slider_value; i > des_slider_value; i--) {
                actions.sendKeys(Keys.ARROW_LEFT).build().perform();
            }
        }else{
            for (int i = slider_value; i < des_slider_value; i++) {
                actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            }
        }

        int slider_value_after = Integer.parseInt(slider.getAttribute("value"));
        Assert.assertEquals(slider_value_after, des_slider_value);
    }

    @Test
    public void test_drag_and_drop() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        Actions actions = new Actions(driver);

        WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));

        WebElement drop = driver.findElement(By.xpath("//div[@id='draggable']/following-sibling::div"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",drag);

        actions.dragAndDrop(drag, drop).perform();
    }

    @Test
    public void test_button_color() throws InterruptedException {
        driver.get("https://jqueryui.com/animate/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        Thread.sleep(2000);

        WebElement toggle_button = driver.findElement(By.id("button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",toggle_button);

        String colorbefore = driver.findElement(By.id("effect")).getCssValue("background-color");
        System.out.println(colorbefore);

       toggle_button.click();

        Thread.sleep(1000);
        String colorafter =driver.findElement(By.id("effect")).getCssValue("background-color");
        System.out.println(colorafter);

        Assert.assertNotEquals(colorbefore, colorafter);
    }

    @Test
    public void test_download() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        By download_loc = By.xpath("//a[@id='downloadButton']");
        Commonfunctions.click(download_loc, driver);

    }

    @Test
    public void test_autocomplete() throws InterruptedException {
        driver.get("https://jqueryui.com/autocomplete/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@id='tags']")).sendKeys("C");

        String texttobeclicked = "Clojure";
        WebElement autooptions  = driver.findElement(By.id("ui-id-1"));

        try {
            wait.until(ExpectedConditions.visibilityOf(autooptions));
        } catch (TimeoutException e) {
            System.out.println("No element present with the character");
        }

        List<WebElement> alloptions = autooptions.findElements(By.tagName("li"));


        for (WebElement option : alloptions) {
            if (option.getText().equals(texttobeclicked)) {
                option.click();
                break;
            }
        }

        String selectedvalue = driver.findElement(By.xpath("//input[@id='tags']")).getAttribute("value");
        Assert.assertEquals(texttobeclicked, selectedvalue);

    }
}
