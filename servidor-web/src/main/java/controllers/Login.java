/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;


import dataTypes.DTProveedor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import dataTypes.DTTurista;
import dataTypes.DTUsuario;

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
        /*si bien el nombre de la variable es nickname tambien
        contempla que en dicho campo ingrese el email del usuario*/
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String errorMessage = null;
        Boolean existeUsuario = false;
        
        /*realiza la logica del login solo si los campos contienen datos*/
        if(validateParameters(request)){
            try {
                Fabrica fabrica = new Fabrica();
                IControlador controlador = fabrica.getInterface();
                
                DTUsuario usuario = controlador.obtenerUsuarioAlternativo(nickname);
                if(usuario == null){
                    errorMessage = "Nombre de usuario o contraseña incorrectas.";
                    request.setAttribute("errorMessage", errorMessage);
                }else{
                    //falta agregar la validacion por contraseña
                    //&& usuario.verifyPassword(password, usuario.getPassword())
                    if(nickname.equals(usuario.getEmail()) ||
                            nickname.equals(usuario.getNickname())){
                        String sessionType = "N/A";

                        if(usuario instanceof DTTurista){
                            sessionType = "TURISTA";
                        }else if(usuario instanceof DTProveedor){
                            sessionType = "PROVEEDOR";
                        }
                        
                        request.getSession().setAttribute("sessionNickname", usuario.getNickname());
                        request.getSession().setAttribute("sessionEmail", usuario.getEmail());
                        request.getSession().setAttribute("sessionType", sessionType);
                        request.getSession().setAttribute("isLogged",true);
                    }else{
                        //errorMessage = "PassError | usuario.verifyPassword = " + Boolean.toString(usuario.verifyPassword(password, usuario.getPassword()))
                                //+ " | usuario.getPassword() = " +  usuario.getPassword() + " | password = " + password;
                        //request.setAttribute("errorMessage", errorMessage);
                    }
                }  
            } catch (Exception e) {
                errorMessage = e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
            }
        }
        
        /*cuando se termine con la logica o hayan campos vacios, se redirige*/
        request.getRequestDispatcher("/WEB-INF/login/login.jsp")
                    .forward(request, response);   
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

    

    
