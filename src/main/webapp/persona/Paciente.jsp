<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pacientes</title>
    </head>
    <body>
        <script src="../js/persona/frmPaciente.js" type="text/javascript"></script>
        <div class="">
            <div class="card">
                <h3 class="card-header text-center bg-secondary text-white">Mantenimiento de Pacientes</h3>
                <div class="card-body border border-secondary">
                    <div class="row">
                        <div class="col-12 col-sm-6 col-md-3 col-lg-3">
                            <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNew();"><i class="fas fa-plus"></i> Agregar</button>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="table-responsive">
                                <table id="tblPaciente" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                       data-url="<%= request.getContextPath()%>/PacienteController" data-method="GET" data-src=""
                                       data-show-buttons="true">
                                    <thead class="bg-blue-light text-white">
                                        <tr>
                                            <th class="text-center" data-key="idPaciente">Código</th>
                                            <th class="text-center" data-key="idPersona.nombres">Nombres</th>
                                            <th class="text-center" data-key="idPersona.apellidos">Apellidos</th>
                                            <th class="text-center" data-key="idPersona.documentoId">Documento No.</th>
                                            <th class="text-center" data-key="idPersona.idTipoDocumento.descripcion">Tipo Documento</th>
                                            <th class="text-center" data-key="idPersona.sexo">Sexo</th>
                                            <th class="text-center" data-key="idPersona.direccion">Dirección</th>
                                            <th class="text-center" data-key="idPersona.telefonos">Telefonos</th>
                                            <th class="text-center" data-key="numeroSeguro">No. Seguro</th>
                                            <th class="text-center" data-key="tipoSangre">Tipo de Sangre</th>
                                            <th class="text-center" data-key="fuma">Es Fumador</th>
                                            <th class="text-center" data-key="activo">Activo</th>
                                            <th class="text-center">Acciones</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="mainPacienteModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="PacienteMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title">Agregar / Editar Usuario</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="card">
                                <h4 class="card-header">Datos del Paciente</h4>
                                <div class="card-body">
                                    <form id="frmPacienteMant">
                                        <div class="row">
                                            <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label for="idPaciente" class="label">Código de Paciente</label>
                                                    <input type="text" class="form-control" 
                                                           id="idPaciente" 
                                                           name="idPaciente"
                                                           data-required data-required-msg="El campo Código es requerido" readonly>
                                                    <input type="text" class="form-control idPersona" 
                                                           id="idPersonaUsuario" style="display:none"
                                                           name="idPersona"
                                                           data-required data-required-msg="El campo Código es requerido" readonly>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label for="numeroSeguro">No. Seguro</label>
                                                    <input type="text" class="form-control" 
                                                           id="numeroSeguro" 
                                                           name="numeroSeguro"
                                                           autocomplete="off"
                                                           data-max-length="25"
                                                           data-max-length-msg="Solo se pueden ingresar 25 caracteres">
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label for="tipoSangre">Tipo Sangre</label>
                                                    <input type="text" class="form-control" 
                                                           id="tipoSangre" 
                                                           name="tipoSangre"
                                                           autocomplete="off"
                                                           data-max-length="5"
                                                           data-max-length-msg="Solo se pueden ingresar 5 caracteres">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label for="fecAlta" class="label">Fecha de Nacimiento</label>
                                                    <input type="text" class="form-control isDatePicker" 
                                                           id="fecAlta" 
                                                           name="fecAlta"
                                                           autocomplete="off"
                                                           data-required data-required-msg="El campo Fecha de Fecha de Alta es requerido">
                                                </div>
                                            </div>
                                            <div class="col-6 col-sm-4 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label for="fuma" class="label">Fuma</label><br>
                                                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                                        <label class="btn btn-outline-secondary active">
                                                            <input type="radio" name="fuma" id="activo" value="S" checked> Si
                                                        </label>
                                                        <label class="btn btn-outline-secondary">
                                                            <input type="radio" name="fuma" id="activo" value="N"> No
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-6 col-sm-4 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label for="activo" class="label">Activo</label><br>
                                                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                                        <label class="btn btn-outline-secondary active">
                                                            <input type="radio" name="activo" id="activo" value="S" checked> Si
                                                        </label>
                                                        <label class="btn btn-outline-secondary">
                                                            <input type="radio" name="activo" id="activo" value="N"> No
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="form-group">
                                                    <label for="">Persona Contacto (*)</label>
                                                    <div class="input-group">
                                                        <input id="idPersonaContacto" name="idPersonaContacto" style="display:none">
                                                        <span class="input-group-text"><i class="fas fa-search"></i></span>
                                                        <input id="idPersonaContactoNombre" name="idPersonaContactoNombre" class="form-control isAutoCompleteCustome" 
                                                               placeholder="Ingrese texto para iniciar la búsqueda" 
                                                               autocomplete="off"
                                                               data-method-url="PersonaConsultaController"
                                                               data-method="GET"
                                                               data-message-control="persona_error_msg_data-required">
                                                    </div>
                                                    <span id="persona_error_msg_data-required" class="validator_error"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="card">
                                <h4 class="card-header">Datos de la Persona</h4>
                                <div class="card-body">
                                    <%@include file="../views/shared/person-maintenance.jsp" %>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="save()">Guardar</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
    </body>
</html>
