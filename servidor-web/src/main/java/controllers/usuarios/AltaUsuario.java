package controllers.usuarios;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;

import webExceptions.EmptyFieldsException;
import webExceptions.NonEqualPasswordException;
import webservice.DtProveedorWS;
import webservice.DtTuristaWS;
import webservice.MyException_Exception;
import webservice.ParseException_Exception;


/**
 *
 * @author alexis
 */
@MultipartConfig
public class AltaUsuario extends HttpServlet {

    public AltaUsuario() {
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
        String userType = (String) request.getSession().getAttribute("sessionType");

        if (!isNull(userType) || request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/usuarios/alta.jsp")
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

        String nickname = "";
        String name = "";
        String lastName = "";
        String email = "";
        String birthdate = "";    
        
        String error = null;
        try {
            if (this.validateParameters(request)) {
                /* Se almacena la fecha en formato string por si llega a haber
                un error */ 
                birthdate = request.getParameter("birthdate");
                
                SimpleDateFormat datePickerFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date birthDateParsed = datePickerFormat.parse(request.getParameter("birthdate"));

                
                nickname = request.getParameter("nickname");
                name = request.getParameter("name");
                lastName = request.getParameter("lastName");
                email = request.getParameter("email");
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
                
                
                String userType = request.getParameter("userType");
                switch (userType) {
                    case "proveedor":
                        String description = request.getParameter("description");
                        String websiteURL = request.getParameter("website").isBlank()
                                ? ""
                                : request.getParameter("website");
                        
                        DtProveedorWS nuevoProveedor = new DtProveedorWS();
                        nuevoProveedor.setNickname(nickname);
                        nuevoProveedor.setName(name);
                        nuevoProveedor.setLastName(lastName);
                        nuevoProveedor.setEmail(email);
                        nuevoProveedor.setBirthDate(datePickerFormat.format(birthDateParsed));
                        nuevoProveedor.setPassword(password);
                        nuevoProveedor.setImagePath("");
                        nuevoProveedor.setPhoto(photo);
                        nuevoProveedor.setDescription(description);
                        nuevoProveedor.setWebsiteURL(websiteURL);
                                
                        portU.altaProveedor(nuevoProveedor);
                        break;

                    case "turista":
                        String nacionality = request.getParameter("nacionality");
                        DtTuristaWS nuevoTurista = new DtTuristaWS();
                        nuevoTurista.setNickname(nickname);
                        nuevoTurista.setName(name);
                        nuevoTurista.setLastName(lastName);
                        nuevoTurista.setEmail(email);
                        nuevoTurista.setBirthDate(datePickerFormat.format(birthDateParsed));
                        nuevoTurista.setPassword(password);
                        nuevoTurista.setImagePath("");
                        nuevoTurista.setPhoto(photo);
                        nuevoTurista.setNacionality(nacionality);
                                
                        portU.altaTurista(nuevoTurista);
                        break;
                }
                request.setAttribute("successMessage", "Usuario dado de Alta!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                    .forward(request, response);
            }
            return;
        } catch (MyException_Exception | NonEqualPasswordException | ParseException_Exception | EmptyFieldsException ex) {
            error = ex.getMessage();
        } catch (ParseException ex) {
            Logger.getLogger(AltaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* En caso de error, se cargan los atributos básicos previamente 
        ingresados en el form, para evitar escribirlos nuevamente cuando se 
        recargue la vista con el get.
        Además, se carga el mensaje de error.
        */
        
        request.setAttribute("errorMessage", error);
        request.setAttribute("nickname", nickname);
        request.setAttribute("name",name);
        request.setAttribute("lastName",lastName);
        request.setAttribute("email",email);
        request.setAttribute("birthdate",birthdate);
        
        this.doGet(request, response);
    }

    /**
     *
     * @param request servel request
     * @return true if all non optional fields are filled
     */
    private boolean validateParameters(HttpServletRequest request)
            throws EmptyFieldsException, NonEqualPasswordException {
        // 1. Comprobar si alguno de los campos obligatorios está vacío
        if (request.getParameter("nickname").isBlank()
                || request.getParameter("name").isBlank()
                || request.getParameter("lastName").isBlank()
                || request.getParameter("password").isBlank()
                || request.getParameter("confirmPassword").isBlank()
                || request.getParameter("birthdate").isBlank()
                || request.getParameter("email").isBlank()) {
            throw new EmptyFieldsException("Algún campo ha quedado vacío");
        }

        // 2. Comprobar si se seleccionó un tipo de usuario
        String userType = request.getParameter("userType");
        if (userType.isBlank()) {
            throw new EmptyFieldsException("Algún campo ha quedado vacío");
        }

        // 3. Comprobar campos específicos según el tipo de usuario
        if (userType.equals("proveedor") && request.getParameter("description").isBlank()) {
            throw new EmptyFieldsException("Algún campo ha quedado vacío");
        }

        if (userType.equals("turista") && request.getParameter("nacionality").isBlank()) {
            throw new EmptyFieldsException("Algún campo ha quedado vacío");
        }

        // 4. Comprobar si las contraseñas coinciden
        if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
            throw new NonEqualPasswordException("Las contraseñas no coinciden");
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
        return "Permite dar de alta en el sistema un usuario";
    }
}
