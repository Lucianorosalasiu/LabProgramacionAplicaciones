<%-- 
    Document   : success
    Created on : Oct 8, 2023, 12:34:54 AM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Éxito</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body class="h-100 d-flex flex-column">
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <h1><%= request.getAttribute("successMessage") %></h1>
        </div>
    
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
