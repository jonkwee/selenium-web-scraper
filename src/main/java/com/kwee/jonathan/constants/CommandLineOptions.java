package com.kwee.jonathan.constants;

public enum CommandLineOptions {

    SITE("site", "s"),
    QUERY("query", "q"),
    LOCATION("location", "l");

    private String longTxt;
    private String shortTxt;

    CommandLineOptions(String longTxt, String shortTxt) {
        this.longTxt = longTxt;
        this.shortTxt = shortTxt;
    }

    public String getLongTxt() { return this.longTxt; }
    public String getShortTxt() { return this.shortTxt; }
}
