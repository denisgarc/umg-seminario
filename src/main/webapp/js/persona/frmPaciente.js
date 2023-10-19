var newRecord = {
    "idPaciente": 0,
    "numeroSeguro": '',
    "tipoSangre": '',
    "fuma": '',
    "fecAlta": new Date(),
    "activo": '',
    "nombre": '',
    "idPersona": {
        "idPersona": 0,
        "nombres": "",
        "apellidos": '',
        "fecNacimiento": new Date(),
        "documentoId": '',
        "sexo": "M",
        "direccion": "",
        "telefonos": '',
        "idTipoDocumento": {
            "idTipoDocumento": -1,
            "descripcion": "",
            "activo": "",
            "abreviatura": ""
        }
    },
    "idPersonaContacto": {
        "idPersona": 0,
        "nombres": "",
        "apellidos": '',
        "fecNacimiento": new Date(),
        "documentoId": '',
        "sexo": "M",
        "direccion": "",
        "telefonos": '',
        "idTipoDocumento": {
            "idTipoDocumento": -1,
            "descripcion": "",
            "activo": "",
            "abreviatura": ""
        }
    }
};

var $modalId = 'mainPacienteModal';
var $formAuxId = 'frmPacienteMant';
var $formPersonId = 'frmPersona';
var $tableId = 'tblPaciente';
var $controller = 'PacienteController';
var $personController = 'PersonaController';

$(document).ready(function (e) {
    // Se cargan los tipos de documento
    loadTipoDocumento();
    loadEspecializacion();
    loadPuesto();

    $(`#${$tableId} tbody`).on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $(`#${$tableId}`).DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "edit":
                loadData(data);
                $('#rowBuscar').hide();
                $(`#${$modalId}`).modal({
                    keyboard: false,
                    focus: true
                });
                break;
            case "delete":
                ShowConfirmationDialog('¿Está seguro de deshabilitar el registro?').then((isOk) => {
                    if (isOk) {
                        data.activo = 'N';
                        //var idPersona = data.idPersona.idPersona;
                        data.idPersona = data.idPersona.idPersona;
                        data.fecAlta = new Date(data.fecAlta).toStringDMY();
                        if(data.idPersonaContacto != null)
                            data.idPersonaContacto = data.idPersonaContacto.idPersona;
                        
                        aaveDelete(data, 'E');
                    }
                }).catch((err) => {
                    console.error(err);
                    ShowErrorDialog('Lo sentimos ha ocurrido un error');
                });
                break;
        }
    });
    
    $('.isAutoCompleteCustome').on("focus", function () {
        var _Url = $BaseUrl + 'PersonaConsultaController';
        $(this).autocomplete({
            source: function (request, response) {
                $.ajax({
                    type: 'GET',
                    url: `${_Url}?filter=${request.term}&type=full`,
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    success: function (data) {
                        response($.map(data, function (item) {
                            return {label: item["nombres"] + ' ' + item["apellidos"], value: item["idPersona"], model: item}
                        }));
                    },
                    error: function (error) {
                        console.error(error);
                    }
                });
            },
            minLength: 3,
            delay: 1000,
            select: function (e, selected) {
                e.preventDefault();
                $(this).val(selected.item.label);
                $(Array.from($(this).parent().find('input')).filter(x => x.id != $(this)[0].id)).val(selected.item.value);
                loadPerson(selected.item.model);
            }
        });
    });

    $('.isAutoCompleteCustomePerson').on("focus", function () {
        var _Url = $BaseUrl + ($(this)[0].hasAttribute("data-method-url") ? ('/' + $(this)[0].attributes.getNamedItem("data-method-url").value) : '');
        var _method = ($(this)[0].hasAttribute("data-method") ? ('/' + $(this)[0].attributes.getNamedItem("data-method").value) : 'POST');
        $(this).autocomplete({
            source: function (request, response) {
                //var datos = { filter: request.term };
                var datos = `filter=${request.term}`;
                $.ajax({
                    type: 'GET',
                    url: `${_Url}?filter=${request.term}`,
                    //data: datos,
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    success: function (data) {
                        response($.map(data, function (item) {
                            return { label: item["label"], value: item["value"] }
                        }));
                    },
                    error: function (error) {
                        console.error(error);
                    }
                });
            },
            minLength: 3,
            delay: 1000,
            select: function (e, selected) {
                e.preventDefault();
                $(this).val(selected.item.label);
                $('#idPersonaContacto').val(selected.item.value);
                //$(Array.from($(this).parent().find('input')).filter(x => x.id != $(this)[0].id)).val(selected.item.value)
            }
        });
    });
    
    // Validación para habilitar fecha de deceso solo cuando el estado se haya seleccionado
    $('#activo').change(function() {
        if ($(this).val() === "Deceso") {
            $('#fecDeceso').prop('disabled', false);
        } else {
            $('#fecDeceso').prop('disabled', true);
        }
    });
});

