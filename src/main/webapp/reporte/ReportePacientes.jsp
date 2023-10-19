<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reporte de Pacientes</title>
    <!-- Agrega tus enlaces a hojas de estilo y scripts aquí si es necesario -->
</head>
<body>
    <div id="resultsContainer">        
        <div id="results">
            <h1>Estadísticas de Pacientes</h1>
        </div>
    </div>              
    <div id="chartContainer" style="height: 500px; width: 100%; padding-right: 50%;"></div>
    
    <script>
        
        function tot()
        {
        var x = document.getElementById("total1").innerText = "Hola";
                
            }
        
        window.onload = function () {
            console.log($BaseUrl + '/ReportePacientes');
            loadPacientCharts();            
        }

        function displayResults(result) {
            document.getElementById("total1").innerText = 'Total: ${result.total}';            
        }
        
        /* ------------------------- Grafica 1 -------------------------*/
        function loadPacientCharts() {
            try {
                ShowWaitingAnimation();
                $.ajax({
                    url: $BaseUrl + '/ReportePacientes?idgrafico=1',
                    type: 'GET',
                    dataType: 'json'
                }).done((result) => {                   

                    var chart = new CanvasJS.Chart("chartContainer", {
                        theme: "light2", //                        
                        animationEnabled: true,
                        title: {
                            text: "Estadísticas de Pacientes con un total de ",
                            text: "Total de pacientes: " + result.total, 
                        },
                        data: [
                            {
                                type: "column",
                                dataPoints: [
                                    //{ label: "Total", y: result.total },
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
        /* ------------------------- Grafica 1 -------------------------*/
        
    </script>
</body>
</html>