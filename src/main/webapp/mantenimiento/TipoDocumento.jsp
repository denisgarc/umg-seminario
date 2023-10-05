<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo de Documento</title>
    </head>
    <body>
        <script src="../js/mantenimiento/frmTipoDocumento.js" type="text/javascript"></script>
        <div class="container">
            <div class="card">
                <h3 class="card-header text-center bg-secondary text-white">Mantenimiento de Tipos de Documento</h3>
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
                                <table id="tblTipoDocumento" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                       data-url="<%= request.getContextPath()%>/TipoDocumentoController" data-method="GET" data-src=""
                                       data-show-buttons="true">
                                    <thead class="bg-blue-light text-white">
                                        <tr>
                                            <th class="text-center" data-key="idTipoDocumento">Código</th>
                                            <th class="text-center" data-key="descripcion">Descripción</th>
                                            <th class="text-center" data-key="abreviatura">Abreviatura</th>
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

            <div id="mainTipoDocModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="tipoDocumentoMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Tipo Documento</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmTipoDocumentoMant">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idTipoDocumento" class="label">Código</label>
                                            <input type="text" class="form-control" 
                                                   id="idTipoDocumento" 
                                                   name="idTipoDocumento"
                                                   data-required data-required-msg="El campo Código es requerido" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="form-group">
                                            <label for="descripcion" class="label">Descripcion</label>
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
                                            <label for="abreviatura" class="label">Abreviatura</label>
                                            <input type="text" class="form-control" 
                                                   id="abreviatura" 
                                                   name="abreviatura"
                                                   data-max-length="10"
                                                   data-max-length-msg="Solo se pueden ingresar 10 caracteres">
                                        </div>
                                    </div>
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
        </div>
    </body>
</html>
