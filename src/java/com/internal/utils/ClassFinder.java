/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.internal.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class ClassFinder {
    
    public static List<Class> getClasses_netbeans(String packageName)
    {
        List<Class> result = new ArrayList<Class>();
        
        String pkg = packageName.replace(".", "/");
        
        File directory = new File("./build/classes/"+pkg);
        
        File[] files = directory.listFiles();
          
        for (File file : files) 
        {              
              if(file.getName().endsWith(".class"))
              {    
                  try {
                      result.add(Class.forName(packageName + "." + file.getName().replace(".class", "")));
                  } catch (ClassNotFoundException ex) {
                      Logger.getLogger(ClassFinder.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
                    
        }
          
        return result;
    }
    
    public static List<Class> getClasses_netbeans_web(String packageName)
    {
        List<Class> result = new ArrayList<Class>();
        
        String pkg = packageName.replace(".", "/");
        
        File directory = new File("./build/web/WEB-INF/classes/"+pkg);
        
        File[] files = directory.listFiles();
          
        for (File file : files) 
        {              
              if(file.getName().endsWith(".class"))
              {    
                  try {
                      result.add(Class.forName(packageName + "." + file.getName().replace(".class", "")));
                  } catch (ClassNotFoundException ex) {
                      Logger.getLogger(ClassFinder.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
                    
        }
          
        return result;
    }
}
