/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.usuarios;

import dataTypes.DTProveedor;
import dataTypes.DTTurista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.util.Objects.isNull;

import dataTypes.DTUsuario;
import exceptions.EmptyFieldsException;
import exceptions.MyException;
import exceptions.NonEqualPasswordException;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();

        String nickname = "";
        String name = "";
        String lastName = "";
        String email = "";
        String birthdate = "";
                
        String error = null;
        try {
            if (this.validateParameters(request)) {
                birthdate = request.getParameter("birthdate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date birthDateParsed = dateFormat.parse(birthdate);
                nickname = request.getParameter("nickname");
                name = request.getParameter("name");
                lastName = request.getParameter("lastName");
                email = request.getParameter("email");
                String password = request.getParameter("password");
                
                byte[] photo = null;

                Part imagePart = request.getPart("photo");
                if (imagePart != null) {
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
s
                        DTProveedor nuevoProveedor = new DTProveedor(
                                nickname,
                                name,
                                lastName,
                                email,
                                birthDateParsed,
                                password,
                                "",
                                photo,
                                description,
                                websiteURL
                        );
                        controlador.actualizarUsuario(nuevoProveedor);
                        break;

                    case "TURISTA":
                        String nacionality = request.getParameter("nacionality");
                        DTTurista nuevoTurista = new DTTurista(
                                nickname,
                                name,
                                lastName,
                                email,
                                birthDateParsed,
                                password,
                                "",
                                photo,
                                nacionality
                        );

                        controlador.actualizarUsuario(nuevoTurista);
                        break;
                }
                request.setAttribute("successMessage", "El usuario fue modificado!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                    .forward(request, response);
            }
            return;
        } catch (NonEqualPasswordException | EmptyFieldsException | MyException ex) {
            error = ex.getMessage();
        } catch (ParseException ex) {
            Logger.getLogger(AltaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return;
        }
        
        /* En caso de error, se cargan los atributos básicos previamente 
        ingresados en el form, para evitar escribirlos nuevamente cuando se 
        recargue la vista con el get.
        Además, se carga el mensaje de error.
        */
        
        // Crear la URL con el parámetro de consulta
        String sessionEmail = (String) request.getSession().getAttribute("sessionEmail");
        String url = "/modificacionusuario?usuario=" + sessionEmail + 
            "&errorMessage=" + error;

        /* Redirigir a la nueva URL pasando el usuario que está modificando el 
        perfil y el error que se obtuvo*/
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
