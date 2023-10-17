<%-- 
    Document   : consulta
    Created on : Oct 17, 2023, 12:26:21 AM
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTDepartamento, dataTypes.DTActividadTuristica, dataTypes.DTSalidaTuristica,dataTypes.DTPaqueteActividadTuristica"%>
<%@page import="java.util.List"%> 
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Consulta Paquete</title>
    </head>
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <body class="h-100 d-flex flex-column">
        <div class="flex-grow-1">
            <form method="post" action="/consultapaquete" id="consultaForm">
                <div class="m-3">
                    <label>Paquetes<span class="text-info"> - Seleccione un paquete para listar su informacion.</span></label>
                    <select name="paquetes" class="form-select" single>
                        <option value="" disabled selected>- seleccionar paquete -</option>
                        <% 
                            String selectedPaquete = request.getParameter("paquetes");
                            for(String paquete : (List<String>) request.getAttribute("paquetes")){
                                String nombrePaquete = paquete;
                            
                        %>
                        <option value="<%= nombrePaquete %>" <% if (nombrePaquete.equals(selectedPaquete)) { %>selected <% } %>>
                            <%= nombrePaquete %>
                        </option>		
                        <% } %>
                    </select>
                </div>
                <%                      
                    if (selectedPaquete != null) {
                %>
                <div class="d-flex flex-column align-items-center p-4 flex-grow-1">
                    <%
                        DTPaqueteActividadTuristica paquete = (DTPaqueteActividadTuristica) request.getAttribute("paqueteEnteros");
                        
                    %>
                    <div class="container w-100">
                        <div class="row">
                            
                            <div  class="col-sm-9" >
                                <fieldset disabled>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Nombre</label>
                                            <input type="text" class="form-control" value="<%= paquete.getNombre() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Descripcion</label>
                                            <textarea rows = "4" cols = "40" disabled><%= paquete.getDescripcion() %></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Fecha de Alta</label>
                                            <input type="text" class="form-control" value="<%= paquete.getFechaAlta() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Descuento</label>
                                            <input type="text" class="form-control" value="<%= paquete.getDescuento() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Validez</label>
                                            <input type="text" class="form-control" value="<%= paquete.getValidez() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Costo</label>
                                            <input type="text" class="form-control" value="<%= paquete.getCosto() %>" >
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="m-3">
                    <label>Actividades<span class="text-info"> - Seleccione una actividad para listar su informacion.</span></label>
                    <select name="actividad" class="form-select" single>
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
                 <%                      
                    if (selectedActividad != null) {
                    DTActividadTuristica actividad = (DTActividadTuristica) request.getAttribute("actividad");
                %>
                <div class="container w-100">
                        <div class="row">
                            
                            <div  class="col-sm-9" >
                                <fieldset disabled>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Nombre</label>
                                            <input type="text" class="form-control" value="<%= actividad.getNombre() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Descripcion</label>
                                            <textarea rows = "4" cols = "40" disabled><%= actividad.getDescripcion() %></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Categoria</label>
                                            
                                            <input type="text" class="form-control" value="<%= actividad.getCategoriasString() %>" >
                                        </div>
                                    
                                    
                                        <div class="col m-3">
                                            <label>Ciudad</label>
                                            
                                            <input type="text" class="form-control" value="<%= actividad.getCiudad() %>" >
                                        </div>
                                    </div>
                                    <div class="row">    
                                        <div class="col m-3">
                                            <label>Duracion</label>
                                            <input type="text" class="form-control" value="<%= actividad.getDuracion() %>" >
                                        </div>
                                        
                                        <div class="col m-3">
                                            <label>Costo</label>
                                            <input type="text" class="form-control" value="<%= actividad.getCosto() %>" >
                                        </div>
                                         <div class="col m-3">
                                            <label>Fecha de alta</label>
                                            <input type="text" class="form-control" value="<%= actividad.getFechaAlta() %>" >
                                        </div>
                                         
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                <% } %>
                <% } %>
                </form>
        </div>
    <script>
            function cleanParameters(parameters) {
                var form = document.getElementById("consultaForm");

                parameters.forEach(function (fieldName) {
                    if (form.elements[fieldName]) {
                        form.elements[fieldName].value = null;
                    }
                });
            }

            var paquetesSelect = document.querySelector('select[name="paquetes"]');
            paquetesSelect.addEventListener('change', function () {
                cleanParameters(["actividad"]);
            document.getElementById("consultaForm").submit();
            });
            
            var actividadSelect = document.querySelector('select[name="actividad"]');
            actividadSelect.addEventListener('change', function () {
                
                document.getElementById("consultaForm").submit();
            });
            
    </script>
    <div></div>
    <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    
    </body>
    
</html>
