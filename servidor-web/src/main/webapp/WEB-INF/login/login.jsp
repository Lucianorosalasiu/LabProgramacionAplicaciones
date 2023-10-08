<%-- 
    Document   : login
    Created on : Oct 5, 2023, 3:47:03 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TurismoUy | Login</title>
    </head>

    <jsp:include page="/WEB-INF/templates/header.jsp"/>

    <body class="h-100 d-flex flex-column">
        <h5>esto es un placeholder para visualizar los datos de sesion nomas</h5>
        <h5>tu session.nickname es: <%=session.getAttribute("sessionNickname")%></h5>
        <h5>tu session.email es: <%=session.getAttribute("sessionEmail")%></h5>
        <h5>tu session.type es: <%=session.getAttribute("sessionType")%></h5>
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <form action="/turismouy/login" method="post">
                <div class="mb-3">
                  <label class="form-label">Nickname</label>
                  <input type="text" class="form-control" name="nickname">
                  <div class="form-text">Puede que compartamos tus datos con alguien, oops!</div>
                </div>
                <div class="mb-3">
                  <label class="form-label">Contraseña</label>
                  <input type="password" class="form-control" name="password">
                </div>
                <div class="d-flex justify-content-evenly align-items-center">
                    <button type="button" class="btn btn-secondary" onclick="redirect()">Iniciar sesión como invitado</button>
                    <script>
                        function redirect (){
                         window.location.href = "/turismouy/home";
                        }
                    </script>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </div>
            </form>
        </div>
    </body>

    <jsp:include page="/WEB-INF/templates/footer.jsp"/>
</html>
