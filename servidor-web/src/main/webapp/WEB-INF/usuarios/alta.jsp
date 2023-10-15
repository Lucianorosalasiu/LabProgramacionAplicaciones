<%-- 
    Document   : altausuario
    Created on : Oct 10, 2023, 2:24:31 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
        <link rel="stylesheet" href="assets/css/styles.css" />
        <link rel="stylesheet" href="assets/css/sweetalert2.min.css">
        <script src="assets/js/sweetalert2.min.js"></script>
        <title>TurismoUy | Alta Usuario</title>
    </head>


    <body class="h-100 d-flex flex-column">
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
        
        <main>
            <!--<div class="d-flex justify-content-center align-items-center p-4 flex-grow-1">-->
            <div class="container py-5 min-vh-70">
                <h3>Alta de usuario</h3>
                <hr />
                <form 
                    id="signup-form" 
                    action="/altausuario"
                    method="post"
                    class="row g-3 needs-validation" 
                    novalidate
                >
                    <!-- -------------------------- Ingreso del email -------------------------- -->
                    <div class="col-md-4">
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
                                required
                                />
                        </div>
                    </div>
                    <!-- --------------------- Ingreso del nombre --------------------- -->
                    <div class="col-md-4">
                        <label for="input-full-name" class="form-label">
                            Nombre
                        </label>
                        <input
                            type="text"
                            id="input-full-name"
                            class="form-control"
                            name="fullName"
                            placeholder="John Doe"
                            required
                        />
                    </div>
                    <!-- ------------------------- Ingreso de la edad -------------------------- -->
                    <div class="col-md-4">
                        <label for="input-age" class="form-label">Edad</label>
                        <input
                            type="number"
                            id="input-age"
                            class="form-control"
                            name="age"
                            placeholder="La edad debe ser mayor a 15"
                            required
                            />
                    </div>
                    <!-- ---------------------- Ingreso de la contraseña ----------------------- -->
                    <div class="col-md-8">
                        <label for="input-password" class="form-label">Contraseña</label>
                        <input
                            type="password"
                            id="input-password"
                            class="form-control"
                            name="password"
                            placeholder="Ingrese una contraseña alternando caractéres"
                            required
                            />
                    </div>
                    <!-- ------------------------ Ingreso del telefono ------------------------- -->
                    <div class="col-md-4">
                        <label for="input-telephone" class="form-label">Teléfono</label>
                        <input
                            type="text"
                            id="input-telephone"
                            class="form-control"
                            name="phone"
                            placeholder="+598123456789"
                            required
                            />
                    </div>
                    <!-- ----------------------- Ingreso de la dirección ----------------------- -->
                    <div class="col-md-12">
                        <label for="input-address" class="form-label">Dirección</label>
                        <input
                            type="text"
                            id="input-address"
                            class="form-control"
                            name="address"
                            placeholder="Calle Juan Pérez N°10"
                            required
                            />
                    </div>
                    <!-- -------------------- Ingreso de la foto de perfil --------------------- -->
                    <div class="col-md-7">
                        <label id="label-insert-photo" for="input-photo" class="form-label"
                               >Foto</label
                        >
                        <br />
                        <input type="file" id="input-photo" name="photo" accept="image/*" />
                    </div>
                    <!-- ------------------ Vista previa de la foto de perfil ------------------ -->
                    <div class="col-md-5 text-center">
                        <label for="image-preview" class="form-label"
                               >Vista previa foto</label
                        >
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
        <script src="assets/js/signupFormValidator.js"></script>
    </body>
</html>