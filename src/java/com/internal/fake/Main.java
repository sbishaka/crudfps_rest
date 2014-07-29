/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.internal.fake;

import com.database.hibernate.Student;
import com.database.hibernateUtil.HibernateUtil;
import com.database.rest.dto.Dto_Student;
import com.database.rest.dto.Dto_Student_Update;
import com.database.rest.restCRUDFPS_REST;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author USER
 */
public class Main {
    
    public static void main(String[] args)
    {
        main1();
    }
    
    public static void main1()
    {
      Session _SESS = HibernateUtil.getSessionFactory().openSession();
      restCRUDFPS_REST sys = new restCRUDFPS_REST(_SESS);
      
      Gson JSON = new Gson();
      String json = "{\"id\":\"1\",\"dto_student\":{\"name\":\"Bishaka Samuel\"}}";
      
      Dto_Student_Update up = JSON.fromJson(json, Dto_Student_Update.class);
      sys.update_Student(up);
      
      sys.shutdown();
      HibernateUtil.shutdown();

    }
        
}
