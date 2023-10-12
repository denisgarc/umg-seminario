var $idCita = 0;
var $idConsulta = 0;

var $formId = 'frmConsulta';
var $formDiagnosticoId = 'frmConsultaDiagnostico';
var $formTratamientoId = 'frmConsultaTratamiento';
var $formRecetaId = 'frmReceta';
var $formRecetaDetalleId = 'frmRecetaDetalle';

var $tableDiagnostico = 'tblDiagnosticos';
var $tableTratamiento = 'tblTratamientos';
var $tableRecetaDetalle = 'tblRecetaDetalle';

var newConsulta = {
    idCita: 0,
    idConsulta: 0,
    fechaConsulta: new Date().toStringDMY(),
    nombres: '',
    apellidos: '',
    idTipoConsulta: -1,
    idEmpleado: '',
    empleadoNombre: '',
    descripcion: '',
    observaciones: '',
    antecedentes: '',
    anamnesis: '',
    examenesComplementarios: '',
    evaluacionClinica: '',
    ordenesMedicas: '',
    activo: 'S'
};

var newDiagnostico = {
    idConsulta: 0,
    idDiagnostico: 0,
    idTipoDiagnostico: {
        idTipoDiagnostico: -1,
        descripcion: '',
        activo: 'S'
    },
    descripcion: '',
    activo: 'S'
};

var newTratamiento = {
    idConsulta: 0,
    idTratamiento: 0,
    idTipoTratamiento: {
        idTipoTratamiento: -1,
        descripcion: '',
        activo: 'S'
    },
    descripcion: '',
    activo: 'S'
};

var newReceta = {
    idReceta: 0,
    idConsulta: 0,
    fechaVencimiento: new Date().toStringDMY(),
    recetaDetalleList: []
};

var newRecetaDetalle = {
    idReceta: 0,
    idMedicamento: -1,
    cantidad: 0,
    indicaciones: '',
    duracion: ''
};

$(document).ready(function(e) {
    loadDataConsulta(newConsulta);
    loadDataConsultaDetalle($formRecetaId, newReceta);
    loadCita();
    
    // Se cargan maestros
    loadTipoConsulta();
    loadTipoDiagnostico();
    loadTipoTratamiento();
    loadMedicamento();
    loadMedicamento();
    
});

function loadDataConsulta(selectedItem){
    Object.keys(selectedItem).forEach((key) => {
        $(`#${$formId} #${key}`).val(selectedItem[key]);
    });
}

function loadDataCita(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if(key == 'activo'){
            $(`#${$formId} input[name="${key}"]`).attr('checked', false);
            $(`#${$formId} input[name="${key}"][value="${selectedItem[key]}"]`).attr('checked', true);
            $(`#${$formId} input[name="${key}"][value="${selectedItem[key]}"]`).click();
        } else if (key == 'idPaciente') {
            Object.keys(selectedItem.idPaciente.idPersona).forEach((pkey) => {
                $(`#${$formId} #${pkey}`).val(selectedItem.idPaciente.idPersona[pkey]);
            });
        } else {
            $(`#${$formId} #${key}`).val(selectedItem[key]);
        }
    });
}

function loadDataConsultaDetalle(formAux, selectedItem){
    Object.keys(selectedItem).forEach((key) => {
        if(key == 'activo'){
            $(`#${formAux} input[name="${key}"]`).attr('checked', false);
            $(`#${formAux} input[name="${key}"][value="${selectedItem[key]}"]`).attr('checked', true);
            $(`#${formAux} input[name="${key}"][value="${selectedItem[key]}"]`).click();
        } else if (key == 'idTipoDiagnostico' || key == 'idTipoTratamiento') {
            $(`#${formAux} #${key}`).val(selectedItem[key][key]);
        } else {
            $(`#${formAux} #${key}`).val(selectedItem[key]);
        }
    });
}

function loadCita() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'CitaController?idCita=' + $idCita,
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            loadDataCita(result);
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

