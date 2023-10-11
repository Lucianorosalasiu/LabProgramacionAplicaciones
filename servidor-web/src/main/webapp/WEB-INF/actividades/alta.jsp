<%-- 
    Document   : alta
    Created on : Oct 11, 2023, 4:57:31 PM
    Author     : ignfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/templates/head.jsp"/>
       <title>TurismoUy | Alta actividad turística</title>
    </head>
    
    <jsp:include page="/WEB-INF/templates/header.jsp"/>
    
    <body class="h-100 d-flex flex-column">
        <div class="flex-grow-1 d-flex justify-content-center align-items-center">
            <form class="d-flex flex-column gap-2 p-2 flex-wrap">
                
                <div class="form-group">
                    <label>Departamento</label>
                    <div class="dropdown flex-grow-1">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownDepartamentos" data-bs-toggle="dropdown" aria-expanded="false">
                        Seleccione un departamento
                    </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item">dpto1</a></li>
                            <li><a class="dropdown-item">dpto2</a></li>
                        </ul>
                    </div>
                </div>
                
                <div class="form-group">
                  <label >Nombre de la actividad<span class="text-danger"> (debe ser unico). </span></label>
                  <input class="form-control" id="exampleInputEmail1" placeholder="Ingresa un nombre">
                </div>
                
                <div class="form-group">
                  <label>Descripción</label>
                  <textarea class="form-control" id="exampleInputPassword1" placeholder="Ingresa una descripción"></textarea>
                </div>
                
                <div class="form-group">
                  <label>Duración en horas</label>
                  <input type="number" class="form-control" id="exampleInputEmail1" value="0" min="0">
                </div>
                
                <div class="form-group">
                  <label>Costo en pesos uruguayos</label>
                  <input type="number" class="form-control" id="exampleInputEmail1" value="0" min="0">
                </div>
                
                <div class="form-group">
                  <label>Ciudad</label>
                  <input type="text" class="form-control" id="exampleInputEmail1">
                </div>
                
                <div class="form-group">
                    <label class="form-label">Imagen de la actividad <span class="text-info">(Opcional).</span></label>
                    <input class="form-control" type="file" id="formFile">
                </div>

                <div class="form-group">
                    <label>Categorias</label>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                          cat1
                        </label>
                    </div>
                    
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                          cat2
                        </label>
                    </div>
                </div>
                
                <button type="submit" class="btn btn-success">Enviar</button>
            </form>
        </div>
    </body>
    
    <jsp:include page="/WEB-INF/templates/footer.jsp"/>
</html>
