/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.actividades;

import exceptions.MyException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.List;
import static java.util.Objects.isNull;
import webservice.DtActividadTuristica;
import webservice.DtActividadesCollectionWS;
import webservice.DtPaquetesCollectionWS;
import webservice.DtSalidasCollectionWS;
import webservice.WSActividadController;
import webservice.WSActividadControllerService;
import webservice.WSSalidaController;
import webservice.WSSalidaControllerService;

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
        String userType = (String) request.getSession().getAttribute("sessionType");
        String nickname = (String) request.getSession().getAttribute("sessionNickname");
        //si estas en mobile y no estas registrado
        //o
        //si estas en mobile, estas registrado pero no como turista
        if ( request.getHeader("User-Agent").toLowerCase().contains("mobile") && isNull(userType)
                || request.getHeader("User-Agent").toLowerCase().contains("mobile") &&
                !isNull(userType) && !userType.equals("TURISTA")) {
            response.sendError(403); 
            return;
        }        
        
        WSActividadControllerService actividadController = new WSActividadControllerService();
        WSActividadController actividadPort = actividadController.getWSActividadControllerPort();
        
        WSSalidaControllerService salidaController = new WSSalidaControllerService();
        WSSalidaController salidaPort = salidaController.getWSSalidaControllerPort();
        
        webservice.WSPaqueteControllerService p = new webservice.WSPaqueteControllerService();
        webservice.WSPaqueteController paquetePort = p.getWSPaqueteControllerPort();
        
        String errorMessage = null;
        
        request.setAttribute("departamentos", actividadPort.obtenerDepartamentos());
        
        request.setAttribute("categorias", actividadPort.obtenerCategorias());
        
        String eliminarFavParameter = (String) request.getParameter("eliminarDeFavoritos");
        if (!isNull(eliminarFavParameter)) {
            actividadPort.eliminarDeFavoritos(Long.parseLong(eliminarFavParameter), nickname);
        }
        
        String agregarFavParameter = (String) request.getParameter("agregarAFavoritos");
        if (!isNull(agregarFavParameter)) {
            actividadPort.agregarAFavoritos(Long.parseLong(agregarFavParameter), nickname);
        }
        
        //en caso de que no se haya seleccionado ninguna actividad, se listan todas
        if(request.getParameter("idActividad") == null){
            if(validateParameters(request)){
                try {
                    if(request.getParameter("categoria") != null){
                        String categoria = request.getParameter("categoria");
                        DtActividadesCollectionWS actividades = actividadPort.obtenerActividadesPorCategoria(categoria);
                            
                        if(actividades.getActividades().size() < 1){
                                request.setAttribute("actividades",null);
                            }else{
                                request.setAttribute("actividades", actividades); 
                            }
                    }
                    if(request.getParameter("departamento") != null && request.getParameter("categoria") == null){
                        String departamento = request.getParameter("departamento");
                        DtActividadesCollectionWS actividades = actividadPort.obtenerActividadesTuristicasPorDepartamento(departamento);

                            if(actividades.getActividades().size() < 1){
                                request.setAttribute("actividades",null);
                            }else{
                                request.setAttribute("actividades", actividades); 
                            }
                    }
                } catch (Exception e) {
                   errorMessage = e.getMessage();  
                    request.setAttribute("errorMessage", errorMessage);
                }
            }
            List<String> favoritasUsuario = null;
            if (!isNull(userType) && userType.equals("TURISTA")) {
                favoritasUsuario = actividadPort.obtenerActividadesFavoritas(nickname).getLista();
            }
            request.setAttribute("favoritasUsuario", favoritasUsuario);
            request.getRequestDispatcher("/WEB-INF/actividades/consulta.jsp")
                        .forward(request, response);
        }else{
            Long idActividad = Long.parseLong(request.getParameter("idActividad"));
            
            DtActividadTuristica actividad;
            int cantidadFavoritos = 0;
            boolean enFavoritos = false;
            try {
                //metodo que busca actividad por su id y si no la encuentra devuelve null a diferencia
                //de metodos previamente existentes
                actividad = actividadPort.obtenerActividadTuristicaNull(idActividad);
                if(actividad == null){
                    throw new exceptions.MyException("¡ERROR! No se ha encontrado la actividad.");
                }
                if (!isNull(userType) && userType.equals("TURISTA")) {
                    if (actividadPort.obtenerActividadesFavoritas(nickname).getLista().contains(actividad.getNombre())) {
                        enFavoritos = true;
                    }
                }
                cantidadFavoritos = actividadPort.obtenerCantidadFavoritos(actividad.getNombre());
            } catch (MyException ex) {
                response.sendError(404);
                return;
            }

            String imageDataUri = "";

            byte [] foto = actividadPort.obtenerFotoActividadTuristica(actividad.getId());
            if(foto != null){
                String imagenBase64 = Base64.getEncoder().encodeToString(foto);
                String contentType = "image/jpeg";
                imageDataUri = "data:" + contentType + ";base64," + imagenBase64;
            }
            
            String categorias = "";
            try {
               categorias += actividad.getCategoriasString(); 
            } catch (Exception e) {
                
            }
            
            DtSalidasCollectionWS salidas = new DtSalidasCollectionWS();
            try{
                salidas = salidaPort.obtenerSalidasTuristicas(actividad.getNombre());
            }catch(Exception ex){
                response.sendError(404);
                return;
            }
            
            DtPaquetesCollectionWS paquetes;
            try{
                paquetes = paquetePort.obtenerPaquetesRelacionados(actividad.getId());
            }catch(Exception ex){
                response.sendError(404);
                return;
            }
            request.setAttribute("paquetes", paquetes);
            request.setAttribute("salidas",salidas);
            request.setAttribute("categorias", categorias);
            request.setAttribute("foto", imageDataUri);
            request.setAttribute("actividad",actividad);
            request.setAttribute("cantidadFavoritos", cantidadFavoritos);
            request.setAttribute("enFavoritos", enFavoritos);
            request.getRequestDispatcher("/WEB-INF/actividades/detalles.jsp")
                        .forward(request, response);
        }
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
