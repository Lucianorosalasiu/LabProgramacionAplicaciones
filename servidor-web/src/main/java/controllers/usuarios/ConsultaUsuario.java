package controllers.usuarios;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import static java.util.Objects.isNull;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import webExceptions.UsuarioNoEncontrado;
import webservice.DtUsuarioCollectionWS;
import webservice.DtUsuarioWrapper;

/**
 *
 * @author alexis
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
        
        /* Se utiliza el webservice para obtener las operaciones*/   
        webservice.WSUsuarioControllerService service = new webservice.WSUsuarioControllerService();
        webservice.WSUsuarioController portU = service.getWSUsuarioControllerPort(); 
        
        String emailUsuario = request.getParameter("usuario");
            
        if (emailUsuario == null) {
            // Si no se elige un usuario en específico, lista todos los usuarios.
            DtUsuarioCollectionWS usrs = portU.obtenerUsuarios();
            List<DtUsuarioWrapper> usersW = new ArrayList();
            usersW = usrs.getUsuariosW();
        
            List<Long> seguidos = new ArrayList();
            
            Long idUser = (Long) request.getSession().getAttribute("id");
            if(!isNull(idUser)) {
                seguidos = portU.obtenerSeguidos(idUser).getLista();
            }
            
            request.setAttribute("usuarios", usersW);
            request.setAttribute("seguidos", seguidos);
            request.getRequestDispatcher("/WEB-INF/usuarios/consulta.jsp")
                    .forward(request, response);
        } else {
            // Caso contrario, ve el perfil de un solo usuario
            DtUsuarioWrapper usr;
            try {
                usr = portU.obtenerUsuarioAlternativo(emailUsuario);
                if(usr == null){
                    throw new UsuarioNoEncontrado("¡ERROR! No se ha encontrado el usuario.");
                }

            } catch (UsuarioNoEncontrado ex) {
                response.sendError(404);
                return;
            }
            
            List<Long> seguidos = new ArrayList();
            List<Long> seguidores = new ArrayList();

            seguidos = portU.obtenerSeguidos(usr.getId()).getLista();
            seguidores = portU.obtenerSeguidores(usr.getId()).getLista();

            request.setAttribute("usuario", usr);
            request.setAttribute("seguidos", seguidos);
            request.setAttribute("seguidores", seguidores);
            
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
