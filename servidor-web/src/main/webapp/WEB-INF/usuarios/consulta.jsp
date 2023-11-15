<%-- 
    Document   : listar
    Created on : 5 oct. 2023, 12:06:58
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTUsuario"%>
<%@page import="dataTypes.DTTurista"%>
<%@page import="dataTypes.DTProveedor"%>
<%@page import="java.util.List"%> 
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
                List<DTUsuario> usuarios = (List<DTUsuario>) request.getAttribute("usuarios");
                String userLogged = (String) request.getSession().getAttribute("sessionNickname");

                for (DTUsuario u : usuarios) {
                    String profileImageUrl = u.getProfileImageUrl();
                %> 
                <!-- Tarjeta Bootstrap para cada usuario -->
                <div class="card mb-4 medium-card">
                    <img src="<%= profileImageUrl %>" class="card-img-top  img-thumbnail medium-image">
                    <div class="card-header">  
                        <h5 class="card-link card-title">
                            <a href="?usuario=<%= u.getNickname() %>">
                                <%= u.getName() %>
                            </a>
                        </h5>
                    </div>        
                    <div class="card-body">
                        <% if (u instanceof DTTurista) { %>
                        <p class="text-info"> Turista </p>
                        <% } else if(u instanceof DTProveedor){ %>
                        <p class="text-warning"> Proveedor </p>
                        <% } %> 
                        <small class="text-body-secondary"><%= u.getEmail() %></small>
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
