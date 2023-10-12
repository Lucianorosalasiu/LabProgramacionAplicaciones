/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.actividades;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import dataTypes.DTActividadTuristica;
import dataTypes.DTCategoria;
import java.util.Arrays;
import java.util.LinkedList;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author ignfer
 */
public class AltaActividad extends HttpServlet {

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
        String infoStr = "campos vacios";
        
        if(validateParameters(request)){
        
            String departamento = request.getParameter("departamento");
            List<DTActividadTuristica> actividades = new LinkedList<>();
        
            infoStr = "depto: " + departamento + "| nombre: " + request.getParameter("nombre") +
                    "| descr: " + request.getParameter("descripcion") + "| duracion: " + request.getParameter("duracion") +
                    "| costo: " + request.getParameter("costo") + "| ciudad: " + request.getParameter("ciudad") +
                    "| categorias: ";
        
            if(request.getParameterValues("categoria") != null){
                List<String> categorias = new LinkedList<>(Arrays.asList(request.getParameterValues("categoria")));
                for(String c : categorias){
                    infoStr += c;
                }
            }
            
            //falta
        //id departamento
        //id proveedor
        //lista de id categorias
        //controlador.altaActividadTuristica();
            
        }
        
        
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
        request.setAttribute("categorias", controlador.obtenerCategorias());
        
        request.setAttribute("info", infoStr);
        request.getRequestDispatcher("/WEB-INF/actividades/alta.jsp")
                    .forward(request, response);
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
    
    private boolean validateParameters(HttpServletRequest request){
        return(request.getParameter("departamento") != null &&
                request.getParameter("nombre") != null &&
                !request.getParameter("nombre").equals("") &&
                request.getParameter("descripcion") != null &&
                !request.getParameter("descripcion").equals("") &&
                request.getParameter("duracion") != null &&
                !request.getParameter("duracion").equals("") &&
                request.getParameter("costo") != null &&
                !request.getParameter("costo").equals("") &&
                request.getParameter("ciudad") != null &&
                !request.getParameter("ciudad").equals("") &&
                request.getParameter("categoria") != null);
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
