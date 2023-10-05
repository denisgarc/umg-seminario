<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
    </head>
    <body>
        <script src="../js/persona/frmEmpleado.js" type="text/javascript"></script>
        <div class="container">
            <div class="card">
                <h3 class="card-header text-center bg-secondary text-white">Mantenimiento de Empleados</h3>
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
                                <table id="tblEmpleado" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                       data-url="<%= request.getContextPath()%>/EmpleadoController" data-method="GET" data-src=""
                                       data-show-buttons="true">
                                    <thead class="bg-blue-light text-white">
                                        <tr>
                                            <th class="text-center" data-key="idEmpleado">Código</th>
                                            <th class="text-center" data-key="idPersona.nombres">Nombres</th>
                                            <th class="text-center" data-key="idPersona.apellidos">Apellidos</th>
                                            <th class="text-center" data-key="idPersona.documentoId">Documento No.</th>
                                            <th class="text-center" data-key="idPersona.idTipoDocumento.descripcion">Tipo Documento</th>
                                            <th class="text-center" data-key="idPersona.sexo">Sexo</th>
                                            <th class="text-center" data-key="idEspecializacion.descripcion">Especializacion</th>
                                            <th class="text-center" data-key="idPuesto.descripcion">Puesto</th>
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
            <div id="mainEmpleadoModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="EmpleadoMaintenance" aria-hidden="true">
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
                                <h4 class="card-header">Datos del Empleado</h4>
                                <div class="card-body">
                                    <form id="frmEmpleadoMant">
                                        <div class="row">
                                            <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                <div class="form-group">
                                                    <label for="idEmpleado" class="label">ID</label>
                                                    <input type="text" class="form-control" 
                                                           id="idEmpleado" 
                                                           name="idEmpleado"
                                                           data-required data-required-msg="El campo Código es requerido" readonly>
                                                    <input type="text" class="form-control idPersona" 
                                                           id="idPersonaUsuario" style="display:none"
                                                           name="idPersona"
                                                           data-required data-required-msg="El campo Código es requerido" readonly>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                <div class="form-group">
                                                    <label for="idEspecializacion">Especialización</label>
                                                    <select id="idEspecializacion" 
                                                        name="idEspecializacion" 
                                                        class="form-control"
                                                        data-required data-required-msg="El campo Especialización es requerido"
                                                        data-select-value-different="0"
                                                        data-select-value-different-msg="Por favor seleccione una Especializacio´n">
                                                        <option value="-1" selected>- Seleccione uno -</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                <div class="form-group">
                                                    <label for="idPuesto">Puesto</label>
                                                    <select id="idPuesto" 
                                                        name="idPuesto" 
                                                        class="form-control"
                                                        data-required data-required-msg="El campo Puesto es requerido"
                                                        data-select-value-different="0"
                                                        data-select-value-different-msg="Por favor seleccione un Puesto">
                                                        <option value="-1" selected>- Seleccione uno -</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                                <div class="form-group">
                                                    <label for="fecIngreso" class="label">Fecha de Alta (*)</label>
                                                    <input type="text" class="form-control isDatePicker" 
                                                           id="fecIngreso" 
                                                           name="fecIngreso"
                                                           autocomplete="off"
                                                           data-required data-required-msg="El campo Fecha de Alta es requerido" >
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-3 col-md-3 col-lg-3">
                                                <div class="form-group">
                                                    <label for="fecBaja" class="label">Fecha de Baja</label>
                                                    <input type="text" class="form-control isDatePicker" 
                                                           id="fecBaja" 
                                                           name="fecBaja"
                                                           autocomplete="off">
                                                </div>
                                            </div>
                                            <div class="col-12 col-sm-3 col-md-3 col-lg-3">
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
