$(document).ready(function () {
    InitializeJSComponents();
});

function InitializeJSComponents() {

    // Iniciamos tablas
    InitializeTables();

    // Iniciamos calendarios
    InitializeCalendars();

    // Iniciamos Switch Checkbox
    InitializeOnOffCheckBox();

    // Iniciamos botones responsive
    InitializeResponsiveButton();

    // Iniciamos las máscaras
    InitializeInputMask();

    // Iniciamos los select-picker
    InitializeSelectPicker();

    // Iniciamos los wizard
    InitializeWizard();

    // Iniciamos los collapse
    InitializeCollapse();

    // Iniciamos los tree grid
    InitializeTreeGrid();

    // Iniciamos los autocomplete
    InitializeAutoComplete();
}

/// Esta función inicializa todas las tablas que se encuentran en el DOM
function InitializeTables() {
    // Asignamos el texto que aparecerá en la opción todos
    var allSelection = 'Todos';

    // Iniciamos las tablas que se encuentren en el DOM
    if ($('.isDataTable').length > 0) {
        $('.isDataTable').each(function (tabIndex) {
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
                
                if(isAjaxTable){
                    var columnsTable = Array.from($curTable.find('thead th'))
                            .filter(x => x.hasAttribute("data-key"))
                            .map(x => { return { data: x.attributes.getNamedItem("data-key").value};});
                    var columnsConfig = Array.from($curTable.find('thead th'))
                            .filter(x => x.hasAttribute("data-visible"))
                            .map(x => { return { targets: [x.attributes.getNamedItem("data-column-orde").value], visible: x.attributes.getNamedItem("data-visible").value };});
                    
                    var isMaintenance = $curTable.attr('data-show-buttons') == undefined ? false : $curTable.attr('data-show-buttons');

                    if(isMaintenance){
                        var event = $curTable.attr('data-click-event');
                        if(event == undefined){
                            var buttons = `<div class="btn-group text-center"><button class="btn btn-outline-info btn-sm btnEditar" data-action="edit"><i class="far fa-edit"></i></button><button class="btn btn-outline-danger btn-sm btnDelete" data-action="delete"><i class="far fa-trash-alt"></i></button></div>`    
                        } else {
                            var buttons = `<div class="btn-group text-center"><button class="btn btn-outline-info btn-sm btnEditar" data-action="edit" onclick="${event}"><i class="far fa-edit"></i></button><button class="btn btn-outline-danger btn-sm btnDelete" data-action="delete" onclick="${event}"><i class="far fa-trash-alt"></i></button></div>`    
                        }
                        columnsTable.push({'defaultContent': buttons});
                        //dataTableSetup.select = true;
                    }
                    
                    dataTableSetup.ajax = {
                        'method': $curTable.attr('data-method') == undefined ? '' : $curTable.attr('data-method'),
                        'url': $curTable.attr('data-url') == undefined ? 'GET' : $curTable.attr('data-url'),
                        'dataSrc': $curTable.attr('data-src') == undefined ? '' : $curTable.attr('data-src'),
                    };
                    dataTableSetup.columns = columnsTable; 
                    
                    if(columnsConfig.length != 0){
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

        })
    }
}

/// Esta función inicializa todos los picker o calendarios que se encuentran en el DOM
function InitializeCalendars() {
    // Definimos idioma estandar para Picker
    $.datepicker.regional['es'] = {
        closeText: 'Cerrar',
        prevText: '< Ant',
        nextText: 'Sig >',
        currentText: 'Hoy',
        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
        dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb'],
        dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'],
        weekHeader: 'Sm',
        dateFormat: 'dd/mm/yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''
    };

    // Asignamos idioma standar para picker
    $.datepicker.setDefaults($.datepicker.regional["es"]);

    // Iniciamos todos los picker
    if ($(".isDatePicker").length > 0) {
        $(".isDatePicker").each(function (pickerIndex) {
            var $picker = $(this);
            var maxToday = $picker.hasClass("maxToday")
            $picker.datepicker({
                changeMonth: true,
                changeYear: true,
                showButtonPanel: true,
                maxDate: (maxToday ? '0' : '')
            });

            $picker.mask('00/00/0000', { placeholder: '__/__/____' });
        });
    }
}

/// Esta función inicializa todos los botone que muestran el loader que se encuentran en el DOM
function InitializeResponsiveButton() {
    if ($(".isResponsiveButton").length > 0) {
        $(".isResponsiveButton").each(function () {
            var $respBtn = $(this);
            if (!$respBtn.hasClass("isValidateButton"))
                $respBtn.click(ShowWaitingAnimation);
        });
    }
}

function InitializeOnOffCheckBox() {
    if ($('.onoffswitch input').length > 0) {
        $('.onoffswitch input').each(function (index) {
            var $curCheckBox = $(this);
            if (!$curCheckBox.hasClass("onoffswitch-checkbox"))
                $curCheckBox.addClass("onoffswitch-checkbox");
        });
    }
};

/// Esta función inicializa todas las máscaras aplicadas a los input que se encuentran en el DOM
function InitializeInputMask() {

    var $defaultSize = 10;
    if ($(".isNumeric").length > 0) {
        $(".isNumeric").each(function () {
            var sizeMask = { mask: '', placeholder: '' };

            var $inputNumeric = $(this);
            var $size = $inputNumeric[0].hasAttribute("data-size") ? parseInt($inputNumeric[0].attributes.getNamedItem("data-size").value) : $defaultSize;
            var $scale = $inputNumeric[0].hasAttribute("data-scale") ? parseInt($inputNumeric[0].attributes.getNamedItem("data-scale").value) : 0;

            // Armamos el tamaño de enteros
            for (var i = 1; i <= $size; i++) {
                sizeMask.mask = sizeMask.mask + (i == 1 ? '0' : '9');
                sizeMask.placeholder = sizeMask.placeholder + '_';
            }

            // Agregamos la escala
            if ($scale !== 0) {
                sizeMask.mask = sizeMask.mask + '.';
                sizeMask.placeholder = sizeMask.placeholder + '.';

                for (var i = 1; i <= $scale; i++) {
                    sizeMask.mask = sizeMask.mask + '0';
                    sizeMask.placeholder = sizeMask.placeholder + '_';
                }
            }

            $inputNumeric.mask(sizeMask.mask, { placeholder: sizeMask.placeholder });
        });
    }

    if ($(".isMaskedInput").length > 0) {
        $(".isMaskedInput").each(function () {
            var inputMask = { mask: '', placeholder: '' };
            var $inputMasked = $(this);

            inputMask.mask = $inputMasked[0].hasAttribute("data-mask") ? $inputMasked[0].attributes.getNamedItem("data-mask").value : null;
            inputMask.placeholder = $inputMasked[0].hasAttribute("data-place-holder") ? $inputMasked[0].attributes.getNamedItem("data-place-holder").value : null;

            if (inputMask.mask !== null && inputMask.mask !== undefined) {
                // Si no existe un place holder, lo generalizamos basandonos en la mascara
                if (inputMask.placeholder === null || inputMask.placeholder === undefined) {
                    inputMask.placeholder = '';
                    for (var i = 1; i <= inputMask.mask.length; i++) {
                        inputMask.placeholder = inputMask.placeholder + '_';
                    }
                }

                // Iniciamos la mascara
                $inputMasked.mask(inputMask.mask, { placeholder: inputMask.placeholder });
            }
        });
    }
}

/// Esta función inicializa todos los select-picker que se encuentran en el DOM
function InitializeSelectPicker() {
    if ($(".isSelectPicker").length > 0) {
        $(".isSelectPicker").each(function (index) {
            var $selectPicker = $(this);
            var selectPickerSetup = {
                liveSearch: true,
                liveSearchStyle: 'contains',
                showTick: true,
                liveSearchPlaceholder: 'Ingrese el texto que desea buscar',
                noneSelectedText: "- Seleccione Uno-",
                noneResultsText: "Sin resultados encontrados {0}",
                selectedTextFormat: "count"
            };

            if (!(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent))) {
                $selectPicker.selectpicker(selectPickerSetup);
            }
            //else {
            //    $selectPicker.selectpicker(selectPickerSetup);
            //}
        });
    }
}

/// Esta funcion sirve para convertir en un wizard una clase
function InitializeWizard() {
    if ($(".isStepWizard").length > 0) {
        $(".isStepWizard").each(function (index) {
            var $stepWizard = $(this);
            var smartWizardSetup = {
                theme: 'arrows',
                backButtonSupport: false, // Enable the back button support
                enableURLhash: false, // Enable selection of the step based on url hash
                transition: {
                    animation: 'none', // Effect on navigation, none/fade/slide-horizontal/slide-vertical/slide-swing
                    speed: '400', // Transion animation speed
                    easing: '' // Transition animation easing. Not supported without a jQuery easing plugin
                },
                toolbarSettings: {
                    toolbarPosition: 'bottom', // none, top, bottom, both
                    toolbarButtonPosition: 'center', // left, right, center
                    showNextButton: true, // show/hide a Next button
                    showPreviousButton: true, // show/hide a Previous button
                    toolbarExtraButtons: [] // Extra buttons to show on toolbar, array of jQuery input/buttons elements
                },
                anchorSettings: {
                    anchorClickable: true, // Enable/Disable anchor navigation
                    enableAllAnchors: false, // Activates all anchors clickable all times
                    markDoneStep: true, // Add done state on navigation
                    markAllPreviousStepsAsDone: false, // When a step selected by url hash, all previous steps are marked done
                    removeDoneStepOnNavigateBack: false, // While navigate back done step after active step will be cleared
                    enableAnchorOnDoneStep: true // Enable/Disable the done steps navigation
                },
                keyboardSettings: {
                    keyNavigation: true, // Enable/Disable keyboard navigation(left and right keys are used if enabled)
                    keyLeft: [37], // Left key code
                    keyRight: [39] // Right key code
                },
                lang: { // Language variables for button
                    next: 'Siguiente',
                    previous: 'Anterior'
                },
                disabledSteps: [], // Array Steps disabled
                errorSteps: [], // Highlight step with errors
                hiddenSteps: [] // Hidden steps
            };

            $stepWizard.smartWizard(smartWizardSetup);
        });
    }
}

/// Esta funcion sirve para convertir en un collapsable
function InitializeCollapse() {
    if ($(".isCollapse").length > 0) {
        $(".isCollapse").each(function (index) {
            var $collapse = $(this);
            var collapseSetup = {
                icons: { "header": "ui-icon-plus", "activeHeader": "ui-icon-minus" },
                collapsible: true
            };

            $collapse.accordion(collapseSetup);
        });
    }
}

/// Esta función inicializa todos los select-picker que se encuentran en el DOM
function InitializeTreeGrid() {
    if ($(".isTreeGrid").length > 0) {
        $(".isTreeGrid").each(function (index) {
            var $treeGrid = $(this);
            var treeGridSetup = {
                //expanderExpandedClass: 'far fa-minus-square',
                //expanderCollapsedClass: 'far fa-plus-square',
                initialState: 'collapsed',

            };

            $treeGrid.treegrid(treeGridSetup);
        });
    }
}

/// Esta función inicializa todos los autocomplete que se encuentren en el DOM
function InitializeAutoComplete() {
    $('.isAutoComplete').on("focus", function () {
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
                $(Array.from($(this).parent().find('input')).filter(x => x.id != $(this)[0].id)).val(selected.item.value)
            }
        });
    });
}

