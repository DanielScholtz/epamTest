package com.epam.automation;

import com.epam.automation.enums.MainMenu;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;

public class CareerTest extends inside {

    @Test
    public void navigationTest() {
        navigation(MainMenu.WHAT_WE_DO_MENU);
        navigation(MainMenu.ABOUT_MENU);
        navigation(MainMenu.HOW_WE_DO_IT_MENU);
        navigation(MainMenu.INSIGHTS_MENU);
        navigation(MainMenu.OUR_WORK_MENU);
        navigation(MainMenu.CAREER_MENU);
    }

    @Test
    public void clickOnSearchButtonTest() {
        navigation(MainMenu.CAREER_MENU);
        clickOnSearchButton();
        checkingResult("Currently we are looking for");
    }

    @Test
    public void keyWordTest() {
        navigation(MainMenu.CAREER_MENU);
        keywords("Test Automation");
        clickOnSearchButton();
        checkingResult("Currently we are looking for a Test Automation Engineer");
        showJobsWithLocation();
    }

    @Test
    public void locationTest() {
        navigation(MainMenu.CAREER_MENU);
        location("Hungary", "Debrecen");
        clickOnSearchButton();
        checkingResult("for our Debrecen office to make the team even stronger.");
        showJobsWithLocation();
    }

    @Test
    public void skillsTest() {
        navigation(MainMenu.CAREER_MENU);
        openSkillTab();
        skills("Software Engineering");
        skills("Cloud & DevOps");
        clickOnSearchButton();
        checkingResult("Currently we are looking for a .NET");
    }

    @Test
    public void sortByDate() throws StaleElementReferenceException {
        navigation(MainMenu.CAREER_MENU);
        clickOnSearchButton();
        sortJobsByDate();
    }

    @Test
    public void searchForJob() {
        navigation(MainMenu.CAREER_MENU);
        keywords("Test Automation");
        location("Hungary", "Debrecen");
        openSkillTab();
        skills("Software Test Engineering");
        clickOnSearchButton();
        checkingResult("Currently we are looking for a Test Automation Engineer for our Debrecen office to make the team even stronger.");
        showJobsWithLocation();
    }

    @Test
    public void searchForJob2() {
        navigation(MainMenu.CAREER_MENU);
        keywords("Developer");
        location("Poland", "Katowice");
        openSkillTab();
        skills("Software Engineering");
        skills("Software Architecture");
        clickOnSearchButton();
        checkingResult("Currently we are looking for a Lead .NET Web Developer for our Katowice office to make the team even stronger.");
        showJobsWithLocation();
    }

    @Test
    public void searchForJob3() {
        navigation(MainMenu.CAREER_MENU);
        keywords("Manager");
        location("United States", "USA");
        clickOnSearchButton();
        checkingResult("Manager-Intelligent Automation (RPA)-Business Consulting");
        checkingResult("USA");
        checkingResult("Hot");
        checkingResult("You are strategic, resilient, engaging with people and a natural self-starter.");
        showJobsWithLocation();
    }
}
