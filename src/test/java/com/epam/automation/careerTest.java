package com.epam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class careerTest extends inside{
    @Test
    public void searchForJob() throws InterruptedException {
        keywords("Test Automation");
        country("Hungary");
        city("Debrecen");
        skills("Software Test Engineering");
        searchButton();
        checkingResult("Currently we are looking for a Test Automation Engineer for our Debrecen office to make the team even stronger.");
        Thread.sleep(1000);
    }
}
