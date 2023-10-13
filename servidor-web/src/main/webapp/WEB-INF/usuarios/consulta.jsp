<%-- 
    Document   : listar
    Created on : 5 oct. 2023, 12:06:58
    Author     : alexis, nacho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataTypes.DTUsuario"%>
<%@page import="java.util.List"%> 
<%--<%@page errorPage="/WEB-INF/errorPages/500.jsp" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>TurismoUy | Consulta Usuario</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
        <div id="listar" class="main">
            <% 
                List<DTUsuario> usuarios = (List<DTUsuario>) request.getAttribute("usuarios");

                for(DTUsuario u: usuarios){
            %>
            <div class="usuario">
                <!--<img src="/media/images/defecto.gif" alt="foto"/>-->

                <div class="derecha">
                    <a class="nombre" href="?usuario=<%= u.getEmail()  %>">
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