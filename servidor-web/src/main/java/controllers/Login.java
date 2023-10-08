/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dataTypes.DTUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.clases.Proveedor;
import logica.clases.Turista;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author ignfer
 */
public class Login extends HttpServlet {

    public Login() {
        super();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String nickname = request.getParameter("nickname");
        String error = null;
        
        if(validateParameters(request)){
            try {
                Fabrica fabrica = new Fabrica();
                IControlador controlador = fabrica.getInterface();

                DTUsuario usuario = controlador.obtenerUsuario(nickname);
                String sessionNickname = usuario.getNickname();
                String sessionEmail = usuario.getEmail();

                request.getSession().setAttribute("sessionNickname", sessionNickname);
                request.getSession().setAttribute("sessionEmail", sessionEmail);

            } catch (Exception e) {
                error = e.getMessage();
            }

            request.getRequestDispatcher("/WEB-INF/login/login.jsp")
                    .forward(request, response);
        }
        
    }
    
    private boolean validateParameters(HttpServletRequest request){
        return(request.getParameter("nickname") != null) &&
            (request.getParameter("password") != null);
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

    

    
