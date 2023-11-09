/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.salidas;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservice.DtpdfWrapper;
import webservice.WSSalidaController;
import webservice.WSSalidaControllerService;

/**
 *
 * @author diego
 */
@WebServlet(name = "verPDF")
public class VerPdf extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        WSSalidaControllerService salidaController = new WSSalidaControllerService();
        WSSalidaController salidaPort = salidaController.getWSSalidaControllerPort();
        
        DtpdfWrapper pdf = salidaPort.obtenerPdf(
                request.getParameter("nickname"), 
                request.getParameter("salida")
        );
        
        response.setContentType("application/pdf");

        // Provide a filename for the PDF
        response.setHeader("Content-Disposition", "inline; filename=generated.pdf");

        // Write the PDF byte array to the response's output stream
        response.getOutputStream().write(pdf.getData());
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
