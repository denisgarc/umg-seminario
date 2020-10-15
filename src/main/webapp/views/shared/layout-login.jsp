<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1" />
        <title>Bienvenido - <sitemesh:write property='title'></sitemesh:write></title>

        <!-- imports -->
        <%@include file="./vendor.jsp" %>
    </head>
    <body class="text-center bg-blue-dark">
        <sitemesh:write property='body'></sitemesh:write>
    </body>
</html>
