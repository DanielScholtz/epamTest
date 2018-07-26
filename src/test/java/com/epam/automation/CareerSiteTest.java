package com.epam.automation;

import com.epam.automation.resources.CareerPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CareerSiteTest extends Basic {
    CareerPage career;

    @BeforeMethod
    public void object() {
        career = new CareerPage(driver);
    }


    @Test
    public void clickOnSearchButtonTest() {
        career.clickOnSearchButton();
        Assert.assertTrue(driver.getPageSource().contains("Currently we are looking for"));
    }

    @Test
    public void keyWordTestInputTest() {
        career.keywords("Test Automation");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("Currently we are looking for a Test Automation Engineer"));
    }

    @Test
    public void locationTestInputTest() {
        career.location("Hungary", "Debrecen");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("for our Debrecen office to make the team even stronger"));
    }

    @Test
    public void skillsTestInputTest() {
        career.openSkillTab();
        career.skills("Software Engineering");
        career.skills("Cloud & DevOps");
        career.clickOnSearchButton();
        Assert.assertTrue(driver.getPageSource().contains("Currently we are looking for a .NET"));
    }

    @Test
    public void sortByDateTest() throws StaleElementReferenceException {
        career.clickOnSearchButton();
        career.sortJobsByDate();
    }

    @Test
    public void searchForTaInDebrecen() {
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
        career.keywords("Manager");
        career.location("United States", "USA");
        career.clickOnSearchButton();
        career.showJobsWithLocation();
        Assert.assertTrue(driver.getPageSource().contains("Account Manager, Bilingual Japanese/English"));
        Assert.assertTrue(driver.getPageSource().contains("USA"));
    }
}
