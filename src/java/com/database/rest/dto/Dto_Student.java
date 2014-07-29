/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.database.rest.dto;

import com.database.hibernate.Student;

/**
 *
 * @author USER
 */
public class Dto_Student extends Dto_Entry{
    
    private String id;
    private String name;

    public Dto_Student() 
    {
    
    }
    
    public Dto_Student(Student student){
        this.name = student.getName();
        this.id = (isGarbage(student.getId()))? "":student.getId()+"";
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Student toHib()
    {
        Student buff = new Student();
        buff.setName(this.getName());
        
        if( !isGarbage(
                this.getId()) )
        {
            buff.setId(Integer.parseInt(
                    this.getId()
            ));
        }
        
        return buff;
    }
}
