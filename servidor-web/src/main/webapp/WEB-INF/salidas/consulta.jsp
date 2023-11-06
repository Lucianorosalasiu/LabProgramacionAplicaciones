<%-- 
    Document   : consultar
    Created on : Oct 7, 2023, 5:48:29 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservice.DtDepartamentoWS, webservice.DtDepartamentosCollectionWS"%>
<%@page import="webservice.DtActividadTuristicaWS, webservice.DtActividadesCollectionWS"%>
<%@page import="webservice.DtSalidaTuristicaWS, webservice.DtSalidasCollectionWS"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Consulta Salida</title>
    </head>

    <%
    String userAgent = request.getHeader("User-Agent");
    if(userAgent != null && userAgent.toLowerCase().contains("mobile")){%>
            <jsp:include page="/WEB-INF/templates/mobileHeader.jsp"/>
    <%}else{%>
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%}%>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-grow-1 flex-column">
            <form class="flex-grow-1" method="post" action="/consultasalida" id="consultaForm">
                <div class="flex-grow-1 d-flex justify-content-center align-items-center">
                    <select class="text-light form-select bg-primary m-2" name="departamento">
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
                <%
                    if (selectedDepartamento != null) {
                %>

                <div class="overflow-x-scroll p-2 m-2 border rounded border-tertiary">
                    <h4>
                        Actividades de <%= selectedDepartamento %>
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
                </div>
                <div class="m-2">
                    <select class="text-light form-select bg-primary" name="actividad">
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
                </div>
                <div class="overflow-x-scroll p-2 m-2 border rounded border-tertiary">
                    <%
                        if (selectedActividad != null) {
                    %>
                    <h4>
                        Salidas de <%= selectedActividad %>
                    </h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Cantidad Max. Turistas</th>
                                <th scope="col">Fecha Salida</th>
                                <th scope="col">Lugar de Salida</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                DtSalidasCollectionWS salidas = (DtSalidasCollectionWS) request.getAttribute("salidas");
                                for(DtSalidaTuristicaWS salida : salidas.getSalidas()){
                            %>
                            <tr>
                                <td><%= salida.getNombre() %></td>
                                <td><%= salida.getCantidadMaxTuristas() %></td>
                                <td><%= salida.getFechaSalida() %></td>
                                <td><%= salida.getLugar() %></td>
                            </tr>		
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="m-2">
                    <select class="text-light form-select bg-primary" name="salida">
                        <option value="" disabled selected>- seleccione una salida -</option>
                        <% 
                            String selectedSalida1 = request.getParameter("salida");
                            for(DtSalidaTuristicaWS salida : salidas.getSalidas()){
                                String nombreSalida = salida.getNombre();
                        %>
                        <option value="<%= nombreSalida %>" <% if (nombreSalida.equals(selectedSalida1)) { %>selected <% } %>>
                            <%= nombreSalida %>
                        </option>		
                        <% } %>
                    </select>
                </div>
                <div class="overflow-x-scroll p-2 m-2 border rounded border-tertiary">
                    <%
                        DtSalidaTuristicaWS selectedSalida2 = (DtSalidaTuristicaWS) request.getAttribute("selectedSalida");
                        if (selectedSalida2 != null && selectedSalida1 != null) {
                    %>
                    <h4>
                        <%= selectedSalida2.getNombre() %>
                    </h4>
                    <div class="container w-100">
                        <div class="row">
                            <div class="col-sm-3">
                                <% 
                                    String base64imagen = (String) request.getAttribute("imagenSalida");
                                    if (!base64imagen.isEmpty()) {
                                %>
                                <img width="250" height="200" src="data:image/jpeg;base64,<%= base64imagen %>">

                                <% 
                                    }
                                %>
                            </div>
                        </div>
                            <div <% if (!base64imagen.isEmpty()) { %> class="col-sm-9" <% } %>>
                                <fieldset disabled>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Lugar Salida</label>
                                            <input type="text" class="form-control" value="<%= selectedSalida2.getLugar() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Cantidad Max. Turistas</label>
                                            <input type="text" class="form-control" value="<%= selectedSalida2.getCantidadMaxTuristas() %>" >
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Fecha Salida</label>
                                            <input type="text" class="form-control" value="<%= selectedSalida2.getFechaSalida()%>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Fecha Alta</label>
                                            <input type="text" class="form-control" value="<%= selectedSalida2.getFechaAlta()%>" >
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        
                    </div>
                </div>
                <%
                    }
                  }
                }
                %>
            </form>
        </div>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>

        <script>
            function cleanParameters(parameters) {
                var form = document.getElementById("consultaForm");

                parameters.forEach(function (fieldName) {
                    if (form.elements[fieldName]) {
                        form.elements[fieldName].value = null;
                    }
                });
            }

            var departamentoSelect = document.querySelector('select[name="departamento"]');
            departamentoSelect.addEventListener('change', function () {
                cleanParameters(["actividad", "salida"]);
                document.getElementById("consultaForm").submit();
            });

            var actividadSelect = document.querySelector('select[name="actividad"]');
            actividadSelect.addEventListener('change', function () {
                cleanParameters(["salida"]);
                document.getElementById("consultaForm").submit();
            });

            var salidaSelect = document.querySelector('select[name="salida"]');
            salidaSelect.addEventListener('change', function () {
                document.getElementById("consultaForm").submit();
            });
        </script>
    </body>
</html>
