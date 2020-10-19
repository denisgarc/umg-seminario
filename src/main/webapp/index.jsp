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
            <div class="card">
                <div class="card-body text-center">
                    BIENVENIDO!
                </div>
            </div>
        </div>
    </body>
</html>
