package controllers.usuarios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import static java.util.Objects.isNull;
import org.apache.commons.io.IOUtils;

import webExceptions.EmptyFieldsException;
import webExceptions.NonEqualPasswordException;
import webservice.DtUsuarioWrapper;
import webservice.DtProveedorWS;
import webservice.DtTuristaWS;
import webservice.MyException_Exception;
import webservice.ParseException_Exception;



/**
 *
 * @author alexis
 */

@MultipartConfig
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
                
        /* Si no hay usuario logueado o se accede desde un celular,
        no es posible editar el perfil */
        if (isNull(userType) || request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403);
            return;
        }
        
        /*Si no se pasa un usuario a modificar como parámetro de la query,
        no es posible acceder al servlet. */
        if (isNull(queryUser)) {
            response.sendError(404);
            return;
        }
        
        String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
        String sessionNickname = (String) request.getSession().getAttribute("sessionNickname");
        /* El usuario logueado solo puede editar su propio perfil. */
        if (
            !queryUser.equals(sessionEmail)
            && !queryUser.equals(sessionNickname)
        ){
            response.sendError(403);
            return;
        }
        
        /* Si el usuario logueado coincide con el perfil a editar */
        /* Se utiliza el webservice para obtener las operaciones*/   
        webservice.WSUsuarioControllerService service = new webservice.WSUsuarioControllerService();
        webservice.WSUsuarioController portU = service.getWSUsuarioControllerPort();
        
        DtUsuarioWrapper usr = portU.obtenerUsuarioAlternativo(queryUser);

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

        /* Se utiliza el webservice para obtener las operaciones*/   
        webservice.WSUsuarioControllerService service = new webservice.WSUsuarioControllerService();
        webservice.WSUsuarioController portU = service.getWSUsuarioControllerPort();

        String error = null;
        try {
            if (this.validateParameters(request)) {
                String nickname = (String) request.getSession().getAttribute("sessionNickname");
                String email = (String) request.getSession().getAttribute("sessionEmail");
                
                String birthdate = request.getParameter("birthdate");               
                String name = request.getParameter("name");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                
                byte[] photo = null;

                Part imagePart = request.getPart("photo");
                if (imagePart != null 
                    && imagePart.getSubmittedFileName() != null 
                    && !imagePart.getSubmittedFileName().isEmpty()
                ){
                    InputStream imageFile = imagePart.getInputStream();
                    photo = IOUtils.toByteArray(imageFile);
                    IOUtils.closeQuietly(imageFile);
                }
                
                String userType = (String) request.getSession().getAttribute("sessionType");
                switch (userType) {
                    case "PROVEEDOR":
                        String description = request.getParameter("description");
                        String websiteURL = request.getParameter("website").isBlank()
                                ? ""
                                : request.getParameter("website");
                        
                        DtProveedorWS nuevoProveedor = new DtProveedorWS();
                        nuevoProveedor.setNickname(nickname);
                        nuevoProveedor.setName(name);
                        nuevoProveedor.setLastName(lastName);
                        nuevoProveedor.setEmail(email);
                        nuevoProveedor.setBirthDate(birthdate);
                        nuevoProveedor.setPassword(password);
                        nuevoProveedor.setImagePath("");
                        nuevoProveedor.setPhoto(photo);
                        nuevoProveedor.setDescription(description);
                        nuevoProveedor.setWebsiteURL(websiteURL);
                                
                        portU.modificarProveedor(nuevoProveedor);
                        break;

                    case "TURISTA":
                        String nacionality = request.getParameter("nacionality");
                        DtTuristaWS nuevoTurista = new DtTuristaWS();
                        nuevoTurista.setNickname(nickname);
                        nuevoTurista.setName(name);
                        nuevoTurista.setLastName(lastName);
                        nuevoTurista.setEmail(email);
                        nuevoTurista.setBirthDate(birthdate);
                        nuevoTurista.setPassword(password);
                        nuevoTurista.setImagePath("");
                        nuevoTurista.setPhoto(photo);
                        nuevoTurista.setNacionality(nacionality);
                                
                        portU.modificarTurista(nuevoTurista);
                        break;
                }
                request.setAttribute("successMessage", "El usuario fue modificado!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                    .forward(request, response);
            }
            return;
        } catch (NonEqualPasswordException | EmptyFieldsException | MyException_Exception | ParseException_Exception ex) {
            error = ex.getMessage();
        }
        
        /* En caso de error, se carga el mensaje de error como parámetro 
        del response. */
        
        /* Primero se codifica el mensaje de error para evitar que se vea mal */
        String encodedError = URLEncoder.encode(error, "UTF-8");
        
        /* Luego se crea la URL con el parámetro de consulta */
        String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
        String url = "/modificacionusuario?usuario=" + sessionEmail + 
            "&errorMessage=" + encodedError;

        /* Y por último se redirige a la nueva URL pasando el usuario que 
        se está intentando modificar el perfil y el error codíficado. */
        response.sendRedirect(request.getContextPath() + url);
    }        

    private boolean validateParameters(HttpServletRequest request)
            throws EmptyFieldsException, NonEqualPasswordException {
        // 1. Comprobar si alguno de los campos obligatorios está vacío
        if (request.getSession().getAttribute("sessionNickname") == null 
                || request.getSession().getAttribute("sessionEmail") == null 
                || request.getParameter("name").isBlank()
                || request.getParameter("lastName").isBlank()
                || request.getParameter("birthdate").isBlank()) {
            throw new EmptyFieldsException("Un campo obligatorio no se ha completado");
        }
        String userType = (String) request.getSession().getAttribute("sessionType");
        
        // 2. Comprobar campos específicos según el tipo de usuario
        if (userType.equals("PROVEEDOR") && request.getParameter("description").isBlank()) {
            throw new EmptyFieldsException("Un campo obligatorio no se ha completado");
        }

        if (userType.equals("TURISTA") && request.getParameter("nacionality").isBlank()) {
            throw new EmptyFieldsException("Un campo obligatorio no se ha completado");
        }
        
        // 3. Comprobar si las contraseñas coinciden
        if(!request.getParameter("password").isBlank() && !request.getParameter("confirmPassword").isBlank()){
            if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
                throw new NonEqualPasswordException("Las contraseñas no coinciden");
            }            
        }

        return true;
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