function loadTipoConsulta() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'TipoConsultaController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idTipoConsulta");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idTipoConsulta).text(item.descripcion));
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

function loadTipoDiagnostico() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'TipoDiagnosticoController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idTipoDiagnostico");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idTipoDiagnostico).text(item.descripcion));
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

function loadTipoTratamiento() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'TipoTratamientoController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idTipoTratamiento");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idTipoTratamiento).text(item.descripcion));
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

function loadMedicamento() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'MedicamentoController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idMedicamento");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idMedicamento).text(item.nombreComercial));
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

function loadNew(tipo){
    switch(tipo){
        case 'diagnostico':
            loadDataConsultaDetalle($formDiagnosticoId,newDiagnostico);
            $(`#${$formDiagnosticoId}Modal`).modal({
                keyboard: false,
                focus: true
            });
            break;
        case 'tratamiento':
            loadDataConsultaDetalle($formTratamientoId,newTratamiento);
            $(`#${$formTratamientoId}Modal`).modal({
                keyboard: false,
                focus: true
            });
            break;
        case 'medicamento':
            loadDataConsultaDetalle($formRecetaDetalleId,newRecetaDetalle);
            $(`#${$formRecetaDetalleId}Modal`).modal({
                keyboard: false,
                focus: true
            });
            break;
    }
}

function save(tipo){
    switch(tipo){
        case 'diagnostico':
            saveDiagnostico();
            break;
        case 'tratamiento':
            saveTratamiento();
            break;
        case 'medicamento':
            saveMedicamento();
            break;
        case 'consulta':
            saveConsulta();
            break;
    }
}

function saveConsulta(){
    if(ValidarFormulario(`${$formId}`)){
        if(ValidarFormulario(`${$formRecetaId}`)){
            ShowWaitingAnimation();
            postHeader().then((result) => {
                $idConsulta = result.idConsulta;
                return sendDiagnosticos($idConsulta);
            }).then((result) => {
                return sendTratamientos($idConsulta);
            }).then((result) => {
                $('#idConsulta').val($idConsulta);
                $('.idConsulta').val($idConsulta);
                return postReceta();
            }).then((result) => {
                return sendRecetaDetalles(result.idReceta);
            }).then((result) => {
                HideWaitingAnimation();
                ShowSuccessDialog("Proceso finalizado correctamente", () => {
                    location.reload();
                });
            }).catch((err) => {
                console.error(err);
                HideWaitingAnimation();
                ShowErrorDialog('Lo sentios ha ocurrido un error');
            })/*.always(() =>{
                HideWaitingAnimation();
            })*/;
        }
    }
}

function saveDiagnostico(){
    if(ValidarFormulario($formDiagnosticoId)){
        var newItem = {};
        $(`#${$formDiagnosticoId}`).serializeArray().forEach((item) => {
           if(item.name == 'idTipoDiagnostico') {
               newItem[item.name] = {
                   idTipoDiagnostico: item.value,
                   descripcion: $(`#${item.name} option:selected`).text()
               }
           } else {
               newItem[item.name] = item.value;
           }
        });
        
        var table = $(`#${$tableDiagnostico}`).DataTable();
        table.row.add(newItem).draw(false);
        CloseModal(`${$formDiagnosticoId}Modal`);
    }
}

function saveTratamiento(){
    if(ValidarFormulario($formTratamientoId)){
        var newItem = {};
        $(`#${$formTratamientoId}`).serializeArray().forEach((item) => {
           if(item.name == 'idTipoTratamiento') {
               newItem[item.name] = {
                   idTipoTratamiento: item.value,
                   descripcion: $(`#${item.name} option:selected`).text()
               }
           } else {
               newItem[item.name] = item.value;
           }
        });
        
        var table = $(`#${$tableTratamiento}`).DataTable();
        table.row.add(newItem).draw(false);
        CloseModal(`${$formTratamientoId}Modal`);
    }
}

