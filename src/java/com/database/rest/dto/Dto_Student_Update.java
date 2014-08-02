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
    
    private final String id;
    private final Dto_Student dto_student;

    public Dto_Student_Update( Dto_Student _dto_student) {
        this.dto_student = _dto_student;
        this.id = _dto_student.getId();
    }

    public String getId() {
        return id;
    }

    public Dto_Student getDto_student() {
        return dto_student;
    }
    
}