/// Esta función muestra el loader en la página
function ShowWaitingAnimation() {
    var message = 'Por favor, espere un momento.';
    $.blockUI({ message: `<div class="waitingMessageContainer><div class="imgContainer"></div><div class="txtContainer"><img src="${$BaseUrl}images/working.gif" /> ${message}</div></div>` });
}

/// Esta funcion oculta el loader en la pagina
function HideWaitingAnimation() {
    $.unblockUI();
}

/// Esta función muestra un confirmation box o mensaje de confirmación
var continueEvent = false;
function ConfirmationDialog(senderObj, message) {
    var retValue = true;

    if (!continueEvent) {
        if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
            var r = confirm(message);
            if (r == true) {
                retValue = true;
                continueEvent = true;
                ShowWaitingAnimation();
                senderObj.click();
            } else {
                retValue = false;
                continueEvent = false;
                $.unblockUI();
            }
        } else {
            event.preventDefault();
            swal({
                title: "Confirmación",
                text: message,
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: 'btn btn-success',
                confirmButtonText: "Si",
                cancelButtonClass: 'btn btn-outline-danger',
                cancelButtonText: "No",
                closeOnConfirm: true,
                closeOnCancel: true,
                showLoaderOnConfirm: true
            },
                function (isConfirm) {
                    if (isConfirm) {
                        continueEvent = true;
                        ShowWaitingAnimation();
                        senderObj.click();
                    } else {
                        retValue = false;
                        $.unblockUI();
                        return false;
                    }
                });
            retValue = false;
        }
    } else {
        continueEvent = false;
        retValue = true;
    }
    return retValue;
}

