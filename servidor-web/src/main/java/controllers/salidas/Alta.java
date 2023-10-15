/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

import dataTypes.DTActividadTuristica;
import dataTypes.DTSalidaTuristica;
import exceptions.MyException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
import model.TipoUsuario;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author diego
 */


@MultipartConfig
public class Alta extends HttpServlet {

    public Alta() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String userType = (String) request.getSession().getAttribute("sessionType");
        
        if (isNull(userType) || !userType.equals("PROVEEDOR")) {
            response.sendError(403); 
            return;
        }
        
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        String departamento = request.getParameter("departamento");
        List<DTActividadTuristica> actividades = new ArrayList();
        
        if (departamento != null) {
            actividades = controlador.obtenerActividadesTuristicas(
                departamento, (Long) request.getSession().getAttribute("id")
            );
        }
        
        String error = null;
        
        if (validateParameters(request)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            
            Part imagePart = request.getPart("imagen");
            byte[] newImage = null;
            if (imagePart != null) {
                InputStream imageFile = imagePart.getInputStream();
                newImage = IOUtils.toByteArray(imageFile);
                
                IOUtils.closeQuietly(imageFile);
            }
            
            DTSalidaTuristica dtSalidaTuristica = new DTSalidaTuristica(
                                            request.getParameter("nombre"),
                                            Integer.parseInt(request.getParameter("cantidadMaxTuristas")),
                                            dateFormat.parse(request.getParameter("fechaSalida")),
                                            request.getParameter("lugar"),
                                            new Date(),
                                            newImage
                                        );
            try {
                controlador.altaSalidaTuristica(
                                        dtSalidaTuristica, 
                                        request.getParameter("actividad")
                );
                
                request.setAttribute("successMessage", "Salida dada de Alta!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                        .forward(request, response);
                return;
            } catch (MyException ex) {
                error = ex.getMessage();
                
            }
        }
        
        request.setAttribute("errorMessage", error);
        
        request.setAttribute("departamento", departamento);
        request.setAttribute("actividades", actividades);
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
        request.getRequestDispatcher("/WEB-INF/salidas/alta.jsp").
                forward(request, response); 
            
    }
    
    private boolean validateParameters(HttpServletRequest request){
        return (request.getParameter("actividad") != null) &&
            (request.getParameter("nombre") != null) &&
            (request.getParameter("lugar") != null) &&
            (request.getParameter("fechaSalida") != null) &&
            (request.getParameter("cantidadMaxTuristas") != null);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
