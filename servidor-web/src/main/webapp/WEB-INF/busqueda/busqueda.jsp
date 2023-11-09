<%-- 
    Document   : busqueda
    Created on : Nov 9, 2023, 4:28:43 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/> 
        <title>TurismoUy | Login</title>
    </head>
    <%
    String userAgent = request.getHeader("User-Agent");
    if(userAgent != null && userAgent.toLowerCase().contains("mobile")){%>
    <jsp:include page="/WEB-INF/templates/mobileHeader.jsp"/>
    <%}else{%>
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%}%>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <h1>Aca va ir los resultados de la busqueda jeje</h1>
        </div>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
