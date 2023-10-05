<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospitales</title>
    </head>
    <body>
        <script src="../js/mantenimiento/frmHospital.js" type="text/javascript"></script>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <ul class="nav nav-tabs nav-fill card-header-tabs" id="opcionesTab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link active" id="listado-tab" data-toggle="tab" href="#listado" role="tab" aria-controls="listado" aria-selected="true">Listado de Hospitales</a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link" id="mantenimiento-tab" data-toggle="tab" href="#mantenimiento" role="tab" aria-controls="mantenimiento" aria-selected="false">Detalle</a>
                                </li>
                            </ul>
                        </div>
                        <div class="card-body">
                            <div class="tab-content" id="listadoTabContent">
                                <div class="tab-pane fade show active" id="listado" role="tabpanel" aria-labelledby="evaluaciones-tab">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <h3 class="card-header text-center bg-secondary text-white">Mantenimiento de Hospitales</h3>
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
                                                                    <table id="tblHospital" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                                                           data-url="<%= request.getContextPath()%>/HospitalController" data-method="GET" data-src=""
                                                                           data-show-buttons="true">
                                                                        <thead class="bg-blue-light text-white">
                                                                            <tr>
                                                                                <th class="text-center" data-key="idHospital">Código</th>
                                                                                <th class="text-center" data-key="nombre">Nombre</th>
                                                                                <th class="text-center" data-key="direccion">Dirección</th>
                                                                                <th class="text-center" data-key="telefonos">Telefonos</th>
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
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="mantenimiento" role="tabpanel" aria-labelledby="cuestionarios-tab">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <div class="card-body border border-secondary">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                                <div class="form-group" id="frmSelectHospital">
                                                                    <label for="ddlHospital" class="label text-danger">Seleccione un Hospital</label>
                                                                    <div class="input-group">
                                                                        <select id="ddlHospital" 
                                                                                name="idHospital" 
                                                                                class="form-control"
                                                                                data-message-control="ddlHospital_error_msg_data_required"
                                                                                data-required data-required-msg="El campo Especialización es requerido"
                                                                                data-select-value-different="0"
                                                                                data-select-value-different-msg="Por favor seleccione un Hospital">
                                                                            <option value="-1" selected>- Seleccione uno -</option>
                                                                        </select>
                                                                        <button type="button" class="btn btn-outline-info" onclick="loadDetail();"><i class="fas fa-info-circle"></i> Detalles</button>
                                                                    </div>
                                                                    <span id="ddlHospital_error_msg_data_required" class="validator_error"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <h3 class="card-header text-center bg-secondary text-white">Salas del Hospital</h3>
                                                    <div class="card-body border border-secondary">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-6 col-md-3 col-lg-3">
                                                                <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNewSala();"><i class="fas fa-plus"></i> Agregar</button>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                                <div class="table-responsive">
                                                                    <table id="tblHospitalSala" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                                                           data-url="<%= request.getContextPath()%>/HospitalSalaController" data-method="GET" data-src=""
                                                                           data-show-buttons="true">
                                                                        <thead class="bg-blue-light text-white">
                                                                            <tr>
                                                                                <th class="text-center" data-key="hospitalSalaPK.idSala">Código</th>
                                                                                <th class="text-center" data-key="descripcion">Descripcion</th>
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
                                            </div>
                                        </div>
                                        <hr>                         
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <h3 class="card-header text-center bg-secondary text-white">Clinicas del Hospital</h3>
                                                    <div class="card-body border border-secondary">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-6 col-md-3 col-lg-3">
                                                                <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNewClinica();"><i class="fas fa-plus"></i> Agregar</button>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                                <div class="table-responsive">
                                                                    <table id="tblHospitalClinica" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                                                           data-url="<%= request.getContextPath()%>/HospitalClinicaController" data-method="GET" data-src=""
                                                                           data-show-buttons="true">
                                                                        <thead class="bg-blue-light text-white">
                                                                            <tr>
                                                                                <th class="text-center" data-key="hospitalClinicaPK.idClinica">Código</th>
                                                                                <th class="text-center" data-key="descripcion">Descripcion</th>
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
                                            </div>
                                        </div>
                                        <hr>             
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="card">
                                                    <h3 class="card-header text-center bg-secondary text-white">Horarios del Hospital</h3>
                                                    <div class="card-body border border-secondary">
                                                        <div class="row">
                                                            <div class="col-12 col-sm-6 col-md-3 col-lg-3">
                                                                <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNewHorario();"><i class="fas fa-plus"></i> Agregar</button>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                                                <div class="table-responsive">
                                                                    <table id="tblHospitalHorario" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                                                           data-url="<%= request.getContextPath()%>/HospitalHorarioController" data-method="GET" data-src=""
                                                                           data-show-buttons="true">
                                                                        <thead class="bg-blue-light text-white">
                                                                            <tr>
                                                                                <th class="text-center" data-key="hospitalHorarioPK.idHorario">Código</th>
                                                                                <th class="text-center" data-key="descripcion">Descripcion</th>
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
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal para hospital-->
            <div id="mainHospitalModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="HospitalMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Hospital</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmHospitalMant">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idHospital" class="label">Código</label>
                                            <input type="text" class="form-control" 
                                                   id="idHospital" 
                                                   name="idHospital"
                                                   data-required data-required-msg="El campo Código es requerido" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label for="nombre" class="label">Nombre</label>
                                            <input type="text" class="form-control" 
                                                   id="nombre" 
                                                   name="nombre"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Nombre es requerido" 
                                                   data-max-length="50"
                                                   data-max-length-msg="Solo se pueden ingresar 50 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label for="direccion" class="label">Dirección</label>
                                            <input type="text" class="form-control" 
                                                   id="direccion" 
                                                   name="direccion"
                                                   autocomplete="off"
                                                   data-max-length="100"
                                                   data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label for="telefonos" class="label">Telefonos</label>
                                            <input type="text" class="form-control" 
                                                   id="telefonos" 
                                                   name="telefonos"
                                                   autocomplete="off"
                                                   data-max-length="100"
                                                   data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
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
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="save()">Guardar</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div> 

            <!-- Modal para sala-->
            <div id="mainHospitalSalaModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="HospitalSalaMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Sala de Hospital</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmHospitalSalaMant">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idSala" class="label">Código</label>
                                            <input id="idHospital" name="idHospital" style="display: none">
                                            <input type="text" class="form-control" 
                                                   id="idSala" 
                                                   name="idSala"
                                                   data-required data-required-msg="El campo Código es requerido" readonly>
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
                                                   data-max-length="50"
                                                   data-max-length-msg="Solo se pueden ingresar 50 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
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
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="saveSala()">Guardar</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div> 

            <!-- Modal para Horario -->
            <div id="mainHospitalHorarioModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="HospitalHorarioMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Horario de Hospital</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmHospitalHorarioMant">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idHorario" class="label">Código</label>
                                            <input id="idHospital" name="idHospital" style="display: none">
                                            <input type="text" class="form-control" 
                                                   id="idHorario" 
                                                   name="idHorario"
                                                   data-required data-required-msg="El campo Código es requerido" readonly>
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
                                                   data-max-length="50"
                                                   data-max-length-msg="Solo se pueden ingresar 50 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="horaInicio" class="label">Hora de Inicio</label>
                                            <input type="time" class="form-control" 
                                                   id="horaInicio" 
                                                   name="horaInicio"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Hora de Inicio es requerido" 
                                                   data-max-length="50"
                                                   data-max-length-msg="Solo se pueden ingresar 50 caracteres">
                                        </div>
                                    </div>
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="horaFin" class="label">Hora de Fin</label>
                                            <input type="time" class="form-control" 
                                                   id="horaFin" 
                                                   name="horaFin"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Hora de fin es requerido" 
                                                   data-max-length="50"
                                                   data-max-length-msg="Solo se pueden ingresar 50 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
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
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="saveHorario()">Guardar</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div> 

            <!-- Modal para Clinica-->
            <div id="mainHospitalClinicaModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="HospitalClinicaMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Clinica de Hospital</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmHospitalClinicaMant">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idClinica" class="label">Código</label>
                                            <input id="idHospital" name="idHospital" style="display: none">
                                            <input type="text" class="form-control" 
                                                   id="idClinica" 
                                                   name="idClinica"
                                                   data-required data-required-msg="El campo Código es requerido" readonly>
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
                                                   data-max-length="50"
                                                   data-max-length-msg="Solo se pueden ingresar 50 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
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
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="saveClinica()">Guardar</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div> 

        </div>
    </body>
</html>
