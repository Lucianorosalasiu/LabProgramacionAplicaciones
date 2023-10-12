/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;



import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dataTypes.DTProveedor;
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
        String loginPassword = request.getParameter("password");
        String sessionType = "N/A";
        String errorMessage = null;
        
        /*realiza la logica del login solo si los campos contienen datos*/
        if(validateParameters(request)){
            try {
                Fabrica fabrica = new Fabrica();
                IControlador controlador = fabrica.getInterface();
                
                DTUsuario usuario = controlador.obtenerUsuarioAlternativo(nickname);
                //si no existe un usuario con esas credenciales
                if(usuario == null || !usuario.verifyPassword(loginPassword,usuario.getPassword())){                    
                    errorMessage = "Nombre de usuario o contraseña incorrectas.";
                    request.setAttribute("errorMessage", errorMessage);
                }else if(usuario.verifyPassword(loginPassword, usuario.getPassword())){  
                    //en caso de que si exista un usuario con esas credenciales
                    if(usuario instanceof DTTurista){
                        sessionType = "TURISTA";
                    }else if(usuario instanceof DTProveedor){
                        sessionType = "PROVEEDOR";
                    }
                    request.getSession().setAttribute("sessionNickname", usuario.getNickname());
                    request.getSession().setAttribute("sessionEmail", usuario.getEmail());
                    request.getSession().setAttribute("sessionType", sessionType);
                    request.getSession().setAttribute("isLogged",true);
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

    

    
