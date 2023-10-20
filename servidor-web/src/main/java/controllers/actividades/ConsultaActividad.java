/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.actividades;

import dataTypes.DTActividadTuristica;
import dataTypes.DTCategoria;
import dataTypes.DTDepartamento;
import exceptions.MyException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
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
        
        request.setAttribute("departamentos", controlador.obtenerDepartamentos());
        request.setAttribute("categorias", controlador.obtenerCategorias());
        
        //en caso de que no se haya seleccionado ninguna actividad, se listan todas
        if(request.getParameter("idActividad") == null){
            if(validateParameters(request)){
                try {
                    if(request.getParameter("departamento") != null){
                        String departamento = request.getParameter("departamento");
                        List<DTActividadTuristica> actividades = controlador.obtenerActividadesTuristicasConId(departamento);
                            if(actividades.size() < 1){
                                request.setAttribute("actividades",null);
                            }else{
                                request.setAttribute("actividades", actividades); 
                            }
                    }else if(request.getParameter("categoria") != null){
                        String categoria = request.getParameter("categoria");
                        List<DTActividadTuristica> actividades = controlador.obtenerActividadesTuristicasPorCategoria(categoria);
                            if(actividades.size() < 1){
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
        request.getRequestDispatcher("/WEB-INF/actividades/consulta.jsp")
                    .forward(request, response);
        }else{
            Long idActividad = Long.parseLong(request.getParameter("idActividad"));
            DTActividadTuristica actividad;
            try {
                //metodo que busca actividad por su id y si no la encuentra devuelve null a diferencia
                //de metodos previamente existentes
                actividad = controlador.obtenerActividadTuristicaNull(idActividad);
                if(actividad == null){
                    throw new exceptions.MyException("¡ERROR! No se ha encontrado la actividad.");
                }
            } catch (MyException ex) {
                response.sendError(404);
                return;
            }
            

            String imageDataUri = "";

            byte [] foto = controlador.obtenerFotoActividadTuristica(actividad.getId());
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
            
            
            request.setAttribute("categorias", categorias);
            request.setAttribute("foto", imageDataUri);
            request.setAttribute("actividad",actividad);
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
