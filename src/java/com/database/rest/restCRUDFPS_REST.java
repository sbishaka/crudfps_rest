/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.database.rest;

import com.database.crudfps.restCRUDFPS;
import com.database.hibernate.Student;
import com.database.rest.dto.Dto_Info;
import com.database.rest.dto.Dto_Student;
import com.database.rest.dto.Dto_Student_Update;
import com.database.rest.routes.R_routes;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author USER
 */
public class restCRUDFPS_REST {
    
    private final restCRUDFPS _SYS;
    private final Gson JSON = new Gson();

    public restCRUDFPS_REST(Session _SESS) {
        this._SYS = new restCRUDFPS(_SESS);
    }
    
    public boolean create_Student(String _dto_student)
    {
        Dto_Student dto_student = JSON.fromJson(_dto_student, Dto_Student.class);
        return create_Student(dto_student);
    }
    
    public boolean create_Student(Dto_Student dto_student)
    {
        Student student = dto_student.toHib();
        this._SYS.create_Student(student);
        return true;
    }

    public boolean create_Student(List<Dto_Student> _dto_students)
    {
        List<Dto_Student> dto_students = _dto_students;
        List<Student> students = new ArrayList<>(dto_students.size());
        for (Dto_Student dto_student : dto_students) {
            students.add(dto_student.toHib());
        }
        _SYS.save_bulk(students);
        return true;
    }
    
    public String read_Student(int id)
    {
        Dto_Student dto_student = new Dto_Student(this._SYS.read_Student(id));
        return this.JSON.toJson(dto_student);
    }

    public String read_All_Student()
    {
        List<Student> students = this._SYS.read_All_Student();
        List<Dto_Student> dto_students = new ArrayList<>(students.size());
        
        for (Student student : students) {
            dto_students.add(new Dto_Student(student));
        }
        
        return this.JSON.toJson(dto_students);
    }

    public void read_All_Student(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print(read_All_Student());
    }    
    
    public boolean update_Student( Dto_Student_Update _dto_student_update )
    {
        Dto_Student_Update dto_student_update = _dto_student_update;
        Integer id = Integer.parseInt(dto_student_update.getId());
        Student student = dto_student_update.getDto_student().toHib();
        this._SYS.update_Student(id, student.getName());
        return true;
    }
    
    public boolean delete_Student(int _id)
    {
        this._SYS.delete_Student(_id);
        return true;
    }
    
    public boolean delete_Student(Student _student)
    {
        this._SYS.delete_Student(_student);
        return true;
    }

    public boolean delete_Student(List<Student> _students)
    {
        this._SYS.delete_Student(_students);
        return true;
    }
    
    public void shutdown()
    {
        this._SYS.shutdown();
    }    

    public void print_info(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
       List<Dto_Info> info = new ArrayList<Dto_Info>();       
       
       info.add(new Dto_Info("/"+R_routes.route_read_All_Student, "read_All_Student()"));
       
       response.getWriter().print(JSON.toJson(info));
       
     }   
    
}
