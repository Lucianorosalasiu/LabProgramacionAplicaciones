<%-- 
    Document   : inscripcion
    Created on : Oct 7, 2023, 5:48:38 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTDepartamento, dataTypes.DTActividadTuristica, dataTypes.DTSalidaTuristica"%>
<%@page import="java.util.List"%> 
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Inscripcion</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
   <body class="h-100 d-flex flex-column">
        <div class="flex-grow-1">
            <form method="post" action="/inscripcion" id="inscripcionForm">
                <div class="m-3">
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
                      
                    <div class="mt-2">
                        <% if (request.getAttribute("errorMessage") != null) { %>
                            <div class="alert alert-danger" role="alert">
                                <%= request.getAttribute("errorMessage") %>
                            </div>
                        <% } %>
                        <select name="salida" required>
                            <option value="" disabled selected>- seleccione una salida -</option>
                            <% 
                                String selectedSalida = request.getParameter("salida");
                                for(DTSalidaTuristica salida : salidas){
                                    String nombreSalida = salida.getNombre();
                            %>
                            <option value="<%= nombreSalida %>" <% if (nombreSalida.equals(selectedSalida)) { %>selected <% } %>>
                                <%= nombreSalida %>
                            </option>		
                            <% } %>
                        </select>
                        <div class="row">
                            <div class="m-3 col">
                                <label>Cantidad de Turistas</label>
                                <input type="number" min="0" class="form-control" name="cantidadTuristas" value="${param.cantidadTuristas}" required>
                            </div>
                            <div class="m-3 col">
                                <label>Forma de Pago</label>
                                <select name="formaPago" required>
                                    <option value="" disabled selected>- seleccione forma de pago -</option>
                                    <% 
                                        String formaPago = request.getParameter("formaPago");
                                    %>
                                    <option value="general" <% if ("general".equals(formaPago)) { %>selected <% } %>>
                                        General
                                    </option>
                                    <option value="paquete" <% if ("paquete".equals(formaPago)) { %>selected <% } %>>
                                        Por Paquete
                                    </option>
                                </select>
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
                  }
                %>
            </form>
        </div>
                
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>  
    
        <script>
            function cleanParameters(parameters) {
                var form = document.getElementById("inscripcionForm");

                parameters.forEach(function(fieldName) {
                    if (form.elements[fieldName]) {
                        form.elements[fieldName].value = null;
                    }
                });
                
                form.submit();
            }

            var departamentoSelect = document.querySelector('select[name="departamento"]');
            departamentoSelect.addEventListener('change', function() {
                cleanParameters(["actividad"]);
            });

            var actividadSelect = document.querySelector('select[name="actividad"]');
            actividadSelect.addEventListener('change', function() {
                cleanParameters([]);
            });
            
        </script>
    </body>
</html>
