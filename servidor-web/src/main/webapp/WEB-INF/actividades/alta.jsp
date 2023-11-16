<%-- 
    Document   : alta
    Created on : Oct 11, 2023, 4:57:31 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%> 
<%@page import="webservice.DtDepartamentosCollectionWS"%>
<%@page import="webservice.DtDepartamentoWS"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
       <title>TurismoUy | Alta actividad turística</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body class="h-100 d-flex flex-column">
        <div class="flex-grow-1 d-flex justify-content-center align-items-center">
            <form class="d-flex flex-column gap-2 p-2" method="post" action="/altaactividad" enctype="multipart/form-data">
                
                <%if(request.getAttribute("errorMessage") != null){%>

                <div class="mt-3 p-3 bg-danger bg-opacity-10 border border-danger rounded text-danger">
                    <p class="m-0"><%=request.getAttribute("errorMessage")%></p>
                </div>

                <%}%>
                
                <div class="form-group">
                    <label>Departamento</label>
                    <select class="text-light form-select bg-primary" name="departamento">
                        <option value="" disabled selected>- seleccione un departamento -</option>
                        <% 
                            String selectedDepartamento = request.getParameter("departamento");
                            DtDepartamentosCollectionWS departamentos = (DtDepartamentosCollectionWS) request.getAttribute("departamentos");
                            
                            for (DtDepartamentoWS departamento : departamentos.getDepartamentos()) {
                                String nombreDepartamento = departamento.getNombre();
                        %>
                        <option value="<%=departamento.getId()%>" <% if (nombreDepartamento.equals(selectedDepartamento)) { %>selected <% } %>>
                            <%= nombreDepartamento %>
                        </option>		
                        <% } %>
                    </select>
                </div>
                
                <div class="form-group">
                  <label >Nombre de la actividad<span class="text-danger"> (debe ser unico). </span></label>
                  <input name="nombre" class="form-control" placeholder="Ingresa un nombre">
                </div>
                
                <div class="form-group">
                  <label>Descripción</label>
                  <textarea name="descripcion" class="form-control" placeholder="Ingresa una descripción"></textarea>
                </div>
                
                <div class="form-group">
                  <label>Duración en horas</label>
                  <input name="duracion" type="number" class="form-control" value="0" min="0">
                </div>
                
                <div class="form-group">
                  <label>Costo en pesos uruguayos</label>
                  <input name="costo" type="number" class="form-control" value="0" min="0">
                </div>
                
                <div class="form-group">
                  <label>Ciudad</label>
                  <input name="ciudad" type="text" class="form-control">
                </div>
                    
                <div class="form-group">
                    <label><span class="text-info">(Opcional)</span>ID de video de youtube ej: www.youtube.com/watch?v=<span class="text-info">Z5vZovv8cPk</span></label>
                  
                  <input name="url" type="text" class="form-control">
                </div>
                
                <div class="form-group">
                    <label class="form-label"><span class="text-info">(Opcional)</span>Imagen de la actividad </label>
                    <input name="imagen" class="form-control" type="file" id="formFile" accept="image/*">
                </div>
                    
                <div class="form-group">
                    <label class="form-label">Fecha de alta</label>  
                    <input name="fecha" type="datetime-local">
                </div>

                <div class="form-group">
                    <label>Categorias<span class="text-info"> (Ctrl + Click izquierdo selección multiple).</span></label>
                    <select name="categoria" class="form-select" multiple>
                        <%
                            String categoriasNombres = (String) request.getAttribute("categoriasNombres");
                            String categoriasIds = (String) request.getAttribute("categoriasIds");
                            
                            String[] categoriasNombresSplit = categoriasNombres.split(",");
                            String[] categoriasIdsSplit = categoriasIds.split(",");
                            
                            int index = 0;

                            for (String categoriaNombreIndividual : categoriasNombresSplit) {
                        %> 
                    
                    <option value="<%=categoriasIdsSplit[index]%>"><%=categoriaNombreIndividual%></option>
                    
                    <%index+=1;}%>
                    </select>
                </div>
                    
                <%if("PROVEEDOR".equals((String) session.getAttribute("sessionType"))){%>
                <button type="submit" class="btn btn-success">Enviar</button>
                <%}else{%>
                <label>Solo los <span class="text-info"> proveedores </span> pueden dar de alta una actividad</label>
                <button type="button" class="btn btn-success disabled" tabindex="-1">Enviar</button>
                <%}%>
            </form>
        </div>    
            
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
