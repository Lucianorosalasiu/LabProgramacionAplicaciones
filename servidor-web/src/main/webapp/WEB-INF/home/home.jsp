<%-- 
    Document   : index
    Created on : 5 oct. 2023, 09:48:00
    Author     : alexis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp"/>  
        <title>Turismouy | Home</title>
    </head>

    <%
    String userAgent = request.getHeader("User-Agent");
    if(userAgent != null && userAgent.toLowerCase().contains("mobile")){%>
            <jsp:include page="/WEB-INF/templates/mobileHeader.jsp"/>
    <%}else{%>
        <jsp:include page="/WEB-INF/templates/header.jsp"/>
    <%}%>

    <body class="h-100 d-flex flex-column">
        <div class="d-flex flex-column justify-content-start align-items-center p-5 flex-grow-1">
            <h1 class="fw-bold text-primary">¡Bienvenido!</h1>
                        
            <div class="d-flex justify-content-around flex-row flex-wrap gap-2">
                <div class="card" style="width: 18rem;">
                    <div class="d-flex justify-content-center align-items-center pt-2">
                        <img width="50" height="50" src="assets/img/help.png" alt="¿Qué es TurismoUY?"/>
                    </div> 
                    <div class="card-body"> 
                        <h5 class="card-title"><strong class="text-primary">¿Qué es TurismoUY? </strong></h5>
                        <p class="card-text">Es una plataforma web que te invita 
                            a explorar las maravillas de Uruguay a través de una experiencia turística personalizada.
                            Diseñada como parte de un proyecto universitario en Java te ofrecemos una
                            ventana virtual perfecta para adentrarse en la riqueza cultural, natural e histórica de este hermoso país.</p>
                    </div>
                </div>

                <div class="card" style="width: 18rem;">
                    <div class="d-flex justify-content-center align-items-center pt-2">
                        <img width="50" height="50" src="assets/img/search.png" alt="Descubre Uruguay"/>
                    </div> 
                    <div class="card-body"> 
                        <h5 class="card-title"><strong class="text-primary">Descubre Uruguay </strong></h5>
                        <p class="card-text"> Podrás descubrir Uruguay
                            de una manera única. La plataforma está organizada en base a departamentos y categorías, 
                            lo que facilita la búsqueda de actividades turísticas según tus intereses personales. Desde 
                            la serena costa de Rocha hasta las vibrantes calles de Montevideo, encontrarás una amplia 
                            variedad de opciones para explorar.</p>
                    </div>
                </div>

                <div class="card" style="width: 18rem;">
                    <div class="d-flex justify-content-center align-items-center pt-2">
                        <img width="50" height="50" src="assets/img/actividad.png" alt="Actividades Turísticas"/>
                    </div> 
                    <div class="card-body"> 
                        <h5 class="card-title"><strong class="text-primary">Actividades Turísticas </strong></h5>
                        <p class="card-text"> Te brindamos una amplia selección de actividades turísticas para disfrutar.
                            Desde caminatas por la naturaleza hasta recorridos históricos y experiencias culinarias, la plataforma
                            reúne lo mejor de Uruguay en un solo lugar. Cada actividad está diseñada para ofrecer una inmersión
                            profunda en los encantos del país.</p>
                    </div>
                </div>

                <div class="card" style="width: 18rem;">
                    <div class="d-flex justify-content-center align-items-center pt-2">
                        <img width="50" height="50" src="assets/img/cart.png" alt="Salidas y Paquetes Promocionales"/>
                    </div> 
                    <div class="card-body"> 
                        <h5 class="card-title"><strong class="text-primary">Salidas y Paquetes Promocionales </strong></h5>
                        <p class="card-text"> Si buscas una experiencia completa, también ofrecemos salidas a 
                            diferentes destinos, lo que te permitirá conocer mejor cada lugar. Además, se han diseñado 
                            paquetes promocionales que combinan múltiples actividades para una experiencia turística integral.
                            Descubrirás lugares excepcionales y aprenderás de la historia y cultura uruguaya en cada paso.</p>
                    </div>
                </div>
            </div>   
        </div>    
        <jsp:include page="/WEB-INF/templates/footer.jsp"/>
    </body>
</html>
