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
| Option Name   | Description       | Long Txt | Short Txt | Values       |
| --------------|-------------------|----------|-----------|--------------|
| Site (req)    | Site to scrape    |--site    |--s        | "indeed"     |
| Query (req)   | Position to query |--query   |--q        | anything     |
| Location (req)| Location of job   |--location|--l        | a location   |
| Date Posted   | Age of job posts to query | --date | --d | 1,3,7,14 (defaults to 1 if value is not valid or not provided|