<%-- 
    Document   : listar
    Created on : 5 oct. 2023, 12:06:58
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservice.DtUsuarioWrapper"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <title>TurismoUy | Consulta Usuario</title>
    </head>

    <body class="h-100 d-flex flex-column">
        <jsp:include page="/WEB-INF/templates/header.jsp"/>

        <div id="listar" class="container py-5 min-vh-70 flex-grow-1">
            <h3>Listado de usuarios</h3>
            <hr />
            <div class="card-deck d-flex flex-row flex-wrap gap-3 p-2 justify-content-center">
                <!-- Iterar sobre la lista de usuarios -->
                <%
                ArrayList<DtUsuarioWrapper> usuarios = (ArrayList<DtUsuarioWrapper>) request.getAttribute("usuarios");
                String userLogged = (String) request.getSession().getAttribute("sessionNickname");
                
                for (DtUsuarioWrapper u : usuarios) {
                    String image = null;
                    if (u.getPhoto() != null && !u.getPhoto().equals("")) {
                        // Si hay una imagen de perfil en formato blob, se convierte a Base64
                        String encodedImage = Base64.getEncoder().encodeToString(u.getPhoto());
                        image = "data:image/jpeg;base64," + encodedImage;
                    } else if (u.getImagePath() != null && !u.getImagePath().isBlank()) {
                        // Si hay una ruta de imagen
                        image = "https://" + u.getImagePath();
                    } else {
                        // Si no se encuentra una imagen, usa la imagen por defecto
                        image = "assets/img/defecto.jpg";
                    }
                %> 
                <!-- Tarjeta Bootstrap para cada usuario -->
                <div class="card mb-4 medium-card">
                    <img src="<%= image %>" class="card-img-top  img-thumbnail medium-image">
                    <div class="card-header">  
                        <h5 class="card-link card-title">
                            <% if (u.isTurista()) { %>
                                <a href="?usuario=<%= u.getDtt().getNickname() %>">
                                    <%= u.getDtt().getName() %>
                                </a>
                            <% } else { %>
                                <a href="?usuario=<%= u.getDtp().getNickname() %>">
                                    <%= u.getDtp().getName() %>
                                </a>
                            <% } %> 

                        </h5>
                    </div>        
                    <div class="card-body">
                        <% if (u.isTurista()) { %>
                        <p class="text-info"> Turista </p>
                        <small class="text-body-secondary"><%= u.getDtt().getEmail() %></small>
                        <% } else { %>
                        <p class="text-warning"> Proveedor </p>
                        <small class="text-body-secondary"><%= u.getDtp().getEmail() %></small>
                        <% } %> 
                    </div>
                    <% if(request.getSession().getAttribute("isLogged") != null) {%>
                    <div class="card-footer">
                        <button type="button" class="btn btn-primary">Seguir</button>
                        <button type="button" class="btn btn-danger float-right">Dejar de seguir</button>
                    </div>
                    <% } %>
                </div>
                <% } %>
            </div>
        </div>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>

    </body>
</html>
