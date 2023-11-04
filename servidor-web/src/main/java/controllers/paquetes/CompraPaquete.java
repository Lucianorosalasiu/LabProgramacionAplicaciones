/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.paquetes;

import dataTypes.DTCompraPaquete;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTTurista;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
/**
 *
 * @author lucho
 */

public class CompraPaquete extends HttpServlet {

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
        
        String userType = (String) request.getSession().getAttribute("sessionType");
        if (isNull(userType) || !userType.equals("TURISTA") || request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403); 
            return;
        }
        
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        List<DTPaqueteActividadTuristica> paquetesEnteros = controlador.obtenerPaquetes();
        request.setAttribute("paquetesEnteros", paquetesEnteros);
        
        int cantPersonas = 0;
        
        Date alta = new Date();
        Date vencimiento = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(vencimiento);
        
        if(request.getParameter("BOTON") != null){
            long idTurista = (long)request.getSession().getAttribute("id");
            int cantidadPersonas = Integer.parseInt(request.getParameter("personas"));
            
            DTTurista turista = controlador.obtenerTurista(idTurista);
            DTPaqueteActividadTuristica paquete = controlador.obtenerPaqueteCosto(request.getParameter("paquetes"));
            c.add(Calendar.DATE, paquete.getValidez());
            vencimiento = c.getTime();
            
            DTCompraPaquete compra = new DTCompraPaquete(turista,paquete,cantidadPersonas,vencimiento,alta,paquete.getCosto()*cantidadPersonas);
            if(controlador.compraExiste(compra)){
                controlador.agregarCompraPaquete(compra);
                request.setAttribute("successMessage", "Compra realizada!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                        .forward(request, response);
                return;
            }else{   
                request.setAttribute("errorMessage", "Ya has comprado este paquete");
            }
            
              
        }
        if(request.getParameter("cancelar") != null){
            request.getRequestDispatcher("/WEB-INF/home/home.jsp").
                forward(request, response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/paquetes/compra.jsp").
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
