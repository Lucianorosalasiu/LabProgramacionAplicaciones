<%-- 
    Document   : detalles
    Created on : Oct 19, 2023, 4:31:55 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTActividadTuristica"%>
<%@page import="dataTypes.DTSalidaTuristica"%>
<%@page import="dataTypes.DTPaqueteActividadTuristica"%>
<%@page import="java.util.List"%> 
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <title>Turismouy | Consulta actividad</title>
    </head>
    
    <%
    String userAgent = request.getHeader("User-Agent");
    if(userAgent != null && userAgent.toLowerCase().contains("mobile")){%>
            <jsp:include page="/WEB-INF/templates/mobileHeader.jsp"/>
    <%}else{%>
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%}%>
    <%
        DTActividadTuristica actividad = (DTActividadTuristica) request.getAttribute("actividad");
        List<DTSalidaTuristica> salidas = (List<DTSalidaTuristica>) request.getAttribute("salidas");
        List<DTPaqueteActividadTuristica> paquetes = (List<DTPaqueteActividadTuristica>) request.getAttribute("paquetes");
        String foto = (String) request.getAttribute("foto");
        String categorias = (String) request.getAttribute("categorias");
        String urlVideo = actividad.getUrl();
        int cantidadFavoritos = (int) request.getAttribute("cantidadFavoritos");
        int vistas = actividad.getCantidadVistas() + 1;
    %>
    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-column flex-grow-1 justify-content-start p-2">
            <h5>Viendo en detalle la actividad <span class="fw-bold text-primary"><%=actividad.getNombre()%></span></h5>
            <span class="text-secondary">Vistas: <%=vistas%></span>
            <span class="text-secondary">Favoritos: <%=cantidadFavoritos%></span>
            <div class="d-flex gap-2">
                <% String[] categoriasSeparadas = categorias.split("\\|");
                for (String categoriaIndividual : categoriasSeparadas) {%>
                    <span class="badge text-bg-success"><%=categoriaIndividual%></span>
                <%}%>
            </div>
            <div class="container w-100">
                    <div class="row justify-content-center">
                        <div  class="col-sm-9" >
                            <fieldset disabled>
                                <div class="row">
                                    <div class="col m-3">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" value="<%= actividad.getNombre() %>" >
                                    </div>

                                    <div class="col m-3">
                                        <label>Id</label>
                                        <input type="text" class="form-control" value="#<%= actividad.getId() %>" >
                                    </div>
                                    <div class="col d-flex flex-column">
                                        <label>Descripción</label>
                                        <textarea rows = "4" disabled><%= actividad.getDescripcion() %> </textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col m-3">
                                        <label>Ciudad</label>
                                        <input class="form-control" type="text" value="<%=actividad.getCiudad()%>" readonly>
                                    </div>
                                    <div class="col m-3">
                                        <label>Costo</label>
                                        <input class="form-control" type="text" value="<%=actividad.getCosto()%>" readonly>
                                    </div>
                                    <div class="col m-3">
                                        <label>Duración (en horas)</label>
                                        <input class="form-control" type="text" value="<%=actividad.getDuracion()%>" readonly>
                                    </div>
                                    
                                </div>
                                <div class="row">
                                    <div class="col m-3">
                                        <label>Fecha alta</label>
                                        <input class="form-control" type="text" value="<%=actividad.getFechaAlta()%>" readonly>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col m-3 d-flex flex-column align-items-center">
                                        <label>Foto</label>
                                        <img src="<%= foto %>" class="card-img-top" alt="..." style="max-width: 500px">
                                    </div>
                                </div>
                                <%if(!urlVideo.equals("")){%>
                                <div class="row">
                                    <div class="col m-3 d-flex flex-column ratio ratio-16x9">
                                        <iframe src="https://www.youtube.com/embed/<%=urlVideo%>" title="YouTube video" allowfullscreen></iframe>
                                    </div>
                                </div>
                                <%}%>
                            </fieldset>
                        </div>
                    </div>
                                    
                    <div class="border rounded border-tertiary p-2 overflow-x-scroll">
                    <h4>Salidas disponibles de la actividad <span class="fw-bold text-primary"><%= actividad.getNombre() %></span></h4>
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
                            
                    <div class="border rounded border-tertiary p-2 mt-2 overflow-x-scroll">
                        <h4>Paquetes que contienen a la actividad <span class="fw-bold text-primary"><%= actividad.getNombre() %></span></h4>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Descripción</th>
                                    <th scope="col">Descuento</th>
                                    <th scope="col">Fecha de alta</th>
                                    <th scope="col">Validez</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                    for(DTPaqueteActividadTuristica paquete : paquetes){
                                %>
                                <tr>
                                    <td><%= paquete.getNombre() %></td>
                                    <td><%= paquete.getDescripcion() %></td>
                                    <td><%= paquete.getDescuento() %></td>
                                    <td><%= paquete.getFechaAlta() %></td>
                                    <td><%= paquete.getValidez() %></td>
                                </tr>		
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        
        </div>
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
