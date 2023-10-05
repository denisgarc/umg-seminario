var $controllerConsultaList = '/consultas/ConsultaBusquedaController';
var $searchFormId = 'frmBuscar';
var $modalId = 'mainCitaModal';
var $formId = 'frmCitaMant';
var $tableId = 'tblConsultas';
var $citaController = 'CitaController';

$(document).ready(function (e) {
    loadDataTable();

    $(`#${$tableId} tbody`).on('click', 'button', function () {
        var action = this.attributes.getNamedItem("data-action").value;
        var data = $(`#${$tableId}`).DataTable().row($(this).parents('tr')).data();

        switch (action) {
            case "update":
                ShowWaitingAnimation();
                window.location.href = $BaseUrl + 'cita/Consulta.jsp?idConsulta=' + data.idConsulta;
                break;
            case "print":
                debugger;
                var link = document.createElement('a');
                document.body.appendChild(link);
                link.href = '/consultas/ConsultaImpresionController?codigo='+ data.idConsulta;
                link.target = '_blank';
                link.click();
                break;
        }
    });
});

function search() {
    if (ValidarFormulario('frmBuscar')) {
        var param = '?' + $('#frmBuscar').serialize(); //`?idHospital=${$('#ddlHospital').val()}`;
        $(`#${$tableId}`).attr('data-url', $controllerConsultaList + param);
        $(`#${$tableId}`).DataTable().ajax.url($controllerConsultaList + param);
        $(`#${$tableId}`).DataTable().ajax.reload();
    }
}

function dataButtons() {
    var buttons = `<div class="btn-group text-center">
            <button class="btn btn-outline-info btn-sm btnPrint" data-action="print"><i class="fas fa-print"></i></button>
        </div>`;
    return buttons;
}

function loadDataTable() {
    var allSelection = 'Todos';
    $('.isDataTableConsulta').each(function (tabIndex) {
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