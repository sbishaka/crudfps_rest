/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.database.rest.dto;

/**
 *
 * @author USER
 */
public class Dto_Info {
    private final String url;
    private final String func;

    public Dto_Info( String url, String func ) {
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
