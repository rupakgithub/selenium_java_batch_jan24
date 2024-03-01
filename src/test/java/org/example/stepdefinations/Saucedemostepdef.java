package org.example.stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Saucedemostepdef {
    WebDriver driver;


    @Given("User initilize the browser")
    public void user_initilize_the_browser() {
//        WebDriverManager.chromedriver().clearDriverCache().setup();
        String rootpath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootpath+"/src/test/resources/driver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {int}")
    public void user_enters_username(Integer username) {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");


    }
    @When("User enters password {int}")
    public void user_enters_password(Integer password) {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();
    }


    @Then("User should be able to login")
    public void user_should_be_able_to_login() {
        String title = driver.getTitle();
        System.out.println("The title of the website is: "+title);
        driver.quit();
    }

    @Given("HR searching for {string} for post of {string} {string}")
    public void hr_searching_for_for_post_of(String job_title, String total, String vacancy) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        System.out.println(job_title);
        System.out.println(total);
        System.out.println(vacancy);
        if(vacancy.equals("Architect")){
            Assert.fail("Test failure");
        }
    }

    @When("User enterss corrects credentials")
    public void user_enterss_corrects_credentials(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        for(List<String> columns: data) {
            System.out.println(columns.get(0));
            System.out.println(columns.get(1));
        }
    }

    @Then("User shoulds be ables to login")
    public void user_shoulds_be_ables_to_login() {
        System.out.println("login successful");
    }
}
