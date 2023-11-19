/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.paquetes;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import webservice.DtActividadTuristica;
import webservice.DtActividadTuristicaWS;
import webservice.DtActividadesCollectionWS;
import webservice.DtPaqueteActividadTuristica;

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
        /* WebService Actividades: */
        webservice.WSActividadControllerService a = new webservice.WSActividadControllerService();
        webservice.WSActividadController portA = a.getWSActividadControllerPort();

        /* WebService Paquetes: */
        webservice.WSPaqueteControllerService p = new webservice.WSPaqueteControllerService();
        webservice.WSPaqueteController portP = p.getWSPaqueteControllerPort();
        
        if (request.getHeader("User-Agent").toLowerCase().contains("mobile")) {
            response.sendError(403); 
            return;
        }
        
        byte [] foto1 = null;
        byte [] foto2 = null;
        String paquete = request.getParameter("paquetes");
        String actividad = request.getParameter("actividad");
        DtActividadesCollectionWS actividades = new DtActividadesCollectionWS();
        DtActividadTuristica actividadCompleta = null;
        DtActividadTuristica actividadfoto = null;
        DtPaqueteActividadTuristica pa = null;
        String imagen = null;
        String categorias = "";
        LinkedHashSet<String> hash = new LinkedHashSet<String>();
        List<DtActividadTuristicaWS> lista = new ArrayList<>();
        if (paquete != null) {
            pa = portP.obtenerPaqueteCosto(paquete);
            actividades = portA.obtenerActividadesRelacionadas(paquete);
             lista = actividades.getActividades();
            for (DtActividadTuristicaWS Actividad : lista){
                DtActividadTuristica actividadCategoria = portA.obtenerActividadTuristicaNull(Actividad.getId());
                categorias += actividadCategoria.getCategoriasString();
            }
            String[] categoriasSeparadas = categorias.split("\\|");
            for(String c :categoriasSeparadas){
                hash.add(c);
            }
            foto1 = portP.obtenerFotoPaqueteActividadTuristica(paquete);
            if(actividad != null){
                actividadCompleta = portA.obtenerActividadTuristica(actividad);
                actividadfoto = portA.obtenerFotoActividadTuristicaID(actividad);
                foto2 = portA.obtenerFotoActividadTuristica(actividadfoto.getId());
            }
        }
        List<String> paquetes = portP.obtenerPaqueteNombres().getLista();
        request.setAttribute("actividades", lista);
        request.setAttribute("foto1", foto1);
        request.setAttribute("foto2", foto2);
        request.setAttribute("actividad", actividadCompleta);
        request.setAttribute("categorias", hash);
        request.setAttribute("paqueteEnteros", pa);
        request.setAttribute("paquetes", paquetes);
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
