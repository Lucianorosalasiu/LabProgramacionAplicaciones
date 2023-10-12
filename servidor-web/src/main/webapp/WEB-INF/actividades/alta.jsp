<%-- 
    Document   : alta
    Created on : Oct 11, 2023, 4:57:31 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTActividadTuristica"%>
<%@page import="dataTypes.DTDepartamento"%>
<%@page import="dataTypes.DTCategoria"%>
<%@page import="java.util.List"%> 
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
       <title>TurismoUy | Alta actividad turística</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body class="h-100 d-flex flex-column">
        <div class="flex-grow-1 d-flex justify-content-center align-items-center">
            <form class="d-flex flex-column gap-2 p-2 flex-wrap" method="post" action="/turismouy/altaactividad">
                
                <div class="form-group">
                    <label>Departamento</label>
                    <select class="text-light form-select bg-primary" name="departamento" onchange="this.form.submit();">
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
                
                <div class="form-group">
                  <label >Nombre de la actividad<span class="text-danger"> (debe ser unico). </span></label>
                  <input name="nombre" class="form-control" id="exampleInputEmail1" placeholder="Ingresa un nombre">
                </div>
                
                <div class="form-group">
                  <label>Descripción</label>
                  <textarea name="descripcion" class="form-control" id="exampleInputPassword1" placeholder="Ingresa una descripción"></textarea>
                </div>
                
                <div class="form-group">
                  <label>Duración en horas</label>
                  <input name="duracion" type="number" class="form-control" id="exampleInputEmail1" value="0" min="0">
                </div>
                
                <div class="form-group">
                  <label>Costo en pesos uruguayos</label>
                  <input name="costo" type="number" class="form-control" id="exampleInputEmail1" value="0" min="0">
                </div>
                
                <div class="form-group">
                  <label>Ciudad</label>
                  <input name="ciudad" type="text" class="form-control" id="exampleInputEmail1">
                </div>
                
                <div class="form-group">
                    <label class="form-label">Imagen de la actividad <span class="text-info">(Opcional).</span></label>
                    <input class="form-control" type="file" id="formFile">
                </div>

                <div class="form-group">
                    <label>Categorias<span class="text-info"> (Ctrl + Click izquierdo selección multiple).</span></label>
                    <select name="categoria" class="form-select" multiple>
                    <%for(DTCategoria c : (List<DTCategoria>) request.getAttribute("categorias")){%> 
                    
                    <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                    
                    <%}%>
                    </select>
                </div>
                
                <button type="submit" class="btn btn-success">Enviar</button>
            </form>
        </div>
    </body>
    
    <%if(request.getAttribute("info") != null){%>
    <div>
        <p><%= (String) request.getAttribute("info")%></p>
    </div>
    <%}%>
    
    <jsp:include page="/WEB-INF/templates/footer.jsp"/>
</html>
