<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio de Sesión</title>
    </head>
    <body>
        <% 
            if(request.getAttribute("error") != null){
                String message = (String)request.getAttribute("message");
        %>
                ShowErrorDialog('<%= message%>');
        <%
            }
        %>
        <form class="form-signin" method="POST" action="<%= request.getContextPath() %>/srvLoginController">
            <div class="card">
                <div class="card-body">
                    <img class="mb-4" src="${req.contextPath}/image/user.png" alt="" width="150" height="150">
                    <h1 class="h3 mb-3 font-weight-normal">Por favor ingrese sus datos</h1>
                    <label for="inputUser" class="sr-only">Usuario</label>
                    <input type="text" id="inputUser" class="form-control" name="user" placeholder="Usuario" required autofocus>
                    <label for="inputPassword" class="sr-only">Contraseña</label>
                    <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Contraseña" required>
                    <button class="btn btn-lg btn-success btn-block" type="submit">Iniciar Sesión</button>
                    <p class="mt-5 mb-3 text-muted">&copy; 2020-2021</p>
                </div>
            </div>
        </form>
    </body>
</html>
