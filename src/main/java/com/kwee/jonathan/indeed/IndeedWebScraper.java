package com.kwee.jonathan.indeed;

import com.kwee.jonathan.interfaces.Scraper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Lazy
@Service
public class IndeedWebScraper implements Scraper {

    private final String GECKO_DRIVER_PATH = "src/main/resources/geckodriver";
    private final String DRIVER_SYS_PROPERTY_NAME = "webdriver.gecko.driver";

    private final String ENTRY_URL = "https://www.indeed.com";
    private final String WHAT_SEARCHBOX_ID = "text-input-what";
    private final String WHERE_SEARCHBOX_ID = "text-input-where";
    private final String FINDJOBS_BTN_CLASS = "icl-Button";

    // Filters classes/ids
    private final String FILTER_DATEPOSTED = "filter-dateposted";

    private WebDriver firefoxDriver;

    @Override
    public void scrapeSite(String query, String location) {
        search(query, location);
    }

    private void search(String position, String location) {
        firefoxDriver.get(ENTRY_URL);
        WebElement whatSearchBox = firefoxDriver.findElement(By.id(WHAT_SEARCHBOX_ID));
        whatSearchBox.sendKeys(position);

        WebElement whereSearchBox = firefoxDriver.findElement(By.id(WHERE_SEARCHBOX_ID));
        whereSearchBox.clear();
        whereSearchBox.sendKeys(location);

        WebElement findJobButton = firefoxDriver.findElement(By.className(FINDJOBS_BTN_CLASS));
        findJobButton.click();
    }

    private void filterResults() {
        firefoxDriver.findElement(By.id(FILTER_DATEPOSTED))
            .click();

    }

    @PostConstruct
    private void setupSystemProperties() {
        Path path = FileSystems.getDefault().getPath(GECKO_DRIVER_PATH);
        System.setProperty(DRIVER_SYS_PROPERTY_NAME, path.toString());

        firefoxDriver = new FirefoxDriver();
    }




}
