<%-- 
    Document   : perfil
    Created on : 15 oct. 2023, 21:36:42
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="webservice.DtActividadTuristicaWS, webservice.DtActividadesCollectionWS"%>
<%@page import="webservice.WSActividadController"%>
<%@page import="webservice.WSActividadControllerService"%> 

<%@page import="webservice.DtSalidaTuristicaWS, webservice.DtSalidasCollectionWS"%>
<%@page import="webservice.WSSalidaController"%>
<%@page import="webservice.WSSalidaControllerService"%> 

<%@page import="webservice.DtUsuarioWrapper"%>

<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.text.SimpleDateFormat"%>

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
            String userLogged = (String) request.getSession().getAttribute("sessionNickname");
            Long sessionID = (Long) request.getSession().getAttribute("id");
            
            DtUsuarioWrapper usr = (DtUsuarioWrapper) request.getAttribute("usuario");
            List<Long> seguidos = (List<Long>) request.getAttribute("seguidos");
            List<Long> seguidores = (List<Long>) request.getAttribute("seguidores");
            
            String image = null;
            if (usr.getPhoto() != null && !usr.getPhoto().equals("")) {
                // Si hay una imagen de perfil en formato blob, se convierte a Base64
                String encodedImage = Base64.getEncoder().encodeToString(usr.getPhoto());
                image = "data:image/jpeg;base64," + encodedImage;
            } else if (usr.getImagePath() != null && !usr.getImagePath().isBlank()) {
                // Si hay una ruta de imagen
                image = "https://" + usr.getImagePath();
            } else {
                // Si no se encuentra una imagen, usa la imagen por defecto
                image = "assets/img/defecto.jpg";
            }
        %>
        <div id="perfil" class ="container py-5 min-vh-70 flex-grow-1">
            <% if(!usr.getNickname().equals(userLogged)){ %>
                <h3 id="titulo-perfil">Perfil del usuario:
                    <% if (usr.isTurista()) { %>
                    <span class="text-info">  <%= usr.getDtt().getNickname() %> </span>
                    <% } else { %>
                    <span class="text-warning"> <%= usr.getDtp().getNickname() %> </span>
                    <% } %>  
                </h3>               
            <% } else { %>
                <h3 id="titulo-perfil">Mi Perfil</h3>
                <a href="/modificacionusuario?usuario=<%= usr.getEmail() %>" 
                   id="editar-perfil" 
                   type="button" 
                   class="btn btn-primary float-right"
                   >
                    Editar Perfil
                </a>  
            <% } %>        
            <hr />
            <div id="perfil_izquierda">
                <img src="<%= image %>">
            </div>
            <div id="perfil_derecha">
                <div>
                    <h2>Información básica</h2>
                    <label class="rotulo">Nombre completo:</label>
                    <% if (usr.isTurista()) { %>
                    <label class="valor"><%= usr.getDtt().getName()%> <%= usr.getDtt().getLastName()%></label>
                    <% } else { %>
                    <label class="valor"><%= usr.getDtp().getName()%> <%= usr.getDtp().getLastName()%></label>
                    <% } %>
                    <br/>
                    <label class="rotulo">Fecha de nacimiento:</label>
                    <label class="valor">
                        <% if (usr.isTurista()) { 
                        SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd");
                        %>
                        <%=new SimpleDateFormat("dd-MM-yyyy").format(formatedDate.parse(usr.getDtt().getBirthDate()))%>
                        <% } else {
                        SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd");
                        %>
                        <%=new SimpleDateFormat("dd-MM-yyyy").format(formatedDate.parse(usr.getDtp().getBirthDate()))%>
                        <% } %> 
                    </label>
                    <br/>
                    <label class="rotulo">Seguidores: </label>
                    <label class="valor"><%= seguidores.size() %></label>
                    <br/>
                    <label class="rotulo">Seguidos: </label>
                    <label class="valor"><%= seguidos.size() %></label>
                    <br/>
                    <% if (usr.isTurista()) { %>  
                    <label class="rotulo">País de Orígen</label>
                    <label class="valor"><%= usr.getDtt().getNacionality() %></label>
                    <br/>
                    <% } else { %>
                    <br/>
                    <label class="rotulo">Descripción:</label>
                    <label class="valor"><%= usr.getDtp().getDescription() %></label>  
                    <% } %> 
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
                    <% if (!usr.isTurista()) { %>
                    <label class="rotulo">Sitio web:</label>
                    <label class="valor">
                        <a href="<%= usr.getDtp().getWebsiteURL() %>">
                            <%= usr.getDtp().getWebsiteURL() %>
                        </a>
                    </label>
                    <% } %> 
                </div>
            </div>
            <br/>
            <hr/>

            <%
                WSActividadControllerService actividadController = new WSActividadControllerService();
                WSActividadController actividadPort = actividadController.getWSActividadControllerPort();
                
                WSSalidaControllerService salidaController = new WSSalidaControllerService();
                WSSalidaController salidaPort = salidaController.getWSSalidaControllerPort();

            if (usr.isTurista()) {
                DtSalidasCollectionWS salidaList = new DtSalidasCollectionWS();
                salidaList = salidaPort.obtenerSalidasDeTurista(usr.getDtt().getId());
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
                            for (DtSalidaTuristicaWS salida : salidaList.getSalidas()) {     
                            %>
                            <tr>
                                <td>
                                    <a href="consultasalida?nombre=<%= salida.getNombre() %>" >
                                        <%= salida.getNombre() %>
                                    </a>
                                </td>
                                <td><%= salida.getFechaSalida()%></td>
                                <td><%= salida.getLugar() %></td>
                                <td>
                                    <button 
                                        class="btn btn-primary" 
                                        onclick="openPdfInscripcion('<%=usr.getDtt().getNickname()%>', '<%=salida.getNombre()%>');"
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
                } else {
                    DtActividadesCollectionWS actividadList = new DtActividadesCollectionWS();
                    DtActividadesCollectionWS actividadesFinalizables = new DtActividadesCollectionWS();
                    DtSalidasCollectionWS salidaList = new DtSalidasCollectionWS();

                    if(usr.getDtp().getId() == sessionID) {
                        actividadList = actividadPort.obtenerActividadesDeProveedorCompleto(sessionID);
                        actividadesFinalizables = actividadPort.obtenerActividadesFinalizables(sessionID);
                        salidaList = salidaPort.obtenerSalidasDeProveedorCompleto(sessionID);

                    } else {
                        actividadList = actividadPort.obtenerActividadesDeProveedor(usr.getDtp().getId());
                        salidaList = salidaPort.obtenerSalidasDeProveedor(usr.getDtp().getId());
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
                                for (DtActividadTuristicaWS actividad : actividadList.getActividades()) {
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
                                for (DtSalidaTuristicaWS salida : salidaList.getSalidas()) {     
                                %>
                                <tr>
                                    <td>
                                        <a href="consultasalida?nombre=<%= salida.getNombre() %>" >
                                            <%= salida.getNombre() %>
                                        </a>
                                    </td>
                                    <td><%= salida.getLugar() %></td>
                                    <td><%= salida.getFechaSalida()%></td>
                                </tr>		
                                <% } %>
                            </tbody>
                        </table>
                    </div>

                    <% if(usr.getDtp().getId() == sessionID) { %>
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