function saveMedicamento(){
    if(ValidarFormulario($formRecetaDetalleId)){
        var newItem = {};
        $(`#${$formRecetaDetalleId}`).serializeArray().forEach((item) => {
           if(item.name == 'idMedicamento') {
               newItem[item.name] = {
                   idMedicamento: item.value,
                   nombreComercial: $(`#${item.name} option:selected`).text()
               }
           } else {
               newItem[item.name] = item.value;
           }
        });
        
        var table = $(`#${$tableRecetaDetalle}`).DataTable();
        table.row.add(newItem).draw(false);
        CloseModal(`${$formRecetaDetalleId}Modal`);
    }
}

function postHeader(selectedItem){
    return new Promise((resolve, reject) => {
        try {
            //ShowWaitingAnimation();
            var payload = selectedItem == undefined ? $(`#${$formId}`).serialize() : selectedItem;
            $.ajax({
                method: 'POST',
                url: $BaseUrl + 'ConsultaController',
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                //HideWaitingAnimation();
            });
        } catch (e) {
            reject(e);
        }
     });
}

function postDiagnostico(selectedItem){
    return new Promise((resolve, reject) => {
        try {
            //ShowWaitingAnimation();
            var payload = selectedItem == undefined ? $(`#${$formDiagnosticoId}`).serialize() : selectedItem;
            $.ajax({
                method: 'POST',
                url: $BaseUrl + 'ConsultaDiagnosticoController',
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                //HideWaitingAnimation();
            });
        } catch (e) {
            reject(e);
        }
     });
}

function postTratamiento(selectedItem){
    return new Promise((resolve, reject) => {
        try {
            //ShowWaitingAnimation();
            var payload = selectedItem == undefined ? $(`#${$formTratamientoId}`).serialize() : selectedItem;
            $.ajax({
                method: 'POST',
                url: $BaseUrl + 'ConsultaTratamientoController',
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                //HideWaitingAnimation();
            });
        } catch (e) {
            reject(e);
        }
     });
}

function postReceta(selectedItem){
    return new Promise((resolve, reject) => {
        try {
            //ShowWaitingAnimation();
            var payload = selectedItem == undefined ? $(`#${$formRecetaId}`).serialize() : selectedItem;
            var list = Array.from($(`#${$tableRecetaDetalle}`).DataTable().data());
            debugger;
            if(list.length == 0){
                resolve({ idReceta: 0 });
            }
            
            $.ajax({
                method: 'POST',
                url: $BaseUrl + 'RecetaController',
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                //HideWaitingAnimation();
            });
        } catch (e) {
            reject(e);
        }
     });
}

function postRecetaDetalle(selectedItem){
    return new Promise((resolve, reject) => {
        try {
            //ShowWaitingAnimation();
            var payload = selectedItem == undefined ? $(`#${$formRecetaDetalleId}`).serialize() : selectedItem;
            $.ajax({
                method: 'POST',
                url: $BaseUrl + 'RecetaDetalleController',
                dataType: 'json',
                data: payload,
            }).done((result) => {
                resolve(result);
            }).fail((err) => {
                reject(err);
            }).always(() => {
                //HideWaitingAnimation();
            });
        } catch (e) {
            reject(e);
        }
     });
}

function sendDiagnosticos(idConsulta){
    var list = Array.from($(`#${$tableDiagnostico}`).DataTable().data());
    var operation = [];
    list.forEach((item) => {
        item.idConsulta = idConsulta;
        operation.push(postDiagnostico(item));
    });

    return Promise.all(operation);
}

function sendTratamientos(idConsulta){
    var list = Array.from($(`#${$tableTratamiento}`).DataTable().data());
    var operation = [];
    list.forEach((item) => {
        item.idConsulta = idConsulta;
        operation.push(postTratamiento(item));
    });

    return Promise.all(operation);
}

function sendRecetaDetalles(idReceta){
    var list = Array.from($(`#${$tableRecetaDetalle}`).DataTable().data());
    var operation = [];
    if(idReceta != 0) {
        list.forEach((item) => {
            item.idReceta = idReceta;
            operation.push(postRecetaDetalle(item));
        });
    }
    return Promise.all(operation);
}