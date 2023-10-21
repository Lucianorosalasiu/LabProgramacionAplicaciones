<%-- 
    Document   : 403
    Created on : 5 oct. 2023, 12:48:33
    Author     : todos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <link rel="stylesheet" href="assets/css/error.css"/>
        <title>Turismouy | Error 403</title>
    </head>

    <jsp:include page="/WEB-INF/templates/header.jsp"/>

    <body id="error_page" class="h-100 d-flex flex-column">
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1 titulo_error">
            <p class="fw-bold">403: Acceso no autorizado</p>
        </div>
        
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <img class="" width="100" height="100" src="/assets/img/403.png" alt="Error 403"/>
        </div>
        
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <p>
                No cuentas con los <span class="fw-bold text-danger">permisos</span> necesarios para acceder aquí. 
                Si deseas, puedes <a href="/home">volver</a> al inicio.
            </p>
        </div>
        
        
        
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
