package com.database.rest.routes;

import com.database.hibernateUtil.HibernateUtil;
import com.database.rest.restCRUDFPS_REST;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

@WebServlet(name = "R_restCRUDFPS_REST", urlPatterns = {"/crud/*"}, loadOnStartup = 1)
public class R_restCRUDFPS_REST extends HttpServlet {
    
    private Session _SESS;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        _SESS = HibernateUtil.getSessionFactory().openSession();
        restCRUDFPS_REST sys = new restCRUDFPS_REST(_SESS);
        
        String path = null;
        try{
            path = request.getPathInfo().substring(1);
        }catch(NullPointerException npe){}
        
        if( path == null || path.isEmpty() )
        {
            path = "info";
        }
        
        if( path.equals(R_routes.route_read_All_Student) )
        {
            sys.read_All_Student(request, response);
        }
        else if( path.startsWith(R_routes.route_read_Student) )
        {
            sys.read_Student(request, response);
        }
        else if(path.equals(R_routes.route_create_Student_S))
        {
            sys.create_Student_S(request, response);
        }
        else if(path.equals(R_routes.route_create_Student_M))
        {
            sys.create_Student_M(request, response);
        }
        else if(path.equals(R_routes.route_update_Student_S))
        {
            sys.update_Student_S(request, response);
        }
        else if(path.startsWith(R_routes.route_delete_Student_S))
        {
            sys.delete_Student_S(request, response);
        }
        else
        {
            sys.print_info(request, response);
        }
            
        _SESS.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
