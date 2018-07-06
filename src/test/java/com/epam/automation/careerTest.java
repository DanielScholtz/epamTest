package com.epam.automation;

import com.epam.automation.enums.Menu;
import org.testng.annotations.Test;

public class careerTest extends inside{

    @Test
    public void navigationTest() {
        navigation(Menu.WHAT_WE_DO);
        navigation(Menu.ABOUT);
        navigation(Menu.HOW_WE_DO_IT);
        navigation(Menu.INSIGHTS);
        navigation(Menu.OUR_WORK);
        navigation(Menu.CAREER);
    }


    @Test
    public void searchForJob() {
        navigation(Menu.CAREER);
        keywords("Test Automation");
        location("Hungary", "Debrecen");
        openSkillTab();
        skills("Software Test Engineering");
        searchButton();
        checkingResult("Currently we are looking for a Test Automation Engineer for our Debrecen office to make the team even stronger.");
        showJobsWithLocation();
    }


    @Test
    public void searchForJob2() {
        navigation(Menu.CAREER);
        keywords("Developer");
        location("Poland", "Katowice");
        openSkillTab();
        skills("Software Engineering");
        skills("Software Architecture");
        searchButton();
        checkingResult("Currently we are looking for a Lead .NET Web Developer for our Katowice office to make the team even stronger.");
        showJobsWithLocation();
    }

    @Test
    public void searchForJob3() {
        navigation(Menu.CAREER);
        keywords("Manager");
        location("United States", "USA");
        searchButton();
        checkingResult("Manager-Intelligent Automation (RPA)-Business Consulting");
        checkingResult("USA");
        checkingResult("Hot");
        checkingResult("You are strategic, resilient, engaging with people and a natural self-starter.");
        showJobsWithLocation();
    }
}
