<%-- 
    Document   : header
    Created on : 5 oct. 2023, 09:32:22
    Author     : progav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
    </head>
    <header class=" w-100 bg-dark text-light d-flex align-items-center flex-grow-0 flex-column">
        <div class="w-100 d-flex justify-content-between align-items-center p-2">
            <h1>Turismo Uy!</h1>
            
            <%if(session.getAttribute("isLogged") != null && (Boolean) session.getAttribute("isLogged")){%>
                <div class="d-flex flex-column gap-2">
                    <p> @<%=session.getAttribute("sessionNickname")%></p>
                    <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
                        <span class="flex-grow-0 badge text-bg-info">Turista</span>
                     <%}else{%>
                        <span type="button" class="badge text-bg-warning">Proveedor</span>
                     <%}%>
                    <button type="button" class="btn btn-outline-danger" onclick="logout()">Cerrar sesión</button>
                    <script>
                        function logout (){
                            window.location.href = "/turismouy/logout";
                        }
                    </script>
                </div>
            <%}else{%>
                <div class="d-flex justify-content-end align-items-center gap-2">
                    <p class="m-0">Actualmente estas navegando como invitado</p>
                    <button type="button" class="btn btn-outline-success" onclick="toLogin()">Iniciar sesión</button>
                    <script>
                        function toLogin (){
                            window.location.href = "/turismouy/login";
                        }
                    </script>
                </div>
            <%}%>
        </div>
        <div class="w-100 d-flex bg-body-secondary">
            <ul class="w-100 list-unstyled m-0 d-flex flex-row p-2 justify-content-around align-items-center">
                <li><a class="fs-5 p-2 text-dark text-decoration-none" href="/turismouy/home">Inicio</a></li>
                <li><a class="fs-5 p-2 text-dark text-decoration-none" href="/turismouy/altausuario">Usuarios</a></li>
                <li><a class="fs-5 p-2 text-dark text-decoration-none" href="/turismouy/consultaactividad">Actividades</a></li>
                <li><a class="fs-5 p-2 text-dark text-decoration-none" href="/turismouy/altasalida">Salidas</a></li>
                <li><a class="fs-5 p-2 text-dark text-decoration-none" href="/turismouy/home">Paquetes</a></li>
            </ul>
        </div>
    </header>
</html>
