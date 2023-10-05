<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1" />
        <title>Citas: <sitemesh:write property='title'></sitemesh:write></title>

        <!-- imports -->
        <%@include file="./vendor.jsp" %>
    </head>
    <body>
        <!-- header -->
        <%@include file="./navbar.jsp" %>
        
        <div class="container-fluid">
            <!-- content -->
            <main role="main" class="col-12">
                <sitemesh:write property='body'></sitemesh:write>
            </main>
        </div>
    
        <!-- footer-->
        <%@include file="./footer.jsp" %>
    </body>
</html>
