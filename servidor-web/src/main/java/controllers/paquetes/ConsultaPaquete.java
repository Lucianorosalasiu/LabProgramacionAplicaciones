/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.paquetes;

import dataTypes.DTActividadTuristica;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author lucho
 */
public class ConsultaPaquete extends HttpServlet {

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
        
        String paquete = request.getParameter("paquetes");
        String actividad = request.getParameter("actividad");
         List<DTActividadTuristica> actividades = new ArrayList();
        DTActividadTuristica actividadCompleta = null;
        
        DTPaqueteActividadTuristica pa = null;
        String imagen = null;
        String categorias = "";
        LinkedHashSet<String> hash = new LinkedHashSet<String>();
        if (paquete != null) {
            pa = controlador.obtenerPaqueteCosto(paquete);
            actividades = controlador.obtenerActividadesRelacionadas(paquete);
            for (DTActividadTuristica a : actividades){
                DTActividadTuristica actividadCategoria = controlador.obtenerActividadTuristicaNull(a.getId());
                categorias += actividadCategoria.getCategoriasString();
            }
            String[] categoriasSeparadas = categorias.split("\\|");
            for(String c :categoriasSeparadas){
                hash.add(c);
            }
            if(actividad != null){
                actividadCompleta = controlador.obtenerActividadTuristica(actividad);
                
                
            }
        }
        
        request.setAttribute("actividades", actividades);
        request.setAttribute("actividad", actividadCompleta);
        request.setAttribute("categorias", hash);
        request.setAttribute("paqueteEnteros", pa);
        request.setAttribute("paquetes", controlador.obtenerPaqueteNombres());
        request.setAttribute("imagenSalida", imagen);
        
        
        
         request.getRequestDispatcher("/WEB-INF/paquetes/consulta.jsp").
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
