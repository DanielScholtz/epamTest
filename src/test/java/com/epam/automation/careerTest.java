package com.epam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class careerTest {
    WebDriver driver;
//    @FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/label/input")
//    private ;
//    @FindBy(
//    private ;
//    @FindBy()
//    private
//    @FindBy(xpath = )
//    private ;
//    @FindBy(
//    private ;
//    @FindBy(
////    ul.multi-select-column:nth-child(oszlop) > li:nth-child(oszlopon belüli sorszám) > label:nth-child(1) > span:nth-child(2)
//    private ;
//    @FindBy(xpath =
//    private ;
//
//    public careerTest(WebDriver driver) {
//        super(driver);
//        driver.get("https://www.epam.com/careers");
//        System.out.println(driver.getTitle());
//    }

    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.epam.com/careers");
        System.out.println(driver.getTitle());
    }


    @Test
    public void searchForJob() throws InterruptedException {
        WebElement keyword = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/label/input"));
        keyword.sendKeys("Test Automation");
        WebElement location = driver.findElement(By.xpath("//*[starts-with(@id,'select-box-location-')]"));
        location.click();
        WebElement country = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[1]/span[2]/div[2]/div/div[2]/ul/li[12]"));
        country.click();
        WebElement city = driver.findElement(By.xpath("//*[contains(@id, 'Debrecen')]"));
        city.click();
        WebElement skills = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[2]/div/div[1]/div[1]"));
        skills.click();
        System.out.println(driver.findElement(By.className("checkbox-custom-label")).getAttribute("Software Test Engineering"));
        WebElement softwareTesterSkill = driver.findElement(By.cssSelector("ul.multi-select-column:nth-child(2) > li:nth-child(5) > label:nth-child(1) > span:nth-child(2)"));
        softwareTesterSkill.click();
        WebElement findButton = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[3]/button"));
        findButton.click();
        assert driver.getPageSource().contains("Currently we are looking for a Test Automation Engineer for our Debrecen office to make the team even stronger.");
        Thread.sleep(1000);
    }

    @AfterMethod
    public void closePage() {
        driver.close();
    }

}
