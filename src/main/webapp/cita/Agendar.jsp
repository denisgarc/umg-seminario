<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendar Cita</title>
    </head>
    <body>
        <script src="../js/cita/frmAgendar.js" type="text/javascript"></script>
        <div class="container" runat="server" id="frmMantenimiento">
            <form id="frmCita" >
                <div class="card">
                    <div class="card-body">
                            <div id="smartwizard" class="isStepWizard withOutButton">
                            <ul>
                                <li><a href="#step-1">Cita<br />
                                        <small>Datos de la Cita</small></a></li>
                                <li><a href="#step-2">Paciente<br />
                                        <small>Datos del Paciente</small></a></li>
                                <li><a href="#step-3">Hospital<br />
                                        <small>Datos del Hospital</small></a></li>
                            </ul>
                            <div>
                                <div id="step-1" class="">
                                    <br />
                                    <div class="row">
                                        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                            <div class="form-group">
                                                <label for="idCita" class="label text-primary">Código</label>
                                                <input type="text" name="idEstado" value="1" style="display:none">
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
                                </div>
                                <div id="step-2" class="">
                                    <br />
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                    <div class="form-group">
                                                        <label for="">Buscar Paciente</label>
                                                        <div class="input-group">
                                                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                                                            <input name="idPaciente" class="idPaciente" style="display:none">
                                                            <input id="pacienteNombre" name="pacienteNombre" class="form-control isAutoCompleteCustomePaciente" 
                                                                   placeholder="Ingrese texto para iniciar la búsqueda" 
                                                                   autocomplete="off"
                                                                   data-method-url="PacienteConsultaController"
                                                                   data-method="GET"
                                                                   data-message-control="paciente_error_msg">
                                                            <button type="button" class="btn btn-success" onclick="loadNewPaciente()"><i class="fas fa-user-plus"></i> Nuevo</button>
                                                        </div>
                                                        <span id="paciente_error_msg" class="validator_error"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <h4 class="card-header bg-secondary">Datos Médicos</h4>
                                        <div class="card-body">
                                            <form></form>
                                            <form id="frmPaciente">
                                                <div class="row">
                                                    <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                                                        <div class="form-group">
                                                            <label for="idPaciente" class="label">Código de Paciente</label>
                                                            <input type="text" name="activo" value="S" style="display: none">
                                                            <input type="text" class="form-control idPaciente" 
                                                                   id="idPaciente"
                                                                   name="idPaciente"
                                                                   data-required data-required-msg="El campo Código es requerido" readonly>
                                                            <input type="text" class="form-control idPersona" 
                                                                   id="idPersonaPaciente" style="display:none"
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
                                                            <label for="fecAlta" class="label">Fecha de Alta</label>
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
<!--                                                    <div class="col-6 col-sm-4 col-md-4 col-lg-4">
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
                                                    </div>-->
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                        <div class="form-group">
                                                            <label for="">Contacto Personal</label>
                                                            <div class="input-group">
                                                                <input id="idPersonaContacto" name="idPersonaContacto" style="display:none">
                                                                <span class="input-group-text"><i class="fas fa-search"></i></span>
                                                                <input id="idPersonaContactoNombre" name="idPersonaContactoNombre" class="form-control isAutoCompleteCustomePerson" 
                                                                       placeholder="Ingrese texto para iniciar la búsqueda" 
                                                                       autocomplete="off"
                                                                       data-method-url="PersonaConsultaController"
                                                                       data-method="GET"
                                                                       data-message-control="persona_contacto_error_msg">
                                                            </div>
                                                            <span id="persona_contacto_error_msg" class="validator_error"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <h4 class="card-header bg-secondary">Datos Personales</h4>
                                        <div class="card-body">
                                            <%@include file="../views/shared/person-maintenance.jsp" %>
                                        </div>
                                    </div>
                                </div>
                                <div id="step-3" class="">
                                    <br />
                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="form-group" id="frmSelectHospital">
                                                <label for="idHospital" class="label">Seleccione un Hospital</label>
                                                <div class="input-group">
                                                    <select id="idHospital" 
                                                            name="idHospital" 
                                                            class="form-control"
                                                            data-message-control="idHospital_error_msg"
                                                            data-required data-required-msg="El campo Hospital es requerido"
                                                            data-select-value-different="0"
                                                            data-select-value-different-msg="Por favor seleccione un Hospital">
                                                        <option value="-1" selected>- Seleccione uno -</option>
                                                    </select>
                                                </div>
                                                <span id="idHospital_error_msg" class="validator_error"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <!--div class="row">
                                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="form-group" id="frmSelectHospital">
                                                <label for="idClinica" class="label">Seleccione una Clinica del Hospital</label>
                                                <div class="input-group">
                                                    <select id="idClinica" 
                                                            name="idClinica" 
                                                            class="form-control"
                                                            data-message-control="idHospitalClinica_error_msg"
                                                            data-required data-required-msg="El campo Clinica de Hospital es requerido"
                                                            data-select-value-different="0"
                                                            data-select-value-different-msg="Por favor seleccione una Clinica de Hospital">
                                                        <option value="-1" selected>- Seleccione uno -</option>
                                                    </select>
                                                </div>
                                                <span id="idHospitalClinica_error_msg" class="validator_error"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="form-group">
                                                <label for="idSala" class="label">Seleccione una Sala de Hospital</label>
                                                <div class="input-group">
                                                    <select id="idSala" 
                                                            name="idSala" 
                                                            class="form-control"
                                                            data-message-control="idSala_error_msg"
                                                            data-required data-required-msg="El campo Sala de Hospital es requerido"
                                                            data-select-value-different="0"
                                                            data-select-value-different-msg="Por favor seleccione una Sala de Hospital">
                                                        <option value="-1" selected>- Seleccione uno -</option>
                                                    </select>
                                                </div>
                                                <span id="idSala_error_msg" class="validator_error"></span>
                                            </div>
                                        </div>
                                    </div-->
                                </div>
                            </div>
                        </div>
                        <br />
                        <div class="row text-center">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="btn-group" role="group">
                                    <button class="btn btn-outline-danger" id="reset-btn" type="button">Cancelar</button>
                                    <button class="btn btn-outline-primary" id="prev-btn" type="button">Anterior</button>
                                    <button class="btn btn-outline-primary" id="next-btn" type="button">Siguiente</button>
                                    <button class="btn btn-outline-success" id="finish-btn" type="button">Finalizar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
