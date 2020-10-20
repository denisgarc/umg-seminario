var newRecord = {
    idTipoDocumento: 0,
    descripcion: '',
    abreviatura: '',
    activo: 'S'
};

$(document).ready(function (e) {

    $('#tblTipoDocumento tbody').on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $('#tblTipoDocumento').DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "edit":
                loadData(data);
                $('#mainTipoDocModal').modal({
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
        $(`#` + key).val(selectedItem[key]);
    });
}

function save(selectedItem, action = 'N') {
    var payload = selectedItem == undefined ? $('#frmTipoDocumentoMant').serialize() : selectedItem;
    if (ValidarFormulario('frmTipoDocumentoMant') || action == 'E') {
        Request(payload).then((result) => {
            CloseModal('mainTipoDocModal');
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
                url: $BaseUrl + 'TipoDocumentoController',
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
    $('#mainTipoDocModal').modal({
        keyboard: false,
        focus: true
    });
}