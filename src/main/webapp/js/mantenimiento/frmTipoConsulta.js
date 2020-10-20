var newRecord = {
    idTipoConsulta: 0,
    descripcion: '',
    activo: 'S'
};

var $modalId = 'mainTipoConsultaModal';
var $formId = 'frmTipoConsultaMant';
var $tableId = 'tblTipoConsulta';
var $controller = 'TipoConsultaController';

$(document).ready(function (e) {

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
});

function loadData(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if(key == 'activo'){
            $('input[name="' + key + '"][value="' + selectedItem[key] + '"]').attr('checked',true);
        } else {
            $(`#` + key).val(selectedItem[key]);
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

function loadNew() {
    loadData(newRecord);
    $(`#${$modalId}`).modal({
        keyboard: false,
        focus: true
    });
}