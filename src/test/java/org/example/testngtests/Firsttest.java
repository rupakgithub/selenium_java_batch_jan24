package org.example.testngtests;

import org.testng.annotations.*;

public class Firsttest {

    @BeforeSuite
    public void beforesuite(){
        System.out.println("beforesuite");
    }

    @BeforeTest
    public void beforetest(){
        System.out.println("beforetest");
    }

    @BeforeClass
    public void beforeclass(){
        System.out.println("beforclass");
    }

    @BeforeMethod
    public void beforemethod(){
        System.out.println("beforemethod");
    }


    @Test(priority = 2, invocationCount = 2)
    public void test1(){
        System.out.println("test1");
    }

    @Test(priority = 1, enabled = false)
    public void test2(){
        System.out.println("test2");
    }

    @AfterMethod
    public void aftermethod(){
        System.out.println("aftermethod");
    }

    @AfterClass
    public void afterclass(){
        System.out.println("afterclass");
    }

    @AfterTest
    public void aftertest(){
        System.out.println("aftertest");
    }

    @AfterSuite
    public void aftersuite(){
        System.out.println("aftersuite");
    }

}
