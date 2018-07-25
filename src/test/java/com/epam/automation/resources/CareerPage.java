package com.epam.automation.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CareerPage extends Page {
    private Logger log = LoggerFactory.getLogger(CareerPage.class);
    private WebDriverWait wait = new WebDriverWait(driver, 60);

    @FindBy(css = ".header__logo")
    public WebElement logo;
    @FindBy(css = "*[id^='select-box-location-'")
    private WebElement locationArrow;
    @FindBy(css = "*[id$='all_locations'")
    private WebElement defaultLocation;
    @FindBy(css = "input[class^='job-search__input']")
    private WebElement keywordInput;
    @FindBy(css = "*[class*='selected-params']")
    private WebElement skillsTabArrow;
    @FindBy(css = ".job-search__submit")
    private WebElement searchButton;
    @FindBy(css = ".search-result__item-name")
    private WebElement searchResult;
    @FindBy(css = "*[data-value*='time']")
    private WebElement sortByDate;

    public CareerPage(WebDriver driver) {
        super(driver);
        driver.get("https://www.epam.com/careers");
        PageFactory.initElements(driver, this);
        locationArrow.click();
        defaultLocation.click();
    }

    /**
     * Putting words to the keywords field from the message String
     *
     * @param message input to the keywords field
     */
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
            driver.findElement(By.cssSelector("*[id*='" + city + "']")).click();
        } else {
            driver.findElement(By.cssSelector("*[id*='" + city + "']")).click();
        }
    }

    /**
     * Its for opening the Skills tab
     * its useful when choosing more Skills and want to avoid errors
     */
    public void openSkillTab() {
        skillsTabArrow.click();
    }


    /**
     * It is not possible to use CSSSelector to find a text in a class so I had to use xpath
     *
     * @param skill to check any skill you need
     */
    public void skills(String skill) {
        driver.findElement(By.xpath("//*[@class='checkbox-custom-label' and contains(., '" + skill + "')]")).click();
    }

    /**
     * Clicks on the search button and waits till the search results loaded
     */
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
}
