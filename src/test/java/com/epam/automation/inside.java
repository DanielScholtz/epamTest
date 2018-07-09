package com.epam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class inside {
    public WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.epam.com");
        System.out.println(driver.getTitle());
    }

    public void navigation(String Menu) {
        // menüpontokra tud lépni enumból kiszedett címekkel, almenüket jelenleg nem raktam bele
        // az oldal sikeres betöltését azzal ellenőrzi, hogy megjelent e a bal felső sarokban lévő epam logó
        driver.findElement(By.xpath("//a[@href='" + Menu + "']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.xpath("//*[contains(@src, 'logo_white-blue.svg')]")).isDisplayed());
        /* Updatelve lett az Epam oldal, ami most már az országot és a várost automatikusan kitölti az általad szedett adatokból
        ezért előre beállítom, hogy mindig állítsa vissz alapértékre a locationt az egyszerűség kedvéért
         */
        if (Menu.equals(com.epam.automation.enums.Menu.CAREER)) {
            driver.findElement(By.xpath("//*[starts-with(@id,'select-box-location-')]")).click();
            driver.findElement(By.xpath("//*[contains(@id, 'all_locations')]")).click();
        }
    }

    public void keywords(String message) {
        WebElement keyword = driver.findElement(By.xpath("//*[starts-with(@class, 'job-search__input')]"));
        keyword.sendKeys(message);
    }

    public void location(String country, String city) {
        // ország, város kiválasztása, ha a városhoz ugyanazt írjuk, mint az ország, akkor az országban lévő összes munkát kilistázza
        // (ez alól kivétel Amerika, ahol USA-t kell írni az ország ismétlése helyett)
        driver.findElement(By.xpath("//*[starts-with(@id,'select-box-location-')]")).click();
        driver.findElement(By.cssSelector("[aria-label=\"" + country + "\"]")).click();
        if (city == country) {
            driver.findElement(By.cssSelector("[aria-label=\"" + country + "\"]")).click();
            driver.findElement(By.xpath("//*[contains(@id, 'all_" + city + "')]")).click();
        } else {
            driver.findElement(By.cssSelector("[aria-label=\"" + country + "\"]")).click();
            driver.findElement(By.xpath("//*[contains(@id, '" + city + "')]")).click();
        }
    }


    public void openSkillTab() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[2]/div/div[1]/div[1]")).click();
    }

    public void skills(String skill) {
        driver.findElement(By.xpath("//*[@class='checkbox-custom-label' and contains(., '" + skill + "')]")).click();
    }

    public void searchButton() {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/section/div/div[4]/form/div/div[3]/button")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void showJobsWithLocation() {
        //kilistázza a munkákat munkavégzés helyével együtt
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

    public void sortJobsByDate() {
        driver.findElement(By.cssSelector("li.search-result__sorting-item:nth-child(2)")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<WebElement> allElements = driver.findElements(
                By.className("search-result__item-name")
        );
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        for (WebElement element : allElements) {
            String idList = element.getAttribute("href");
            /*feltételeztem, hogy id szerint csökkenő sorrend lenne, ezzel ellenőrízhető a dátum szerinti sorrend, de vannak régebbi jobok
              amik frissítve lettek, így habár id szerint kisebbek, de mégis fentebb kerültek, ezért nem is bontottam tovább substringgal, hogy
              id-kat hasonlítson össze */
            System.out.println(idList);
        }
    }

    public void checkingResult(String text) {
        //megkeresi az oldalon található szövegben, hogy pontos-e a találat
        assert driver.getPageSource().contains(text);
    }


    @AfterMethod
    public void closePage() {
        driver.close();
    }
}
