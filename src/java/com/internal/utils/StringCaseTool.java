/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.internal.utils;

/**
 *
 * @author USER
 */
public class StringCaseTool {
    
    public static String toCamelCase(String word)
    {
        String lhs = word.charAt(0)+"";
        String rhs = word.substring(1);
        return lhs.toUpperCase() + rhs;
    }
    
}
