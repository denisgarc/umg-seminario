<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    ArrayList<String> roles = new ArrayList<String>();
    if (sesion.getAttribute("session") == null) {
        response.sendRedirect("user/login.jsp");
    } else {
        roles = (ArrayList<String>) session.getAttribute("roles");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Citas</title>
    </head>
    <body>
        <% if (roles.contains("Administrador")) { %>
        <script src="../js/cita/frmBuscar.js" type="text/javascript"></script>
        <% } else { %>
        <script src="../js/cita/frmBuscarImprimir.js" type="text/javascript"></script>
        <%}%>
        <div class="container">
            <form id="frmBuscar">
                <div class="card">
                    <h5 class="card-header bg-secondary">Criterios de Búsqueda</h5>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="fechaDesde" class="label">Fecha Desde</label>
                                    <input type="text" class="form-control isDatePicker" 
                                           id="fechaDesde" 
                                           name="fechaDesde"
                                           autocomplete="off"
                                           data-required data-required-msg="El campo Fecha Desde es requerido">
                                </div>
                            </div>
                            <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label for="fechaHasta" class="label">Fecha Hasta</label>
                                    <input type="text" class="form-control isDatePicker" 
                                           id="fechaHasta" 
                                           name="fechaHasta"
                                           autocomplete="off"
                                           data-required data-required-msg="El campo Fecha Hasta es requerido">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="">Buscar Paciente</label>
                                    <div class="input-group">
                                        <span class="input-group-text"><i class="fas fa-search"></i></span>
                                        <input name="idPaciente" class="idPaciente" style="display:none" value="0">
                                        <input id="pacienteNombre" name="pacienteNombre" class="form-control isAutoComplete" 
                                               placeholder="Ingrese texto para iniciar la búsqueda" 
                                               autocomplete="off"
                                               data-method-url="PacienteConsultaController"
                                               data-method="GET"
                                               data-message-control="paciente_error_msg">
                                    </div>
                                    <span id="paciente_error_msg" class="validator_error"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                <button type="button" class="btn btn-outline-success" onclick="search()"><i class="fas fa-search"></i> Buscar</button>
                                <% if (roles.contains("Administrador")) { %>
                                <button type="button" class="btn btn-outline-info" onclick="loadNew();"><i class="fas fa-plus"></i> Agregar</button>
                                <%}%>
                                <button type="reset" class="btn btn-outline-danger"><i class="fas fa-window-close"></i> Cancelar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <hr>
        <div class="container">
            <div class="card">
                <h5 class="card-header bg-secondary">Citas Programadas</h5>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="table-responsive">
                                <table id="tblCitas" class="table table-hover table-sm isDataTableCita withOutAutoFit isAjaxTable" 
                                       data-url="<%= request.getContextPath()%>/CitaConsultaController" data-method="GET" data-src=""
                                       data-show-buttons="true"
                                       data-buttons="true">
                                    <thead class="bg-blue-light text-white">
                                        <tr>
                                            <th class="text-center" data-key="idCita">Código</th>
                                            <th class="text-center" data-key="fechaCita">Fecha</th>
                                            <th class="text-center" data-key="idPaciente.idPersona.nombres">Nombres</th>
                                            <th class="text-center" data-key="idPaciente.idPersona.apellidos">Apellidos</th>
                                            <th class="text-center" data-key="idPaciente.idPersona.documentoId">Documento No.</th>
                                            <th class="text-center" data-key="idPaciente.idPersona.idTipoDocumento.descripcion">Tipo de Documento</th>
                                            <th class="text-center" data-key="idHospital.nombre">Hospital</th>
                                            <th class="text-center" data-key="idEstado.descripcion">Estado</th>
                                            <th class="text-center">Acciones</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% if (roles.contains("Administrador")) { %>
            <div id="mainCitaModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="citaMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Actualizar Cita</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmCitaMant">
                                <input type="text" id="idHospital" name="idHospital" style="display: none">
                                <input type="text" id="idClinica" name="idClinica" style="display: none">
                                <input type="text" id="idSala" name="idSala" style="display: none">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idCita" class="label text-primary">Código</label>
                                            <input type="text" class="form-control" 
                                                   id="idCita" 
                                                   name="idCita" value="0" text="0"
                                                   data-required data-required-msg="El campo Código es requerido" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="fechaCita" class="label">Fecha de Cita</label>
                                            <input type="text" class="form-control isDatePicker" 
                                                   id="fechaCita" 
                                                   name="fechaCita"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Fecha de Cita es requerido">
                                        </div>
                                    </div>
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="horaCita" class="label">Hora de Cita</label>
                                            <input type="time" class="form-control" 
                                                   id="horaCita" 
                                                   name="horaCita"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Hora de Cita es requerido" >
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <input type="text" id="idPaciente" name="idPaciente" style="display:none">
                                            <label for="nombres" class="label">Nombres</label>
                                            <input type="text" class="form-control" 
                                                   id="nombres" readonly
                                                   name="nombres"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Nombres es requerido" 
                                                   data-max-length="100"
                                                   data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                        </div>
                                    </div>
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="apellidos" class="label">Apellidos</label>
                                            <input type="text" class="form-control" 
                                                   id="apellidos" readonly
                                                   name="apellidos"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Apellidos es requerido" 
                                                   data-max-length="100"
                                                   data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idEstado">Estado</label>
                                            <select id="idEstado" 
                                                    name="idEstado" 
                                                    class="form-control"
                                                    data-required data-required-msg="El campo Estado es requerido"
                                                    data-select-value-different="0"
                                                    data-select-value-different-msg="Por favor seleccione un Estado">
                                                <option value="-1" selected>- Seleccione uno -</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="saveUpdate()">Guardar</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div> 
        <%}%>
    </body>
</html>
