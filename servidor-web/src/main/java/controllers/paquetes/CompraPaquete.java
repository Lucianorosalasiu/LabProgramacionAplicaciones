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
import java.util.GregorianCalendar;
import java.util.List;
import static java.util.Objects.isNull;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import logica.fabrica.Fabrica;
import logica.interfaces.IControlador;
<<<<<<< Updated upstream
import webservice.DtActividadesCollectionWS;
import webservice.DtPaqueteActividadTuristica;
import webservice.DtPaqueteWS;
import webservice.DtPaquetesCollectionWS;
=======
import webService.dataTypesWS.DTPaquetesCollectionWS;
import webservice.DtCompraPaquete;
import webservice.DtCompraWS;
import webservice.DtPaqueteActividadTuristica;
import webservice.DtPaqueteWS;
import webservice.DtPaquetesCollectionWS;
import webservice.DtTurista;
>>>>>>> Stashed changes
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
        
<<<<<<< Updated upstream
        /* WebService Usuarios: */
=======
         /* WebService Usuarios: */
>>>>>>> Stashed changes
        webservice.WSUsuarioControllerService u = new webservice.WSUsuarioControllerService();
        webservice.WSUsuarioController portU = u.getWSUsuarioControllerPort();

        /* WebService Actividades: */
        webservice.WSActividadControllerService a = new webservice.WSActividadControllerService();
<<<<<<< Updated upstream
        webservice.WSActividadController portA = a.getWSActividadControllerPort();      
=======
        webservice.WSActividadController portA = a.getWSActividadControllerPort();

        /* WebService Salidas: */
        webservice.WSSalidaControllerService s = new webservice.WSSalidaControllerService();
        webservice.WSSalidaController portS = s.getWSSalidaControllerPort();
>>>>>>> Stashed changes

        /* WebService Paquetes: */
        webservice.WSPaqueteControllerService p = new webservice.WSPaqueteControllerService();
        webservice.WSPaqueteController portP = p.getWSPaqueteControllerPort();
        
<<<<<<< Updated upstream
        byte [] foto1 = null;
=======
>>>>>>> Stashed changes
        String userType = (String) request.getSession().getAttribute("sessionType");
        if (isNull(userType) || !userType.equals("TURISTA") || request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403); 
            return;
        }
        
<<<<<<< Updated upstream
        Fabrica fabrica = new Fabrica();
        IControlador controlador = fabrica.getInterface();
        DtPaquetesCollectionWS paquetesEnteros = new DtPaquetesCollectionWS();
        
        paquetesEnteros = portP.obtenerPaquetes();
        List<DtPaqueteWS> paquetes1 = paquetesEnteros.getPaquetes();
        request.setAttribute("paquetesEnteros", paquetes1);
=======
        byte [] foto1 = null;

        DtPaquetesCollectionWS paquetesEnteros = portP.obtenerPaquetes();
        List<DtPaqueteWS> PaquetesEnteros = paquetesEnteros.getPaquetes();
        request.setAttribute("paquetesEnteros", PaquetesEnteros);
        if(request.getParameter("paquetes") != null){
            foto1 = portP.obtenerFotoPaqueteActividadTuristica(request.getParameter("paquetes"));
             request.setAttribute("foto1", foto1);
        }
        
>>>>>>> Stashed changes
        
        
        int cantPersonas = 0;
      
         
        Date alta = new Date();
        Date vencimiento = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(vencimiento);
        
        if(request.getParameter("BOTON") != null){
            long idTurista = (long)request.getSession().getAttribute("id");
            int cantidadPersonas = Integer.parseInt(request.getParameter("personas"));
            
<<<<<<< Updated upstream
            DTTurista turista = controlador.obtenerTurista(idTurista);
            DtPaqueteActividadTuristica pa = portP.obtenerPaqueteCosto(request.getParameter("paquetes"));
            c.add(Calendar.DATE, pa.getValidez());
            vencimiento = c.getTime();
            
            DTPaqueteActividadTuristica paquete = new DTPaqueteActividadTuristica(pa.getNombre(),pa.getDescripcion(),pa.getValidez(),pa.getDescuento(),pa.getFechaAlta().toGregorianCalendar().getTime());
            foto1 = portP.obtenerFotoPaqueteActividadTuristica(request.getParameter("paquetes"));
            DTCompraPaquete compra = new DTCompraPaquete(turista,paquete,cantidadPersonas,vencimiento,alta,paquete.getCosto()*cantidadPersonas);
            if(controlador.compraExiste(compra)){
                controlador.agregarCompraPaquete(compra);
=======
            DtTurista turista = portU.obtenerTurista(idTurista);
            DTTurista dtturista = new DTTurista(turista.getId(),turista.getNickname(),turista.getEmail(),turista.getNacionality());
            
            DtPaqueteActividadTuristica paquete = portP.obtenerPaqueteCosto(request.getParameter("paquetes"));
           
            
            DTPaqueteActividadTuristica dtpaquete = new DTPaqueteActividadTuristica(paquete.getNombre(), paquete.getDescripcion(), paquete.getValidez(), paquete.getDescuento(), paquete.getFechaAlta().toGregorianCalendar().getTime());
            
            c.add(Calendar.DATE, paquete.getValidez());
            vencimiento = c.getTime();
            
            GregorianCalendar xmlDateAlta = new GregorianCalendar();
            xmlDateAlta.setTime(alta);
            XMLGregorianCalendar xmlAlta = null;
            
            GregorianCalendar xmlDateVencimiento = new GregorianCalendar();
            xmlDateVencimiento.setTime(vencimiento);
            XMLGregorianCalendar xmlVencimiento = null;
            try{
                
                xmlAlta = DatatypeFactory.newInstance().newXMLGregorianCalendar(xmlDateAlta);

                xmlVencimiento = DatatypeFactory.newInstance().newXMLGregorianCalendar(xmlDateVencimiento);
           
            }catch(Exception e){}
            
            DTCompraPaquete compra = new DTCompraPaquete(dtturista,dtpaquete,cantidadPersonas,vencimiento,alta,paquete.getCosto()*cantidadPersonas);
            DtCompraWS dtcompra = new DtCompraWS();
            dtcompra.setCOMPRADOR(turista);
            dtcompra.setCANTTURISTAS(cantidadPersonas);
            dtcompra.setPAQUETE(paquete);
            dtcompra.setVENCIMIENTO(xmlVencimiento);
            dtcompra.setFECHACOMPRA(xmlAlta);
            dtcompra.setCOSTO(paquete.getCosto()*cantidadPersonas);
            
            if(portP.compraExiste(dtcompra)){
                portP.agregarCompraPaquete(dtcompra);
>>>>>>> Stashed changes
                request.setAttribute("successMessage", "Compra realizada!");
                request.getRequestDispatcher("/WEB-INF/templates/success.jsp")
                        .forward(request, response);
                return;
            }else{   
                request.setAttribute("errorMessage", "Ya has comprado este paquete");
            }
            
<<<<<<< Updated upstream
           request.setAttribute("foto1", foto1);   
=======
             
>>>>>>> Stashed changes
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
