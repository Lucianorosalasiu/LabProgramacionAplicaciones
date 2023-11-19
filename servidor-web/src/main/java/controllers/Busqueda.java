/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import webservice.DtBusquedaCollectionWS;
import webservice.DtBusquedaWS;
import webservice.WSActividadController;
import webservice.WSActividadControllerService;

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
        DtBusquedaCollectionWS resultadoBusqueda = new DtBusquedaCollectionWS();
        String peticionBusqueda = (String) request.getParameter("peticionBusqueda");
        int tipoDeFiltro = 0;
        
        if(request.getParameter("tipoDeFiltro") != null){
            tipoDeFiltro = Integer.parseInt(request.getParameter("tipoDeFiltro"));
            request.setAttribute("test",tipoDeFiltro);
        }
        
        try {
            
            WSActividadControllerService actividadController = new WSActividadControllerService();
            WSActividadController actividadPort = actividadController.getWSActividadControllerPort();
            
            request.setAttribute("departamentos", actividadPort.obtenerDepartamentos());
            request.setAttribute("categorias", actividadPort.obtenerCategorias());
            
            resultadoBusqueda = actividadPort.obtenerBusqueda(peticionBusqueda);
            
            if(request.getParameter("departamento") != null){
                String departamento = request.getParameter("departamento");
                resultadoBusqueda = actividadPort.ordenarBusquedaDepartamento(peticionBusqueda,departamento);

                request.setAttribute("departamento",departamento);
            }
            
            if(request.getParameter("categoria") != null){
                String categoria = request.getParameter("categoria");
                resultadoBusqueda = actividadPort.ordenarBusquedaCategoria(peticionBusqueda,categoria);
                request.setAttribute("categoria",categoria);
            }
            
            if(tipoDeFiltro == 1){
                Collections.sort(resultadoBusqueda.getResultadosBusqueda(), Comparator.comparing(DtBusquedaWS::getNombre));
            }
            
            if(tipoDeFiltro == 2){
                DtBusquedaCollectionWS resultadosSinFiltrar = actividadPort.ordenarBusquedaFecha(peticionBusqueda);
                DtBusquedaCollectionWS listaAuxiliar = new DtBusquedaCollectionWS();
                for(DtBusquedaWS dtb : resultadosSinFiltrar.getResultadosBusqueda()){
                    if(request.getParameter("departamento") != null &&
                            dtb.getDepartamento().contains(request.getParameter("departamento"))){
                        listaAuxiliar.getResultadosBusqueda().add(dtb);
                    }
                    if(request.getParameter("categoria") != null &&
                            dtb.getCategorias().contains(request.getParameter("categoria"))){
                        listaAuxiliar.getResultadosBusqueda().add(dtb);
                    }
                    if(request.getParameter("departamento") == null &&
                        request.getParameter("categoria") == null){
                    listaAuxiliar.getResultadosBusqueda().add(dtb);
                    }
                }
                
                resultadoBusqueda = listaAuxiliar;
            }
            
        } catch(Exception e){
            e.printStackTrace();
            response.sendError(500);
            return;
        }
        
        //request.setAttribute("filtro", Integer.toString(tipoDeFiltro));
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
