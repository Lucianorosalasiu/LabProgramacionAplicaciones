<%-- 
    Document   : consulta
    Created on : Oct 17, 2023, 12:26:21 AM
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTDepartamento, dataTypes.DTActividadTuristica, dataTypes.DTSalidaTuristica,dataTypes.DTPaqueteActividadTuristica"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.List"%> 
<%@page import="java.util.Base64"%> 
<%@page import="logica.fabrica.Fabrica"%> 
<%@page import="logica.interfaces.IControlador"%>
<%@page import="java.util.HashSet"%> 
<%@page import="java.util.LinkedHashSet"%>
<%@page import="webservice.DtActividadTuristica"%>
<%@page import="webservice.DtActividadTuristicaWS"%>
<%@page import="webservice.DtActividadesCollectionWS"%>
<%@page import="webservice.DtPaqueteActividadTuristica"%>


<!DOCTYPE html>
<html class="h-100">
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
                        DtPaqueteActividadTuristica paquete = (DtPaqueteActividadTuristica) request.getAttribute("paqueteEnteros");
                    %>
                    <div class="container w-100">
                        <div class="row">
                            
                            <div  class="col-sm-9" >
                                <fieldset disabled>
                                    <div class="row">
                                        <% 
                                        Fabrica fabrica = new Fabrica();
                                        IControlador controlador = fabrica.getInterface();
                                        String imageDataUri = "";
                                                byte [] foto = ( byte[]) request.getAttribute("foto1");
                                                    if(foto != null){
                                                        String imagenBase64 = Base64.getEncoder().encodeToString(foto);
                                                        String contentType = "image/jpeg";
                                                        imageDataUri = "data:" + contentType + ";base64," + imagenBase64;
                                                    }
                                                 LinkedHashSet<String> categorias = ( LinkedHashSet<String>) request.getAttribute("categorias");
                                        %>
                                        <div class="col m-3">
                                            <img src="<%= imageDataUri %>" width="400" height="200" class="card-img-top" alt="...">
                                        </div>
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
                                        <div class="d-flex gap-2">
                                            <% 
                                            
                                            for (String categoriaIndividual : categorias) {%>
                                                <span class="badge text-bg-success"><%=categoriaIndividual%></span>
                                            <%}%>
                                          </div>
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
                            for(DtActividadTuristicaWS actividad : (List<DtActividadTuristicaWS>) request.getAttribute("actividades")){
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
                    DtActividadTuristica actividad = (DtActividadTuristica) request.getAttribute("actividad");
                    String image = "";
                        
                            byte [] foto2 = ( byte[]) request.getAttribute("foto2");
                                if(foto2 != null){
                                    String imagenBase64 = Base64.getEncoder().encodeToString(foto2);
                                    String contentType = "image/jpeg";
                                    image = "data:" + contentType + ";base64," + imagenBase64;
                                }
                %>
                <div class="container w-100">
                        <div class="row">
                            
                            <div  class="col-sm-9" >
                                
                                <fieldset disabled>
                                    <div class="row">
                                        <div class="col m-3">
                                            <img src="<%= image %>" width="400" height="200" class="card-img-top" alt="...">
                                        </div>
                                        
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
