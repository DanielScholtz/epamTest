package com.epam.automation;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
     * @param sortByDate is to reorder the search results by date
     */

    private WebDriver driver;
    private String country = "";
    private String city = "";
    @FindBy(xpath = "//*[contains(@src, 'logo_white-blue.svg')]")
    private WebElement logo;
    @FindBy(xpath = "//*[starts-with(@id,'select-box-location-')]")
    private WebElement locationArrow;
    @FindBy (xpath = "//*[contains(@id, 'all_locations')]")
    private WebElement defaultLocation;
    @FindBy (xpath = "//*[starts-with(@class, 'job-search__input')]")
    private WebElement keywordInput;
    @FindBy (xpath =  "/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[2]/div/div[1]/div[1]")
    private WebElement skillsTabArrow;
    @FindBy (xpath = "/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[3]/button")
    private WebElement searchButton;
    @FindBy (className = "search-result__item-name")
    private WebElement searchResult;
    @FindBy (css = "li.search-result__sorting-item:nth-child(2)")
    private WebElement sortByDate;

    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.epam.com");
        System.out.println(driver.getTitle());
        PageFactory.initElements(driver, this);
    }

    public void navigation(String Menu) {
        /**
         * Navigation between main menus
         * @param Menu getting a String from enums which refers to a main menu
         * Checking the site load correctly with the logo display
         * with the latest Epam website update we always set the location back to All location by default
         * since its get the location from our ip address
         */
        driver.findElement(By.xpath("//a[@href='" + Menu + "']")).click();
        System.out.println(driver.getTitle());
        assert(logo.isDisplayed());
        if (Menu.equals(MainMenu.CAREER_MENU)) {
            locationArrow.click();
            defaultLocation.click();
        }
    }

    public void keywords(String message) {
        keywordInput.sendKeys(message);
    }

    public void location(String country, String city) {
        /**
         * This method is stands for choosing the location
         * if we want to list all cities of the country we have to use the same name as the country
         * there is only one exception
         * when the country is United States, we should use USA at city variable
         */
        locationArrow.click();
        driver.findElement(By.cssSelector("[aria-label=\"" + country + "\"]")).click();
        if (city == country) {
            driver.findElement(By.xpath("//*[contains(@id, 'all_" + city + "')]")).click();
        } else {
            driver.findElement(By.xpath("//*[contains(@id, '" + city + "')]")).click();
        }
    }


    public void openSkillTab() {
        /**
         * Its for opening the skills tab
         * its useful when choosing more skills and want to avoid errors
         */
        skillsTabArrow.click();
    }

    public void skills(String skill) {
        driver.findElement(By.xpath("//*[@class='checkbox-custom-label' and contains(., '" + skill + "')]")).click();
    }

    public void clickOnSearchButton() {
        searchButton.click();
        WebDriverWait wait =  new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-result__item-name")));
    }

    public void showJobsWithLocation() {
        /**
         * Making a log of jobs with location from search result
         */
        String jobs;
        String loc = "";
        List<WebElement> job = driver.findElements(By.className("search-result__item-name"));
        for (WebElement ele : job) {
            jobs = ele.getText();

            List<WebElement> location = driver.findElements(By.className("search-result__location"));
            for (WebElement ele2 : location) {
                loc = ele2.getText();
            }
            System.out.println(jobs + " " + loc);
        }
    }

    public void sortJobsByDate() throws StaleElementReferenceException {
        /**
         * Sorting the jobs by date
         * the try/catch block is for not refreshing the page in time
         * sadly the test cannot be verified from id
         * because if they update a job it become the newest but with the old id
         */
        try {
            sortByDate.click();
            WebDriverWait wait =  new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.search-result__sorting-item:nth-child(1)")));
            List<WebElement> allElements = driver.findElements(
                    By.className("search-result__item-name")
            );
            for (WebElement element : allElements) {
                String idList = element.getAttribute("href");
                System.out.println(idList);
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("error in sortJobsByDate" +e);
        }
    }


    public void checkingResult(String text) {
        /**
         * Checking if the search result is correct by the description of the job
         */
        assert driver.getPageSource().contains(text);
    }


    @AfterMethod
    public void closePage() {
        driver.close();
    }
}