function loadData(selectedItem) {
    // Para el usuario
    Object.keys(selectedItem).forEach((key) => {
        if (key != 'idPersona') {
            if (key == 'activo' || key == 'fuma') {
                $('input[name="' + key + '"]').attr('checked', false);
                $('input[name="' + key + '"][value="' + selectedItem[key] + '"]').attr('checked', true);
                $('input[name="' + key + '"][value="' + selectedItem[key] + '"]').click();
            } else if (key == 'fecAlta') {
                $(`#` + key).val(new Date(selectedItem[key]).toStringDMY());
            } else if(key == 'idPersonaContacto'){
                if(selectedItem[key] != null){
                    $(`#` + key).val(selectedItem[key].idPersona);
                    $(`#` + key + 'Nombre').val(selectedItem[key].nombres + ' ' + selectedItem[key].apellidos);
                }
            } else {
                $(`#` + key).val(selectedItem[key]);
            }
        }
    });

    // Para la Persona
    loadPerson(selectedItem.idPersona);
}

function loadPerson(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if (key == 'activo' || key == 'sexo') {
            if(key != 'activo'){
                $('input[name="' + key + '"]').attr('checked', false);
                $('input[name="' + key + '"][value="' + selectedItem[key] + '"]').attr('checked', true);
                $('input[name="' + key + '"][value="' + selectedItem[key] + '"]').click();
            }
        } else if (key == 'fecNacimiento') {
            $(`#` + key).val(new Date(selectedItem[key]).toStringDMY());
        } else if (key == 'idTipoDocumento') {
            $(`#` + key).val(selectedItem[key].idTipoDocumento);
        } else if (key == 'idPersona') {
            $(`.` + key).val(selectedItem[key]);
        } else {
            $(`#` + key).val(selectedItem[key]);
        }
    });
}

function save(selectedItem, action = 'N') {
    if (ValidarFormulario(`${$formAuxId}`) || action == 'E') {
        if (ValidarFormulario(`${$formPersonId}`) || action == 'E') {
            var personPayload = selectedItem == undefined ? $(`#${$formPersonId}`).serialize() : selectedItem.idPersona;
            ShowWaitingAnimation();
            savePerson(personPayload)
                .then((person) => {
                    var payload = selectedItem == undefined ? $(`#${$formAuxId}`).serialize() : selectedItem;
                    return saveAux(payload);
                }).then((result) => {
                    CloseModal(`${$modalId}`);
                    ShowSuccessDialog("Proceso finalizado correctamente", () => {
                        location.reload();
                    });
                }).catch((err) => {
                    console.error(err);
                    ShowErrorDialog('Error');
                }).finally(() => {
                    HideWaitingAnimation();
                });
        }
    }
}

function aaveDelete(selectedItem, action = 'N'){
    var payload = selectedItem == undefined ? $(`#${$formAuxId}`).serialize() : selectedItem;
    ShowWaitingAnimation();
    saveAux(payload).then((result) => {
        CloseModal(`${$modalId}`);
        ShowSuccessDialog("Proceso finalizado correctamente", () => {
            location.reload();
        });
    }).catch((err) => {
        console.error(err);
        ShowErrorDialog('Lo sentimos, ha ocurrido un error');
    }).finally(() => {
        HideWaitingAnimation();
    });
}

function savePerson(payload) {
    return new Promise((resolve, reject) => {
        try {
            $.ajax({
                method: 'POST',
                url: $BaseUrl + $personController,
                dataType: 'json',
                data: payload,
            }).done((result) => {
                $('.idPersona').val(result.idPersona);
                resolve(result);
            }).fail((err) => {
                reject(err);
            });

        } catch (e) {
            reject(e);
        }
    });
}

function saveAux(payload) {
    return new Promise((resolve, reject) => {
        try {
            $.ajax({
                method: 'POST',
                url: $BaseUrl + $controller,
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            });

        } catch (e) {
            reject(e);
        }
    });
}

function loadNew() {
    loadData(newRecord);
    $('#rowBuscar').show();
    $(`#${$modalId}`).modal({
        keyboard: false,
        focus: true
    });
}

function loadTipoDocumento() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'TipoDocumentoController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idTipoDocumento");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idTipoDocumento).text(item.descripcion));
            });
        }).fail((err) => {
            console.error(err);
            ShowErrorDialog('Lo sentimos ha ocurrido un error');
        }).always(() => {
            HideWaitingAnimation();
        });
    } catch (e) {
        console.error(e);
        ShowErrorDialog('Lo sentimos ha ocurrido un error');
    }
}

function loadEspecializacion() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'EspecializacionController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idEspecializacion");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idEspecializacion).text(item.descripcion));
            });
        }).fail((err) => {
            console.error(err);
            ShowErrorDialog('Lo sentimos ha ocurrido un error');
        }).always(() => {
            HideWaitingAnimation();
        });
    } catch (e) {
        console.error(e);
        ShowErrorDialog('Lo sentimos ha ocurrido un error');
    }
}

function loadPuesto() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'PuestoController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idPuesto");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idPuesto).text(item.descripcion));
            });
        }).fail((err) => {
            console.error(err);
            ShowErrorDialog('Lo sentimos ha ocurrido un error');
        }).always(() => {
            HideWaitingAnimation();
        });
    } catch (e) {
        console.error(e);
        ShowErrorDialog('Lo sentimos ha ocurrido un error');
    }
}