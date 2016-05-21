
package Servelet;

import Clases.Conexion;
import Clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Validar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
       String rut = request.getParameter("txtmod");
       String error="";
       
       
        if (rut.equals("") || rut == null) {
            
            error="Los campos estan vacios";
            request.getSession().setAttribute("myError", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
            
        }
        
        else{

          Usuario u = new Usuario(rut);
          Conexion cnn = new Conexion();
  
         if (cnn.Verificador(u)>0) {
            
            cnn.desconectar();
            request.getSession().setAttribute("mrut", rut);
            request.getRequestDispatcher("modificando.jsp").forward(request, response);    
            }
            
            else{
             
            cnn.desconectar();
            error="El rut no coincide!";
            request.getSession().setAttribute("myError", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
                      
            }
                      
            }
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
