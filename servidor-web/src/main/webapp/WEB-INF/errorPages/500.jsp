<%-- 
    Document   : 500
    Created on : 5 oct. 2023, 12:48:14
    Author     : progav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <link rel="stylesheet" href="assets/css/error.css"/>
        <title>Turismouy | Error 500</title>
    </head>
    
    <%
    String userAgent = request.getHeader("User-Agent");
    if(userAgent != null && userAgent.toLowerCase().contains("mobile")){%>
            <jsp:include page="/WEB-INF/templates/mobileHeader.jsp"/>
    <%}else{%>
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%}%>
    
    <body id="error_page" class="h-100 d-flex flex-column">
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1 titulo_error">
            <p class="fw-bold">500: Error de servidor</p>
        </div>
        
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <img class="" width="100" height="100" src="/assets/img/500.png" alt="Error 500"/>
        </div>
        
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <p>
                Ha ocurrido un <span class="fw-bold text-danger">error al procesar la información.</span> 
                Si deseas, puedes <a href="/home">volver</a> al inicio.
            </p>
        </div>
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
