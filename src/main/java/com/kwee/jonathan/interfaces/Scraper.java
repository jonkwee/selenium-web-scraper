package com.kwee.jonathan.interfaces;

import com.kwee.jonathan.indeed.filters.DatePosted;

public interface Scraper {

    void scrapeSite(String query, String location, DatePosted datePosted);

}
