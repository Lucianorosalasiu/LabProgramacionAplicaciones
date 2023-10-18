/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.usuarios;

import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import exceptions.EmptyFieldsException;
import exceptions.MyException;
import exceptions.NonEqualPasswordException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import org.apache.commons.io.IOUtils;

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

        if (!isNull(userType)) {
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

        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();

        String nickname = "";
        String name = "";
        String lastName = "";
        String email = "";

        String error = null;
        try {
            if (this.validateParameters(request)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                nickname = request.getParameter("nickname");
                name = request.getParameter("name");
                lastName = request.getParameter("lastName");
                email = request.getParameter("email");
                Date birthDate = dateFormat.parse(request.getParameter("birthdate"));
                String password = request.getParameter("password");
                
                Part imagePart = request.getPart("photo");
                byte[] newImage = null;
                if (imagePart != null) {
                    InputStream imageFile = imagePart.getInputStream();
                    newImage = IOUtils.toByteArray(imageFile);
                    IOUtils.closeQuietly(imageFile);
                }
                // ToDo: De momento no se carga la imagen. Falta arreglar esto
                String imagePath = "";
                
                String userType = request.getParameter("userType");
                switch (userType) {
                    case "proveedor":
                        String description = request.getParameter("description");
                        String websiteURL = request.getParameter("website").isEmpty()
                                ? ""
                                : request.getParameter("website");

                        DTProveedor nuevoProveedor = new DTProveedor(
                                nickname,
                                name,
                                lastName,
                                email,
                                birthDate,
                                password,
                                imagePath,
                                description,
                                websiteURL
                        );
                        controlador.altaProveedor(nuevoProveedor);
                        break;

                    case "turista":
                        String nacionality = request.getParameter("nacionality");
                        DTTurista nuevoTurista = new DTTurista(
                                nickname,
                                name,
                                lastName,
                                email,
                                birthDate,
                                password,
                                imagePath,
                                nacionality
                        );

                        controlador.altaTurista(nuevoTurista);
                        break;
                }
                request.setAttribute("successMessage", "Usuario dado de Alta!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                    .forward(request, response);
            }
            return;
        } catch (NonEqualPasswordException | EmptyFieldsException | MyException ex) {
            error = ex.getMessage();
        } catch (ParseException ex) {
            Logger.getLogger(AltaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* En caso de error, se mandan los atributos básicos previamente ingresados 
        en el formulario, para evitar cargarlos nuevamente*/
        request.setAttribute("errorMessage", error);
        request.setAttribute("nickname", nickname);
        request.setAttribute("name",name);
        request.setAttribute("lastName",lastName);
        request.setAttribute("email",email);
        request.getRequestDispatcher("/WEB-INF/usuarios/alta.jsp").
                forward(request, response);  
    }

    /**
     *
     * @param request servel request
     * @return true if all non optional fields are filled
     */
    private boolean validateParameters(HttpServletRequest request)
            throws EmptyFieldsException, NonEqualPasswordException {
        // 1. Comprobar si alguno de los campos obligatorios está vacío
        if (request.getParameter("nickname") == null
                || request.getParameter("name") == null
                || request.getParameter("lastName") == null
                || request.getParameter("password") == null
                || request.getParameter("confirmPassword") == null
                || request.getParameter("birthdate") == null
                || request.getParameter("email") == null) {
            throw new EmptyFieldsException("Algún campo ha quedado vacío");
        }

        // 2. Comprobar si se seleccionó un tipo de usuario
        String userType = request.getParameter("userType");
        if (userType == null) {
            throw new EmptyFieldsException("Algún campo ha quedado vacío");
        }

        // 3. Comprobar campos específicos según el tipo de usuario
        if ("proveedor".equals(userType) && request.getParameter("description") == null) {
            throw new EmptyFieldsException("Algún campo ha quedado vacío");
        }

        if ("turista".equals(userType) && request.getParameter("nacionality") == null) {
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
