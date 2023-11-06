/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author progav
 */
public class Home extends HttpServlet {

    public Home() {
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

        /* WebService Usuarios: */
        webservice.WSUsuarioControllerService u = new webservice.WSUsuarioControllerService();
        webservice.WSUsuarioController portU = u.getWSUsuarioControllerPort();

        /* WebService Actividades: */
        webservice.WSActividadControllerService a = new webservice.WSActividadControllerService();
        webservice.WSActividadController portA = a.getWSActividadControllerPort();

        /* WebService Salidas: */
        webservice.WSSalidaControllerService s = new webservice.WSSalidaControllerService();
        webservice.WSSalidaController portS = s.getWSSalidaControllerPort();

        /* WebService Paquetes: */
        webservice.WSPaqueteControllerService p = new webservice.WSPaqueteControllerService();
        webservice.WSPaqueteController portP = p.getWSPaqueteControllerPort();
        
        
        String responsePingUsuarioWS = portU.ping();
        String responsePingActividadWS = portA.ping();
        String responsePingSalidaWS = portS.ping();
        String responsePingPaqueteWS = portP.ping();
        
        request.setAttribute("pingUsuario", responsePingUsuarioWS);
        request.setAttribute("pingActividad", responsePingActividadWS);
        request.setAttribute("pingSalida", responsePingSalidaWS);
        request.setAttribute("pingPaquete", responsePingPaqueteWS);
        
        request.getRequestDispatcher("/WEB-INF/home/home.jsp")
                .forward(request, response);
    }

}
