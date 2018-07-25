//package com.epam.automation;
//
//import com.epam.automation.core.DriverManager;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.*;
//
//public class Basic {
//    WebDriver driver;
//
//    @BeforeSuite
//    public void setupBrowser() {
//        driver = DriverManager.getDriver(driver);
//    }
//
//    @AfterSuite
//    public void tearDown() {
//        driver.quit();
//    }
//}
