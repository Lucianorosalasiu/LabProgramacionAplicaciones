<%-- 
    Document   : busqueda
    Created on : Nov 9, 2023, 4:28:43 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTActividadTuristica"%>
<%@page import="dataTypes.DTDepartamento"%>
<%@page import="dataTypes.DTCategoria"%>
<%@page import="dataTypes.DTBusqueda"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%> 

<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/> 
        <title>TurismoUy | Login</title>
    </head>
    
    <%
    String userAgent = request.getHeader("User-Agent");
    if(userAgent != null && userAgent.toLowerCase().contains("mobile")){%>
    <jsp:include page="/WEB-INF/templates/mobileHeader.jsp"/>
    <%}else{%>
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%}%>
    
    <%
        ArrayList<DTBusqueda> resultadosBusqueda = (ArrayList<DTBusqueda>) request.getAttribute("resultadosBusqueda");
    %>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-row align-items-center justify-content-between p-2">
            <h4>Resultados de la busqueda</h4>
        </div>

        <div class="p-2 overflow-scroll flex-grow-1">    
            <div class="border rounded border-tertiary p-2 ">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Fecha de alta</th>
                            <th scope="col">Categorias</th>
                            <th scope="col">Departamento</th>
                            <th scope="col">Tipo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            for(DTBusqueda dtb : resultadosBusqueda){
                        %>
                        <tr>
                            <td><%= dtb.getNombre() %></td>
                            <td><%= dtb.getDescripcion() %></td>
                            <td><%= dtb.getFechaAltaComoString() %></td>
                            <td><span class="badge text-bg-primary">Categoria</span></td>
                            <td><span class="badge text-bg-primary">Departamento</span></td>
                            <td><%= dtb.getTipoResultado() %></td>
                        </tr>		
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        
        
        
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
