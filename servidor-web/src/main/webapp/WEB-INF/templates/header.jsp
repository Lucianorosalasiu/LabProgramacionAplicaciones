<%-- 
    Document   : header
    Created on : 5 oct. 2023, 09:32:22
    Author     : todos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class=" w-100 bg-dark text-light d-flex align-items-center flex-grow-0 flex-column">
    <div class="w-100 d-flex justify-content-between align-items-center p-2">
        <h1>Turismo Uy!</h1>

        <%if(session.getAttribute("isLogged") != null && (Boolean) session.getAttribute("isLogged")){%>
        <div class="d-flex flex-column gap-2">
            <a href="/consultausuario?usuario=<%=session.getAttribute("sessionNickname")%>"> @<%=session.getAttribute("sessionNickname")%></a>
            <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
            <span class="flex-grow-0 badge text-bg-info">Turista</span>
            <%}else{%>
            <span type="button" class="badge text-bg-warning">Proveedor</span>
            <%}%>
            <a href="/logout" type="button" class="btn btn-outline-danger">Cerrar sesión</a>
        </div>
        <%}else{%>
        <div class="d-flex justify-content-end align-items-center gap-2">
            <p class="m-0">Actualmente estas navegando como invitado</p>
            <a href="/login" type="button" class="btn btn-outline-success">Iniciar sesión</a>
        </div>
        <%}%>
    </div>
    <div class="w-100 d-flex flex-row flex-wrap bg-body-secondary justify-content-evenly">

        <a href="/home" type="button" class="flex-grow-1 btn btn-outline-primary rounded-0">Inicio</a>

        <div class="dropdown flex-grow-1">
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                Usuarios
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="/altausuario">Alta usuario</a></li>
                <li><a class="dropdown-item" href="/consultausuario">Consulta usuarios</a></li>
                <li><a class="dropdown-item" href="/modificacionusuario">Modificar datos de usuario</a></li>
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
                <li><a class="dropdown-item" href="/consultasalida">Consulta de salida</a></li>
                <li><a class="dropdown-item" href="/inscripcion">Inscripción a salida</a></li>
            </ul>
        </div>

        <div class="dropdown flex-grow-1">
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                Paquetes
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="/consultapaquete">Consulta de paquete</a></li>  
                <li><a class="dropdown-item" href="/comprapaquete">Compra de paquete</a></li>  
            </ul>
        </div>
    </div>
</header>
