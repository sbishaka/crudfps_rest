package com.database.crudfps;

import com.database.hibernate.Student;
import com.internal.utils.StringCaseTool;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

public class restCRUDFPS {

private final Session _SESS;

public restCRUDFPS(Session _SESS) {
	this._SESS = _SESS;
}

/*
#############################
    Begin Table : student
#############################    
*/

public void create_Student(Student student)
{
    Student ref = student;
    this.save(ref);
}

public void create_Student(List<Student> students)
{
    List<Student> refs = students;
    this.save_bulk(refs);
}

public Student create_Rtrn_Student(Student student)
{
    Student ref = student;
    this.save(ref);
    return ref;
}

public Student read_Student(int id)
{
    Student result = null;
    result = (Student)this.get(Student.class, id);
    return result;
}

public List<Student> read_All_Student()
{
    List<Student> result = this.get_All(Student.class);
    return result;
}

public void update_Student( int id, String _name)
{
    Student student = this.read_Student(id);
    student.setName((isGarbage(_name))? student.getName():_name);
    this.save(student);
}

public void delete_Student(int id)
{
    this.delete(this.read_Student(id));
}

public void delete_Student(Student student)
{
    Student ref = student;
    this.delete(ref);
}

public void delete_Student(List<Student> students)
{
    List<Student> refs = students;
    this.delete_bulk(refs);
}

public void print_Student(int id)
{
    this.print(this.read_Student(id));
}

public void print_All_Student()
{
    List<Student> students = this.read_All_Student();
    for (Student student : students) {
        this.print(student);
        System.out.println();
    }
}

/*
#############################
    End Table : student
#############################    
*/

/*
#############################
    Utility Functions
#############################    
*/

public void save(Object obj)
{
    this._SESS.beginTransaction();
    this._SESS.save(obj);
    this._SESS.getTransaction().commit();
}

public void save_bulk( List objs )
{
    /**
     TO DO: LOOK FOR SANE HIBERNATE BATCH OPERATION CODE
     */
    this._SESS.beginTransaction();

    for (Object obj : objs) {
      this._SESS.save(obj);  
    }
    
    this._SESS.getTransaction().commit();
    
}

public void delete(Object obj)
{
    this._SESS.beginTransaction();
    this._SESS.delete(obj);
    this._SESS.getTransaction().commit();
}

public void delete_bulk( List objs )
{
    /**
     TO DO: LOOK FOR SANE HIBERNATE BATCH OPERATION CODE
     */
    this._SESS.beginTransaction();

    for (Object obj : objs) {
      this._SESS.delete(obj);  
    }
    
    this._SESS.getTransaction().commit();
    
}

private Object get(Class type, Serializable srlzbl)
{
   return this._SESS.get(type, srlzbl);
}

private List get_All(Class type)
{
   return this._SESS.createCriteria(type).list();
}

private boolean isGarbage(String string)
{
    return string == null || string.isEmpty();
}

private boolean isGarbage(Object obj)
{
    return obj == null;
}

private void print(Object obj)
{
    Class<?> type = obj.getClass();
    Field[] fields = type.getDeclaredFields();
    
    for (Field field : fields) {
           
        if(!field.getType().getSimpleName().equalsIgnoreCase("Set"))
        {
        try {            
            System.out.println(field.getName() + "	: " + field.get(obj));            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(restCRUDFPS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {            
            try {                
                    Method method = type.getDeclaredMethod("get"+StringCaseTool.toCamelCase(field.getName()));
                    Object result = method.invoke(obj);
                    System.out.println(field.getName() + "	: " + result);
                    
            } catch (NoSuchMethodException ex1) {
                Logger.getLogger(restCRUDFPS.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(restCRUDFPS.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IllegalAccessException ex1) {
                Logger.getLogger(restCRUDFPS.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IllegalArgumentException ex1) {
                Logger.getLogger(restCRUDFPS.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (InvocationTargetException ex1) {
                Logger.getLogger(restCRUDFPS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }        
        }
        
    }
    
}

public void shutdown()
{
    this._SESS.clear();
    this._SESS.close();
}

public Session getSESS() {
    return _SESS;
}


}