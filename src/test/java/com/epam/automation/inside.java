package com.epam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class inside{
    public WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.epam.com/careers");
        System.out.println(driver.getTitle());
    }

    public void keywords (String message) {
        WebElement keyword = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/label/input"));
        keyword.sendKeys(message);
    }

    public void country(String country) {
        driver.findElement(By.xpath("//*[starts-with(@id,'select-box-location-')]")).click();
        driver.findElement(By.cssSelector("[aria-label="+country+"]")).click();
    }

    public void city(String city) {
        driver.findElement(By.xpath("//*[contains(@id, '"+city+"')]")).click();
    }

    public void skills(String skill)  {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[2]/div/div[1]/div[1]")).click();
        driver.findElement(By.xpath("//li[contains(.,'"+skill+"')]")).click();
    }

    public void searchButton() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[3]/button")).click();
    }

    public void checkingResult(String description) {
        assert driver.getPageSource().contains(description);
    }

    @AfterMethod
    public void closePage() {
        driver.close();
    }
}
