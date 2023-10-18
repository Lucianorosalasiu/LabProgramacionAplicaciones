<%-- 
    Document   : index
    Created on : 5 oct. 2023, 09:48:00
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Home</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body class="h-100 d-flex flex-column">
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <h1>¡Bienvenido a la página de inicio de TurismoUY!</h1>
        </div>    
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
