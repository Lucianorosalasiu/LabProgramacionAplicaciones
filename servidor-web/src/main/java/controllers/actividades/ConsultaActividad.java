/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.actividades;

import dataTypes.DTCategoria;
import dataTypes.DTDepartamento;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;

/**
 *
 * @author ignfer
 */
public class ConsultaActividad extends HttpServlet {

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
            try {
                String departamento = request.getParameter("departamento");
                request.setAttribute("actividades", controlador.obtenerActividadesTuristicas(departamento));
            } catch (Exception e) {
                errorMessage = e.getMessage();  
                request.setAttribute("errorMessage", errorMessage);
            }
        }
        
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
        request.setAttribute("categorias", controlador.obtenerCategorias());
        
//        List<DTCategoria> categorias = (List<DTCategoria>) request.getAttribute("categorias");
//        List<DTDepartamento> departamentos = (List<DTDepartamento>) request.getAttribute("departamentos");
//        
//        errorMessage = "VALIDATE_PARAMETERS = " + validateParameters(request) +
//                "|categorias.size(): " + categorias.size() +
//                "|departamentos.size(): " + departamentos.size();
//        
//        for(DTCategoria c : categorias){
//            errorMessage+="|" + c.getId() + "|" + c.getNombre();
//        }
//        
//        for(DTDepartamento d : departamentos){
//            errorMessage+="|" + d.getId() + "|" + d.getNombre();
//        }
//        
//        
//        request.setAttribute("errorMessage", errorMessage);
        
        request.getRequestDispatcher("/WEB-INF/actividades/consulta.jsp")
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
    return(request.getParameter("departamento") != null ||
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
