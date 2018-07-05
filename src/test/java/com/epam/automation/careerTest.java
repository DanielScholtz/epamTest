package com.epam.automation;

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
