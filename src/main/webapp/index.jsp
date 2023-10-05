<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if(sesion.getAttribute("session") == null){
        response.sendRedirect("user/login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Inicio</title>
    </head>
    <body>
        <div class="container">
            <img src="images/bienvenido.png" class="img-fluid" style="max-width: 70%" alt=""/>
        </div>
    </body>
</html>
