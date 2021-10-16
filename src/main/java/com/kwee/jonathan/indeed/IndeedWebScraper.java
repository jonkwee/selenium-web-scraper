package com.kwee.jonathan.indeed;

import com.kwee.jonathan.indeed.filters.DatePosted;
import com.kwee.jonathan.interfaces.Scraper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Lazy
@Service
public class IndeedWebScraper implements Scraper {

    private final String GECKO_DRIVER_PATH = "src/main/resources/geckodriver";
    private final String DRIVER_SYS_PROPERTY_NAME = "webdriver.gecko.driver";

    private final String ENTRY_URL = "https://www.indeed.com/jobs?q=%s&l=%s";
    private final String TAP_ITEM_CLASS = "tapItem";
    private final String IFRAME_ID = "vjs-container-iframe";
    private final String JOB_TITLE_CLASS = "jobsearch-JobInfoHeader-title";

    // Pop up
    private final String POP_UP_CLOSE_CLASS = "popover-x-button-close";

    // Filters classes/ids
    private final String FITLER_CLASS = "yosegi-FilterPill-dropdownListItemLink";
    private final String FILTER_DATEPOSTED_ID = "filter-dateposted";

    private WebDriver firefoxDriver;

    @Override
    public void scrapeSite(String query, String location, DatePosted datePosted) {
        search(query, location, datePosted);
    }

    private void search(String position, String location, DatePosted datePosted) {
        firefoxDriver.get(
                String.format(ENTRY_URL, position, location)
        );

        List<WebElement> filterOptions = firefoxDriver.findElements(By.className(FITLER_CLASS));
        // select date posted filters
        firefoxDriver.findElement(By.id(FILTER_DATEPOSTED_ID)).click();
        filterOptions.stream()
                .filter(e -> datePosted.getText().equalsIgnoreCase(e.getText()))
                .findFirst()
                .ifPresent(WebElement::click);

        // Handle potential pop up
        try {
            Thread.sleep(2000);
            firefoxDriver.findElement(By.className(POP_UP_CLOSE_CLASS)).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> jobCards = firefoxDriver.findElements(By.className(TAP_ITEM_CLASS));
        for (WebElement card : jobCards) {
            card.click();
            WebElement jobInfoElement = findElementInIFrame(JOB_TITLE_CLASS,
                    identifier -> firefoxDriver.findElement(By.className(identifier)));
        }
    }


    private <T, R> R findElementInIFrame(T elementIdentifier, Function<T, R> executableFunction) {
        firefoxDriver.switchTo()
                .frame(firefoxDriver.findElement(By.id(IFRAME_ID)));
        R result = executableFunction.apply(elementIdentifier);
        firefoxDriver.switchTo().defaultContent();
        return result;
    }

    @PostConstruct
    private void setupSystemProperties() {
        Path path = FileSystems.getDefault().getPath(GECKO_DRIVER_PATH);
        System.setProperty(DRIVER_SYS_PROPERTY_NAME, path.toString());

        firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
    }




}
