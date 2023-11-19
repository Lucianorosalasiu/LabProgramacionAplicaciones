<%-- 
    Document   : busqueda
    Created on : Nov 9, 2023, 4:28:43 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="webservice.DtDepartamentosCollectionWS"%>
<%@page import="webservice.DtDepartamentoWS"%>
<%@page import="webservice.DtBusquedaCollectionWS"%>
<%@page import="webservice.DtBusquedaWS"%>

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
        DtBusquedaCollectionWS resultadosBusqueda = (DtBusquedaCollectionWS) request.getAttribute("resultadosBusqueda");
        String strPeticionBusqueda = (String) request.getAttribute("peticionBusqueda");
        
        String attributeDepartamento = (String) request.getAttribute("departamento");
        String attributeCategoria = (String) request.getAttribute("categoria");
    %>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-row align-items-center justify-content-between p-2">
            <h4>Resultados de la busqueda: <span class="text-secondary"><%=strPeticionBusqueda%></h4>
            <form method="post" action="/busqueda?peticionBusqueda=<%=strPeticionBusqueda%><% if (attributeCategoria != null) { %>&categoria=<%=attributeCategoria%><% } %><% if (attributeDepartamento != null) { %>&departamento=<%=attributeDepartamento%><% } %>">
                <div class="form-group">
                    <select class="form-select btn btn-primary" name="tipoDeFiltro" onchange="this.form.submit();">
                        <option disable selected >Selecciona un orden</option>
                        <option value="1"> Ordenar alfabeticamente </option>
                        <option value="2"> Ordenar por fecha de alta </option>
                    </select>
                </div>
            </form>
            <form method="post" action="/busqueda?peticionBusqueda=<%=strPeticionBusqueda%>">
                <div class="form-group">
                    <label>Filtro por Departamento</label>
                    <select class="text-light form-select bg-primary" name="departamento" onchange="this.form.submit();">
                        <option value="" disabled selected>- seleccione un departamento -</option>
                        <% 
                            String selectedDepartamento = request.getParameter("departamento");
                            DtDepartamentosCollectionWS departamentos = (DtDepartamentosCollectionWS) request.getAttribute("departamentos");
                            
                            for (DtDepartamentoWS departamento : departamentos.getDepartamentos()) {
                                String nombreDepartamento = departamento.getNombre();
                        %>
                        <option value="<%= nombreDepartamento %>">
                            <%= nombreDepartamento %>
                        </option>		
                        <% } %>
                    </select>
                </div>
            </form>
            <form method="post" action="/busqueda?peticionBusqueda=<%=strPeticionBusqueda%>">
                <div class="form-group">
                    <label>Filtro por Categoria</label>
                    <select class="text-light form-select bg-primary" name="categoria" onchange="this.form.submit();">
                        <option value="" disabled selected>- seleccione una categoria -</option>
                        <% 
                            String categorias = (String) request.getAttribute("categorias");
                            String[] categoriasSeparadas = categorias.split(",");

                            for (String categoriaIndividual : categoriasSeparadas) {
                        %>
                        <option value="<%=categoriaIndividual%>">
                            <%= categoriaIndividual %>
                        </option>		
                        <% } %>
                    </select>
                </div>
            </form>
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
                            for(DtBusquedaWS dtb : resultadosBusqueda.getResultadosBusqueda()){
                        %>
                        <tr>
                            <td><%= dtb.getNombre() %></td>
                            <td><%= dtb.getDescripcion() %></td>
                            <td><%= dtb.getFechaAltaComoString() %></td>
                            <td>
                                <% String[] categoriasBusquedaSeparadas = dtb.getCategorias().split(",");
                                for (String categoriaBusquedaIndividual : categoriasBusquedaSeparadas) {%>
                                    <span class="badge text-bg-primary"><%=categoriaBusquedaIndividual%></span>
                                <%}%>
                            </td>

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
                            <td><%=dtb.getTipoResultado()%></td>
                        </tr>		
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        
        
        
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
