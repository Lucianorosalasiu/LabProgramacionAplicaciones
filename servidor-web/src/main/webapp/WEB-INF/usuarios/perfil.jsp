<%-- 
    Document   : perfil
    Created on : 15 oct. 2023, 21:36:42
    Author     : progav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTUsuario"%>
<%@page import="dataTypes.DTTurista"%>
<%@page import="dataTypes.DTProveedor"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <title>TurismoUy | Perfil de Usuario</title>
    </head>
    <body class="h-100 d-flex flex-column">
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
        <%
            DTUsuario usr = (DTUsuario) request.getAttribute("usuario");
            String profileImageUrl = usr.getProfileImageUrl();
        %>
        <div id="perfil" class ="container py-5 min-vh-70 flex-grow-1">
            <h3>Perfil del usuario: 
                <span class="text-info float-right"> <%= usr.getNickname() %></span>
            </h3>
            <hr />
            <div id="perfil_izquierda">
                <img src="<%= profileImageUrl %>">
            </div>
            <div id="perfil_derecha">
                <div class="contenedor">
                    <h2>Información básica</h2>
                    <label class="rotulo">Nombre completo:</label>
                    <label class="valor"><%= usr.getName()%> <%= usr.getLastName()%></label>
                    <br/>
                    <label class="rotulo">Fecha de nacimiento:</label>
                    <label class="valor">
                        <%=
                            // Formato deseado para la fecha
                            new SimpleDateFormat("dd-MM-yyyy").format(usr.getBirthDate())
                        %>
                    </label>
                    <br/>
                    <%
                    if (usr instanceof DTTurista) {
                        DTTurista turista = (DTTurista) usr;
                    %>        
                        <label class="rotulo">País de Orígen</label>
                        <label class="valor"><%= turista.getNacionality() %></label>
                        <br/>
                    <%
                    } else if (usr instanceof DTProveedor) {
                        DTProveedor proveedor = (DTProveedor) usr;
                    %>
                        <br/>
                        <label class="rotulo">Descripción:</label>
                        <label class="valor"><%= proveedor.getDescription() %></label>  
                    <%
                    }
                    %> 
                </div>

                <div class="contenedor">
                    <h2>Información de contacto</h2>
                    <label class="rotulo">Correo electrónico:</label>
                    <label class="valor">
                        <a href="mailto:<%= usr.getEmail() %>">
                            <%= usr.getEmail() %>
                        </a>
                    </label>
                    <br/>
                    <%
                    if (usr instanceof DTProveedor) {
                        DTProveedor proveedor = (DTProveedor) usr;
                    %>    
                    <label class="rotulo">Sitio web:</label>
                    <label class="valor">
                        <a href="<%= proveedor.getWebsiteURL() %>">
                            <%= proveedor.getWebsiteURL() %>
                        </a>
                    </label>
                    <%
                    }
                    %> 
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
