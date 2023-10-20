<%-- 
    Document   : detalles
    Created on : Oct 19, 2023, 4:31:55 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTActividadTuristica"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <title>Turismouy | Consulta actividad</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%
        DTActividadTuristica actividad = (DTActividadTuristica) request.getAttribute("actividad");
        String foto = (String) request.getAttribute("foto");
        String categorias = (String) request.getAttribute("categorias");
    %>
    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-column flex-grow-1 justify-content-start p-2">
            <h5>Viendo en detalle la actividad <span class="fw-bold text-primary"><%=actividad.getNombre()%></span></h5>
            <div class="d-flex gap-2">
                <% String[] categoriasSeparadas = categorias.split("\\|");
                for (String categoriaIndividual : categoriasSeparadas) {%>
                    <span class="badge text-bg-success"><%=categoriaIndividual%></span>
                <%}%>
            </div>
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
                                        <label>Id</label>
                                        <input type="text" class="form-control" value="#<%= actividad.getId() %>" >
                                    </div>
                                    <div class="col m-3">
                                        <label>Descripcion</label>
                                        <textarea rows = "4" cols = "40" disabled><%= actividad.getDescripcion() %></textarea>
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
                                    <div class="col m-3">
                                        <label>Foto</label>
                                        <img src="<%= foto %>" class="card-img-top" alt="...">
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        
        </div>
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
