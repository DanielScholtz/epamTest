package com.epam.automation.resources;

import com.epam.automation.enums.MainMenu;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class Page {
    public final WebDriver driver = new FirefoxDriver();
    public WebDriverWait wait;
    public Logger log = LoggerFactory.getLogger(CareerPage.class);
    CareerPage career = new CareerPage(driver);

    @BeforeSuite
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeClass
    public void openBrowser() {
        wait = new WebDriverWait(driver, 10);
        driver.get("https://www.epam.com");
        PageFactory.initElements(driver, this);
    }

    public Page (WebDriver driver) {
        driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * Navigation between main menus
     *
     * @param Menu      getting a String from enums which refers to a main menu
     * @param siteTitle gets the Title of the site
     *                  Checking the site load correctly with the logo display
     *                  with the latest Epam website update we always set the location back to All location by default
     *                  since its get the location from our ip address
     */
    public void navigation(String Menu) {
        String siteTitle = driver.getTitle();
        driver.findElement(By.cssSelector("a[href='" + Menu + "']")).click();
        log.info(siteTitle);
        Assert.assertTrue(career.logo.isDisplayed(), "The page didn't open correctly");
        if (Menu.equals(MainMenu.CAREER_MENU)) {
            career.locationArrow.click();
            career.defaultLocation.click();
        }
    }

    @AfterClass
    public void closePage() {
        driver.close();
    }
}
