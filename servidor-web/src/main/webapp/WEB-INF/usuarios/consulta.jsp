<%-- 
    Document   : listar
    Created on : 5 oct. 2023, 12:06:58
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTUsuario"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.Base64"%> 
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
            <h3>Consulta de usuario</h3>
            <hr />
            <%
            List<DTUsuario> usuarios = (List<DTUsuario>) request.getAttribute("usuarios");

            for (DTUsuario u : usuarios) {
                String profileImageUrl = u.getProfileImageUrl();
            %>     
                <div class="usuario">
                    <img src="<%= profileImageUrl %>" class="rounded-circle">
                    <div class="derecha">
                        <a class="nombre" href="?usuario=<%= u.getEmail() %>">
                            <%= u.getName() %>
                        </a>
                        <span class="email">
                            <%= u.getEmail() %>
                        </span>
                    </div>
                </div>
            <% } %>
        </div>
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
