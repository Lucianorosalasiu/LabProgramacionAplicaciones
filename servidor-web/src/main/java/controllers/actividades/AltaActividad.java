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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
        String errorMessage = null;
        
        if(validateParameters(request)){
            try{
                String departamento = request.getParameter("departamento");
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String duracion = request.getParameter("duracion");
                Float costo = Float.parseFloat(request.getParameter("costo"));
                String ciudad = request.getParameter("ciudad");
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date fecha = dateFormat.parse(request.getParameter("fecha"));
                
                List<Long> idCategoriasLong = new LinkedList<>();
                
                //obtengo todas las categorias seleccionadas
                if(request.getParameterValues("categoria") != null){
                    List<String> idCategoriasString = new LinkedList<>(Arrays.asList(request.getParameterValues("categoria")));
                    //parseo cada una de ellas de string a float
                    for(String c : idCategoriasString){
                        try {
                            idCategoriasLong.add(Long.parseLong(c));
                        } catch (Exception e) {

                        }
                    }
                }

                //obtengo idProveedor
                Long idProveedor = (Long)request.getSession().getAttribute("id");

                //obtengo idDepartamento
                Long idDepartamento = Long.parseLong(request.getParameter("departamento"));


                DTActividadTuristica nuevaActividadTuristica = new DTActividadTuristica(nombre, descripcion, duracion, costo, ciudad, fecha);
                 
                //debug de alta actividad
                //errorMessage = "depto: " + departamento + "| nombre: " + nombre +
                //        "| descr: " + descripcion + "| duracion: " + duracion +
                //        "| costo: " + costo + "| ciudad: " + ciudad +
                //        "| idProveedor: " + idProveedor + "| idDepartamento: " + idDepartamento +
                //        "| fecha: " + fecha;
                
                controlador.existeActividadTuristica(nombre);
                controlador.altaActividadTuristica(nuevaActividadTuristica, idDepartamento, idProveedor, idCategoriasLong);
                request.setAttribute("success", true);
            }catch(Exception e){
                errorMessage = e.getMessage();  
                request.setAttribute("errorMessage", errorMessage);
            }
        }
        
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
        request.setAttribute("categorias", controlador.obtenerCategorias());
        
        request.setAttribute("errorMessage", errorMessage);
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
                request.getParameter("categoria") != null) &&
                request.getParameter("fecha") != null;
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
