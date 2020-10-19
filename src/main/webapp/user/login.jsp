<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio de Sesión</title>
    </head>
    <body>
        <form id="frmLogin" class="form-signin" method="POST" action="<%= request.getContextPath()%>/srvLoginController">
            <div class="card">
                <div class="card-body">

                    <img class="mb-4" src="${req.contextPath}/images/user.png" alt="" width="150" height="150">
                    <h1 class="h3 mb-3 font-weight-normal">Por favor ingrese sus datos</h1>
                    <label for="inputUser" class="sr-only">Usuario</label>
                    <input type="text" id="inputUser" class="form-control" name="user" placeholder="Usuario" autocomplete="off" required min="1" autofocus data-required data-required-msg="El campo Nombre es requerido" >
                    <label for="inputPassword" class="sr-only">Contraseña</label>
                    <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Contraseña" min="1" required data-required data-required-msg="El campo Nombre es requerido" >
                    <button class="btn btn-lg btn-success btn-block" type="button" onclick="submitForm()">Iniciar Sesión</button>
                    <p class="mt-5 mb-3 text-muted">&copy; 2020-2021</p>

                </div>
            </div>
        </form>
        <script>
            function submitForm() {
                var formLog = $('#frmLogin');
                if (ValidarFormulario('frmLogin')) {
                    ShowWaitingAnimation();
                    $.ajax({
                        url: '<%= request.getContextPath()%>/srvLoginController',
                        method: "GET",
                        data: formLog.serialize(),
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8'
                    }).done((result) => {
                        if (result.error) {
                            ShowWarningDialog(result.message);
                        } else {
                            var url = '<%= request.getContextPath()%>';
                            window.location.href = url;
                        }
                    }).fail((err) => {
                        console.error(err);
                        ShowErrorDialog('Lo sentimos ha ocurrido un error.');
                    }).always(() => {
                        HideWaitingAnimation();
                    });
                }
            }
        </script>
    </body>
</html>
