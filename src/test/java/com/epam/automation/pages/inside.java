package com.epam.automation.pages;

import com.epam.automation.enums.MainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

public class inside {
    /**
     * There are some Page Objects and contsuctors
     * @param driver to initalize our browser
     * @param country is here because we use it in more methods
     * @param city is here because we use it in more methods
     * @param logo for checking the site loaded correctly
     * @param locationArrow opening location tab
     * @param defaultLocation to select All Location by default
     * @param keywordsInput to write inputs in the keywords place
     * @param skillsTabArrow to open the skills tab
     * @param searchButton to click the search button
     * @param searchResult for checking the search result
     * @param sortByDateTest is to reorder the search results by date
     */

    private Logger log = LoggerFactory.getLogger(inside.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private String country = "";
    @FindBy(className = "header__logo")
    private WebElement logo;
    @FindBy(css = "*[id^='select-box-location-'")
    private WebElement locationArrow;
    @FindBy (css = "*[id$='all_locations'")
    private WebElement defaultLocation;
    @FindBy (css = "input[class^='job-search__input']")
    private WebElement keywordInput;
    @FindBy (css =  "*[class*='selected-params']")
    private WebElement skillsTabArrow;
    @FindBy (className = "job-search__submit")
    private WebElement searchButton;
    @FindBy (className = "search-result__item-name")
    private WebElement searchResult;
    @FindBy (css = "*[data-value*='time']")
    private WebElement sortByDate;

    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,10);
        driver.get("https://www.epam.com");
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigation between main menus
     * @param Menu getting a String from enums which refers to a main menu
     * @param siteTitle gets the Title of the site
     * Checking the site load correctly with the logo display
     * with the latest Epam website update we always set the location back to All location by default
     * since its get the location from our ip address
     */
    public void navigation(String Menu) {
        String siteTitle = driver.getTitle();
        driver.findElement(By.xpath("//a[@href='" + Menu + "']")).click();
        log.info(siteTitle);
        Assert.assertTrue(logo.isDisplayed(), "The page didn't open correctly");
        if (Menu.equals(MainMenu.CAREER_MENU)) {
            locationArrow.click();
            defaultLocation.click();
        }
    }

    public void keywords(String message) {
        keywordInput.sendKeys(message);
    }

    /**
     * This method is stands for choosing the location
     * if we want to list all cities of the country we have to use the same name as the country
     * there is only one exception
     * when the country is United States, we should use USA at city variable
     */
    public void location(String country, String city) {
        locationArrow.click();
        driver.findElement(By.cssSelector("[aria-label=\"" + country + "\"]")).click();
        if (city.equals(country)) {
            driver.findElement(By.xpath("//*[contains(@id, 'all_" + city + "')]")).click();
        } else {
            driver.findElement(By.xpath("//*[contains(@id, '" + city + "')]")).click();
        }
    }


    /**
     * Its for opening the skills tab
     * its useful when choosing more skills and want to avoid errors
     */
    public void openSkillTab() {
        skillsTabArrow.click();
    }

    public void skills(String skill) {
        driver.findElement(By.xpath("//*[@class='checkbox-custom-label' and contains(., '" + skill + "')]")).click();
    }

    public void clickOnSearchButton() {
        searchButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-result__item-name")));
    }

    /**
     * Making a log of jobs with location from search result
     */
    public void showJobsWithLocation() {
        String jobs;
        String loc = "";
        List<WebElement> job = driver.findElements(By.className("search-result__item-name"));
        for (WebElement ele : job) {
            jobs = ele.getText();

            List<WebElement> location = driver.findElements(By.className("search-result__location"));
            for (WebElement ele2 : location) {
                loc = ele2.getText();
            }
            log.info(jobs + " " + loc);
        }
    }

    /**
     * Sorting the jobs by date
     * the try/catch block is for not refreshing the page in time
     * sadly the test cannot be verified from id
     * because if they update a job it become the newest but with the old id
     */
    public void sortJobsByDate() throws StaleElementReferenceException {
        try {
            sortByDate.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.search-result__sorting-item:nth-child(1)")));
            List<WebElement> allElements = driver.findElements(
                    By.className("search-result__item-name")
            );
            for (WebElement element : allElements) {
                String idList = element.getAttribute("href");
                log.info(idList);
            }
        } catch (StaleElementReferenceException e) {
            log.error("error in sortJobsByDate", e);
        }
    }


    /**
     * Checking if the search result is correct by the description of the job
     */
    public void checkingResult(String text) {
        assert driver.getPageSource().contains(text);
    }


    @AfterClass
    public void closePage() {
        driver.close();
    }
}