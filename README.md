# Selenium Web Scraper for Job Postings

## Overview
This maven project makes use of Selenium to access and extract information from job sites.
The intention is to scrape job sites for data which can be further
transformed and analyzed in further downstream processes.

## Compile 
`mvn clean install` to compile the project into a jar file.

## Run
`java -jar target/selenium-web-scraper.jar` to execute the jar.

### Options
| Option Name   | Description       | Long Txt | Short Txt |
| --------------|-------------------|----------|-----------|
| Site (req)    | Site to scrape    |--site    |--s        |
| Query (req)   | Position to query |--query   |--q        |
| Location (req)| Location of job   |--location|--l        |