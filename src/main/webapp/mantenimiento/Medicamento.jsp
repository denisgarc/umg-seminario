<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medicamento</title>
    </head>
    <body>
        <script src="../js/mantenimiento/frmMedicamento.js" type="text/javascript"></script>
        <div class="container">
            <div class="card">
                <h3 class="card-header text-center bg-secondary text-white">Mantenimiento de Medicamentos</h3>
                <div class="card-body border border-secondary">
                    <div class="row">
                        <div class="col-6 col-sm-6 col-md-3 col-lg-3">
                            <button id="btnAdd" class="btn btn-outline-info btn-block" onclick="loadNew();"><i class="fas fa-plus"></i> Agregar</button>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="table-responsive">
                                <table id="tblMedicamento" class="table table-hover table-sm isDataTable withOutAutoFit isAjaxTable" 
                                       data-url="<%= request.getContextPath()%>/MedicamentoController" data-method="GET" data-src=""
                                       data-show-buttons="true">
                                    <thead class="bg-blue-light text-white">
                                        <tr>
                                            <th class="text-center" data-key="idMedicamento">Código</th>
                                            <th class="text-center" data-key="nombre">Nombre</th>
                                            <th class="text-center" data-key="nombreComercial">Nombre Comercial</th>
                                            <th class="text-center" data-key="precio">Precio (Q)</th>
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

            <div id="mainMedicamentoModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="MedicamentoMaintenance" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-blue-light text-white">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar / Editar Medicamento</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="frmMedicamentoMant">
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="idMedicamento" class="label">Código</label>
                                            <input type="text" class="form-control" 
                                                   id="idMedicamento" 
                                                   name="idMedicamento"
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
                                            <label for="nombreComercial" class="label">Nombre Comercial</label>
                                            <input type="text" class="form-control" 
                                                   id="nombreComercial" 
                                                   name="nombreComercial"
                                                   autocomplete="off"
                                                   data-required data-required-msg="El campo Nombre Comercial es requerido" 
                                                   data-max-length="100"
                                                   data-max-length-msg="Solo se pueden ingresar 100 caracteres">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6 col-md-6 col-lg-6">
                                        <div class="form-group">
                                            <label for="descripcion" class="label">Precio (Q)</label>
                                            <input type="text" class="form-control isNumeric" 
                                                   id="precio" 
                                                   name="precio"
                                                   autocomplete="off"
                                                   data-size="8" data-scale="2"
                                                   data-required data-required-msg="El campo Precio es requerido" 
                                                   data-max-length="11"
                                                   data-max-length-msg="Solo se pueden ingresar 11 caracteres">
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
