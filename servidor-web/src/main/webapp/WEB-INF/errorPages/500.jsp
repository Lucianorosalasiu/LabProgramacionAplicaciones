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
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <title>Turismouy | Error 500</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body id="error_page" class="h-100 d-flex flex-column">
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1 titulo_error">
            500: Error de servidor    
        </div>
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <p>
                Ha ocurrido un error al procesar la información. 
                Si deseas, puedes <a href="/home">volver</a> al inicio.
            </p>
        </div>
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
