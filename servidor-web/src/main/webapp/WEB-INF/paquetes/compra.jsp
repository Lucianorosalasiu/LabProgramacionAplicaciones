<%-- 
    Document   : compra
    Created on : Oct 17, 2023, 2:58:30 AM
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTDepartamento, dataTypes.DTActividadTuristica, dataTypes.DTSalidaTuristica,dataTypes.DTPaqueteActividadTuristica"%>
<%@page import="java.util.List"%> 
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Compra de Paquete</title>
    </head>
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <body class="h-100 d-flex flex-column">
        <div class="flex-grow-1">
            <form method="post" action="/comprapaquete" id="consultaForm">
                <div class="m-3">
                    <label>Paquetes<span class="text-info"> - Seleccione un paquete para listar su informacion.</span></label>
                    <select name="paquetes" class="form-select" single>
                        <option value="" disabled selected>- seleccionar paquete -</option>
                        <% 
                            String selectedPaquete = request.getParameter("paquetes");
                            for(DTPaqueteActividadTuristica paquete : (List<DTPaqueteActividadTuristica>) request.getAttribute("paquetesEnteros")){
                                String nombrePaquete = paquete.getNombre();
                            
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
                        DTPaqueteActividadTuristica paqueteSeleccionado = null;
                        for(DTPaqueteActividadTuristica paquete : (List<DTPaqueteActividadTuristica>) request.getAttribute("paquetesEnteros")){
                            if(paquete.getNombre().equals(selectedPaquete)){
                                paqueteSeleccionado = paquete;
                            }
                        }
                        
                    %>
                    <div class="container w-100">
                        <div class="row">
                            
                            <div  class="col-sm-9" >
                                <fieldset disabled>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Nombre</label>
                                            <input type="text" name="nombre" class="form-control" value="<%= paqueteSeleccionado.getNombre() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Descripcion</label>
                                            <textarea rows = "4" name="desc" cols = "40" disabled><%= paqueteSeleccionado.getDescripcion() %></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col m-3">
                                            <label>Fecha de Alta</label>
                                            <input type="text" name="alta" class="form-control" value="<%= paqueteSeleccionado.getFechaAlta() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Descuento</label>
                                            <input type="text" name="descuento" class="form-control" value="<%= paqueteSeleccionado.getDescuento() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Validez</label>
                                            <input type="text" name="validez" class="form-control" value="<%= paqueteSeleccionado.getValidez() %>" >
                                        </div>
                                        <div class="col m-3">
                                            <label>Costo</label>
                                            <input type="text" name="costo" class="form-control" value="<%= paqueteSeleccionado.getCosto() %>" >
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            <div class="container w-100">
             <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
                <button type="submit" class="btn btn-success" name="BOTON" value="compra">Realizar Compra</button>
               <%}else{%>
                <label>Solo los <span class="text-info"> turistas </span> pueden comprar paquetes</label>
                <button type="button" class="btn btn-success disabled" tabindex="-1">Realizar Compra</button>
            <%}%> 
            
            <button type="button" class="btn btn-danger">Cancelar</button>                       
            </div>    
            </form>
        </div>
                                        
        <% } %>
        
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
                document.getElementById("consultaForm").submit()
            });
            
            
    </script>
    </body>
    <jsp:include page="/WEB-INF/templates/footer.jsp"/>
</html>
