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
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.PropertyValueException;
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

    public void create_Student_S(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    String msg = request.getParameter("msg");
    try{
        create_Student_S(msg);
    }
    catch(NullPointerException npe)
    {
        Msg info = new Msg("create_Student_S", "failed because of empty (msg) parameter");

        response.setContentType("application/json");
        response.getWriter().print(JSON.toJson(info));
        return; 
    }
    catch( PropertyValueException pve )
    {
        Msg info = new Msg("create_Student_S", "failed because a property which shouldn't be null is null");

        response.setContentType("application/json");
        response.getWriter().print(JSON.toJson(info));
        return;         
    }
    catch(JsonSyntaxException jse)
    {
        Msg info = new Msg("create_Student_S", "failed because server recieved a malformed json string");

        response.setContentType("application/json");
        response.getWriter().print(JSON.toJson(info));
        return;                
    }

    Msg info = new Msg("create_Student_S", "success");

    response.setContentType("application/json");
    response.getWriter().print(JSON.toJson(info));

}

    
    public void create_Student_M(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String msg = request.getParameter("msg");
        try{
            create_Student_M(msg);
        }
        catch(NullPointerException npe)
        {
            Msg info = new Msg("create_Student_M", "failed because of empty (msg) parameter");

            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));
            return; 
        }
        catch( PropertyValueException pve )
        {
            Msg info = new Msg("create_Student_M", "failed because a property which shouldn't be null is null");

            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));
            return;         
        }
        catch(JsonSyntaxException jse)
        {
            Msg info = new Msg("create_Student_M", "failed because server recieved a malformed json string");

            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));
            return;                
        }
        
        Msg info = new Msg("create_Student_M", "success");
        
        response.setContentType("application/json");
        response.getWriter().print(JSON.toJson(info));
        
    }
    
    public boolean create_Student_S(String _dto_student)
    {
        Dto_Student dto_student = JSON.fromJson(_dto_student, Dto_Student.class);
        return create_Student(dto_student);
    }
    
    public boolean create_Student_M(String _dto_students)
    {
        List<Dto_Student> dto_students =  Arrays.asList(JSON.fromJson(_dto_students, Dto_Student[].class));
        return create_Student(dto_students);
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

    public void read_Student(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String _id = request
                    .getPathInfo()
                    .substring(1).replace(R_routes.route_read_Student, "")
                    .replace("/", "");
        
        int id = -1;
        
        try{
            id = Integer.parseInt(_id);
        }catch(NumberFormatException nfe)
        {
            sendTo(request, response, "/crud");
            return;
        }
        
        response.setContentType("application/json");
        response.getWriter().print(read_Student(id));
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
        response.setContentType("application/json");
        response.getWriter().print(read_All_Student());
    }    
    
    public void update_Student_S(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String msg = request.getParameter("msg");
        boolean result = false;
        try{
            result = update_Student_S(msg);
        }
        catch(NullPointerException npe)
        {
            Msg info = new Msg("update_Student_S", "failed because of empty (msg) parameter");
            npe.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));
            return; 
        }
        catch( PropertyValueException pve )
        {
            Msg info = new Msg("update_Student_S", "failed because a property which shouldn't be null is null");

            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));
            return;         
        }
        catch(JsonSyntaxException jse)
        {
            Msg info = new Msg("update_Student_S", "failed because server recieved a malformed json string");

            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));
            return;                
        }

        if(result)
        {
            Msg info = new Msg("update_Student_S", "success");
            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));
        }
        else
        {
            Msg info = new Msg("update_Student_S", "failed");
            response.setContentType("application/json");
            response.getWriter().print(JSON.toJson(info));        
        }
        

    }        
    
    
    public boolean update_Student_S( String _dto_student )
    {
        Dto_Student dto_student = JSON.fromJson(_dto_student, Dto_Student.class);
        Dto_Student_Update dto_student_update = new Dto_Student_Update(dto_student);        
        return update_Student(dto_student_update);
    }
    
    public boolean update_Student( Dto_Student_Update _dto_student_update )
    {
        Dto_Student_Update dto_student_update = _dto_student_update;
        Integer id = Integer.parseInt(dto_student_update.getId());
        Student student = dto_student_update.getDto_student().toHib();
        try{
            this._SYS.update_Student(id, student.getName());
        }
        catch(NullPointerException npe)
        {
            //Failed to find object attached to id passed
            return false;
        }
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

       info.add(new Dto_Info("/"+R_routes.route_create_Student_S+"?msg=:dto_student", "create_Student_S(:dto_student)"));
       info.add(new Dto_Info("/"+R_routes.route_create_Student_M+"?msg=:dto_students", "create_Student_M(:dto_students)"));
       
       info.add(new Dto_Info("/"+R_routes.route_read_All_Student, "read_All_Student()"));
       info.add(new Dto_Info("/"+R_routes.route_read_Student+"/:id", "read_Student(id)"));
       
       info.add(new Dto_Info("/"+R_routes.route_update_Student_S+"?msg=:dto_student", "update_Student_S(:dto_student)"));
       
       response.setContentType("application/json");
       response.getWriter().print(JSON.toJson(info));
       
     }   
    
    
    private void sendTo(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
            request.getRequestDispatcher(url).forward(request, response);
    }	
    
}
