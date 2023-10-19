<%@page import="app.consultas.entities.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="app.consultas.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="ses" value="${pageContext.session}" />

<%
    HttpSession sesion = request.getSession();
    ArrayList<String> roles = new ArrayList<String>();
    if(sesion.getAttribute("session") == null){
        response.sendRedirect("user/login.jsp");
    } else {
        roles = (ArrayList<String>)session.getAttribute("roles");
    }
%>

<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-blue-dark shadow">
        <a class="navbar-brand" href="${req.contextPath}"><i class="fas fa-home"></i></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <%if(roles.contains("Administrador") ){%>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="far fa-calendar-alt"></i> Citas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="${req.contextPath}/cita/Agendar.jsp">Crear Cita</a>
                                <a class="dropdown-item" href="${req.contextPath}/cita/Buscar.jsp">Buscar Citas</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${req.contextPath}/cita/BuscarConsulta.jsp">Buscar Consulta Médica</a>
                        </div>
                    </li>
                <%}%>
                <%if(roles.contains("Administrador") ){%>
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
                            <a class="dropdown-item" href="${req.contextPath}/mantenimiento/Hospital.jsp">Hospitales</a>
                            <a class="dropdown-item" href="${req.contextPath}/mantenimiento/Medicamento.jsp">Medicamentos</a>
                            <a class="dropdown-item" href="${req.contextPath}/mantenimiento/Unidad.jsp">Unidad de Medida</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-hospital-user"></i> Personas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${req.contextPath}/persona/Paciente.jsp">Pacientes</a>
                            <a class="dropdown-item" href="${req.contextPath}/persona/Empleado.jsp">Empleados</a>
                            <a class="dropdown-item" href="${req.contextPath}/persona/Persona.jsp">Personas</a>
                        </div>
                    </li>
                <%}%>
                <%if(roles.contains("Administrador") ||  roles.contains("Consulta")){%>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-chart-pie"></i> Reportes
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${req.contextPath}/reporte/FichaMedica.jsp">Ficha Médica</a>
                            <a class="dropdown-item" href="${req.contextPath}/cita/BuscarConsulta.jsp">Imprimir Consultas Médicas</a>
                            <a class="dropdown-item" href="${req.contextPath}/cita/Buscar.jsp">Imprimir Citas</a>
                        </div>
                    </li>
                <%}%>
                <%if(roles.contains("Administrador") ){%>
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
                <%}%>
            </ul>
            <ul class="navbar-nav mr-5 pr-5">
                <li class="nav-item  dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user-secret"></i> ${ses.getAttribute("nombreUsuario")}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${req.contextPath}/user/login.jsp?close=true">Cerrar Sesión</a>
<!--                        <a class="dropdown-item" href="#">Cambiar Contraseña</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#"></a>-->
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>
<br><br><br>
