<%-- 
    Document   : mobileHeader
    Created on : Nov 3, 2023, 4:16:45 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Base64"%> 
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbar-list"
                aria-controls="navbarNav"
                aria-expanded="false"
                aria-label="Toggle navigation"
                >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbar-list">
                <ul class="navbar-nav align-items-center">
                    <li class="nav-item">
                        <a class="navbar-brand" aria-current="page" href="/home">
                            <h1 class="fw-bold">Turismo Uy!</h1>
                            <%if(session.getAttribute("isLogged") != null && (Boolean) session.getAttribute("isLogged")){%>
                            <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
                            <img class="bx bx-rotate-90 bx-tada bx-md" width="50" height="50" src="/assets/img/mateTurista.png" alt="Matienzo"/>
                            <%}else{%>
                            <img class="bx bx-rotate-90 bx-tada bx-md" width="50" height="50" src="/assets/img/mateProveedor.png" alt="Matienzo"/>
                            <%}%>
                            <%}else{%>
                            <img class="bx bx-rotate-90 bx-tada bx-md" width="50" height="50" src="/assets/img/mate.png" alt="Matienzo"/>
                            <%}%>
                        </a>
                    </li>          
                </ul>
            </div>


            <% if(session.getAttribute("isLogged") != null && (Boolean) session.getAttribute("isLogged")){ %>
            <div class="collapse navbar-collapse justify-content-end" id="navbar-list">
                <ul class="navbar-nav d-flex justify-content-end align-items-center gap-2">
                    <div class="dropdown-center">
                        <button class="btn btn-dark dropdown-toggle"
                                type="button" 
                                data-bs-toggle="dropdown" 
                                aria-expanded="false"
                                >
                            <img src="<%=(String) session.getAttribute("sessionPhoto")%>" width="50" height="50" class="rounded-circle">
                        </button>
                        <ul class="dropdown-menu dropdown-menu-lg-end dropdown-menu-dark text-center p-2">
                            <li><strong class="dropdown-item"><%=session.getAttribute("sessionNickname")%></strong></li>
                            <li><small class="dropdown-item"><%=session.getAttribute("sessionEmail")%></small></li>
                                <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
                            <li><small class="dropdown-item badge text-bg-info">Turista</small></li>
                                <%}else{%>
                            <li><small class="dropdown-item badge text-bg-warning">Proveedor</small></li>
                                <%}%>
                            <li class="mt-2"><a class="dropdown-item text-danger border rounded border-danger" href="/logout">Cerrar sesión</a></li>
                        </ul>
                    </div>
                </ul>
            </div> 
            <% } else { %>
            <div class="collapse navbar-collapse justify-content-end" id="navbar-list">
                <ul class="navbar-nav d-flex justify-content-end align-items-center gap-2">
                    <li class="nav-item text-light">Actualmente estás navegando como invitado</li>
                    <li class="nav-item">
                        <a href="/login" stype="button" class="btn btn-outline-success">
                            Iniciar Sesión
                        </a>
                    </li>
                </ul>
            </div>
            <% } %>
        </div>
    </nav>
    <div class="w-100 d-flex flex-row flex-wrap bg-body-secondary justify-content-evenly">
        <%if(session.getAttribute("isLogged") != null && (Boolean) session.getAttribute("isLogged") &&
        "TURISTA".equals((String) session.getAttribute("sessionType"))){%>
        <div class="dropdown flex-grow-1">
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                Actividades turísticas
            </button>
            <ul class="dropdown-menu w-100" aria-labelledby="dropdownMenuButton2">
                <li><a class="dropdown-item" href="/consultaactividad">Consulta actividades</a></li>
            </ul>
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton3" data-bs-toggle="dropdown" aria-expanded="false">
                Salidas turísticas
            </button>
            <ul class="dropdown-menu w-100" aria-labelledby="dropdownMenuButton3">
                <li><a class="dropdown-item" href="/consultasalida">Consulta de salida</a></li>
            </ul>
        </div>
        <%}%>
    </div>
</header>
