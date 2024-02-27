package org.example.stepdefinations;

import io.cucumber.java.*;

public class CommonStepDef {
    @BeforeAll
    public static void setup(){
        System.out.println("cucumber before step executed");
    }

    @AfterAll
    public static void teardown(){
        System.out.println("Cucumber all steps executed");
    }

    @AfterStep
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            System.out.println("Scenario "+scenario.getName()+" is failed");
        }
    }


}
