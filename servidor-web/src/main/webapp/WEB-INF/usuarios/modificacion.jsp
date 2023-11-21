<%-- 
    Document   : modificacion
    Created on : 13 oct. 2023, 11:06:25
    Author     : progav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="webservice.DtUsuarioWrapper"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <link rel="stylesheet" href="assets/css/styles.css" />
        <title>TurismoUy | Modificación Usuario</title>
    </head>

    <body class="h-100 d-flex flex-column">
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
        <%
            DtUsuarioWrapper u = (DtUsuarioWrapper) request.getAttribute("usuario");
            String image = null;
            if (u.getPhoto() != null && !u.getPhoto().equals("")) {
                // Si hay una imagen de perfil en formato blob, se convierte a Base64
                String encodedImage = Base64.getEncoder().encodeToString(u.getPhoto());
                image = "data:image/jpeg;base64," + encodedImage;
            } else if (u.getImagePath() != null && !u.getImagePath().isBlank()) {
                // Si hay una ruta de imagen
                image = "https://" + u.getImagePath();
            } else {
                // Si no se encuentra una imagen, usa la imagen por defecto
                image = "assets/img/defecto.jpg";
            }
        %>
        <main>
            <!--<div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">-->
            <div class="container py-5 min-vh-70 flex-grow-1">
                <h3>Editar perfil</h3>
                <hr />
                <% if (request.getParameter("errorMessage") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getParameter("errorMessage") %>
                </div>
                <% } %>
                <form 
                    id="new-form" 
                    action="/modificacionusuario"
                    method="post"
                    enctype="multipart/form-data"
                    class="row g-3 needs-validation" 
                    novalidate
                    >
                    <!-- ------------------------ Ingreso del nickname ------------------------- -->
                    <div class="col-md-6">
                        <label for="input-nickname" class="form-label">Nombre de usuario</label>
                        <input
                            type="text"
                            id="input-nickname"
                            class="form-control"
                            name="nickname"
                            <% if (u.isTurista()) { %>
                            value="<%= u.getDtt().getNickname() %>"
                            <% } else { %>
                            value="<%= u.getDtp().getNickname() %>"
                            <% } %> 
                            required
                            disabled
                            />
                    </div>
                    <!-- --------------- Ingreso del email --------------- -->
                    <div class="col-md-6">
                        <label for="input-email" class="form-label">Email</label>
                        <div class="input-group">
                            <span class="input-group-text" id="inputGroupFusion">@</span>
                            <input
                                type="email"
                                id="input-email"
                                class="form-control"
                                name="email"
                                aria-describedby="inputGroupFusion"
                                <% if (u.isTurista()) { %>
                                value="<%= u.getDtt().getEmail() %>"
                                <% } else { %>
                                value="<%= u.getDtp().getEmail() %>"
                                <% } %> 
                                required
                                disabled
                                />
                        </div>
                    </div>                             
                    <!-- --------------------- Ingreso del nombre --------------------- -->
                    <div class="col-md-4">
                        <label for="input-name" class="form-label">
                            Nombre
                        </label>
                        <input
                            type="text"
                            id="input-name"
                            class="form-control"
                            name="name"
                            placeholder="John"
                            <% if (u.isTurista()) { %>
                            value="<%= u.getDtt().getName() %>"
                            <% } else { %>
                            value="<%= u.getDtp().getName() %>"
                            <% } %>
                            required
                            />
                    </div>
                    <!-- ----------------------- Ingreso del apellido ----------------------- -->
                    <div class="col-md-4">
                        <label for="input-lastname" class="form-label">Apellido</label>
                        <input
                            type="text"
                            id="input-lastname"
                            class="form-control"
                            name="lastName"
                            placeholder="Doe"
                            <% if (u.isTurista()) { %>
                            value="<%= u.getDtt().getLastName() %>"
                            <% } else { %>
                            value="<%= u.getDtp().getLastName() %>"
                            <% } %>                            
                            required
                            />
                    </div>
                    <!-- --------- Ingreso de la fecha de nacimiento ---------- -->
                    <div class="col-md-4">
                        <label for="input-birthdate" class="form-label">Fecha de nacimiento</label>
                        <input
                            type="date"
                            id="input-birthdate"
                            class="form-control"
                            name="birthdate"
                            <% if (u.isTurista()) { %>
                            value="<%=u.getDtt().getBirthDate()%>"
                            <% } else { %>
                            value="<%=u.getDtp().getBirthDate()%>"
                            <% } %>                             
                            required
                            />
                    </div>
                    <%
                    if (u.isTurista()) {
                    %>        
                    <!-- ----- Ingreso de la nacionalidad del turista ----- -->
                    <div class="col-md-4" id="nacionality-div">
                        <label for="select-nacionality" class="form-label">
                            Nacionalidad
                        </label>
                        <select
                            type="text"
                            id="select-nacionality"
                            class="form-select"
                            name="nacionality"
                            required
                            >
                            <option value=''>Selecciona el país</option>
                            <option value="<%=u.getDtt().getNacionality()%>" selected>
                                <%=u.getDtt().getNacionality()%>   
                            </option>
                        </select>
                    </div>  
                    <br/>
                    <%
                    } else {
                    %>
                    <!-- ----- Ingreso del sitio web del proveedor ----- -->
                    <div class="col-md-6" id="website-div">
                        <label for="input-website" class="form-label">
                            URL del Sitio Web 
                            <span class="text-info"> (Opcional)</span>
                        </label>
                        <input
                            type="text"
                            id="input-website"
                            class="form-control"
                            name="website"
                            value="<%= u.getDtp().getWebsiteURL() %>"
                            placeholder="https://example.com"
                            />
                    </div>
                    <!-- ----- Ingreso de la descripción del proveedor ----- -->
                    <div class='form-group' id="description-div">
                        <label for='input-description' class='form-label'>Descripción</label>
                        <textarea
                            id='input-description'
                            class='form-control'
                            name='description'
                            type='text'
                            cols='30'
                            rows='4'
                            placeholder='Una breve descripción de tu persona'
                            ><%= u.getDtp().getDescription() %>
                        </textarea>
                    </div>
                    <%
                    }
                    %> 
                    <hr />
                    <!-- ---------------------- Ingreso de la contraseña ----------------------- -->
                    <div class="col-md-4">
                        <label for="input-password" class="form-label">Nueva contraseña
                            <span class="text-info"> (Opcional)</span>
                        </label>
                        <input
                            type="password"
                            id="input-password"
                            class="form-control"
                            name="password"
                            placeholder="Ingrese una contraseña segura"
                            />
                    </div>
                    <!-- ---- Ingreso de la confirmación de contraseña ---- -->
                    <div class="col-md-4">
                        <label for="input-confirm-password" class="form-label">Confirmación de nueva contraseña
                            <span class="text-info"> (Opcional)</span>
                        </label>
                        <input
                            type="password"
                            id="input-confirm-password"
                            class="form-control"
                            name="confirmPassword"
                            placeholder="Repite la contraseña previamente ingresada"
                            />
                    </div>
                    <!-- -------------------- Ingreso de la foto de perfil --------------------- -->
                    <div class="col-md-7">
                        <label 
                            id="label-insert-photo"
                            for="input-photo"
                            class="form-label"
                            >
                            Foto
                            <span class="text-info"> (Opcional)</span>
                        </label>
                        <br />
                        <input type="file" id="input-photo" name="photo" accept="image/*" />
                    </div>
                    <!-- ------------------ Vista previa de la foto de perfil ------------------ -->
                    <div class="col-md-5 text-center">
                        <label>Vista previa foto</label>
                        <br />
                        <img
                            id="image-preview"
                            src="<%= image %>"
                            width="150"
                            height="150"
                            class="rounded-circle"
                            />
                    </div>
                    <!-- --- Botones para crear usuario o ir al inicio de sesión --- -->
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-success">
                            Actualizar datos
                        </button>
                        <a href="/consultausuario?usuario=<%=session.getAttribute("sessionNickname")%>" type="button" class="btn btn-primary">Volver al perfil</a>
                    </div>
                </form>
            </div>
        </main>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>

        <!-- -------------------------- Inicio de scripts -------------------------- -->        
        <!-- Validación de campos a nivel de front -->
        <script src="assets/js/usuarios/formValidator.js"></script>
        <!-- Cargar los países del campo select a través de un CSV -->
        <script src="assets/js/usuarios/loadCountryList.js"></script>
        <!-- Cargar vista previa de la imagen seleccionada en el form -->
        <script src="assets/js/usuarios/imagePreview.js"></script>
    </body>   
</html>