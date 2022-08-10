
// Chart bar
const cvsLine = document.getElementById('chartLine').getContext('2d');
const chartLine = new Chart(cvsLine, {
    type: 'line',
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
// 
const cvsBar = document.getElementById('chartBar').getContext('2d');
const charBar = new Chart(cvsBar, {
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

// 
const cvsPolar = document.getElementById('chartPolar');
const charPolar = new Chart(cvsPolar, {
    type: 'polarArea',
    data: {
      labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
      datasets: [
        {
          label: "Population (millions)",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
          data: [2478,5267,734,784,433]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Predicted world population (millions) in 2050'
      }
    }
});
// 
new Chart(document.getElementById("bar-chart-grouped"), {
    type: 'bar',
    data: {
      labels: ["1900", "1950", "1999", "2050"],
      datasets: [
        {
          label: "Africa",
          backgroundColor: "#3e95cd",
          data: [133,221,783,2478]
        }, {
          label: "Europe",
          backgroundColor: "#8e5ea2",
          data: [408,547,675,734]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Population growth (millions)'
      }
    }
});
// 
new Chart(document.getElementById("mixed-chart"), {
    type: 'bar',
    data: {
      labels: ["1900", "1950", "1999", "2050"],
      datasets: [{
          label: "Europe",
          type: "line",
          borderColor: "#8e5ea2",
          data: [408,547,675,734],
          fill: false
        }, {
          label: "Africa",
          type: "line",
          borderColor: "#3e95cd",
          data: [133,221,783,2478],
          fill: false
        }, {
          label: "Europe",
          type: "bar",
          backgroundColor: 'rgb(255, 205, 86)',
          data: [408,547,675,734],
        }, {
          label: "Africa",
          type: "bar",
          backgroundColor: 'rgb(201, 203, 207)',
          backgroundColorHover: "#3e95cd",
          data: [133,221,783,2478]
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Population growth (millions): Europe & Africa'
      },
      legend: { display: false }
    }
});

// 

new Chart(document.getElementById("line-chart"), {
    type: 'line',
    data: {
      labels: [1500,1600,1700,1750,1800,1850,1900,1950,1999,2050],
      datasets: [{ 
          data: [86,114,106,106,107,111,133,221,783,2478],
          label: "Africa",
          borderColor: "#3e95cd",
          fill: false
        }, { 
          data: [282,350,411,502,635,809,947,1402,3700,5267],
          label: "Asia",
          borderColor: "#8e5ea2",
          fill: false
        }, { 
          data: [168,170,178,190,203,276,408,547,675,734],
          label: "Europe",
          borderColor: "#3cba9f",
          fill: false
        }, { 
          data: [40,20,10,16,24,38,74,167,508,784],
          label: "Latin America",
          borderColor: "#e8c3b9",
          fill: false
        }, { 
          data: [6,3,2,2,7,26,82,172,312,433],
          label: "North America",
          borderColor: "#c45850",
          fill: false
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'World population per region (in millions)'
      }
    }
  });
  
  function addContact() {
    window.location="../html/contactForm.html";
}

function signout() {
    window.location="../html/login.html";
}