/**
 * Para mostrar un mensaje de confirmación utilizando una promesa
  * @param {any} message El mensaje de confirmación
 */
function ShowConfirmationDialog(message) {
    return new Promise((resolve, reject) => {
        var retValue = true;

        try {
            if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
                var retValue = confirm(message);
                resolve(retValue);
            } else {
                swal({
                    title: "Confirmación",
                    text: message,
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: 'btn btn-success',
                    confirmButtonText: "Si",
                    cancelButtonClass: 'btn btn-outline-danger',
                    cancelButtonText: "No",
                    closeOnConfirm: true,
                    closeOnCancel: true,
                    showLoaderOnConfirm: true
                },
                function (isConfirm) {
                    retValue = isConfirm;
                    resolve(retValue);
                });
            }
        } catch (e) {
            reject(e);
        }
    });
}

/**
 * Muestra un cuadro de dialogo, utilizando la libreria sweet alert
 * @param {any} objMessage El modelo del objeto para poder dibujar el cuadro de dialogo
 * @param {any} handler La función para manejar la respuesta del cuadro de dialogo
 */
function ShowDialog(objMessage, handler = null) {
    setTimeout(function () {
        swal({
            title: objMessage.TituloMensaje,
            text: objMessage.TextoMensaje,
            type: objMessage.TipoMensaje
        }, handler);
    }, 500);
}

