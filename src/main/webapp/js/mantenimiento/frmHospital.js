var newRecord = {
   "idHospital":0,
   "nombre":"",
   "direccion":"",
   "telefonos":"",
   "activo":"S",
   "hospitalSalaList":[],
   "hospitalHorarioList":[],
   "hospitalClinicaList":[]
};

var newSalaRecord =  {
    "hospitalSalaPK":{
       "idHospital":0,
       "idSala":0
    },
    "descripcion":"",
    "activo":"S"
};

var newHorarioRecord = {
    "hospitalHorarioPK":{
       "idHospital":0,
       "idHorario":0
    },
    "descripcion":"",
    "horaInicio":new Date(),
    "horaFin":new Date(),
    "activo":"S"
};

var newClinicaRecord = {
    "hospitalClinicaPK":{
       "idHospital":0,
       "idClinica":0
    },
    "descripcion":"",
    "activo":"S"
};

var $modalId = 'mainHospitalModal';
var $formId = 'frmHospitalMant';
var $tableId = 'tblHospital';
var $controller = 'HospitalController';

var $modalSalaId = 'mainHospitalSalaModal';
var $formSalaId = 'frmHospitalSalaMant';
var $tableSalaId = 'tblHospitalSala';
var $controllerSala = 'HospitalSalaController';
var $controllerSalaList = '/consultas/HospitalSalaController';

var $modalHorarioId = 'mainHospitalHorarioModal';
var $formHorarioId = 'frmHospitalHorarioMant';
var $tableHorarioId = 'tblHospitalHorario';
var $controllerHorario = 'HospitalHorarioController';
var $controllerHorarioList = '/consultas/HospitalHorarioController';

var $modalClinicaId = 'mainHospitalClinicaModal';
var $formClinicaId = 'frmHospitalClinicaMant';
var $tableClinicaId = 'tblHospitalClinica';
var $controllerClinica = 'HospitalClinicaController';
var $controllerClinicaList = '/consultas/HospitalClinicaController';

$(document).ready(function (e) {
    // Cargamos el combo de hospitales
    loadHospitales();
    
    $('#pacienteNombre').on('change', function(e) {
       var valor = $(this).val();
       if(valor == "")
           $('#idPaciente').val(0);
    });
    
    $(`#${$tableId} tbody`).on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $(`#${$tableId}`).DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "edit":
                loadData(data);
                $(`#${$modalId}`).modal({
                    keyboard: false,
                    focus: true
                });
                break;
            case "delete":
                ShowConfirmationDialog('¿Está seguro de deshabilitar el registro?').then((isOk) => {
                    if (isOk) {
                        data.activo = 'N';
                        save(data, 'E');
                    }
                }).catch((err) => {
                    console.error(err);
                    ShowErrorDialog('Lo sentimos ha ocurrido un error');
                });
                break;
        }
    });
    
    $(`#${$tableSalaId} tbody`).on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $(`#${$tableSalaId}`).DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "edit":
                loadDataSala(data);
                $(`#${$modalSalaId}`).modal({
                    keyboard: false,
                    focus: true
                });
                break;
            case "delete":
                ShowConfirmationDialog('¿Está seguro de deshabilitar el registro?').then((isOk) => {
                    if (isOk) {
                        data.activo = 'N';
                        data.idHospital = data.hospitalSalaPK.idHospital;
                        data.idSala = data.hospitalSalaPK.idSala;
                        saveSala(data, 'E');
                    }
                }).catch((err) => {
                    console.error(err);
                    ShowErrorDialog('Lo sentimos ha ocurrido un error');
                });
                break;
        }
    });
    
    $(`#${$tableHorarioId} tbody`).on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $(`#${$tableHorarioId}`).DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "edit":
                loadDataHorario(data);
                $(`#${$modalHorarioId}`).modal({
                    keyboard: false,
                    focus: true
                });
                break;
            case "delete":
                ShowConfirmationDialog('¿Está seguro de deshabilitar el registro?').then((isOk) => {
                    if (isOk) {
                        data.activo = 'N';
                        data.idHospital = data.hospitalHorarioPK.idHospital;
                        data.idHorario = data.hospitalHorarioPK.idHorario;
                        data.horaInicio = new Date(data.horaInicio).toStringTime();
                        data.horaFin = new Date(data.horaFin).toStringTime();
                        saveHorario(data, 'E');
                    }
                }).catch((err) => {
                    console.error(err);
                    ShowErrorDialog('Lo sentimos ha ocurrido un error');
                });
                break;
        }
    });
    
    $(`#${$tableClinicaId} tbody`).on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $(`#${$tableClinicaId}`).DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "edit":
                loadDataClinica(data);
                $(`#${$modalClinicaId}`).modal({
                    keyboard: false,
                    focus: true
                });
                break;
            case "delete":
                ShowConfirmationDialog('¿Está seguro de deshabilitar el registro?').then((isOk) => {
                    if (isOk) {
                        data.activo = 'N';
                        data.idHospital = data.hospitalClinicaPK.idHospital;
                        data.idClinica = data.hospitalClinicaPK.idClinica;
                        saveClinica(data, 'E');
                    }
                }).catch((err) => {
                    console.error(err);
                    ShowErrorDialog('Lo sentimos ha ocurrido un error');
                });
                break;
        }
    });
});

