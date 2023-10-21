/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.usuarios;

import dataTypes.DTUsuario;
import exceptions.UsuarioNoEncontrado;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import static java.util.Objects.isNull;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author progav
 */
public class ModificacionUsuario extends HttpServlet {
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
        
        String userType = (String) request.getSession().getAttribute("sessionType");
        String queryUser = request.getParameter("usuario");
        /* Si no hay usuario logueado o no se pasa un usuario a modificar en 
        la query, no es posible editar el perfil */
        if (isNull(userType) || isNull(queryUser)) {
            response.sendError(403);
            return;
        }
        String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
        String sessionNickname = (String) request.getSession().getAttribute("sessionNickname");
        
        /* El usuario logueado no puede editar un perfil que no es el suyo. */
        if (
            !queryUser.equals(sessionEmail)
            && !queryUser.equals(sessionNickname)
        ){
            response.sendError(403);
            return;
        }
        
        /* Si el usuario logueado coincide con el perfil a editar */
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();

        DTUsuario usr = controlador.obtenerUsuarioAlternativo(queryUser);

        // Se setea el usuario
        request.setAttribute("usuario", usr);

        request.getRequestDispatcher("/WEB-INF/usuarios/modificacion.jsp")
                .forward(request, response);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
