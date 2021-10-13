package com.kwee.jonathan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class WebScraperApplication {

    private static final String GECKO_DRIVER_PATH = "src/main/resources/geckodriver";
    private static final String DRIVER_SYS_PROPERTY_NAME = "webdriver.gecko.driver";

    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath(GECKO_DRIVER_PATH);
        System.setProperty(DRIVER_SYS_PROPERTY_NAME, path.toString());

        WebDriver driver = new FirefoxDriver();

        driver.get("https://artoftesting.com");

        driver.quit();
    }
}
