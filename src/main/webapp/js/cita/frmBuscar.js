var $tableClinicaId = 'tblCitas';
var $controllerCitaList = '/consultas/CitaConsultaController';
var $searchFormId = 'frmBuscar';
var $modalId = 'mainCitaModal';
var $formId = 'frmCitaMant';
var $tableId = 'tblCitas';
var $citaController = 'CitaController';

$(document).ready(function (e) {
    loadEstados();
    loadDataTable();

    $(`#${$tableId} tbody`).on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $(`#${$tableId}`).DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "update":
                loadData(data);
                $(`#${$modalId}`).modal({
                    keyboard: false,
                    focus: true
                });
                break;
            case "attend":
                break;
            case "print":
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


function search() {
    if (ValidarFormulario('frmBuscar')) {
        var param = '?' + $('#frmBuscar').serialize(); //`?idHospital=${$('#ddlHospital').val()}`;
        $(`#${$tableClinicaId}`).attr('data-url', $controllerCitaList + param);
        $(`#${$tableClinicaId}`).DataTable().ajax.url($controllerCitaList + param);
        $(`#${$tableClinicaId}`).DataTable().ajax.reload();
    }
}

function loadEstados() {
    try {
        ShowWaitingAnimation();
        $.ajax({
            url: $BaseUrl + 'EstadoController',
            type: 'GET',
            dataType: 'json'
        }).done((result) => {
            var options = $("#idEstado");
            options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
            $.each(result, function (index, item) {
                options.append($("<option />").val(item.idEstado).text(item.descripcion));
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

function dataButtons() {
    var buttons = `<div class="btn-group text-center">
            <button class="btn btn-outline-info btn-sm btnPrint" data-action="print"><i class="fas fa-print"></i></button>
            <button class="btn btn-outline-info btn-sm btnState" data-action="update"><i class="far fa-arrow-alt-circle-right"></i></button>
            <button class="btn btn-outline-success btn-sm btnAttend" data-action="attend"><i class="fas fa-user-check"></i></button>
        </div>`;
    return buttons;
}

function loadDataTable() {
    var allSelection = 'Todos';
    $('.isDataTableCita').each(function (tabIndex) {
        var $curTable = $(this);
        if ($curTable.attr('role') == undefined) {
            var withOutPagin = $curTable.hasClass("withOutPagin");
            var noAutoFit = $curTable.hasClass("withOutAutoFit");
            var responsiveTable = $curTable.hasClass("isResponsiveTable");
            var stateSave = $curTable.hasClass("isStateSave");
            var filterColumn = $curTable.hasClass("WithColumnFilter");
            var addButtons = $curTable.hasClass("isExportTable");
            var search = !$curTable.hasClass("withOutSearch");
            var isAjaxTable = $curTable.hasClass("isAjaxTable");

            var dataTableSetup = {
                "autoWidth": !noAutoFit,
                "responsive": responsiveTable,
                "bStateSave": stateSave,
                "bPaginate": !withOutPagin,
                "aaSorting": [],
                "scrollCollapse": true,
                "aLengthMenu": [[-1, 10, 25, 50, 100], [allSelection, 10, 25, 50, 100]],
                "iDisplayLength": 10,
                "fixedcolumns": true,
                "fixedHeader": true,
                "ordercellstop": true,
                "searching": search,
                "processing": true,
                "language": {
                    "sProcessing": "Procesando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "No se encontraron resultados",
                    "sEmptyTable": "Ningún dato disponible en esta tabla",
                    "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                    "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "sInfoThousands": ",",
                    "sLoadingRecords": "Cargando...",
                    "oPaginate": {
                        "sFirst": "Primero",
                        "sLast": "Último",
                        "sNext": "Siguiente",
                        "sPrevious": "Anterior"
                    },
                    "oAria": {
                        "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                    }
                },
                "ordering": !filterColumn,
                "dom": "Rlfrtip",
                "initComplete": function () {
                    if (filterColumn) {
                        this.api().columns().every(function () {
                            var column = this;
                            if ($(column.header()).hasClass('filter-header')) {
                                var select = $('<select class="filter-table-control"><option value="">-Todos-</option></select>')
                                        .appendTo($(column.header()))
                                        .on('change', function () {
                                            var val = $.fn.dataTable.util.escapeRegex(
                                                    $(this).val()
                                                    );

                                            column
                                                    .search(val ? '^' + val + '$' : '', true, false)
                                                    .draw();
                                        });

                                column.data().unique().sort().each(function (d, j) {
                                    select.append('<option value="' + d + '">' + d + '</option>')
                                });
                            }
                        });
                    }
                }
            };

            if (isAjaxTable) {
                var columnsTable = Array.from($curTable.find('thead th'))
                        .filter(x => x.hasAttribute("data-key"))
                        .map(x => {
                            return {data: x.attributes.getNamedItem("data-key").value};
                        });
                var columnsConfig = Array.from($curTable.find('thead th'))
                        .filter(x => x.hasAttribute("data-visible"))
                        .map(x => {
                            return {targets: [x.attributes.getNamedItem("data-column-orde").value], visible: x.attributes.getNamedItem("data-visible").value};
                        });

                var isMaintenance = $curTable.attr('data-show-buttons') == undefined ? false : $curTable.attr('data-show-buttons');

                if (isMaintenance) {
                    var buttons = dataButtons();
                    columnsTable.push({'defaultContent': buttons});
                    //dataTableSetup.select = true;
                }

                dataTableSetup.ajax = {
                    'method': $curTable.attr('data-method') == undefined ? '' : $curTable.attr('data-method'),
                    'url': $curTable.attr('data-url') == undefined ? 'GET' : $curTable.attr('data-url'),
                    'dataSrc': $curTable.attr('data-src') == undefined ? '' : $curTable.attr('data-src'),
                };
                dataTableSetup.columns = columnsTable;

                if (columnsConfig.length != 0) {
                    dataTableSetup.columnDefs = columnsConfig;
                }
            }

            if (addButtons) {
                dataTableSetup.dom = 'Blfrtip';
                dataTableSetup.buttons = [
                    {
                        extend: 'pdf',
                        text: 'PDF',
                        className: 'btn btn-info'
                    },
                    {
                        extend: 'excel',
                        text: 'Excel',
                        className: 'btn btn-info'
                    }
                ]
            }

            $curTable.DataTable(dataTableSetup);
            if (!responsiveTable) {
                $($curTable.dataTable.tables(true)).DataTable().columns.adjust().draw();
                // $($curTable.dataTable.tables(true)).DataTable().columns.adjust().fixedColumns().relayout();
                $($curTable.dataTable.tables(true)).DataTable().scroller.measure();
            }
        }

    });
}

function loadNew() {
    ShowWaitingAnimation();
    window.location.href = $BaseUrl + 'cita/Agendar.jsp';
}

function loadData(selectedItem) {
    Object.keys(selectedItem).forEach((key) => {
        if (key == 'activo') {
            $(`#${$formId} input[name="${key}"]`).attr('checked', false);
            $(`#${$formId} input[name="${key}"][value="${selectedItem[key]}"]`).attr('checked', true);
            $(`#${$formId} input[name="${key}"][value="${selectedItem[key]}"]`).click();
        } else if (key == 'horaCita') {
            $(`#${$formId} #${key}`).val(new Date(selectedItem[key]).toStringTime());
        } else if (key == 'fechaCita') {
            $(`#${$formId} #${key}`).val(new Date(selectedItem[key]).toStringDMY());
        } else if (key == 'idPaciente') {
            $(`#${$formId} #${key}`).val(selectedItem[key].idPaciente);
            Object.keys(selectedItem[key].idPersona).forEach((pKey) => {
                $(`#${$formId} #${pKey}`).val(selectedItem[key].idPersona[pKey]);
            });
        } else if (key == 'idEstado') {
            $(`#${$formId} #${key}`).val(selectedItem[key].idEstado);
        } else if (key == 'idHospital'){
            $(`#${$formId} #${key}`).val(selectedItem[key].idHospital);
        } else {
            $(`#${$formId} #${key}`).val(selectedItem[key]);
        }
    });
}

function saveUpdate(selectedItem, action = 'N') {
    if (ValidarFormulario(`${$formId}`) || action == 'E') {
        var payload = selectedItem == undefined ? $(`#${$formId}`).serialize() : selectedItem;
        saveCita(payload).then((result) => {
            ShowSuccessDialog(`Cita No. ${result.idCita} actualizada correctamente`, () => {
                CloseModal($modalId);
                search();
            });
        }).catch((err) => {
            console.error(err);
            ShowErrorDialog('Lo sentimos, ha ocurrido une error');
        }).finally(() => {
            HideWaitingAnimation();
        });
    }
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