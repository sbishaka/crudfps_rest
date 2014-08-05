package com.database.rest.models;

public class M_Info {
    private final String url;
    private final String func;

    public M_Info(String url, String func ) {
        this.url = url;
        this.func = func;
    }

    public String getUrl() {
        return url;
    }

    public String getFunc() {
        return func;
    }
    
}
