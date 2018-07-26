package com.epam.automation.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://www.epam.com");
    }

    @Test
    public void navigation(String Menu) {
        driver.findElement(By.cssSelector("a[href='" + Menu + "']")).click();
    }
}
