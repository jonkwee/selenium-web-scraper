package com.kwee.jonathan.indeed;

import com.kwee.jonathan.interfaces.Scraper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
public class IndeedWebScraper implements Scraper {

    private final String INDEED_ENTRY_URL = "https://www.indeed.com";


    @Override
    public void scrapeSite() {

        WebDriver driver = new FirefoxDriver();

        driver.get(INDEED_ENTRY_URL);

        driver.quit();
    }


}
