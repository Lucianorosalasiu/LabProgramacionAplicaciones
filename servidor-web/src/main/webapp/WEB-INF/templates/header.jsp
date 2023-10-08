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
    <header class=" w-100 bg-primary bg-gradient text-light d-flex align-items-center flex-grow-0 flex-column">
        <div class="w-100 d-flex justify-content-between p-2">
            <h1>Turismo Uy!</h1>
            <a class="text-light" href="/turismouy/login">Iniciar sesión</a>
        </div>
        <div class="w-100 d-flex bg-body-secondary">
            <ul class="w-100 list-unstyled m-0 d-flex flex-row p-2 justify-content-around align-items-center">
                <li><a class="fs-5 p-2 text-dark" href="/turismouy/home">Inicio</a></li>
                <li><a class="fs-5 p-2 text-dark" href="/turismouy/home">Usuarios</a></li>
                <li><a class="fs-5 p-2 text-dark" href="/turismouy/consultaactividad">Actividades</a></li>
                <li><a class="fs-5 p-2 text-dark" href="/turismouy/altasalida">Salidas</a></li>
                <li><a class="fs-5 p-2 text-dark" href="/turismouy/home">Paquetes</a></li>
            </ul>
        </div>
    </header>
</html>
