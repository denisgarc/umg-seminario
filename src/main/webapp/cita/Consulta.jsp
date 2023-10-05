<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Long idCita = 0L;
    Long idConsulta = 0L;
    if (request.getParameter("idCita") != null) {
        idCita = Long.parseLong(request.getParameter("idCita"));
    }

    if (request.getParameter("idConsulta") != null) {
        idConsulta = Long.parseLong(request.getParameter("idConsulta"));
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Médica</title>
    </head>
    <body>
        <script src="../js/cita/frmConsulta.js" type="text/javascript"></script>
        <div class="container-fluid">
            <!--            <div class="row">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
            
                            </div>
                        </div>-->
            <div class="row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="card">
                        <div class="card-header">
                            <ul class="nav nav-tabs nav-fill card-header-tabs" id="opcionesTab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link active" id="consulta-tab" data-toggle="tab" href="#consulta" role="tab" aria-controls="consulta" aria-selected="true">Consulta</a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link" id="diagnostico-tab" data-toggle="tab" href="#diagnostico" role="tab" aria-controls="diagnostico" aria-selected="false">Diagnosticos</a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link" id="tratamiento-tab" data-toggle="tab" href="#tratamiento" role="tab" aria-controls="tratamiento" aria-selected="false">Tratamientos</a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link" id="receta-tab" data-toggle="tab" href="#receta" role="tab" aria-controls="tratamiento" aria-selected="false">Receta</a>
                                </li>
                            </ul>
                        </div>
                        <div class="card-body">
                            <div class="tab-content" id="consultaTabContent">
                                <div class="tab-pane fade show active" id="consulta" role="tabpanel" aria-labelledby="consulta-tab">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <form id="frmConsulta">
                                                    <div class="row">
                                                        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                            <div class="form-group">
                                                                <label for="idCita" class="label text-primary">Código</label>
                                                                <input type="text" id="idCita" name="idCita" style="display:none">
                                                                <input type="text" id="activo" name="activo" value="S" style="display:none">
                                                                <input type="text" class="form-control" 
                                                                       id="idConsulta" 
                                                                       name="idConsulta" value="0" text="0"
                                                                       data-required data-required-msg="El campo Código es requerido" readonly>
                                                            </div>
                                                        </div>
                                                        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                            <div class="form-group">
                                                                <label for="fechaConsulta" class="label">Fecha de Consulta</label>
                                                                <input type="text" class="form-control isDatePicker" 
                                                                       id="fechaConsulta" 
                                                                       name="fechaConsulta"
                                                                       autocomplete="off"
                                                                       data-required data-required-msg="El campo Fecha de Consulta es requerido">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                            <div class="form-group">
                                                                <label for="nombres" class="label">Nombres</label>
                                                                <input type="text" class="form-control" readonly
                                                                       id="nombres" 
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
                                                                <input type="text" class="form-control" readonly
                                                                       id="apellidos" 
                                                                       name="apellidos"
                                                                       autocomplete="off"
                                                                       data-required data-required-msg="El campo Apellidos es requerido" 
                                                                       data-max-length="100"
                                                                       data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-3 col-md-3 col-lg-3">
                                                            <div class="form-group" id="frmSelectHospital">
                                                                <label for="idHospital" class="label">Tipo de Consulta</label>
                                                                <div class="input-group">
                                                                    <select id="idTipoConsulta" 
                                                                            name="idTipoConsulta" 
                                                                            class="form-control"
                                                                            data-message-control="idTipoConsulta_error_msg"
                                                                            data-required data-required-msg="El campo Tipo de Consulta es requerido"
                                                                            data-select-value-different="0"
                                                                            data-select-value-different-msg="Por favor seleccione un Tipo de Consulta">
                                                                        <option value="-1" selected>- Seleccione uno -</option>
                                                                    </select>
                                                                </div>
                                                                <span id="idTipoConsulta_error_msg" class="validator_error"></span>
                                                            </div>
                                                        </div>
                                                        <div class="col-12 col-sm-9 col-md-9 col-lg-9">
                                                            <div class="form-group">
                                                                <label for="">Médico que Atiende</label>
                                                                <div class="input-group">
                                                                    <input id="idEmpleado" name="idEmpleado" style="display:none">
                                                                    <span class="input-group-text"><i class="fas fa-search"></i></span>
                                                                    <input id="empleadoNombre" name="empleadoNombre" class="form-control isAutoComplete" 
                                                                           placeholder="Ingrese texto para iniciar la búsqueda" 
                                                                           autocomplete="off"
                                                                           data-method-url="EmpleadoConsultaController"
                                                                           data-method="GET"
                                                                           data-message-control="empleado_error_msg"
                                                                           data-required data-required-msg="El campo Médico que atiende es requerido">
                                                                </div>
                                                                <span id="empleado_error_msg" class="validator_error"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                            <div class="form-group">
                                                                <label for="descripcion">Descripción</label>
                                                                <textarea id="descripcion" name="descripcion" rows="5"
                                                                          class="form-control"
                                                                          maxlength="250" 
                                                                          data-required data-required-msg="El campo Fecha de Consulta es requerido"
                                                                          data-max-length="250"
                                                                          data-max-length-msg="Solo se pueden ingresar 250 caracteres"></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                            <div class="form-group">
                                                                <label for="observaciones">Observaciones</label>
                                                                <textarea id="descripcion" name="observaciones" rows="5"
                                                                          class="form-control"
                                                                          maxlength="250" 
                                                                          data-max-length="250"
                                                                          data-max-length-msg="Solo se pueden ingresar 250 caracteres"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <button type="button" class="btn btn-success" onclick="save('consulta')">Guardar</button>
                                                <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="diagnostico" role="tabpanel" aria-labelledby="diagnostico-tab">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <!--                                                    <h3 class="card-header text-center bg-secondary text-white">Tratmientos</h3>-->
                                                    <div class="card-body border border-secondary">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-6 col-md-3 col-lg-3">
                                                                <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNew('diagnostico');"><i class="fas fa-plus"></i> Agregar</button>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                                <div class="table-responsive">
                                                                    <table id="tblDiagnosticos" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                                                           data-url="<%= request.getContextPath()%>/ConsultaDiagnosticoController?idConsulta=0" data-method="GET" data-src=""
                                                                           data-show-buttons="true">
                                                                        <thead class="bg-blue-light text-white">
                                                                            <tr>
                                                                                <th class="text-center" data-key="idDiagnostico">Código</th>
                                                                                <th class="text-center" data-key="idTipoDiagnostico.descripcion">Tipo Diagnostico</th>
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
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="tratamiento" role="tabpanel" aria-labelledby="tratamiento-tab">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <!--                                                    <h3 class="card-header text-center bg-secondary text-white">Tratmientos</h3>-->
                                                    <div class="card-body border border-secondary">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-6 col-md-3 col-lg-3">
                                                                <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNew('tratamiento');"><i class="fas fa-plus"></i> Agregar</button>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                                <div class="table-responsive">
                                                                    <table id="tblTratamientos" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                                                           data-url="<%= request.getContextPath()%>/ConsultaTratamientoController?idConsulta=0" data-method="GET" data-src=""
                                                                           data-show-buttons="true">
                                                                        <thead class="bg-blue-light text-white">
                                                                            <tr>
                                                                                <th class="text-center" data-key="idTratamiento">Código</th>
                                                                                <th class="text-center" data-key="idTipoTratamiento.descripcion">Tipo Tratamiento</th>
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
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="receta" role="tabpanel" aria-labelledby="receta-tab">
                                    <div class="container-fluid">
                                        <form id="frmReceta">
                                            <div class="row">

                                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                    <div class="form-group">
                                                        <label for="idReceta" class="label text-primary">Código</label>
                                                        <input type="text" id="idConsulta" name="idConsulta" class="idConsulta" style="display:none">
                                                        <input type="text" class="form-control" 
                                                               id="idReceta" 
                                                               name="idReceta" value="0" text="0"
                                                               data-required data-required-msg="El campo Código es requerido" readonly>
                                                    </div>
                                                </div>
                                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                    <div class="form-group">
                                                        <label for="fechaVencimiento" class="label">Fecha de Vencimiento</label>
                                                        <input type="text" class="form-control isDatePicker" 
                                                               id="fechaVencimiento" 
                                                               name="fechaVencimiento"
                                                               autocomplete="off"
                                                               data-required data-required-msg="El campo Fecha de Vencimiento es requerido">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <hr>
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <!--                                                    <h3 class="card-header text-center bg-secondary text-white">Tratmientos</h3>-->
                                                    <div class="card-body border border-secondary">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-6 col-md-3 col-lg-3">
                                                                <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNew('medicamento');"><i class="fas fa-plus"></i> Agregar</button>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                                <div class="table-responsive">
                                                                    <table id="tblRecetaDetalle" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                                                           data-url="<%= request.getContextPath()%>/RecetaDetalleController?idReceta=0" data-method="GET" data-src=""
                                                                           data-show-buttons="true">
                                                                        <thead class="bg-blue-light text-white">
                                                                            <tr>
                                                                                <th class="text-center" data-key="idReceta">Código</th>
                                                                                <th class="text-center" data-key="idMedicamento.nombreComercial">Medicamento</th>
                                                                                <th class="text-center" data-key="cantidad">Cantidad</th>
                                                                                <th class="text-center" data-key="indicaciones">Indicaciones</th>
                                                                                <th class="text-center" data-key="duracion">Duración</th>
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
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modales -->
        <div id="frmConsultaDiagnosticoModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ConsultaDiagnosticoMaintenance" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-blue-light text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Diagnostico</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmConsultaDiagnostico">
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label for="idDiagnostico" class="label">Código</label>
                                        <input id="idConsulta" name="idConsulta" value="0" style="display:none">
                                        <input id="activo" name="activo" value="S" style="display:none">
                                        <input type="text" class="form-control" 
                                               id="idDiagnostico" 
                                               name="idDiagnostico"
                                               data-required data-required-msg="El campo Código es requerido" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group" id="frmSelectHospital">
                                        <label for="idTipoDiagnostico" class="label">Tipo de Diagnostico</label>
                                        <div class="input-group">
                                            <select id="idTipoDiagnostico" 
                                                    name="idTipoDiagnostico" 
                                                    class="form-control"
                                                    data-message-control="idTipoDiagnostico_error_msg"
                                                    data-required data-required-msg="El campo Tipo de Diagnostico es requerido"
                                                    data-select-value-different="0"
                                                    data-select-value-different-msg="Por favor seleccione un Tipo de Diagnostico">
                                                <option value="-1" selected>- Seleccione uno -</option>
                                            </select>
                                        </div>
                                        <span id="idTipoDiagnostico_error_msg" class="validator_error"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="descripcion" class="label">Descripción</label>
                                        <input type="text" class="form-control" 
                                               id="descripcion" 
                                               name="descripcion"
                                               autocomplete="off"
                                               data-required data-required-msg="El campo Descripción es requerido" 
                                               data-max-length="100"
                                               data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="save('diagnostico')">Guardar</button>
                        <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="frmConsultaTratamientoModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ConsultaTratamientoMaintenance" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-blue-light text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Tratamiento</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmConsultaTratamiento">
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label for="idTratamiento" class="label">Código</label>
                                        <input id="idConsulta" name="idConsulta" value="0" style="display:none">
                                        <input id="activo" name="activo" value="S" style="display:none">
                                        <input type="text" class="form-control" 
                                               id="idTratamiento" 
                                               name="idTratamiento"
                                               data-required data-required-msg="El campo Código es requerido" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group" id="frmSelectHospital">
                                        <label for="idTipoTratamiento" class="label">Tipo de Tratamiento</label>
                                        <div class="input-group">
                                            <select id="idTipoTratamiento" 
                                                    name="idTipoTratamiento" 
                                                    class="form-control"
                                                    data-message-control="idTipoTratamiento_error_msg"
                                                    data-required data-required-msg="El campo Tipo de Tratamiento es requerido"
                                                    data-select-value-different="0"
                                                    data-select-value-different-msg="Por favor seleccione un Tipo de Tratamiento">
                                                <option value="-1" selected>- Seleccione uno -</option>
                                            </select>
                                        </div>
                                        <span id="idTipoTratamiento_error_msg" class="validator_error"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="descripcion" class="label">Descripción</label>
                                        <input type="text" class="form-control" 
                                               id="descripcion" 
                                               name="descripcion"
                                               autocomplete="off"
                                               data-required data-required-msg="El campo Descripción es requerido" 
                                               data-max-length="100"
                                               data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="save('tratamiento')">Guardar</button>
                        <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="frmRecetaDetalleModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="RecetaDetalleMaintenance" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-blue-light text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Medicamento</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmRecetaDetalle">
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group" id="frmSelectHospital">
                                        <label for="idMedicamento" class="label">Medicamento</label>
                                        <input id="idReceta" name="idReceta" class="idReceta" value="0" style="display:none">
                                        <div class="input-group">
                                            <select id="idMedicamento" 
                                                    name="idMedicamento" 
                                                    class="form-control"
                                                    data-message-control="idMedicamento_error_msg"
                                                    data-required data-required-msg="El campo Medicamento es requerido"
                                                    data-select-value-different="0"
                                                    data-select-value-different-msg="Por favor seleccione un Medicamento">
                                                <option value="-1" selected>- Seleccione uno -</option>
                                            </select>
                                        </div>
                                        <span id="idMedicamento_error_msg" class="validator_error"></span>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-2 col-md-2 col-lg-2">
                                    <div class="form-group">
                                        <label for="cantidad" class="label">Cantidad</label>
                                        <input type="text" class="form-control isNumeric" 
                                               id="cantidad" 
                                               name="cantidad"
                                               autocomplete="off"
                                               data-size="3" data-scale="0"
                                               data-required data-required-msg="El campo Cantidad es requerido">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label for="indicaciones" class="label">Indicaciones</label>
                                        <input type="text" class="form-control" 
                                               id="indicaciones" 
                                               name="indicaciones"
                                               autocomplete="off"
                                               data-required data-required-msg="El campo Indicaciones es requerido" 
                                               data-max-length="100"
                                               data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                    </div>
                                </div>
                                <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                    <div class="form-group">
                                        <label for="duracion" class="label">Duración</label>
                                        <input type="text" class="form-control" 
                                               id="duracion" 
                                               name="duracion"
                                               autocomplete="off"
                                               data-required data-required-msg="El campo Duración es requerido" 
                                               data-max-length="100"
                                               data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="save('medicamento')">Guardar</button>
                        <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $idCita = <%out.println(idCita); %>;
            $idConsulta = <%out.println(idConsulta);%>;
        </script>
    </body>
</html>
