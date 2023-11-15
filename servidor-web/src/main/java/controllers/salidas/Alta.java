/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

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
import java.util.Date;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

import webservice.DtActividadesCollectionWS;
import webservice.DtSalidaTuristicaWS;
import webservice.MyException_Exception;
import webservice.ParseException_Exception;
import webservice.WSActividadController;
import webservice.WSActividadControllerService;
import webservice.WSSalidaController;
import webservice.WSSalidaControllerService;

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
        
        if (isNull(userType) || !userType.equals("PROVEEDOR") || request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403); 
            return;
        }
        
        WSSalidaControllerService salidaController = new WSSalidaControllerService();
        WSSalidaController salidaPort = salidaController.getWSSalidaControllerPort();
        
        WSActividadControllerService actividadController = new WSActividadControllerService();
        WSActividadController actividadPort = actividadController.getWSActividadControllerPort();
        
        
        String departamento = request.getParameter("departamento");
        DtActividadesCollectionWS actividades = new DtActividadesCollectionWS();
        
        if (departamento != null) {
            actividades = actividadPort.obtenerActividadesTuristicas(
                departamento, (Long) request.getSession().getAttribute("id")
            );
        }
        
        String error = null;
        
        if (validateParameters(request)) {
            SimpleDateFormat datePickerFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Part imagePart = request.getPart("imagen");
            byte[] newImage = null;
            if (imagePart != null) {
                InputStream imageFile = imagePart.getInputStream();
                newImage = IOUtils.toByteArray(imageFile);
                
                IOUtils.closeQuietly(imageFile);
            }
            
            Date fechaSalida = datePickerFormat.parse(request.getParameter("fechaSalida"));
            
            DtSalidaTuristicaWS dtSalidaTuristica = new DtSalidaTuristicaWS();
            dtSalidaTuristica.setNombre(request.getParameter("nombre"));
            dtSalidaTuristica.setLugar(request.getParameter("lugar"));
            dtSalidaTuristica.setCantidadMaxTuristas(Integer.parseInt(request.getParameter("cantidadMaxTuristas")));
            dtSalidaTuristica.setFechaSalida(dateFormat.format(fechaSalida));
            dtSalidaTuristica.setFechaAlta(dateFormat.format(new Date()));
            dtSalidaTuristica.setImagen(newImage);
            
            try {
                salidaPort.altaSalidaTuristica(
                                        dtSalidaTuristica, 
                                        request.getParameter("actividad")
                );
                
                request.setAttribute("successMessage", "Salida dada de Alta!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                        .forward(request, response);
                return;
            } catch (MyException_Exception | ParseException_Exception ex) {
                error = ex.getMessage();
            }
            
        }
        
        request.setAttribute("errorMessage", error);
        
        request.setAttribute("departamento", departamento);
        request.setAttribute("actividades", actividades);
        request.setAttribute("departamentos", actividadPort.obtenerDepartamentos());
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
