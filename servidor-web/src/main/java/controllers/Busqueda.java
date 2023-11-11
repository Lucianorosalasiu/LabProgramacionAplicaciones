/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import com.sun.jdi.IntegerType;
import dataTypes.DTBusqueda;
import java.io.IOException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author ignfer
 */
public class Busqueda extends HttpServlet {
    
    public Busqueda() {
        super();
    }
    
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
        ArrayList<DTBusqueda> resultadoBusqueda = new ArrayList<>();
        String peticionBusqueda = (String) request.getParameter("peticionBusqueda");
        int tipoDeFiltro = 0;
        
        if(request.getParameter("tipoDeFiltro") != null){
            tipoDeFiltro = Integer.parseInt(request.getParameter("tipoDeFiltro"));
            request.setAttribute("test",tipoDeFiltro);
        }
        
        try {
            
            Fabrica fabrica = new Fabrica();
            IControlador controlador = fabrica.getInterface();
            resultadoBusqueda = controlador.obtenerBusqueda(peticionBusqueda);
            request.setAttribute("departamentos", controlador.obtenerDepartamentos());
            request.setAttribute("categorias", controlador.obtenerCategorias());
            
            if(tipoDeFiltro == 1){
                Collections.sort(resultadoBusqueda, Comparator.comparing(DTBusqueda::getNombre));
            }
            
            if(tipoDeFiltro == 2){
                resultadoBusqueda = controlador.ordenarBusquedaFecha(peticionBusqueda);
            }
            
        } catch(Exception e){
            
        }
        
        request.setAttribute("filtro", Integer.toString(tipoDeFiltro));
        request.setAttribute("peticionBusqueda",peticionBusqueda);
        request.setAttribute("resultadosBusqueda", resultadoBusqueda);
        request.getRequestDispatcher("/WEB-INF/busqueda/busqueda.jsp")
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
