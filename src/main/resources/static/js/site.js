window.onload = function() {

    var pieСhart = new CanvasJS.Chart("pieСhartContainer", {
        theme: "light2", // "light1", "dark1", "dark2"
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: "Соотношение уровня сотрудников в компании"
        },
        data: [{
            type: "pie",
            showInLegend: "true",
            legendText: "{label}",
            yValueFormatString: "#,###\"%\"",
            indexLabelFontSize: 16,
            indexLabel: "{label} - {y}",
            dataPoints: positionsCounts[0]
        }]
    });

    var barChart = new CanvasJS.Chart("barChartContainer", {
        theme: "light1", // "light1", "light2", "dark1"
        animationEnabled: true,
        title: {
            text: "Стоимость проектов"
        },
        axisY: {
            title: "Стоимость (USD)",
            includeZero: true,
            prefix: "$",
            suffix: ""
        },
        data: [{
            type: "bar",
            yValueFormatString: "$#,##0",
            indexLabel: "{y}",
            dataPoints: projectsPrices[0]
        }]
    });

    pieСhart.render();
    barChart.render();

}