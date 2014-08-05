package com.database.rest.models;

public class M_Entry {
    
    
    protected boolean isGarbage(Object obj)
    {
        return obj == null;
    }
    
    protected boolean isGarbage(String string)
    {
        return string == null || string.isEmpty();
    }    

}