function loadData(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if(key == 'activo'){
            //$('input[name="' + key + '"]').attr('checked', false);
            //$('input[name="' + key + '"][value="' + selectedItem[key] + '"]').attr('checked', true);
            //$('input[name="' + key + '"][value="' + selectedItem[key] + '"]').click();
            $(`#${$formId} input[name="${key}"]`).attr('checked', false);
            $(`#${$formId} input[name="${key}"][value="${selectedItem[key]}"]`).attr('checked', true);
            $(`#${$formId} input[name="${key}"][value="${selectedItem[key]}"]`).click();
        } else {
            //$(`#` + key).val(selectedItem[key]);
            $(`#${$formId} #${key}`).val(selectedItem[key]);
        }
    });
}

function loadDataSala(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if(key == 'activo'){
            $(`#${$formSalaId} input[name="${key}"]`).attr('checked', false);
            $(`#${$formSalaId} input[name="${key}"][value="${selectedItem[key]}"]`).attr('checked', true);
            $(`#${$formSalaId} input[name="${key}"][value="${selectedItem[key]}"]`).click();
        } else if(key == 'hospitalSalaPK'){
            $(`#${$formSalaId} #idHospital`).val(selectedItem[key].idHospital);
            $(`#${$formSalaId} #idSala`).val(selectedItem[key].idSala);
        } else {
            $(`#${$formSalaId} #${key}`).val(selectedItem[key]);
        }
    });
}

function loadDataHorario(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if(key == 'activo'){
            $(`#${$formHorarioId} input[name="${key}"]`).attr('checked', false);
            $(`#${$formHorarioId} input[name="${key}"][value="${selectedItem[key]}"]`).attr('checked', true);
            $(`#${$formHorarioId} input[name="${key}"][value="${selectedItem[key]}"]`).click();
        } else if(key == 'horaInicio' || key == 'horaFin') {
            $(`#${$formHorarioId} #${key}`).val(new Date(selectedItem[key]).toStringTime());
        } else if(key == 'hospitalHorarioPK'){
            $(`#${$formHorarioId} #idHospital`).val(selectedItem[key].idHospital);
            $(`#${$formHorarioId} #idHorario`).val(selectedItem[key].idHorario);
        }else {
            $(`#${$formHorarioId} #${key}`).val(selectedItem[key]);
        }
    });
}

function loadDataClinica(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if(key == 'activo'){
            $(`#${$formClinicaId} input[name="${key}"]`).attr('checked', false);
            $(`#${$formClinicaId} input[name="${key}"][value="${selectedItem[key]}"]`).attr('checked', true);
            $(`#${$formClinicaId} input[name="${key}"][value="${selectedItem[key]}"]`).click();
        } else if(key == 'hospitalClinicaPK'){
            $(`#${$formClinicaId} #idHospital`).val(selectedItem[key].idHospital);
            $(`#${$formClinicaId} #idClinica`).val(selectedItem[key].idClinica);
        } else {
            $(`#${$formClinicaId} #${key}`).val(selectedItem[key]);
        }
    });
}

