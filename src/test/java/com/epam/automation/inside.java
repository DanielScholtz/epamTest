package com.epam.automation;

import com.epam.automation.enums.Menu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class inside {
    public WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.epam.com");
        System.out.println(driver.getTitle());
    }

    public void navigation(String Menu) {
        driver.findElement(By.xpath("//a[@href='" + Menu + "']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
    }

    public void keywords(String message) {
        WebElement keyword = driver.findElement(By.xpath("//*[starts-with(@class, 'job-search__input')]"));
        keyword.sendKeys(message);
    }

    public void location(String country, String city) {
        driver.findElement(By.xpath("//*[starts-with(@id,'select-box-location-')]")).click();
        driver.findElement(By.cssSelector("[aria-label=\"" + country + "\"]")).click();
        if (city == country) {
            driver.findElement(By.xpath("//*[contains(@id, 'all_" + city + "')]")).click();
        } else {
            driver.findElement(By.xpath("//*[contains(@id, '" + city + "')]")).click();
        }
    }


    public void openSkillTab() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[2]/div/div[1]/div[1]")).click();
    }

    public void skills(String skill) {
        WebElement check = driver.findElement(By.xpath("//*[@class='checkbox-custom-label' and contains(., '" + skill + "')]"));
        check.click();
    }

    public void searchButton() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[3]/button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void showJobsWithLocation() {
        String jobs = "";
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

    public void checkingResult(String text) {
        assert driver.getPageSource().contains(text);
    }


    @AfterClass
    public void closePage() {
        driver.close();
    }
}
