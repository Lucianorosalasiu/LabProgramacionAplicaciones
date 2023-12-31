<%-- 
    Document   : alta
    Created on : Oct 7, 2023, 5:48:16 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservice.DtActividadesCollectionWS"%>
<%@page import="webservice.DtActividadTuristicaWS"%>
<%@page import="webservice.DtDepartamentosCollectionWS"%>
<%@page import="webservice.DtDepartamentoWS"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Alta Salida</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body class="h-100 d-flex flex-column">
        <div class="flex-grow-1">
            <form method="post" action="/altasalida" enctype="multipart/form-data" >
                <div class="m-3">
                    <select name="departamento" onchange="this.form.submit();">
                        <option value="" disabled selected>- seleccione un departamento -</option>
                        <% 
                            String selectedDepartamento = request.getParameter("departamento");
                            DtDepartamentosCollectionWS departamentos = (DtDepartamentosCollectionWS) request.getAttribute("departamentos");
                            for(DtDepartamentoWS departamento : departamentos.getDepartamentos()){
                                String nombreDepartamento = departamento.getNombre();
                        %>
                        <option value="<%= nombreDepartamento %>" <% if (nombreDepartamento.equals(selectedDepartamento)) { %>selected <% } %>>
                            <%= nombreDepartamento %>
                        </option>		
                        <% } %>
                    </select>
                </div>
                <div class="d-flex flex-column align-items-center p-4 flex-grow-1">
                    <%
                        String departamento = (String) request.getAttribute("departamento");
                        if (departamento != null) {
                    %>
                    <h4>
                        Actividades de <%= departamento %>
                    </h4>
                    <table class="table">
                        <thead>
                        <tr>
                          <th scope="col">Nombre</th>
                          <th scope="col">Descripcion</th>
                          <th scope="col">Duración</th>
                          <th scope="col">Costo</th>
                          <th scope="col">Ciudad</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                            DtActividadesCollectionWS actividades = (DtActividadesCollectionWS) request.getAttribute("actividades");
                            for(DtActividadTuristicaWS actividad : actividades.getActividades()){
                        %>
                        <tr>
                          <td><%= actividad.getNombre() %></td>
                          <td><%= actividad.getDescripcion() %></td>
                          <td><%= actividad.getDuracion() %></td>
                          <td><%= actividad.getCosto() %></td>
                          <td><%= actividad.getCiudad() %></td>
                        </tr>		
                        <% } %>
                      </tbody>
                    </table>
                    <div class="mt-5">
                        <% if (request.getAttribute("errorMessage") != null) { %>
                            <div class="alert alert-danger" role="alert">
                                <%= request.getAttribute("errorMessage") %>
                            </div>
                        <% } %>
                        <select class="m-3" name="actividad" required>
                            <option value="" disabled selected>- seleccione una actividad -</option>
                            <%
                                String selectedActividad = request.getParameter("actividad"); 
                                for(DtActividadTuristicaWS actividad : actividades.getActividades()){
                                String nombreActividad = actividad.getNombre();
                            %>
                            <option value="<%= nombreActividad %>" <% if (nombreActividad.equals(selectedActividad)) { %>selected <% } %>>
                                <%= nombreActividad %>
                            </option>		
                            <% } %>
                        </select>
                        <div class="row">
                            <div class="col m-3">
                                <label>Nombre de Salida</label>
                                <input type="text" class="form-control" name="nombre" value="${param.nombre}" required>
                            </div>
                            <div class="col m-3">
                                <label>Lugar de Salida</label>
                                <input type="text" class="form-control" name="lugar" value="${param.lugar}" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col m-3">
                                <label>Fecha de Salida</label>
                                <input type="datetime-local" class="form-control" name="fechaSalida" value="${param.fechaSalida}" required>
                            </div>
                            <div class="col m-3">
                                <label>Cantidad max. de Turistas</label>
                                <input type="number" min="0" class="form-control" name="cantidadMaxTuristas" value="${param.cantidadMaxTuristas}" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col m-3">
                                <input type="file" name="imagen" accept="image/*" value="${param.imagen}"/>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-secondary">
                                Enviar
                            </button>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>                
            </form> 
        </div>
            
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>        
    </body>
</html>