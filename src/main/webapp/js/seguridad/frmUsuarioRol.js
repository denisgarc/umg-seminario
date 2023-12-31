$(document).ready(function(e) {
    loadRoles();
});

function loadRoles(){
    try {
        ShowWaitingAnimation();
            $.ajax({
                url: $BaseUrl + 'RolController',
                type: 'GET',
                dataType: 'json'
            }).done((result) => {
                var options = $("#ddlRol");
                options.find('option')
                    .remove()
                    .end()
                    .append('<option value="-1">- Seleccione uno -</option>')
                    .val('-1');
                $.each(result, function(index, item) {
                    options.append($("<option />").val(item.idRol).text(item.descripcion));
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

function loadPerfiles(){
    try {
        if(ValidarFormulario('frmBusqueda')){
            var usuario = $('#frmBusqueda').serialize();
            ShowWaitingAnimation();
            $.ajax({
                url: $BaseUrl + 'UsuarioRolController',
                type: 'GET',
                dataType: 'json',
                data: usuario
            }).done((result) => {
                $('.idUsuario').val(result.idUsuario);
                var options = $("#ddlRolUsuario");
                options.empty();
                $.each(result.roles, function(index, item) {
                    options.append($("<option />").val(item.idRol).text(item.descripcion));
                });
            }).fail((err) => {
                console.error(err);
                ShowErrorDialog('Lo sentimos ha ocurrido un error');
            }).always(() => {
               HideWaitingAnimation(); 
            });
        }
    } catch (e) {
        console.error(e);
        ShowErrorDialog('Lo sentimos ha ocurrido un error');
    }
}

function asignarRol(){
    try {
        $('.action').val('agregar');
        if(ValidarFormulario('frmBusqueda')){
            if(ValidarFormulario('frmAddRol')){
                if(!validarExistencia()){
                    ShowConfirmationDialog('Esta seguro de asignar el rol seleccionado').then((isOk) => {
                        if(isOk){
                            var data = $('#frmAddRol').serialize();
                            ShowWaitingAnimation();
                            $.ajax({
                                url: $BaseUrl + 'UsuarioRolController',
                                type: 'POST',
                                dataType: 'json',
                                data: data,
                            }).done((result) => {
                                ShowSuccessDialog('Rol asignado correctamente', () => {
                                    loadPerfiles();
                                });
                            }).fail((err) => {
                                console.error(err);
                                ShowErrorDialog('Lo sentimos ha ocurrido un error');
                            }).always(() => {
                               HideWaitingAnimation(); 
                            });
                        }
                    }).catch((err) => {
                        console.error(err);
                        ShowErrorDialog('Lo sentimos ha ocurrido un error');
                    });
                } else {
                    ShowWarningDialog('El rol seleccionado, ya se encuentra asignado');
                }
            }
        }
    } catch (e) {
        console.error(e);
        ShowErrorDialog('Lo sentimos ha ocurrido un error');
    }
}

function eliminarRol(){
    try {
        $('.action').val('eliminar');
        if(ValidarFormulario('frmBusqueda')){
            if(ValidarFormulario('frmDeleteRol')){
                ShowConfirmationDialog('Esta seguro de desasignar el rol seleccionado').then((isOk) => {
                    if(isOk){
                        var data = $('#frmDeleteRol').serialize();
                        ShowWaitingAnimation();
                        $.ajax({
                            url: $BaseUrl + 'UsuarioRolController',
                            type: 'POST',
                            dataType: 'json',
                            data: data,
                        }).done((result) => {
                            ShowSuccessDialog('Rol desasignado correctamente', () => {
                                loadPerfiles();
                            });
                        }).fail((err) => {
                            console.error(err);
                            ShowErrorDialog('Lo sentimos ha ocurrido un error');
                        }).always(() => {
                           HideWaitingAnimation(); 
                        });
                    }
                }).catch((err) => {
                    console.error(err);
                    ShowErrorDialog('Lo sentimos ha ocurrido un error');
                });
            }
        }
    } catch (e) {
        console.error(e);
        ShowErrorDialog('Lo sentimos ha ocurrido un error');
    }
}

function validarExistencia(){
    var asignados = Array.from($('#ddlRolUsuario option'));
    var selected = Number($('#ddlRol').val());
    return (asignados.filter(x => x.value == selected).length != 0);
}