var $iframe = $('#visorPDF');

$(document).ready(function(e) {
   $('#divVisor').hide(); 
});

function loadProfile(){
    if(ValidarFormulario('frmPaciente')){
        var idPaciente = $('#idPaciente').val();
        var link = document.createElement('a');
        var url = '/consultas/FichaMedicaController?codigo='+ idPaciente;
        
        if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
            link.target = 'visorPDF';
            url = url + '&type=mobile'
            $('#divVisor').hide();
        }
        else{
            link.target = 'visorPDF'
            //link.target = '_blank';
            $('#divVisor').show();
        }
        
        document.body.appendChild(link);    
        link.href = url;
        link.click();
    } else {
        $('#divVisor').hide();
    }
}

    
function showIframe() {
    if (window.matchMedia("(min-width: 480px)").matches) {
        var src = $iframe.data('src');
        $iframe.attr('src', src);
    }
}

$(window).on('resize', showIframe);

showIframe();