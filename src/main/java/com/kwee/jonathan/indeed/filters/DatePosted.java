package com.kwee.jonathan.indeed.filters;

public enum DatePosted {

    LAST_24_HOURS(1),
    LAST_3_DAYS(3),
    LAST_7_DAYS(7),
    LAST_14_DAYS(14);

    private final int fromAge;

    DatePosted(int fromAge) {
        this.fromAge = fromAge;
    }
}
