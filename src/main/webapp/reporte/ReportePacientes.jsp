<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reporte de Pacientes</title>
    <!-- Agrega tus enlaces a hojas de estilo y scripts aquí si es necesario -->
</head>
<body>
    <div id="chartContainer" style="height: 300px; width: 50%;"></div>
    <script>
        window.onload = function () {
            console.log($BaseUrl + '/ReportePacientes');
            loadPacientCharts();
        }
        
        function loadPacientCharts() {
            try {
                ShowWaitingAnimation();
                $.ajax({
                    url: $BaseUrl + '/ReportePacientes',
                    type: 'GET',
                    dataType: 'json'
                }).done((result) => {
                    
                    var chart = new CanvasJS.Chart("chartContainer", {
                        animationEnabled: true,
                        title: {
                            text: "Estadísticas de Pacientes"
                        },
                        data: [
                            {
                                type: "column",
                                dataPoints: [
                                    { label: "Total", y: result.total },
                                    { label: "Hombres", y: result.hombres },
                                    { label: "Mujeres", y: result.mujeres }
                                ]
                            }
                        ]
                    });

                    chart.render();
                    
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
    </script>
</body>
</html>
