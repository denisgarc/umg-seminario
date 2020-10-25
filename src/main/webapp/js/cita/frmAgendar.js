var newPaciente = {
    "idPaciente": 0,
    "numeroSeguro": '',
    "tipoSangre": '',
    "fuma": 'S',
    "fecAlta": new Date(),
    "activo": 'S',
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
            "activo": "S",
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
            "activo": "S",
            "abreviatura": ""
        }
    }
};

var $formId = 'frmCita';
var $formAuxId = 'frmPaciente';
var $formPersonId = 'frmPersona';
var $citaController = 'CitaController';
var $pacienteController = 'PacienteController';
var $personController = 'PersonaController';

$(document).ready(function (e) {
    
    loadTipoDocumento();
    loadHospitales();
    loadPaciente(newPaciente);
    
    $("#smartwizard").on("leaveStep", function (e, anchorObject, currentStepIndex, nextStepIndex, stepDirection) {
        return ValidarFormulario(anchorObject[0].hash.replace('#', ''));
    });

    $("#reset-btn").on("click", function () {
        $('#smartwizard').smartWizard("reset");
        return true;
    });

    $("#prev-btn").on("click", function () {
        $('#smartwizard').smartWizard("prev");
        return true;
    });

    $("#next-btn").on("click", function () {
        $('#smartwizard').smartWizard("next");
        return true;
    });
    
    $("#finish-btn").on("click", function () {
        save();
        return true;
    });
    
    $('#idHospital').on('change', function (){
       var idHospital = $(this).val();
       loadClinicas(idHospital);
       loadSalas(idHospital);
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
        $(this).autocomplete({
            source: function (request, response) {
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
            }
        });
    });
    
    $('.isAutoCompleteCustomePaciente').on("focus", function () {
        var _Url = $BaseUrl + 'PacienteConsultaController';
        $(this).autocomplete({
            source: function (request, response) {
                $.ajax({
                    type: 'GET',
                    url: `${_Url}?filter=${request.term}&type=full`,
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    success: function (data) {
                        response($.map(data, function (item) {
                            return {label: item["idPersona"].nombres + ' ' + item["idPersona"].apellidos, value: item["idPaciente"], model: item}
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
                $('#rowBuscar').hide();
                loadPaciente(selected.item.model);
            }
        });
    });

});

function loadNewPaciente(){
    loadPaciente(newPaciente);
    $('#rowBuscar').show();
    $('#pacienteNombre').val('');
}

function validarFormularios() {
    var bEsValido = true;
    var conceptos = $('#tblConceptos').DataTable().data().rows().count();

    // Validamos formulario
    bEsValido = ValidarFormulario('step-1');

    // Validamos que existan conceptos
    if (bEsValido && conceptos == 0) {
        ShowWarningDialog("No ha agregado ningun concepto al comportamiento");
        $('#smartwizard').smartWizard("goToStep", 1);
        return false;
    }

    if (bEsValido)
        ShowWaitingAnimation();

    // Validamos formulario encabezado
    return bEsValido;
}

function selectStep(stepNo) {
    $('#smartwizard').smartWizard("goToStep", (stepNo - 1));
}

function finalizarWizard() {
    alert('finalizar');
}

function loadPaciente(selectedItem) {
    // Para el paciente
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

function loadHospitales() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'HospitalController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idHospital");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idHospital).text(item.nombre));
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

function loadClinicas(idHospital) {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + `HospitalClinicaController?idHospital=${idHospital}`,
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idClinica");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.hospitalClinicaPK.idClinica).text(item.descripcion));
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

function loadSalas(idHospital) {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + `HospitalSalaController?idHospital=${idHospital}`,
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idSala");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.hospitalSalaPK.idSala).text(item.descripcion));
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

function savePaciente(payload) {
    return new Promise((resolve, reject) => {
        try {
            $.ajax({
                method: 'POST',
                url: $BaseUrl + $pacienteController,
                dataType: 'json',
                data: payload,
            }).done((result) => {
                $('.idPaciente').val(result.idPaciente);
                resolve(result);
            }).fail((err) => {
                reject(err);
            });

        } catch (e) {
            reject(e);
        }
    });
}

function saveCita(payload) {
    return new Promise((resolve, reject) => {
        try {
            $.ajax({
                method: 'POST',
                url: $BaseUrl + $citaController,
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

function save(selectedItem, action = 'N') {
    if (ValidarFormulario(`${$formId}`) || action == 'E'){
        if (ValidarFormulario(`${$formAuxId}`) || action == 'E') {
            if (ValidarFormulario(`${$formPersonId}`) || action == 'E') {
                var personPayload = selectedItem == undefined ? $(`#${$formPersonId}`).serialize() : selectedItem.idPersona;
                ShowWaitingAnimation();
                savePerson(personPayload)
                    .then((person) => {
                        var pacientePayload = selectedItem == undefined ? $(`#${$formAuxId}`).serialize() : selectedItem.idPaciente;
                        return savePaciente(pacientePayload);
                    }).then((result) => {
                        var payload = selectedItem == undefined ? $(`#${$formId}`).serialize() : selectedItem;
                        return saveCita(payload);
                    }).then((result) => {
                        ShowSuccessDialog(`Cita No. ${result.idCita} programada correctamente`, () => {
                            $('#smartwizard').smartWizard("reset");
                            location.reload();
                        });
                    }).catch((err) => {
                        console.error(err);
                        ShowErrorDialog('Lo sentimos, ha ocurrido une error');
                    }).finally(() => {
                        HideWaitingAnimation();
                    });
            }
        }
    }
}