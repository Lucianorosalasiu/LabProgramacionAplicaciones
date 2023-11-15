/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;

import webservice.DatatypeConfigurationException_Exception;
import webservice.DtActividadesCollectionWS;
import webservice.DtInscripcionWS;
import webservice.DtPaquetesCollectionWS;
import webservice.DtSalidasCollectionWS;
import webservice.MyException_Exception;
import webservice.ParseException_Exception;
import webservice.TipoInscripcion;
import webservice.WSActividadController;
import webservice.WSActividadControllerService;
import webservice.WSPaqueteController;
import webservice.WSPaqueteControllerService;
import webservice.WSSalidaController;
import webservice.WSSalidaControllerService;

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
            throws ServletException, IOException, DatatypeConfigurationException_Exception {
        Long idUser = (Long) request.getSession().getAttribute("id");
        String userType = (String) request.getSession().getAttribute("sessionType");
        String nickname = (String) request.getSession().getAttribute("sessionNickname");
        
        if (isNull(userType) || !userType.equals("TURISTA") || request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403); 
            return;
        }
        
        WSSalidaControllerService salidaController = new WSSalidaControllerService();
        WSSalidaController salidaPort = salidaController.getWSSalidaControllerPort();
        
        WSActividadControllerService actividadController = new WSActividadControllerService();
        WSActividadController actividadPort = actividadController.getWSActividadControllerPort();
        
        WSPaqueteControllerService paqueteController = new WSPaqueteControllerService();
        WSPaqueteController paquetePort = paqueteController.getWSPaqueteControllerPort();
        
        String departamento = request.getParameter("departamento"); 
        String actividad = request.getParameter("actividad");
        String formaPago = request.getParameter("formaPago");
        String salida = request.getParameter("salida");
        String cantidadTuristas = request.getParameter("cantidadTuristas");
        String error = null;
       
        
        DtActividadesCollectionWS actividades = new DtActividadesCollectionWS();
        DtSalidasCollectionWS salidas = new DtSalidasCollectionWS();
        DtPaquetesCollectionWS paquetes = new DtPaquetesCollectionWS();

        if (departamento != null) {
            actividades = actividadPort.obtenerActividadesTuristicasPorDepartamento(departamento);

            if (actividad != null) {
                salidas = salidaPort.obtenerSalidasTuristicas(actividad);

                if (formaPago != null && salida != null && !cantidadTuristas.isBlank()) {
                    Integer cantTuristas = Integer.valueOf(cantidadTuristas);
                    if (formaPago.equals("paquete")) {
                        paquetes = paquetePort.obtenerPaquetesComprados(
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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    
                    DtInscripcionWS dtInscripcion = new DtInscripcionWS();
                    dtInscripcion.setFecha(dateFormat.format(new Date()));
                    dtInscripcion.setCantidadTuristas(Integer.parseInt(cantidadTuristas));
                    
                    String nombrePaquete = request.getParameter("paquete");

                    if (formaPago.equals("paquete")) { 
                        float costoActividad = actividadPort.obtenerCostoActividad(actividad);
                        float descuento = paquetes.getPaquetes().stream()
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

                    salidaPort.altaInscripcion(dtInscripcion, 
                            actividad, 
                            salida, 
                            nickname
                    );
                    
                    request.setAttribute("successMessage", "Inscripción realizada correctamente!");
                    request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                            .forward(request, response);
                    return;
                    
                } catch (MyException_Exception ex) {
                    error = ex.getMessage();
                } catch (ParseException_Exception ex) {
                    Logger.getLogger(Inscripcion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
        
        
        request.setAttribute("errorMessage", error);
        request.setAttribute("departamentos", actividadPort.obtenerDepartamentos());
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
        try {
            processRequest(request, response);
        } catch (DatatypeConfigurationException_Exception ex) {
            Logger.getLogger(Inscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DatatypeConfigurationException_Exception ex) {
            Logger.getLogger(Inscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
