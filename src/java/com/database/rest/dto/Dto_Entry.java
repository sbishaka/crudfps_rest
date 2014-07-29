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
public class Dto_Entry {
    
    
    protected boolean isGarbage(Object obj)
    {
        return obj == null;
    }
    
    protected boolean isGarbage(String string)
    {
        return string == null || string.isEmpty();
    }    

}
