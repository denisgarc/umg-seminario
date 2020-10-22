<%@page import="app.consultas.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="ses" value="${pageContext.session}" />

<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-blue-dark shadow">
        <a class="navbar-brand" href="${req.contextPath}"><i class="fas fa-home"></i></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="far fa-calendar-alt"></i> Citas
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Crear Cita</a>
                        <a class="dropdown-item" href="#">Buscar Citas</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-cogs"></i> Mantenimientos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/Especializacion.jsp">Especialización</a>
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/Puesto.jsp">Puesto</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/TipoDocumento.jsp">Tipo de Documento</a>
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/TipoConsulta.jsp">Tipo de Consulta</a>
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/TipoDiagnostico.jsp">Tipo de Diagnostico</a>
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/TipoTratamiento.jsp">Tipo de Tratamiento</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/Medicamento.jsp">Medicamentos</a>
                        <a class="dropdown-item" href="${req.contextPath}/mantenimiento/Unidad.jsp">Unidad de Medida</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-hospital-user"></i> Personas
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Pacientes</a>
                        <a class="dropdown-item" href="#">Empleados</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-chart-pie"></i> Reportes
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Resumen de Citas</a>
                        <a class="dropdown-item" href="#">Ficha Médica</a>
                        <a class="dropdown-item" href="#">Asignación de Roles</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                         <i class="fas fa-lock"></i> Seguridad
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${req.contextPath}/seguridad/Usuario.jsp">Usuarios</a>
                        <a class="dropdown-item" href="${req.contextPath}/seguridad/Rol.jsp">Roles</a>
                        <a class="dropdown-item" href="${req.contextPath}/seguridad/UsuarioRol.jsp">Asignación de Roles</a>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav mr-5 pr-5">
                <li class="nav-item  dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user-secret"></i> ${ses.getAttribute("nombreUsuario")}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${req.contextPath}/user/login.jsp?close=true">Cerrar Sesión</a>
                        <a class="dropdown-item" href="#">Cambiar Contraseña</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#"></a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>
<br><br><br>