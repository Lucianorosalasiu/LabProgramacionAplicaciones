/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

import Enums.TipoInscripcion;
import dataTypes.DTActividadTuristica;
import dataTypes.DTInscripcion;
import dataTypes.DTPaqueteActividadTuristica;
import dataTypes.DTSalidaTuristica;
import exceptions.MyException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;
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
        Long idUser = (Long) request.getSession().getAttribute("id");
        String userType = (String) request.getSession().getAttribute("sessionType");
        String nickname = (String) request.getSession().getAttribute("sessionNickname");
        
        if (isNull(userType) || !userType.equals("TURISTA")) {
            response.sendError(403); 
            return;
        }
        
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        
        String departamento = request.getParameter("departamento"); 
        String actividad = request.getParameter("actividad");
        String formaPago = request.getParameter("formaPago");
        String salida = request.getParameter("salida");
        String cantidadTuristas = request.getParameter("cantidadTuristas");
        String error = null;
       
        
        List<DTActividadTuristica> actividades = new ArrayList();
        List<DTSalidaTuristica> salidas = new ArrayList();
        List<DTPaqueteActividadTuristica> paquetes = new ArrayList();

        if (departamento != null) {
            actividades = controlador.obtenerActividadesTuristicas(departamento);

            if (actividad != null) {
                salidas = controlador.obtenerSalidasTuristicas(actividad);

                if (formaPago != null && salida != null && !cantidadTuristas.isBlank()) {
                    Integer cantTuristas = Integer.valueOf(cantidadTuristas);
                    if (formaPago.equals("paquete")) {
                        paquetes = controlador.obtenerPaquetesComprados(
                                        idUser, 
                                        salida, 
                                        cantTuristas);
                    }
                }
            }
        }
        
        if (request.getParameter("BOTON") != null) {
            if (!validateRequest(request)) {
                error = "Complete todos los campos";
            } else {
                try {
                    DTInscripcion dtInscripcion = new DTInscripcion(
                                            new Date(),
                                            Integer.parseInt(cantidadTuristas)
                                        );

                    String nombrePaquete = request.getParameter("paquete");

                    if (formaPago.equals("paquete")) { 
                        float costoActividad = controlador.obtenerCostoActividad(actividad);
                        float descuento = paquetes.stream()
                                .filter(p -> p.getNombre().equals(nombrePaquete))
                                .findFirst()
                                .get()
                                .getDescuento();

                        float costoUnitario = costoActividad - (costoActividad * (descuento/100)); 
                        float costoTotal = Integer.parseInt(cantidadTuristas) * costoUnitario;

                        dtInscripcion.setCostoTotal(costoTotal);
                        dtInscripcion.setTipo(TipoInscripcion.PAQUETE);   
                    } else {
                        dtInscripcion.setTipo(TipoInscripcion.GENERAL);  
                    }

                    controlador.altaInscripcion(dtInscripcion, 
                            actividad, 
                            salida, 
                            nickname
                    );
                    
                    request.setAttribute("successMessage", "Inscripción realizada correctamente!");
                    request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                            .forward(request, response);
                    return;
                    
                } catch (MyException ex) {
                    error = ex.getMessage();
                }
            }
            
            
        }
        
        
        request.setAttribute("errorMessage", error);
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
        request.setAttribute("actividades", actividades);
        request.setAttribute("salidas", salidas);
        request.setAttribute("paquetes", paquetes);
        
        request.getRequestDispatcher("/WEB-INF/salidas/inscripcion.jsp").
                forward(request, response);
        
    }

    private boolean validateRequest(HttpServletRequest request){
        if (request.getParameter("formaPago") == null || 
                request.getParameter("salida") == null ||
                request.getParameter("cantidadTuristas") == null) {
            return false;
        }
        if (request.getParameter("paquete") == null && request.getParameter("formaPago").equals("paquete")) {
            return false;
        }
        
        return true;
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