function save(selectedItem, action = 'N') {
    var payload = selectedItem == undefined ? $(`#${$formId}`).serialize() : selectedItem;
    if (ValidarFormulario(`${$formId}`) || action == 'E') {
        Request(payload).then((result) => {
            CloseModal(`${$modalId}`);
            ShowSuccessDialog("Proceso finalizado correctamente", () => {
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

function saveSala(selectedItem, action = 'N') {
    var payload = selectedItem == undefined ? $(`#${$formSalaId}`).serialize() : selectedItem;
    if (ValidarFormulario(`${$formSalaId}`) || action == 'E') {
        RequestSala(payload).then((result) => {
            CloseModal(`${$modalSalaId}`);
            ShowSuccessDialog("Proceso finalizado correctamente", () => {
                //location.reload();
                loadDetail();
            });
        }).catch((err) => {
            console.error(err);
            ShowErrorDialog('Lo sentimos, ha ocurrido une error');
        }).finally(() => {
            HideWaitingAnimation();
        });
    }
}

function saveHorario(selectedItem, action = 'N') {
    var payload = selectedItem == undefined ? $(`#${$formHorarioId}`).serialize() : selectedItem;
    if (ValidarFormulario(`${$formHorarioId}`) || action == 'E') {
        RequestHorario(payload).then((result) => {
            CloseModal(`${$modalHorarioId}`);
            ShowSuccessDialog("Proceso finalizado correctamente", () => {
                //location.reload();
                loadDetail();
            });
        }).catch((err) => {
            console.error(err);
            ShowErrorDialog('Lo sentimos, ha ocurrido une error');
        }).finally(() => {
            HideWaitingAnimation();
        });
    }
}

function saveClinica(selectedItem, action = 'N') {
    var payload = selectedItem == undefined ? $(`#${$formClinicaId}`).serialize() : selectedItem;
    if (ValidarFormulario(`${$formClinicaId}`) || action == 'E') {
        RequestClinica(payload).then((result) => {
            CloseModal(`${$modalClinicaId}`);
            ShowSuccessDialog("Proceso finalizado correctamente", () => {
                //location.reload();
                loadDetail();
            });
        }).catch((err) => {
            console.error(err);
            ShowErrorDialog('Lo sentimos, ha ocurrido une error');
        }).finally(() => {
            HideWaitingAnimation();
        });
    }
}

function Request(payload) {
    return new Promise((resolve, reject) => {
        try {
            ShowWaitingAnimation();

            $.ajax({
                method: 'POST',
                url: $BaseUrl + $controller,
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                HideWaitingAnimation();
            });

        } catch (e) {
            reject(e);
        }
    });
}

function RequestSala(payload) {
    return new Promise((resolve, reject) => {
        try {
            ShowWaitingAnimation();

            $.ajax({
                method: 'POST',
                url: $BaseUrl + $controllerSala,
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                HideWaitingAnimation();
            });

        } catch (e) {
            reject(e);
        }
    });
}

function RequestHorario(payload) {
    return new Promise((resolve, reject) => {
        try {
            ShowWaitingAnimation();

            $.ajax({
                method: 'POST',
                url: $BaseUrl + $controllerHorario,
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                HideWaitingAnimation();
            });

        } catch (e) {
            reject(e);
        }
    });
}

function RequestClinica(payload) {
    return new Promise((resolve, reject) => {
        try {
            ShowWaitingAnimation();

            $.ajax({
                method: 'POST',
                url: $BaseUrl + $controllerClinica,
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                HideWaitingAnimation();
            });

        } catch (e) {
            reject(e);
        }
    });
}

function loadNew() {
    loadData(newRecord);
    $(`#${$modalId}`).modal({
        keyboard: false,
        focus: true
    });
}

function loadNewSala() {
    if(ValidarFormulario('frmSelectHospital')){
        newSalaRecord.hospitalSalaPK.idHospital = $('#ddlHospital').val();
        loadDataSala(newSalaRecord);
        $(`#${$modalSalaId}`).modal({
            keyboard: false,
            focus: true
        });
    }
}

function loadNewHorario() {
    if(ValidarFormulario('frmSelectHospital')){
        newHorarioRecord.hospitalHorarioPK.idHospital = $('#ddlHospital').val();
        loadDataHorario(newHorarioRecord);
        $(`#${$modalHorarioId}`).modal({
            keyboard: false,
            focus: true
        });
    }
}

function loadNewClinica() {
    if(ValidarFormulario('frmSelectHospital')){
        newClinicaRecord.hospitalClinicaPK.idHospital = $('#ddlHospital').val();
        loadDataClinica(newClinicaRecord);
        $(`#${$modalClinicaId}`).modal({
            keyboard: false,
            focus: true
        });
    }
}

function loadDetail(){
    if(ValidarFormulario('frmSelectHospital')){
        var param = `?idHospital=${$('#ddlHospital').val()}`;
        $(`#${$tableSalaId}`).attr('data-url', $controllerSalaList + param);
        $(`#${$tableSalaId}`).DataTable().ajax.url($controllerSalaList + param);
        $(`#${$tableSalaId}`).DataTable().ajax.reload();
        
        $(`#${$tableHorarioId}`).attr('data-url', $controllerHorarioList + param);
        $(`#${$tableHorarioId}`).DataTable().ajax.url($controllerHorarioList + param);
        $(`#${$tableHorarioId}`).DataTable().ajax.reload();
        
        $(`#${$tableClinicaId}`).attr('data-url', $controllerClinicaList + param);
        $(`#${$tableClinicaId}`).DataTable().ajax.url($controllerClinicaList + param);
        $(`#${$tableClinicaId}`).DataTable().ajax.reload();
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
            var options = $("#ddlHospital");
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