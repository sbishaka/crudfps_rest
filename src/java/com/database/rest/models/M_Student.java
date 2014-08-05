package com.database.rest.models;

import com.database.hibernate.Student;

public class M_Student extends M_Entry{
    
    private String id;
    private String name;

    public M_Student() 
    {
    
    }
    
    public M_Student( Student student){
        
            try{
                this.name = (isGarbage(student.getName()))? "":student.getName()+"";
            }catch(NullPointerException npe)
            {
                this.name = "";
            }
            
            try{
            this.id = (isGarbage(student.getId()))? "":student.getId()+"";
            }
            catch(NullPointerException npe)
            {
                this.id = "";
            }
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
        
        if( !isGarbage(this.getId()) )
        {
            buff.setId(Integer.parseInt(this.getId()
            ));
        }
         
        return buff;
    }
}
