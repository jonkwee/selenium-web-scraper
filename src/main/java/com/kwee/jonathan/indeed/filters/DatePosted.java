package com.kwee.jonathan.indeed.filters;

import javax.annotation.Nullable;

public enum DatePosted {

    LAST_24_HOURS("Last 24 hours"),
    LAST_3_DAYS("Last 3 days"),
    LAST_7_DAYS("Last 7 days"),
    LAST_14_DAYS("Last 14 days");

    private final String text;

    DatePosted(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Nullable
    public static DatePosted convertInputToEnum(String input) {
        if ("1".equals(input)) return LAST_24_HOURS;
        else if ("3".equalsIgnoreCase(input)) return LAST_3_DAYS;
        else if ("7".equalsIgnoreCase(input)) return LAST_7_DAYS;
        else if ("14".equalsIgnoreCase(input)) return LAST_14_DAYS;
        else return LAST_24_HOURS;
    }
}
