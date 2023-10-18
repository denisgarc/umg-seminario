<%@ page import="app.consultas.entities.PacienteStatistics" %>
<%@ page import="app.consultas.dal.PacienteFacade" %>
<%
    PacienteFacade pacienteFacade = new PacienteFacade();
    PacienteStatistics pacienteStatistics = pacienteFacade.getPacienteStatistics();
    int total = pacienteStatistics.getTotal();
    int hombres = pacienteStatistics.getHombres();
    int mujeres = pacienteStatistics.getMujeres();
%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Reporte de Pacientes</title>
    <!-- Agrega tus enlaces a hojas de estilo y scripts aquí si es necesario -->
</head>
<body>
    <div id="chartContainer" style="height: 300px; width: 50%;"></div>
    <script>
        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                title: {
                    text: "Estadísticas de Pacientes"
                },
                data: [
                    {
                        type: "column",
                        dataPoints: [
                            { label: "Total", y: <%= total %> },
                            { label: "Hombres", y: <%= hombres %> },
                            { label: "Mujeres", y: <%= mujeres %> }
                        ]
                    }
                ]
            });

            chart.render();
        }
    </script>
</body>
</html>
