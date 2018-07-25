package com.epam.automation;

import com.epam.automation.enums.MainMenu;
import com.epam.automation.resources.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends Basic {

    @Test
    public void navigationToMenus() {
        HomePage home = new HomePage(driver);
        home.navigation(MainMenu.WHAT_WE_DO_MENU);
        home.navigation(MainMenu.ABOUT_MENU);
        home.navigation(MainMenu.HOW_WE_DO_IT_MENU);
        home.navigation(MainMenu.INSIGHTS_MENU);
        home.navigation(MainMenu.OUR_WORK_MENU);
        home.navigation(MainMenu.CAREER_MENU);
    }
}