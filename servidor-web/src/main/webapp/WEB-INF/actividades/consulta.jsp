<%-- 
    Document   : consulta
    Created on : Oct 8, 2023, 6:14:14 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTActividadTuristica"%>
<%@page import="dataTypes.DTDepartamento"%>
<%@page import="dataTypes.DTCategoria"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.Base64"%> 
<%@page import="logica.fabrica.Fabrica"%> 
<%@page import="logica.interfaces.IControlador"%> 

<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
       <title>TurismoUy | Consulta actividades turísticas</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
        
    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-column flex-grow-1 justify-content-start">
            <form class="d-flex flex-row gap-2 p-2 w-100 justify-content-between align-items-center border-bottom border-info" method="post" action="/consultaactividad">
                <%if(request.getAttribute("errorMessage") != null){%>

                <div class="mt-3 p-3 bg-danger bg-opacity-10 border border-danger rounded text-danger">
                    <p class="m-0"><%=request.getAttribute("errorMessage")%></p>
                </div>

                <%}%>
            <label>Seleccione un valor por el cual buscar actividades turísticas</label>         
            <div class="form-group">
                <label>Departamento</label>
                <select class="text-light form-select bg-primary" name="departamento" onchange="this.form.submit();">
                    <option value="" disabled selected>- seleccione un departamento -</option>
                    <% 
                        for (DTDepartamento departamento : (List<DTDepartamento>) request.getAttribute("departamentos")) {
                            String nombreDepartamento = departamento.getNombre();
                    %>
                    <option value="<%= departamento.getNombre() %>">
                        <%= nombreDepartamento %>
                    </option>		
                    <% } %>
                </select>
            </div>
                
            <div class="form-group">
                <label>Categoria</label>
                <select class="text-light form-select bg-primary" name="categoria" onchange="this.form.submit();">
                    <option value="" disabled selected>- seleccione una categoria -</option>
                    <% 
                        for(DTCategoria c : (List<DTCategoria>) request.getAttribute("categorias")){
                            String nombreCategoria = c.getNombre();
                    %>
                    <option value="<%=nombreCategoria%>">
                        <%= nombreCategoria %>
                    </option>		
                    <% } %>
                </select>
            </div> 
                
            </form>
   
            <%if(request.getAttribute("actividades") != null){%>
            <div class="d-flex flex-row flex-wrap gap-2 p-2 justify-content-center">
                <% 
                    Fabrica fabrica = new Fabrica();
                    IControlador controlador = fabrica.getInterface();
                    String imageDataUri = "";
                        for(DTActividadTuristica actividad : (List<DTActividadTuristica>) request.getAttribute("actividades")){
                            byte [] foto = controlador.obtenerFotoActividadTuristica(actividad.getId());
                                if(foto != null){
                                    String imagenBase64 = Base64.getEncoder().encodeToString(foto);
                                    String contentType = "image/jpeg";
                                    imageDataUri = "data:" + contentType + ";base64," + imagenBase64;
                                }
                %>
                    <div class="card" style="width: 18rem;">
                    <img src="<%= imageDataUri %>" class="card-img-top" alt="...">
                        <div class="card-body">
                          <h5 class="card-title"><%=actividad.getNombre()%></h5>
                          <p class="card-text"><%=actividad.getDescripcion()%></p>
                          <a href="#" class="btn btn-primary">Ver detalles</a>
                        </div>
                    </div>
                <% } %>
            </div>
            <% }else{%>
            <div class="d-flex flex-row flex-wrap gap-2 p-2 justify-content-center p-2">
                <label>No se encontraron actividades turisticas con los parametros indicados. <a href="/altaactividad">Crea una tu mismo!</a></label><%}%>
            </div>
        </div>
        
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
