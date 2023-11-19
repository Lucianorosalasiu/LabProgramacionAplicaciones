<%-- 
    Document   : consultaIndividual
    Created on : Nov 19, 2023, 1:45:49 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservice.DtSalidaTuristicaWS, webservice.DtSalidasCollectionWS"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <title>Turismouy | Consulta Salida</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body class="h-100 d-flex flex-column">
        <div class="container w-100 d-flex flex-column flex-grow-1 justify-content-center">
            <%
                DtSalidaTuristicaWS salida = (DtSalidaTuristicaWS) request.getAttribute("salida");
                if (salida != null && salida != null) {
            %>
            <div class="d-flex flex-column justify-content-center">
                <div>                
                    <div class="w-100 justify-content-center">
                        <h2>
                            <%= salida.getNombre() %>
                        </h2>
                    </div>
                    <% 
                    String base64imagen = (String) request.getAttribute("imagenSalida");
                    if (!base64imagen.isEmpty()) {
                    %>
                        <div class="row justify-content-center">
                            <img src="data:image/jpeg;base64,<%= base64imagen %>"style="max-width: 600px">
                        </div>
                    <% 
                    }
                    %>
                    <div class="row">
                        <div class="col m-3">
                            <label>Lugar Salida</label>
                            <input type="text" class="form-control" value="<%= salida.getLugar() %>" >
                        </div>
                        <div class="col m-3">
                            <label>Cantidad Max. Turistas</label>
                            <input type="text" class="form-control" value="<%= salida.getCantidadMaxTuristas() %>" >
                        </div>
                    </div>
                    <div class="row">
                        <div class="col m-3">
                            <label>Fecha Salida</label>
                            <input type="text" class="form-control" value="<%= salida.getFechaSalida()%>" >
                        </div>
                        <div class="col m-3">
                            <label>Fecha Alta</label>
                            <input type="text" class="form-control" value="<%= salida.getFechaAlta()%>" >
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
        
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
