package org.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "@simpletest", features = {"src/test/resources/features"}, glue = {"org/example/stepdefinations"}, plugin = {"pretty", "html:target/cucumber-report.html"})
public class CucumberRunner extends AbstractTestNGCucumberTests {


}
