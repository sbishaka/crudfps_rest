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
public class Dto_Student_Update extends Dto_Entry{
    
    private String id;
    private Dto_Student dto_student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dto_Student getDto_student() {
        return dto_student;
    }

    public void setDto_student(Dto_Student dto_student) {
        this.dto_student = dto_student;
    }
    
}
