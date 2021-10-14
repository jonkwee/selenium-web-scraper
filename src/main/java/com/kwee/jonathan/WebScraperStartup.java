package com.kwee.jonathan;

import com.kwee.jonathan.indeed.IndeedWebScraper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.nio.file.FileSystems;
import java.nio.file.Path;

@Component
public class WebScraperStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final String GECKO_DRIVER_PATH = "src/main/resources/geckodriver";
    private final String DRIVER_SYS_PROPERTY_NAME = "webdriver.gecko.driver";

    private final IndeedWebScraper indeedWebScraper;

    public WebScraperStartup(IndeedWebScraper indeedWebScraper) {
        this.indeedWebScraper = indeedWebScraper;

        setupSystemProperties();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        indeedWebScraper.scrapeSite();
    }

    private void setupSystemProperties() {
        Path path = FileSystems.getDefault().getPath(GECKO_DRIVER_PATH);
        System.setProperty(DRIVER_SYS_PROPERTY_NAME, path.toString());
    }
}
