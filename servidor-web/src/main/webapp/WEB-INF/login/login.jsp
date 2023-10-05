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
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <form>
                <div class="mb-3">
                  <label for="exampleInputEmail1" class="form-label">Email</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                  <div id="emailHelp" class="form-text">Puede que compartamos tus datos con alguien, oops!</div>
                </div>
                <div class="mb-3">
                  <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                  <input type="password" class="form-control" id="exampleInputPassword1">
                </div>
                <div class="d-flex justify-content-evenly align-items-center">
                    <button type="submit" class="btn btn-secondary" onclick="redirect()">Iniciar sesión como invitado</button>
                    <script>
                        function redirect (){
                         window.location.href = "/turismouy/home";
                        }
                    </script>
                    <button type="submit" class="btn btn-primary" >Enviar</button>
                </div>
            </form>
        </div>
    </body>

    <jsp:include page="/WEB-INF/templates/footer.jsp"/>
</html>
