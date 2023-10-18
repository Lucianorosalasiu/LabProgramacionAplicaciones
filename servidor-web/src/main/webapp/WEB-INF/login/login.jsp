<%-- 
    Document   : login
    Created on : Oct 5, 2023, 3:47:03 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/> 
        <title>TurismoUy | Login</title>
    </head>

    <jsp:include page="/WEB-INF/templates/header.jsp"/>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">
            <form action="/login" method="post">
                <div class="mb-3">
                    <label class="form-label">Nickname o Email</label>
                    <input type="text" class="form-control" name="nickname">
                    <div class="form-text">Puede que compartamos tus datos con alguien, oops!</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <div class="d-flex justify-content-evenly align-items-center gap-2">
                    <button type="button" class="btn btn-secondary" onclick="toHome()">Navegar como invitado</button>
                    <script>
                        function toHome() {
                            window.location.href = "/home";
                        }
                    </script>
                    <button type="button" class="btn btn-primary" onclick="toNewUser()">Registrarse</button>
                    <script>
                        function toNewUser() {
                            window.location.href = "/altausuario";
                        }
                    </script>
                    <button type="submit" class="btn btn-success">Iniciar sesión</button>
                </div>
                <%if(request.getAttribute("errorMessage") != null){%>
                <div class="mt-3 p-3 bg-danger bg-opacity-10 border border-danger rounded text-danger">
                    <p class="m-0"><%=request.getAttribute("errorMessage")%></p>
                </div>
                <%}%>

                <%if(request.getSession().getAttribute("isLogged") != null){%>
                <script>
                    window.location.href = "/home";
                </script>
                <%}%>
            </form>
        </div>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
