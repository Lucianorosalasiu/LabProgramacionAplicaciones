/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

import dataTypes.DTActividadTuristica;
import dataTypes.DTSalidaTuristica;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author diego
 */
public class Inscripcion extends HttpServlet {

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
        List<DTActividadTuristica> actividades = new ArrayList();
        List<DTSalidaTuristica> salidas = new ArrayList();
        
        if (departamento != null) {
            actividades = controlador.obtenerActividadesTuristicas(departamento);
            
            if (actividad != null) {
                salidas = controlador.obtenerSalidasTuristicas(actividad);
                
            }
        }
        
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
        request.setAttribute("actividades", actividades);
        request.setAttribute("salidas", salidas);
        request.getRequestDispatcher("/WEB-INF/salidas/inscripcion.jsp").
                forward(request, response);
        
        /*
        Logica una vez se implemente el login
        
        
        TipoUsuario tipoUsuario = (TipoUsuario) request.getSession().getAttribute("tipo");
        
        if (tipoUsuario == TipoUsuario.TURISTA) {

        
        } else {
            response.sendError(403); 
            request.getRequestDispatcher("/WEB-INF/errorPages/403.jsp")
                    .include(request, response);
        }*/
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
