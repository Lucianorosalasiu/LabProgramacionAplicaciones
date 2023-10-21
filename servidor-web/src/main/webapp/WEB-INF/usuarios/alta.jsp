<%-- 
    Document   : altausuario
    Created on : Oct 10, 2023, 2:24:31 PM
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <link rel="stylesheet" href="assets/css/styles.css" />
        <title>TurismoUy | Alta Usuario</title>
    </head>


    <body class="h-100 d-flex flex-column">
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
        <main>
            <!--<div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">-->
            <div class="container py-5 min-vh-70 flex-grow-1">
                <h3>Alta de usuario</h3>
                <hr />
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("errorMessage") %>
                    </div>
                 <% } %>
                <form 
                    id="signup-form" 
                    action="/altausuario"
                    method="post"
                    enctype="multipart/form-data"
                    class="row g-3 needs-validation" 
                    novalidate
                    >
                    <!-- ------------------------ Ingreso del nickname ------------------------- -->
                    <div class="col-md-4">
                        <label for="input-nickname" class="form-label">Nombre de usuario sin espacios</label>
                        <input
                            type="text"
                            id="input-nickname"
                            class="form-control"
                            name="nickname"
                            placeholder="johnDoe"
                            <% if (request.getAttribute("nickname") != null) { %>
                            value="<%= request.getParameter("nickname") %>"
                            <% } %>
                            required
                            />
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
                            <% if (request.getAttribute("name") != null) { %>
                            value="<%= request.getParameter("name") %>"
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
                            <% if (request.getAttribute("lastName") != null) { %>
                            value="<%= request.getParameter("lastName") %>"
                            <% } %>
                            required
                            />
                    </div>                  
                    <!-- ---------------------- Ingreso de la contraseña ----------------------- -->
                    <div class="col-md-4">
                        <label for="input-password" class="form-label">Contraseña</label>
                        <input
                            type="password"
                            id="input-password"
                            class="form-control"
                            name="password"
                            placeholder="Ingrese una contraseña segura"
                            required
                            />
                    </div>
                    <!-- ---- Ingreso de la confirmación de contraseña ---- -->
                    <div class="col-md-4">
                        <label for="input-confirm-password" class="form-label">Confirmación de contraseña</label>
                        <input
                            type="password"
                            id="input-confirm-password"
                            class="form-control"
                            name="confirmPassword"
                            placeholder="Repite la contraseña previamente ingresada"
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
                            <% if (request.getAttribute("birthdate") != null) { %>
                            value="<%= request.getParameter("birthdate") %>"
                            <% } %>
                            required
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
                                placeholder="john-doe@example.com"
                                <% if (request.getAttribute("email") != null) { %>
                                value="<%= request.getParameter("email") %>"
                                <% } %>
                                required
                                />
                        </div>
                    </div>
                    <!-- -------------------- Selección del tipo de usuario --------------------- -->
                    <div class='col-md-6'>
                        <label for='select-user-type' class='form-label'>Tipo de usuario</label>
                        <select
                            id='select-user-type'
                            class='form-control'
                            name='userType'
                            required
                            >
                            <option value=''>Selecciona el tipo de usuario</option>
                            <option value='proveedor'>Proveedor/a</option>
                            <option value='turista'>Turista</option>
                        </select>
                    </div>
                    <!-- ----- Ingreso de la nacionalidad del turista ----- -->
                    <div class="col-md-4" id="nacionality-div">
                        <label for="select-nacionality" class="form-label">
                            Nacionalidad
                        </label>
                        <select
                            type="text"
                            id="select-nacionality"
                            class="form-control"
                            name="nacionality"
                            required
                            >
                            <option value=''>Selecciona el país</option>
                        </select>
                    </div>
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
                            placeholder='Una breve descripción de mi persona'
                            ></textarea>
                    </div>
                    <hr />
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
                            width="150"
                            height="150"
                            class="rounded-circle"
                            />
                    </div>
                    <!-- --- Botones para crear usuario o ir al inicio de sesión --- -->
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-success">
                            Crear usuario
                        </button>
                        <a href="/login" type="button" class="btn btn-primary">Iniciar sesión</a>
                    </div>
                </form>
            </div>
        </main>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>

        <!-- -------------------------- Inicio de scripts -------------------------- -->
        <!-- Validación de campos a nivel de front -->
        <script src="assets/js/usuarios/signupFormValidator.js"></script>
        <!-- Mostrar u ocultar campos según el tipo de usuario seleccionado -->
        <script src="assets/js/usuarios/toggleFields.js"></script>
        <!-- Cargar los países del campo select a través de un CSV -->
        <script src="assets/js/usuarios/loadCountryList.js"></script>
        <!-- Cargar vista previa de la imagen seleccionada en el form -->
        <script src="assets/js/usuarios/imagePreview.js"></script>
    </body>
</html>