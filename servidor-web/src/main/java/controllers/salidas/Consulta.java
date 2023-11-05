/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

import dataTypes.DTActividadTuristica;
import dataTypes.DTSalidaTuristica;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import static java.util.Objects.isNull;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

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
            throws ServletException, IOException {
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        String departamento = request.getParameter("departamento");
        String actividad = request.getParameter("actividad");
        String salida = request.getParameter("salida");
        List<DTActividadTuristica> actividades = new ArrayList();
        List<DTSalidaTuristica> salidas = new ArrayList();
        DTSalidaTuristica selectedSalida = null;
        String imagen = "";
        
        if (departamento != null) {
            actividades = controlador.obtenerActividadesTuristicas(departamento);
            
            if (actividad != null) {
                salidas = controlador.obtenerSalidasTuristicas(actividad);
                
                if (salida != null) {
                    selectedSalida = controlador.obtenerSalidaTuristica(salida);
                    if (!isNull(selectedSalida.getImagen())) {
                        imagen = Base64.getEncoder().encodeToString(selectedSalida.getImagen());   
                    }
                }
            }
        }
        
        
        request.setAttribute("actividades", actividades);
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
