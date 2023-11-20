<%-- 
    Document   : perfil
    Created on : 15 oct. 2023, 21:36:42
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="exceptions.MyException"%>
<%@page import="logica.fabrica.Fabrica"%>
<%@page import="logica.interfaces.IControlador"%>   
<%@page import="dataTypes.DTUsuario"%>
<%@page import="dataTypes.DTTurista"%>
<%@page import="dataTypes.DTProveedor"%>
<%@page import="dataTypes.DTSalidaTuristica"%>
<%@page import="dataTypes.DTActividadTuristica"%>

<%@page import="webservice.DtActividadTuristicaWS, webservice.DtActividadesCollectionWS"%>
<%@page import="webservice.WSActividadController"%>
<%@page import="webservice.WSActividadControllerService"%> 
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%> 

<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <link rel="stylesheet" href="assets/css/styles.css"/>
        <title>TurismoUy | Perfil de Usuario</title>
    </head>
    <body class="h-100 d-flex flex-column">
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
        <%
            DTUsuario usr = (DTUsuario) request.getAttribute("usuario");
            String userLogged = (String) request.getSession().getAttribute("sessionNickname");
            String profileImageUrl = usr.getProfileImageUrl();
            WSActividadControllerService actividadController = new WSActividadControllerService();
            WSActividadController actividadPort = actividadController.getWSActividadControllerPort(); 
        %>
        <div id="perfil" class ="container py-5 min-vh-70 flex-grow-1">
            <%
                if(!usr.getNickname().equals(userLogged)){
            %>
            <h3 id="titulo-perfil">Perfil del usuario:
                <% if (usr instanceof DTTurista) {
                %>
                <span class="text-info">  <%= usr.getNickname() %> </span>
                <%
                } else if(usr instanceof DTProveedor){     
                %>
                <span class="text-warning"> <%= usr.getNickname() %> </span>
                <%
                }
                %>   
            </h3>
            <%
                } else {
            %>
            <h3 id="titulo-perfil">Mi Perfil</h3>
            <a href="/modificacionusuario?usuario=<%= usr.getEmail() %>" 
               id="editar-perfil" 
               type="button" 
               class="btn btn-primary float-right"
               >
                Editar Perfil
            </a>            
            <%
                }
            %>        
            <hr />
            <div id="perfil_izquierda">
                <img src="<%= profileImageUrl %>">
            </div>
            <div id="perfil_derecha">
                <div>
                    <h2>Información básica</h2>
                    <label class="rotulo">Nombre completo:</label>
                    <label class="valor"><%= usr.getName()%> <%= usr.getLastName()%></label>
                    <br/>
                    <label class="rotulo">Fecha de nacimiento:</label>
                    <label class="valor">
                        <%=
                            // Formato deseado para la fecha
                            new SimpleDateFormat("dd-MM-yyyy").format(usr.getBirthDate())
                        %>
                    </label>
                    <br/>
                    <%
                    if (usr instanceof DTTurista) {
                        DTTurista turista = (DTTurista) usr;
                    %>        
                    <label class="rotulo">País de Orígen</label>
                    <label class="valor"><%= turista.getNacionality() %></label>
                    <br/>
                    <%
                    } else if (usr instanceof DTProveedor) {
                        DTProveedor proveedor = (DTProveedor) usr;
                    %>
                    <br/>
                    <label class="rotulo">Descripción:</label>
                    <label class="valor"><%= proveedor.getDescription() %></label>  
                    <%
                    }
                    %> 
                </div>
                <br/>
                <div>
                    <h2>Información de contacto</h2>
                    <label class="rotulo">Correo electrónico:</label>
                    <label class="valor">
                        <a href="mailto:<%= usr.getEmail() %>">
                            <%= usr.getEmail() %>
                        </a>
                    </label>
                    <br/>
                    <%
                    if (usr instanceof DTProveedor) {
                        DTProveedor proveedor = (DTProveedor) usr;
                    %>    
                    <label class="rotulo">Sitio web:</label>
                    <label class="valor">
                        <a href="<%= proveedor.getWebsiteURL() %>">
                            <%= proveedor.getWebsiteURL() %>
                        </a>
                    </label>
                    <%
                    }
                    %> 
                </div>
            </div>
            <br/>
            <hr/>

            <%
                Fabrica fabrica = new Fabrica();
                IControlador controlador = fabrica.getInterface();
                Long sessionID = (Long) request.getSession().getAttribute("id");

            if (usr instanceof DTTurista) {
                DTTurista turista = (DTTurista) usr;
                List<DTSalidaTuristica> salidaList = controlador.obtenerSalidasDeTurista(turista.getId());
            %>
            <div class="container py-5 min-vh-70 flex-grow-1 justify-content-lg-start">
                <h3 class="text-center">Salidas Turísticas:</h3>
                <div class="table-responsive-lg">
                    <table class="table align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>Nombre</th>
                                <th>Fecha Salida</th>
                                <th>Lugar de Salida</th>
                                <th>Inscripción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                            for (DTSalidaTuristica salida : salidaList) {     
                            %>
                            <tr>
                                <td>
                                    <a href="consultasalida?nombre=<%= salida.getNombre() %>" >
                                        <%= salida.getNombre() %>
                                    </a>
                                </td>
                                <td><%=
                                    // Formato deseado para la fecha
                                    new SimpleDateFormat("dd-MM-yyyy").format(salida.getFechaSalida())
                                    %>
                                </td>
                                <td><%= salida.getLugar() %></td>
                                <td>
                                    <button 
                                        class="btn btn-primary" 
                                        onclick="openPdfInscripcion('<%=turista.getNickname()%>', '<%=salida.getNombre()%>');"
                                        >
                                        Ver PDF
                                    </button>
                                </td>
                            </tr>		
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <%
                } else if (usr instanceof DTProveedor) {
                    DTProveedor proveedor = (DTProveedor) usr;
                    List<DTActividadTuristica> actividadList;
                    List<DTSalidaTuristica> salidaList;
                    DtActividadesCollectionWS actividadesFinalizables = new DtActividadesCollectionWS();

                    if(proveedor.getId() == sessionID) {
                        actividadList = controlador.obtenerActividadesDeProveedorCompleto(sessionID);
                        salidaList = controlador.obtenerSalidasDeProveedorCompleto(sessionID);
                        actividadesFinalizables = actividadPort.obtenerActividadesFinalizables(sessionID);

                    } else {
                        actividadList = controlador.obtenerActividadesDeProveedor(proveedor.getId());
                        salidaList = controlador.obtenerSalidasDeProveedor(proveedor.getId());
                    }
                %>
                <div class="container py-5 min-vh-70 flex-grow-1 justify-content-lg-start">
                    <h3 class="text-center">Actividades Turísticas:</h3>
                    <div class="table-responsive-lg">
                        <table class="table align-middle">
                            <thead class="table-dark">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Ciudad</th>
                                    <th>Descripción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                for (DTActividadTuristica actividad : actividadList) {
                                %>
                                <tr>
                                    <td>
                                        <a href="consultaactividad?idActividad=<%= actividad.getId() %>" >
                                            <%= actividad.getNombre() %>
                                        </a>
                                    </td>
                                    <td><%= actividad.getCiudad() %></td>
                                    <td><%= actividad.getDescripcion() %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <h3 class="text-center">Salidas Turísticas asociadas:</h3>
                    <div class="table-responsive-lg">
                        <table class="table align-middle">
                            <thead class="table-dark">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Lugar de Salida</th>
                                    <th>Fecha Salida</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                for (DTSalidaTuristica salida : salidaList) {     
                                %>
                                <tr>
                                    <td>
                                        <a href="consultasalida?nombre=<%= salida.getNombre() %>" >
                                            <%= salida.getNombre() %>
                                        </a>
                                    </td>
                                    <td><%= salida.getLugar() %></td>
                                    <td><%=
                                        // Formato deseado para la fecha
                                        new SimpleDateFormat("dd-MM-yyyy").format(salida.getFechaSalida())
                                        %>
                                    </td>
                                </tr>		
                                <% } %>
                            </tbody>
                        </table>
                    </div>

                    <% if(proveedor.getId() == sessionID) { %>
                    <h3 class="text-center">
                        Actividades turísticas finalizables
                        <span class="text-secondary">(Confirmadas y sin salidas por hacer)</span>:
                    </h3>
                    <div class="table-responsive-lg">
                        <table class="table align-middle">
                            <thead class="table-dark">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Descripcion</th>
                                    <th>Ciudad</th>
                                    <th>Costo</th>
                                    <th>Finalizar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                for (DtActividadTuristicaWS a : actividadesFinalizables.getActividades()){
                                %>
                                <tr>
                                    <td><%= a.getNombre() %></td>
                                    <td><%= a.getDescripcion() %></td>
                                    <td><%= a.getCiudad() %></td>
                                    <td><%= a.getCosto() %></td>
                                    <td>
                                        <form action="/finalizaractividad?idActividad=<%=a.getId()%>" method="post">  
                                            <button type="submit" class="btn btn-outline-danger">Finalizar</button>
                                        </form>  
                                    </td>
                                </tr>		
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <% } %> 
                    <% } %> 
                </div>  
            </div>
        </div>
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
        <script>
            function openPdfInscripcion(nickname, nombreSalida) {
                var url = '/pdfInscripcion?nickname=' + nickname + '&salida=' + nombreSalida;
                var newWindow = window.open(url, '_blank');

                newWindow.focus();
            }
        </script>
    </body>
</html>

