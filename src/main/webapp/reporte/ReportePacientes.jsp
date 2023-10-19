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
    <div id="chartContainer2" style="height: 300px; width: 50%;"></div>
    <script>
        window.onload = function () {
            console.log($BaseUrl + '/ReportePacientes');
            loadPacientCharts();
            loadPacientCharts2();
        }
        
        function loadPacientCharts() {
            try {
                ShowWaitingAnimation();
                $.ajax({
                    url: $BaseUrl + '/ReportePacientes?idgrafico=1',
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
        
        function loadPacientCharts2() {
            try {
                ShowWaitingAnimation();
                $.ajax({
                    url: $BaseUrl + '/ReportePacientes?idgrafico=2',
                    type: 'GET',
                    dataType: 'json'
                }).done((result) => {
                    
                    var chart = new CanvasJS.Chart("chartContainer2", {
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
