// dashboard.js - Handles loading and displaying dashboard stats and charts for Vehicle Analyzer

// Dashboard statistics
let vehicleTypesChart;
let engineTypesChart;
let fuelTypesChart;

// Fetch and display dashboard data
// --------------------------------
async function loadDashboardData() {
    try {
        const response = await fetch('/api/vehicles');
        const vehicles = await response.json();

        // Update statistics
        document.getElementById('totalVehicles').textContent = vehicles.length;

        // Count unique types
        const uniqueVehicleTypes = new Set(vehicles.map(v => v.type));
        const uniqueEngineTypes = new Set(vehicles.map(v => v.engine_Type));
        
        document.getElementById('vehicleTypes').textContent = uniqueVehicleTypes.size;
        document.getElementById('engineTypes').textContent = uniqueEngineTypes.size;

        // Prepare chart data
        const vehicleTypeData = countOccurrences(vehicles.map(v => v.type));
        const engineTypeData = countOccurrences(vehicles.map(v => v.engine_Type));
        const fuelTypeData = countOccurrences(vehicles.map(v => v.fuel_Type));

        // Update charts
        updateVehicleTypesChart(vehicleTypeData);
        updateEngineTypesChart(engineTypeData);
        updateFuelTypesChart(fuelTypeData);

    } catch (error) {
        console.error('Error loading dashboard data:', error);
    }
}

// Helper function to count occurrences
function countOccurrences(arr) {
    const counts = {};
    arr.forEach(item => {
        counts[item] = (counts[item] || 0) + 1;
    });
    return counts;
}

// Chart creation functions
// -----------------------
function updateVehicleTypesChart(data) {
    const ctx = document.getElementById('vehicleTypesChart').getContext('2d');
    
    if (vehicleTypesChart) {
        vehicleTypesChart.destroy();
    }

    vehicleTypesChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: Object.keys(data),
            datasets: [{
                data: Object.values(data),
                backgroundColor: [
                    '#FF6384',
                    '#36A2EB',
                    '#FFCE56',
                    '#4BC0C0',
                    '#9966FF',
                    '#FF9F40',
                    '#FF6384'
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'right'
                },
                title: {
                    display: true,
                    text: 'Vehicle Types Distribution'
                }
            }
        }
    });
}

function updateEngineTypesChart(data) {
    const ctx = document.getElementById('engineTypesChart').getContext('2d');
    
    if (engineTypesChart) {
        engineTypesChart.destroy();
    }

    engineTypesChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: Object.keys(data),
            datasets: [{
                data: Object.values(data),
                backgroundColor: [
                    '#36A2EB',
                    '#FF6384',
                    '#FFCE56',
                    '#4BC0C0'
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'right'
                },
                title: {
                    display: true,
                    text: 'Engine Types Distribution'
                }
            }
        }
    });
}

function updateFuelTypesChart(data) {
    const ctx = document.getElementById('fuelTypesChart').getContext('2d');
    
    if (fuelTypesChart) {
        fuelTypesChart.destroy();
    }

    fuelTypesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: Object.keys(data),
            datasets: [{
                label: 'Number of Vehicles',
                data: Object.values(data),
                backgroundColor: '#36A2EB'
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false
                },
                title: {
                    display: true,
                    text: 'Fuel Types Distribution'
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize: 1
                    }
                }
            }
        }
    });
}

// Load dashboard data when page loads
// -----------------------------------
document.addEventListener('DOMContentLoaded', loadDashboardData); 