/**
 * Muestra un cuadro de dialogo de advertencia
 * @param {any} message El mensaje que será mostrado en pantalla
 * @param {any} handler La función para manejar la respuesta del cuadro de dialogo
 */
function ShowWarningDialog(message, handler = null) {
    var objMessage = {
        TituloMensaje: '¡Advertencia!',
        TipoMensaje: 'warning',
        TextoMensaje: message
    };
    ShowDialog(objMessage, handler);
}

/**
 * Muestra un cuadro de dialogo de error
 * @param {any} message El mensaje que será mostrado en pantalla
 * @param {any} handler La función para manejar la respuesta del cuadro de dialogo
 */
function ShowErrorDialog(message, handler = null) {
    var objMessage = {
        TituloMensaje: '¡Error!',
        TipoMensaje: 'error',
        TextoMensaje: message
    };
    ShowDialog(objMessage, handler);
}

/**
 * Muestra un cuadro de dialogo de exito o finalizacion
 * @param {any} message El mensaje que será mostrado en pantalla
 * @param {any} handler La función para manejar la respuesta del cuadro de dialogo
 */
function ShowSuccessDialog(message, handler = null) {
    var objMessage = {
        TituloMensaje: '¡Correcto!',
        TipoMensaje: 'success',
        TextoMensaje: message
    };
    ShowDialog(objMessage, handler);
}

/**
 * Valida si una fecha en formato string DD/MM/YYYY es una fecha válida
 * @param {any} dateString La fecha en formato DD/MM/YYYY
 */
function isValidDate(dateString) {
    // revisar el patrón
    if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
        return false;

    // convertir los numeros a enteros
    var parts = dateString.split("/");
    var day = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[2], 10);

    // Revisar los rangos de año y mes
    if ((year < 1000) || (year > 3000) || (month == 0) || (month > 12))
        return false;

    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // Ajustar para los años bisiestos
    if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
        monthLength[1] = 29;

    // Revisar el rango del dia
    return day > 0 && day <= monthLength[month - 1];
};

function ShowModal(modalId) {
    CloseModal(modalId);
    $(`#${modalId}`).modal({
        keyboard: false
    });
}

function CloseModal(modalId) {
    $(`#${modalId}`).modal('hide');
    //$(`#${modalId}`).modal('dispose');
    $('body').removeClass('modal-open')
    $('.modal-backdrop').remove()
}

function ValidarFormulario(formId, showLoad = false) {
    $(`#${formId}`).initialize({
        debug: false,
        scrollToFirstElementOffset: 100,
    });

    $(`#${formId}`).registerValidator('data-max-length', function (control) {
        var validator_name = 'data-max-length';
        if (control.attr(validator_name) != null) {
            var maxLength = control.attr(validator_name);
            var controlString = control.val();
            var lengthOk = true;
            try {
                lengthOk = controlString.length <= maxLength;
            } catch (e) { lengthOk = false; alert(e); }
            if (!control.isEmpty() && !lengthOk) {
                return false;
            } else {
                return true;
            }
        }
    });

    var ok = Boolean($(`#${formId}`).validate());
    if (ok && showLoad) ShowWaitingAnimation();
    return ok;
}