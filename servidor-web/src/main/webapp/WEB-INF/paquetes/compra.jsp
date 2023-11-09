<%-- 
    Document   : compra
    Created on : Oct 17, 2023, 2:58:30 AM
    Author     : lucho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTDepartamento, dataTypes.DTActividadTuristica, dataTypes.DTSalidaTuristica,dataTypes.DTPaqueteActividadTuristica"%>

<%@page import="java.util.List"%> 
<%@page import="java.util.Base64"%> 
<%@page import="logica.fabrica.Fabrica"%> 
<%@page import="logica.interfaces.IControlador"%> 
<%@page import="webservice.DtActividadTuristica"%>
<%@page import="webservice.DtActividadTuristicaWS"%>
<%@page import="webservice.DtActividadesCollectionWS"%>
<%@page import="webservice.DtPaqueteActividadTuristica"%>
<%@page import="webservice.DtPaqueteWS"%>

<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Compra de Paquete</title>
    </head>
     <jsp:include page="/WEB-INF/templates/header.jsp"/> 
    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-grow-1 flex-column">
            <form class="d-flex flex-column flex-grow-1" method="post" action="/comprapaquete" id="consultaForm">
                <div class="flex-grow-1 m-3">
                    <label>Paquetes<span class="text-info"> - Seleccione un paquete para listar su informacion.</span></label>
                    <select name="paquetes" class="form-select" single>
                        <option value="" disabled selected>- seleccionar paquete -</option>
                        <% 
                            String selectedPaquete = request.getParameter("paquetes");
                            for(DtPaqueteWS paquete : (List<DtPaqueteWS>) request.getAttribute("paquetesEnteros")){
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
                        DtPaqueteWS paqueteSeleccionado = null;
                        for(DtPaqueteWS paquete : (List<DtPaqueteWS>) request.getAttribute("paquetesEnteros")){
                            if(paquete.getNombre().equals(selectedPaquete)){
                                paqueteSeleccionado = paquete;
                            }
                        }
                        
                    %>
                    <div class="container w-100">
                        <div class="row">
                            
                            <div  class="col-sm-9" >
                                <div class="d-flex flex-row flex-wrap gap-2 p-2 justify-content-center">
                                    <% 
                                        Fabrica fabrica = new Fabrica();
                                        IControlador controlador = fabrica.getInterface();
                                        String imageDataUri = "";
                                                byte [] foto = (byte[]) request.getAttribute("foto1");
                                                    if(foto != null){
                                                        String imagenBase64 = Base64.getEncoder().encodeToString(foto);
                                                        String contentType = "image/jpeg";
                                                        imageDataUri = "data:" + contentType + ";base64," + imagenBase64;
                                                    }
                                    %>
                                
                                
                                </div>
                                <fieldset disabled>
                                    <div class="row">
                                        <div class="col m-3">
                                        <img src="<%= imageDataUri %>" width="400" height="200" class="card-img-top" alt="...">
                                        </div>
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
                                            <input type="number" name="costo"  id="costo" class="form-control" value="<%= paqueteSeleccionado.getCosto() %>" >
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="form-group">
                                 <label>Ingrese la cantidad de personas que asistiran al paquete</label>
                                <input name="personas" type="number" class="form-control" id="personas" value="0" min=0 onChange="updateInfo();">
                            </div>
                            <fieldset disabled>            
                                <div class="form-group">
                                    <label>Costo Total de la compra</label>
                                    <input type="text" name="costoT"  id="costoT" class="form-control" value="0" min=1 >
                                </div>
                            </fieldset disabled>
                        </div>
                    </div>
                </div>
            <div class="container w-100 p-2">
             <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
                <button type="submit" class="btn btn-success" name="BOTON" value="compra">Realizar Compra</button>
               <%}else{%>
                <label>Solo los <span class="text-info"> turistas </span> pueden comprar paquetes</label>
                <button type="button" class="btn btn-success disabled" tabindex="-1">Realizar Compra</button>
            <%}%> 
           
            <button  type="button" value="cancelar" class="btn btn-danger" onclick="location.href = '/home'">Cancelar</button>   
            <label><span class="text-info"> (este boton lo redirigira al inicio) </span></label>
            </div>    
            <%if(request.getAttribute("errorMessage") != null){%>
                <div class="m-3 p-3 bg-danger bg-opacity-10 border border-danger rounded text-danger">
                    <p class="m-0"><%=request.getAttribute("errorMessage")%></p>
                </div>
                <%}%>
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
            function updateInfo() {
                var cantidadPersonas = document.getElementById("personas").value;
                var costo = document.getElementById("costo").value;
                document.getElementById("costoT").value = costo*cantidadPersonas;
            }
            var paquetesSelect = document.querySelector('select[name="paquetes"]');
            paquetesSelect.addEventListener('change', function () {   
                document.getElementById("consultaForm").submit()
            });
            var reset = document.querySelector('select[name="reset"]');
            paquetesSelect.addEventListener('change', function () {
                cleanParameters(["paquete"]);
            document.getElementById("consultaForm").submit();
            });
            
    </script>
    
    <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
