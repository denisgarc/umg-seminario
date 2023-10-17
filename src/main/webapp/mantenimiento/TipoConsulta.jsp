<%-- 
    Document   : TipoConsulta
    Created on : 20/10/2020, 06:25:05 AM
    Author     : DOxlaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo Consulta</title>
    </head>
    <body>
        <script src="../js/mantenimiento/frmTipoConsulta.js" type="text/javascript"></script>
        <div class="container">
            <div class="card">
                <h3 class="card-header text-center bg-secondary ">Mantenimiento de Tipos de Consulta</h3>
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
                                <table id="tblTipoConsulta" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                       data-url="<%= request.getContextPath()%>/TipoConsultaController" data-method="GET" data-src=""
                                       data-show-buttons="true">
                                    <thead class="bg-blue-light text-white">
                                        <tr>
                                            <th class="text-center" data-key="idTipoConsulta">Código</th>
                                            <th class="text-center" data-key="descripcion">Descripción</th>
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

            <div id="mainTipoConsultaModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="tipoConsultaMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Tipo Consulta</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmTipoConsultaMant">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idTipoConsulta" class="label">Código</label>
                                            <input type="text" class="form-control" 
                                                   id="idTipoConsulta" 
                                                   name="idTipoConsulta"
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
                                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
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
