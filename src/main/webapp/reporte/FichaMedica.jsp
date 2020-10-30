<%-- 
    Document   : FichaMedica
    Created on : 29/10/2020, 04:18:53 PM
    Author     : DOxlaj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ficha Médica</title>
    </head>
    <body>
        <script src="../js/reporte/frmFichaMedica.js" type="text/javascript"></script>

        <form id="frmPaciente">
            <div ciass="container">
                <div class="card">
                    <h3 class="card-header bg-secondary text-white text-center">Ficha Médica de Paciente</h3>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="form-group">
                                    <label for="">Nombre Paciente (*)</label>
                                    <div class="input-group">
                                        <input id="idPaciente" name="idPaciente" style="display:none">
                                        <span class="input-group-text"><i class="fas fa-search"></i></span>
                                        <input id="nombre" name="nombre" class="form-control isAutoComplete" 
                                               placeholder="Ingrese texto para iniciar la búsqueda" 
                                               autocomplete="off"
                                               data-method-url="PacienteConsultaController"
                                               data-method="GET"
                                               data-message-control="paciente_error_msg"
                                               data-required data-required-msg="El campo Paciente es obligatorio">
                                        <button type="button" class="btn btn-outline-info" onclick="loadProfile();">Mostrar</button>
                                    </div>
                                    <span id="paciente_error_msg" class="validator_error"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </form>
        <hr>
        <div id="divVisor">
            <iframe src="" width="100%" height="100%" style="min-height: 800px" name="visorPDF" id="visorPDF"></iframe>
        </div>
    </body>
</html>
