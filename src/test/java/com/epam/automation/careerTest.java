package com.epam.automation;

import org.testng.annotations.Test;

public class careerTest extends inside{
    @Test
    public void searchForJob() throws InterruptedException {
        keywords("Test Automation");
        country("Hungary");
        city("Debrecen");
        openSkillTab();
        skills("Software Test Engineering");
        searchButton();
        checkingResult("Currently we are looking for a Test Automation Engineer for our Debrecen office to make the team even stronger.");
        Thread.sleep(1000);
    }


    @Test
    public void searchForJob2() throws InterruptedException {
        keywords("Developer");
        country("Poland");
        city("Katowice");
        openSkillTab();
        skills("Software Engineering");
        skills("Software Architecture");
        searchButton();
        checkingResult("Currently we are looking for a .NET (Azure) Developer for our Katowice office to make the team even stronger.");
        Thread.sleep(1000);
    }

    @Test
    public void searchForJob3() throws InterruptedException {
        keywords("Manager");
        country("United States");
        city("USA");
        searchButton();
        Thread.sleep(1000);
        checkingResult("Manager-Intelligent Automation (RPA)-Business Consulting");
        checkingResult("USA");
        checkingResult("Hot");
        checkingResult("You are strategic, resilient, engaging with people and a natural self-starter.");
        Thread.sleep(1000);
    }
}
