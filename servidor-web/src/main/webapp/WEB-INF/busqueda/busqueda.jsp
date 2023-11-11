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
        String strPeticionBusqueda = (String) request.getAttribute("peticionBusqueda");
    %>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-row align-items-center justify-content-between p-2">
            <h4>Resultados de la busqueda: <span class="text-secondary"><%=strPeticionBusqueda%></span></h4>
            <form method="post" action="/busqueda?peticionBusqueda=<%=strPeticionBusqueda%>">
                <div class="form-group">
                    <select class="form-select btn btn-primary" name="tipoDeFiltro" onchange="this.form.submit();">
                        <option disable selected >Selecciona un orden</option>
                        <option value="1"> Ordenar alfabeticamente </option>
                        <option value="2"> Ordenar por fecha de alta </option>
                    </select>
                </div>
            </form>
            <div class="form-group">
                <label>Filtro por Departamento</label>
                <select class="text-light form-select bg-primary" name="departamento" onchange="this.form.submit();">
                    <option value="" disabled selected>- seleccione un departamento -</option>
                    <% 
                        for (DTDepartamento departamento : (List<DTDepartamento>) request.getAttribute("departamentos")) {
                            String nombreDepartamento = departamento.getNombre();
                    %>
                    <option value="<%= departamento.getNombre() %>">
                        <%= nombreDepartamento %>
                    </option>		
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <label>Filtro por Categoria</label>
                <select class="text-light form-select bg-primary" name="categoria" onchange="this.form.submit();">
                    <option value="" disabled selected>- seleccione una categoria -</option>
                    <% 
                        for(DTCategoria c : (List<DTCategoria>) request.getAttribute("categorias")){
                            String nombreCategoria = c.getNombre();
                    %>
                    <option value="<%=nombreCategoria%>">
                        <%= nombreCategoria %>
                    </option>		
                    <% } %>
                </select>
            </div> 
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
                            <%if(dtb.getTipoResultado().equals("Actividad")){%>
                                <td><span class="badge text-bg-primary"><%= dtb.getDepartamento() %></span></td>
                            <%} else {%>
                                <td>
                                    <% String[] departamentosSeparados = dtb.getDepartamento().split(",");
                                    for (String departamentoIndividual : departamentosSeparados) {%>
                                        <span class="badge text-bg-primary"><%=departamentoIndividual%></span>
                                    <%}%>
                                </td>
                            <%}%>
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
