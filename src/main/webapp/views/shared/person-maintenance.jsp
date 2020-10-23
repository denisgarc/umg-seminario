<div class="row" id="rowBuscar" >
    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
        <div class="form-group">
            <label for="">Buscar Persona (*)</label>
            <div class="input-group">
                <input id="usuario" name="usuario" style="display:none">
                <span class="input-group-text"><i class="fas fa-search"></i></span>
                <input id="nombre" name="nombre" class="form-control isAutoCompleteCustome" 
                       placeholder="Ingrese texto para iniciar la búsqueda" 
                       autocomplete="off"
                       data-message-control="persona_error_msg_data-required"
                       data-required data-required-msg="El campo Usuario es obligatorio">
            </div>
            <span id="persona_error_msg_data-required" class="validator_error"></span>
        </div>
    </div>
</div>
<form id="frmPersona">
    <div class="row">
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
            <div class="form-group">
                <label for="idPersona" class="label">Código de Persona</label>
                <input type="text" class="form-control idPersona" 
                       id="idPersona" 
                       name="idPersona"
                       data-required data-required-msg="El campo Código es requerido" readonly>
                <input type="text" name="activo" value="S" style="display: none">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
            <div class="form-group">
                <label for="nombres" class="label">Nombres</label>
                <input type="text" class="form-control" 
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
                <input type="text" class="form-control" 
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
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
            <div class="form-group">
                <label for="idTipoDocumento">Tipo de Documento</label>
                <select id="idTipoDocumento" 
                        name="idTipoDocumento" 
                        class="form-control"
                        data-required data-required-msg="El campo Tipo de Documento es requerido"
                        data-select-value-different="0"
                        data-select-value-different-msg="Por favor seleccione un Tipo de Documento">
                    <option value="-1" selected>- Seleccione uno -</option>
                </select>
            </div>
        </div>
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
            <div class="form-group">
                <label for="documentoId" class="label">Documento No.</label>
                <input type="text" class="form-control" 
                       id="documentoId" 
                       name="documentoId"
                       autocomplete="off"
                       data-required data-required-msg="El campo Documento No. es requerido" 
                       data-max-length="30"
                       data-max-length-msg="Solo se pueden ingresar 30 caracteres">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
            <div class="form-group">
                <label for="fecNacimiento" class="label">Fecha de Nacimiento</label>
                <input type="text" class="form-control isDatePicker" 
                       id="fecNacimiento" 
                       name="fecNacimiento"
                       autocomplete="off"
                       data-required data-required-msg="El campo Fecha de Fecha de Nacimiento es requerido">
            </div>
        </div>
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
            <div class="form-group">
                <label for="sexo" class="label">Sexo</label><br>
                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label class="btn btn-outline-secondary active">
                        <input type="radio" name="sexo" id="sexo" value="M" checked> M
                    </label>
                    <label class="btn btn-outline-secondary">
                        <input type="radio" name="sexo" id="sexo" value="F"> F
                    </label>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
            <div class="form-group">
                <label for="direccion" class="label">Dirección</label>
                <input type="text" class="form-control" 
                       id="direccion" 
                       name="direccion"
                       autocomplete="off"
                       data-max-length="200"
                       data-max-length-msg="Solo se pueden ingresar 200 caracteres">
            </div>
        </div>
        <div class="col-12 col-sm-6 col-md-6 col-lg-6">
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
</form>