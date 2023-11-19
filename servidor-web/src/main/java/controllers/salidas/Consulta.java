/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;

import webservice.DatatypeConfigurationException_Exception;
import webservice.DtActividadesCollectionWS;
import webservice.DtSalidaTuristicaWS;
import webservice.DtSalidasCollectionWS;
import webservice.WSActividadController;
import webservice.WSActividadControllerService;
import webservice.WSSalidaController;
import webservice.WSSalidaControllerService;

/**
 *
 * @author diego
 */
public class Consulta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DatatypeConfigurationException_Exception {
        
        /*si esta en mobile, solo puede ver la consulta en caso de que sea turista, el flujo
        de mobile solo permite que si un usuario este logeado es porque es turista*/
        String userType = (String) request.getSession().getAttribute("sessionType");
        if (request.getHeader("User-Agent").toLowerCase().contains("mobile") && isNull(userType)) {
            response.sendError(403); 
            return;
        }
        
        
        WSSalidaControllerService salidaController = new WSSalidaControllerService();
        WSSalidaController salidaPort = salidaController.getWSSalidaControllerPort();
        
        String nombreSalida = request.getParameter("nombre");
        if (!isNull(nombreSalida)) {
            DtSalidaTuristicaWS salida = salidaPort.obtenerSalidaTuristica(nombreSalida);
            request.setAttribute("salida", salida);
            if (!isNull(salida.getImagen())) {
                request.setAttribute("imagenSalida", Base64.getEncoder().encodeToString(salida.getImagen()));
            } else {
                request.setAttribute("imagenSalida", "");
            }
            
            request.getRequestDispatcher("/WEB-INF/salidas/consultaIndividual.jsp").
                forward(request, response);
            return;
        }
        
        WSActividadControllerService actividadController = new WSActividadControllerService();
        WSActividadController actividadPort = actividadController.getWSActividadControllerPort();
        
        String departamento = request.getParameter("departamento");
        String actividad = request.getParameter("actividad");
        String salida = request.getParameter("salida");
        DtActividadesCollectionWS actividades = new DtActividadesCollectionWS();
        DtSalidasCollectionWS salidas = new DtSalidasCollectionWS();
        DtSalidaTuristicaWS selectedSalida = null;
        String imagen = "";
        
        if (departamento != null) {
            actividades = actividadPort.obtenerActividadesTuristicasPorDepartamento(departamento);
            
            if (actividad != null) {
                salidas = salidaPort.obtenerSalidasTuristicas(actividad);
                
                if (salida != null) {
                    selectedSalida = salidaPort.obtenerSalidaTuristica(salida);
                    if (!isNull(selectedSalida.getImagen())) {
                        imagen = Base64.getEncoder().encodeToString(selectedSalida.getImagen());   
                    }
                }
            }
        }
        
        
        request.setAttribute("actividades", actividades);
        request.setAttribute("departamentos", actividadPort.obtenerDepartamentos());
        request.setAttribute("salidas", salidas);
        request.setAttribute("selectedSalida", selectedSalida);
        request.setAttribute("imagenSalida", imagen);
        
        request.getRequestDispatcher("/WEB-INF/salidas/consulta.jsp").
                forward(request, response);
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
        } catch (DatatypeConfigurationException_Exception ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DatatypeConfigurationException_Exception ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
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
