<%-- 
    Document   : detalles
    Created on : Oct 19, 2023, 4:31:55 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTActividadTuristica"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <title>Turismouy | Consulta actividad</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%
        DTActividadTuristica actividad = (DTActividadTuristica) request.getAttribute("actividad");
    %>
    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-column flex-grow-1 justify-content-start p-2">
            <h5>Viendo en detalle la actividad <span class="fw-bold text-primary"><%=actividad.getNombre()%></span></h5>
           
        </div>
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
