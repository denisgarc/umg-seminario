<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignación de Roles</title>
    </head>
    <body>
        <script src="../js/seguridad/frmUsuarioRol.js" type="text/javascript"></script>
        <div class="container">
            <div class="card">
                <h5 class="card-header bg-secondary">Usuario Seleccionado</h5>
                <div class="card-body border border-secondary">
                    <form id="frmBusqueda">
                        <div class="row" >
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="">Usuario / Nombre Usuario (*)</label>
                                    <div class="input-group">
                                        <input id="usuario" name="usuario" style="display:none">
                                        <span class="input-group-text"><i class="fas fa-search"></i></span>
                                        <input id="nombre" name="nombre" class="form-control isAutoComplete" 
                                               placeholder="Ingrese texto para iniciar la búsqueda" 
                                               autocomplete="off"
                                               data-method-url="UsuarioConsultaController"
                                               data-method="GET"
                                               data-message-control="nombreUsr_error_msg_data-required"
                                               data-required data-required-msg="El campo Usuario es obligatorio">
                                        <button type="button" class="btn btn-outline-info" onclick="loadPerfiles();">Roles</button>
                                    </div>
                                    <span id="nombreUsr_error_msg_data-required" class="validator_error"></span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="card" id="divRoles">
                <h5 class="card-header bg-secondary">Roles Asignados</h5>
                <div class="card-body border border-secondary">
                    <div class="row">
                        <div class="col-12">
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group mr-2" role="group" aria-label="First group">
                                    <button type="button" class="btn btn-success" onclick="asignarRol();"><i class="fas fa-plus-circle"></i></button>
                                    <button type="button" class="btn btn-danger" onclick="eliminarRol();"><i class="far fa-trash-alt"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-12">
                            <form id="frmAddRol">
                                <input class="idUsuario" name="idUsuario" style="display:none">
                                <input class="action" name="action" style="display:none">
                                <div class="form-group">
                                    <label for="">Rol (*)</label>
                                    <select id="ddlRol" class="form-control" name="idRol"
                                        data-required data-required-msg="El campo Rol es requerido"
                                        data-select-value-different="0"
                                        data-select-value-different-msg="Por favor seleccione un Rol a Asignar">
                                        <option value="-1" selected>- Seleccione uno -</option>
                                    </select>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <form id="frmDeleteRol">
                                <input class="idUsuario" name="idUsuario" style="display:none">
                                <input class="action" name="action" style="display:none">
                                <select name="idRol" 
                                    id="ddlRolUsuario" 
                                    class="form-control"
                                    data-required data-required-msg="El campo Rol es requerido"
                                    multiple>
                                </select>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
