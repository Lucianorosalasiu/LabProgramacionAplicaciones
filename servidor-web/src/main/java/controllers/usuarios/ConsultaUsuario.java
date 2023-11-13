package controllers.usuarios;

import java.util.List;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dataTypes.DTUsuario;
import webExceptions.UsuarioNoEncontrado;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author progav
 */
public class ConsultaUsuario extends HttpServlet {

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
        
        if (request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403);
            return;
        }
        
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        /* Se utiliza el webservice para obtener las operaciones*/   
//        webservice.WSUsuarioControllerService service = new webservice.WSUsuarioControllerService();
//        webservice.WSUsuarioController port = service.getWSUsuarioControllerPort(); 
        
        
        String emailUsuario = request.getParameter("usuario");

        if (emailUsuario == null) {
            // Si no se elige un usuario en específico, lista todos los usuarios.
            List<DTUsuario> usrs = controlador.obtenerUsuarios();

            request.setAttribute("usuarios", usrs);

            request.getRequestDispatcher("/WEB-INF/usuarios/consulta.jsp")
                    .forward(request, response);
        } else {
            // Caso contrario, ve el perfil de un solo usuario
            DTUsuario usr;
            try {
                usr = controlador.obtenerUsuarioAlternativo(emailUsuario);
                if(usr == null){
                    throw new UsuarioNoEncontrado("¡ERROR! No se ha encontrado el usuario.");
                }
            } catch (UsuarioNoEncontrado ex) {
                response.sendError(404);
                return;
            }
            
            // setea el usuario
            request.setAttribute("usuario", usr);

            request.getRequestDispatcher("/WEB-INF/usuarios/perfil.jsp").
                    forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet que muestra los usuarios del sistema o uno en específico";
    }
}
