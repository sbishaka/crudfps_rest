package com.database.rest.models;

import com.database.hibernate.Student;

public class M_Student_Update extends M_Entry{
    
    private final String id;
    private final M_Student m_student;

    public M_Student_Update(M_Student _m_student) {
        this.m_student = _m_student;
        this.id = _m_student.getId();
    }

    public String getId() {
        return id;
    }

    public M_Student getM_student() {
        return m_student;
    }

}
