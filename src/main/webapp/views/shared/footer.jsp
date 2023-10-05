<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="ses" value="${pageContext.session}" />
<br><br>
<div class="container-fluid">
    <footer class="fixed-bottom bg-dark">
        <div class="container">
            <div class="row">
                <div class="col-6 col-sm-6 col-md-4 col-lg-4 text-left">
                    <span class="text-white-50">Fecha: <% out.println(new SimpleDateFormat("dd/MM/yyyy").format(new Date())); %> </span>
                </div>
                <div class="d-none d-sm-none d-md-block col-md-4 col-lg-4 text-center">
                    <span class="text-white-50"></span>
                </div>
                <div class="col-6 col-sm-6 col-md-4 col-lg-4 text-right">
                    <span class="text-white-50">Usuario: ${ses.getAttribute("usuario")} </span>
                </div>
            </div>
        </div>
    </footer>    
</div>



