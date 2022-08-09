
// Chart bar
const cvsBar = document.getElementById('chartLine').getContext('2d');
const chartBar = new Chart(cvsBar, {
    type: 'bar',
    data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        datasets: [{
            label: 'Số lượng',
            data: [10, 42, 35, 78, 60, 91, 32, 57, 58, 32, 32, 77],
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 2
        }]
    },
    plugins: [ChartDataLabels],
    options: {
        scales: {
            y: {
                beginAtZero: true,
            }
        },
        plugins: {
            datalabels: {
                align: 'end',
                anchor: 'end'
            }
        }
    }    
});

// Chart Doughnut
const cvsDoughnut = document.getElementById('chartDoughnut');
const chartDoughtnut = new Chart(cvsDoughnut, {
    type: 'doughnut',
    data: {
        labels: ['Chợ trung an', 'Chợ Ô Môn', 'Thốt nốt', 'Chợ cái Răng', 'TTTM Cái Khế', 'Chợ'],
        datasets: [{
            label: 'Số lượng',
            data: [0.083, 0.093 , 0.222, 0.13, 0.204, 0.255],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            hoverOffset: 4
        }]
    },
    options: {
        plugins: {
            datalabels: {
                formatter: (value, context) => {
                    console.log(context.chart.data.datasets[0].data)
                    const datapoints = context.chart.data.datasets[0].data;
                    function totalSum(total, datapoint) {
                        return total + datapoint
                    }
                    const totalValue = datapoints.reduce(totalSum, 0);
                    const percentageValue = (value / totalValue * 100).toFixed(1);
                    return percentageValue + '%';
                }
            },
            tooltip: {
                enabled: false,
            }
        }
    },
    plugins: [ChartDataLabels],
});
