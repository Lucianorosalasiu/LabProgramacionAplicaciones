package controllers.usuarios;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservice.MyException_Exception;

/**
 *
 * @author alexis
 */
public class Unfollow extends HttpServlet {

    public Unfollow() {
        super();
    }
    
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
        
        // Se obtiene la URL del .jsp donde fue llamado el servlet
        String referer = request.getHeader("referer");

        // Se redirige de nuevo a dicha URL
        response.sendRedirect(referer);
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
        
        String userType = (String) request.getSession().getAttribute("sessionType");
        String queryUser = request.getParameter("usuario");
                
        /* Si no hay usuario logueado o se accede desde un celular,
        no es posible dejar de seguir a otro usuario */
        if (isNull(userType) || request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403);
            return;
        }
        
        /*Si no se pasa un usuario a dejar de seguir como parámetro de la query,
        no es posible acceder al servlet. */
        if (isNull(queryUser)) {
            response.sendError(404);
            return;
        }
        
        /* El usuario logueado no se puede dejar de seguir a sí mismo. */
        /*
        String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
        String sessionNickname = (String) request.getSession().getAttribute("sessionNickname");
        if (
            queryUser.equals(sessionEmail)
            || queryUser.equals(sessionNickname)
        ){

        }
        */
        Long userId = (Long) request.getSession().getAttribute("id");        
        if (userId.longValue() == Long.valueOf(queryUser).longValue()) {
            response.sendError(403);
            return; 
        }
        
        // Hacer post

        /* WebService Usuarios: */
        webservice.WSUsuarioControllerService u = new webservice.WSUsuarioControllerService();
        webservice.WSUsuarioController portU = u.getWSUsuarioControllerPort();   
        try {
            portU.dejarDeSeguirUsuario(
                userId, 
                Long.parseLong(queryUser)
            );     
        } catch (MyException_Exception ex) {
            response.sendError(500);
            return;
        }

        this.doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Permite dejar de seguir a un usuario";
    }
}
