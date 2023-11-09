<%-- 
    Document   : header
    Created on : 5 oct. 2023, 09:32:22
    Author     : todos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/consultausuario?usuario=<%=session.getAttribute("sessionNickname")%>">Mi Perfil</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item text-bg-danger" href="/logout">Cerrar sesión</a></li>
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
                    <li class="nav-item">   
                        <a href="/altausuario" type="button" class="btn btn-outline-light">
                            Registrarse
                        </a>
                    </li>
                </ul>
            </div>
            <% } %>
        </div>
    </nav>
    <div class="w-100 d-flex flex-row flex-wrap bg-body-secondary justify-content-evenly">
        <div class="dropdown flex-grow-1">
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                Usuarios
            </button>
            <ul class="dropdown-menu w-100" aria-labelledby="dropdownMenuButton1">
                <%if(request.getSession().getAttribute("isLogged") == null){%>
                    <li><a class="dropdown-item" href="/altausuario">Alta usuario</a></li>
                <%}%>
                <li><a class="dropdown-item" href="/consultausuario">Consulta usuarios</a></li>
            </ul>
        </div>

        <div class="dropdown flex-grow-1">
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                Actividades turísticas
            </button>
            <ul class="dropdown-menu w-100" aria-labelledby="dropdownMenuButton2">
                <%if("PROVEEDOR".equals((String) session.getAttribute("sessionType"))){%>
                    <li><a class="dropdown-item" href="/altaactividad">Alta de actividad</a></li>
                <%}%>
                <li><a class="dropdown-item" href="/consultaactividad">Consulta actividades</a></li>
            </ul>
        </div>

        <div class="dropdown flex-grow-1">
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton3" data-bs-toggle="dropdown" aria-expanded="false">
                Salidas turísticas
            </button>
            <ul class="dropdown-menu w-100" aria-labelledby="dropdownMenuButton3">
                <%if("PROVEEDOR".equals((String) session.getAttribute("sessionType"))){%>
                    <li><a class="dropdown-item" href="/altasalida">Alta de salida</a></li>
                <%}%>
                <li><a class="dropdown-item" href="/consultasalida">Consulta de salida</a></li>
                <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
                    <li><a class="dropdown-item" href="/inscripcion">Inscripción a salida</a></li>
                <%}%>                
            </ul>
        </div>

        <div class="dropdown flex-grow-1">
            <button class="w-100 btn btn-outline-primary dropdown-toggle rounded-0" type="button" id="dropdownMenuButton4" data-bs-toggle="dropdown" aria-expanded="false">
                Paquetes
            </button>
            <ul class="dropdown-menu w-100" aria-labelledby="dropdownMenuButton4">
                <li><a class="dropdown-item" href="/consultapaquete">Consulta de paquete</a></li>
                <%if("TURISTA".equals((String) session.getAttribute("sessionType"))){%>
                    <li><a class="dropdown-item" href="/comprapaquete">Compra de paquete</a></li>  
                <%}%>  
            </ul>
        </div>
    </div>
</header>