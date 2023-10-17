<%-- 
    Document   : BuscarConsulta
    Created on : 29/10/2020, 07:47:37 PM
    Author     : DOxlaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Consulta</title>
    </head>
    <body>
        <script src="../js/cita/frmBuscarConsulta.js" type="text/javascript"></script>
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
                <h5 class="card-header bg-secondary">Consultas Realizadas</h5>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="table-responsive">
                                <table id="tblConsultas" class="table table-hover table-sm isDataTableConsulta isResponsiveTable withOutAutoFit isAjaxTable" 
                                       data-url="<%= request.getContextPath()%>/ConsultaBusquedaController" data-method="GET" data-src=""
                                       data-show-buttons="true"
                                       data-buttons="true">
                                    <thead class="bg-blue-light text-white">
                                        <tr>
                                            <th class="text-center" data-key="idConsulta">Código</th>
                                            <th class="text-center" data-key="fechaConsulta">Fecha</th>
                                            <th class="text-center" data-key="idTipoConsulta.descripcion">Tipo de Consulta</th>
                                            <th class="text-center" data-key="idCita.idPaciente.idPersona.nombres">Nombres</th>
                                            <th class="text-center" data-key="idCita.idPaciente.idPersona.apellidos">Apellidos</th>
                                            <th class="text-center" data-key="descripcion">Descripción</th>
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
    </body>
</html>
