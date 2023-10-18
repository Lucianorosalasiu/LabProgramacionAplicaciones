<%-- 
    Document   : consultar
    Created on : Oct 7, 2023, 5:48:29 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTDepartamento, dataTypes.DTActividadTuristica, dataTypes.DTSalidaTuristica"%>
<%@page import="java.util.List"%> 
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Consulta Salida</title>
    </head>

    <jsp:include page="/WEB-INF/templates/header.jsp"/>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-grow-1 flex-column">
            <form class="flex-grow-1" method="post" action="/consultasalida" id="consultaForm">
                <div class="flex-grow-1 d-flex justify-content-center align-items-center">
                    <select name="departamento">
                        <option value="" disabled selected>- seleccione un departamento -</option>
                        <% 
                            String selectedDepartamento = request.getParameter("departamento");
                            for(DTDepartamento departamento : (List<DTDepartamento>) request.getAttribute("departamentos")){
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
                <div class="d-flex flex-column align-items-center p-4 flex-grow-1">
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
                                List <DTActividadTuristica> actividades = (List<DTActividadTuristica>) request.getAttribute("actividades");
                                for(DTActividadTuristica actividad : actividades){
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
                <div class="m-3">
                    <select name="actividad">
                        <option value="" disabled selected>- seleccione una actividad -</option>
                        <% 
                            String selectedActividad = request.getParameter("actividad");
                            for(DTActividadTuristica actividad : (List<DTActividadTuristica>) request.getAttribute("actividades")){
                                String nombreActividad = actividad.getNombre();
                        %>
                        <option value="<%= nombreActividad %>" <% if (nombreActividad.equals(selectedActividad)) { %>selected <% } %>>
                            <%= nombreActividad %>
                        </option>		
                        <% } %>
                    </select>
                </div>
                <div class="d-flex flex-column align-items-center p-4 flex-grow-1">
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
                                List <DTSalidaTuristica> salidas = (List<DTSalidaTuristica>) request.getAttribute("salidas");
                                for(DTSalidaTuristica salida : salidas){
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
                <div class="m-3">
                    <select name="salida">
                        <option value="" disabled selected>- seleccione una salida -</option>
                        <% 
                            String selectedSalida1 = request.getParameter("salida");
                            for(DTSalidaTuristica salida : salidas){
                                String nombreSalida = salida.getNombre();
                        %>
                        <option value="<%= nombreSalida %>" <% if (nombreSalida.equals(selectedSalida1)) { %>selected <% } %>>
                            <%= nombreSalida %>
                        </option>		
                        <% } %>
                    </select>
                </div>
                <div class="d-flex flex-column align-items-center p-4 flex-grow-1">
                    <%
                        DTSalidaTuristica selectedSalida2 = (DTSalidaTuristica) request.getAttribute("selectedSalida");
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
                                            <input type="text" class="form-control" value="<%= selectedSalida2.getFechaSalida() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Fecha Alta</label>
                                            <input type="text" class="form-control" value="<%= selectedSalida2.getFechaAlta() %>" >
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
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
