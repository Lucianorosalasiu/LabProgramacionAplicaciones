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
        <script src="assets/js/bootstrap.bundle.min.js"></script>
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
                            window.location.href = "/logout";
                        }
                    </script>
                </div>
            <%}else{%>
                <div class="d-flex justify-content-end align-items-center gap-2">
                    <p class="m-0">Actualmente estas navegando como invitado</p>
                    <button type="button" class="btn btn-outline-success" onclick="toLogin()">Iniciar sesión</button>
                    <script>
                        function toLogin (){
                            window.location.href = "/login";
                        }
                    </script>
                </div>
            <%}%>
        </div>
        <div class="w-100 d-flex flex-row flex-wrap bg-body-secondary justify-content-evenly">
            
            <button type="button" class="flex-grow-1 btn btn-outline-primary rounded-0" onclick="toHome()">Inicio</button>
            <script>
                function toHome(){
                    window.location.href = "/home";
                }
            </script>
            
            <div class="dropdown flex-grow-1">
                <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    Usuarios
                </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="/altausuario">Alta usuario</a></li>
                        <li><a class="dropdown-item" href="#">Consulta usuarios</a></li>
                        <li><a class="dropdown-item" href="#">Modificar datos de usuario</a></li>
                    </ul>
            </div>
            
            <div class="dropdown flex-grow-1">
                <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    Actividades turísticas
                </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="/altaactividad">Alta de actividad</a></li>
                        <li><a class="dropdown-item" href="#">Consulta actividades</a></li>
                    </ul>
            </div>
            
            <div class="dropdown flex-grow-1">
                <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    Salidas turísticas
                </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="/altasalida">Alta de salida</a></li>
                        <li><a class="dropdown-item" href="#">Consulta de salida</a></li>
                        <li><a class="dropdown-item" href="#">Inscripción a salida</a></li>
                    </ul>
            </div>
            
            <div class="dropdown flex-grow-1">
                <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    Paquetes
                </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="#">Consulta de paquete</a></li>  
                        <li><a class="dropdown-item" href="#">Compra de paquete</a></li>  
                    </ul>
            </div>
        </div>
    </header>
</html>
