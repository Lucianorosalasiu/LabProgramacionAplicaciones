<%-- 
    Document   : perfil
    Created on : 15 oct. 2023, 21:36:42
    Author     : progav
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

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%> 

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
        %>
        <div id="perfil" class ="container py-5 min-vh-70 flex-grow-1">
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
                if(usr.getNickname().equals(userLogged)){
            %>

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
                Long userID = usr.getId();
                Long sessionID = (Long) request.getSession().getAttribute("id");

            if (usr instanceof DTTurista) {
                DTTurista turista = (DTTurista) usr;
                List<DTSalidaTuristica> salidaList = controlador.obtenerSalidasDeTurista(userID);
            %>  
            <div class="container py-5 min-vh-70 flex-grow-1 justify-content-lg-start">
                <form id="form" class="row g-3">
                    <div class='col-lg-5'>
                        <label for='select-salida' class='form-label'>
                            <h4>Salidas Turísticas a las que se inscribió:</h4>
                        </label>
                        <select
                            id='select-salida'
                            class='form-select'
                            name='salidaTurista'
                            >
                            <option value=''>Selecciona una salida</option>
                            <% 
                            for (DTSalidaTuristica salida : salidaList) {     
                            %>
                            <option value="<%= salida.getNombre() %>">
                                <%= salida.getNombre() %>
                            </option>		
                            <% } %>
                        </select>
                    </div>
                </form>
            </div>
            <%
            } else if (usr instanceof DTProveedor) {
                DTProveedor proveedor = (DTProveedor) usr;
                List<DTActividadTuristica> actividadList = controlador.obtenerActividadesDeProveedor(userID);
                List<DTSalidaTuristica> salidaList = controlador.obtenerSalidasDeProveedor(userID);
            %>
            <div class="container py-5 min-vh-70 flex-grow-1 justify-content-lg-start">
                <form id="form" class="row g-3">
                    <div class='col-lg-5'>
                        <label for='select-actividad-proveedor' class='form-label'>
                            <h4>Actividades Turísticas que ofrece:</h4>
                        </label>
                        <select
                            id='select-actividad-proveedor'
                            class='form-select'
                            name='actividadProveedor'
                            >
                            <option value=''>Selecciona una actividad</option>
                            <% 
                            for (DTActividadTuristica actividad : actividadList) {    
                            %>
                            <option value="<%= actividad.getNombre() %>">
                                <%= actividad.getNombre() %>
                            </option>		
                            <% } %>
                        </select>
                    </div>
                    <!-- Div que actua como separador -->
                    <div class='col-lg-2'></div>
                    <div class='col-lg-5'>
                        <label for='select-salida-proveedor' class='form-label'>
                            <h4>Salidas Turísticas asociadas:</h4>
                        </label>
                        <select
                            id='select-salida-proveedor'
                            class='form-select'
                            name='salidaProveedor'
                            >
                            <option value=''>Selecciona una salida</option>
                            <% 
                            for (DTSalidaTuristica salida : salidaList) {     
                            %>
                            <option value="<%= salida.getNombre() %>">
                                <%= salida.getNombre() %>
                            </option>		
                            <% } %>
                        </select>
                    </div>
                </form>
            </div>
            <% } %> 
        </div>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>

