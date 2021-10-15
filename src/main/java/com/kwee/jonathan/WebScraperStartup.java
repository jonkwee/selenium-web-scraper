package com.kwee.jonathan;

import static com.kwee.jonathan.constants.CommandLineOptions.SITE;
import static com.kwee.jonathan.constants.CommandLineOptions.QUERY;
import static com.kwee.jonathan.constants.CommandLineOptions.LOCATION;

import com.kwee.jonathan.constants.CommandLineOptions;
import com.kwee.jonathan.indeed.IndeedWebScraper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class WebScraperStartup implements ApplicationRunner {

    private final IndeedWebScraper indeedWebScraper;

    private final List<CommandLineOptions> requiredOptions = List.of(SITE, QUERY, LOCATION);

    /** Values **/
    private final String INDEED_SITE_VALUE = "indeed";

    public WebScraperStartup(@Lazy IndeedWebScraper indeedWebScraper) {
        this.indeedWebScraper = indeedWebScraper;
    }

    @Override
    public void run(ApplicationArguments args) {
        validateRequiredOptions(args);

        List<String> sites = extractValuesFromArgs(args, SITE);
        List<String> queries = extractValuesFromArgs(args, QUERY);
        List<String> locations = extractValuesFromArgs(args, LOCATION);

        String query = queries.get(0);
        String location = locations.get(0);

        System.out.printf("Scraping with query: %s and location: %s.%n", query, location);


        sites.forEach(s -> {
            if (INDEED_SITE_VALUE.equalsIgnoreCase(s)) {
                indeedWebScraper.scrapeSite(query, location);
            }
        });
    }

    private void validateRequiredOptions(ApplicationArguments args) {
        boolean hasMissingRequiredFields = false;

        for (CommandLineOptions option : requiredOptions) {
            if (!(args.containsOption(option.getLongTxt()) ^ args.containsOption(option.getShortTxt()))) {
                hasMissingRequiredFields = true;
                System.out.printf("Argument '%s' or '%s' is required! Please provide only one of the arguments!%n", option.getLongTxt(), option.getShortTxt());
            }
        }

        if (hasMissingRequiredFields) System.exit(0);

    }

    private List<String> extractValuesFromArgs(ApplicationArguments args, CommandLineOptions option) {
        return !CollectionUtils.isEmpty(args.getOptionValues(option.getLongTxt())) ?
                args.getOptionValues(option.getLongTxt()) : args.getOptionValues(option.getShortTxt());
    }


}
