package com.epam.automation;

import com.epam.automation.resources.CareerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CareerSiteTest extends Basic {


    @Test
    public void clickOnSearchButtonTest() {
        CareerPage career = new CareerPage(driver);
        career.clickOnSearchButton();
        career.checkingResult("Currently we are looking for");
    }

    @Test
    public void keyWordTestInputTest() {
        CareerPage career = new CareerPage(driver);
        career.keywords("Test Automation");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("Currently we are looking for a Test Automation Engineer"));
    }

    @Test
    public void locationTestInputTest() {
        CareerPage career = new CareerPage(driver);
        career.location("Hungary", "Debrecen");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("for our Debrecen office to make the team even stronger"));
    }

    @Test
    public void skillsTestInputTest() {
        CareerPage career = new CareerPage(driver);
        career.openSkillTab();
        career.skills("Software Engineering");
        career.skills("Cloud & DevOps");
        career.clickOnSearchButton();
        Assert.assertTrue(driver.getPageSource().contains("Currently we are looking for a .NET"));
    }

    @Test
    public void sortByDateTest() throws StaleElementReferenceException {
        CareerPage career = new CareerPage(driver);
        career.clickOnSearchButton();
        career.sortJobsByDate();
    }

    @Test
    public void searchForTaInDebrecen() {
        CareerPage career = new CareerPage(driver);
        career.keywords("Test Automation");
        career.location("Hungary", "Debrecen");
        career.openSkillTab();
        career.skills("Software Test Engineering");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("Currently we are looking for a Test Automation Engineer for our Debrecen office to make the team even stronger"));
    }

    @Test
    public void searchForDevInKatowice() {
        CareerPage career = new CareerPage(driver);
        career.keywords("Developer");
        career.location("Poland", "Katowice");
        career.openSkillTab();
        career.skills("Software Engineering");
        career.skills("Software Architecture");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("Currently we are looking for a Lead .NET Web Developer for our Katowice office to make the team even stronger"));
    }

    @Test
    public void searchForManagerInUSA() {
        CareerPage career = new CareerPage(driver);
        career.keywords("Manager");
        career.location("United States", "USA");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("Account Manager, Bilingual Japanese/English"));
        Assert.assertTrue(driver.getPageSource().contains("USA"));
    }
}
