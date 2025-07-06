// vehicle-details.js - Handles loading and displaying details, stats, and data for a single vehicle

// Global variables
let vehicleId;
let engineChart;
let fuelChart;

// On page load, get vehicle ID and load all data
// ---------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
    // Get vehicle ID from URL
    const urlParams = new URLSearchParams(window.location.search);
    vehicleId = urlParams.get('id');

    if (vehicleId) {
        loadVehicleDetails();
        loadEngineAverages();
        loadFuelAverages();
        loadEngineData();
        loadFuelData();
    } else {
        alert('No vehicle specified');
        window.location.href = 'vehicles.html';
    }
});

// Load and display vehicle info
// ----------------------------
async function loadVehicleDetails() {
    try {
        const response = await fetch(`/api/vehicles/${vehicleId}`);
        const vehicle = await response.json();

        document.getElementById('vehicleTitle').textContent =
            `${vehicle.manufacturer} ${vehicle.model} (${vehicle.year_of_Mfg})`;

        document.getElementById('basicInfo').innerHTML = `
            <p><strong>Type:</strong> ${vehicle.type}</p>
            <p><strong>Engine:</strong> ${vehicle.engine_Type}</p>
            <p><strong>Fuel:</strong> ${vehicle.fuel_Type}</p>
        `;
    } catch (error) {
        console.error('Error loading vehicle:', error);
        showError('Failed to load vehicle details');
    }
}

// Load and display engine averages
async function loadEngineAverages() {
    try {
        // Fetch average RPM
        const rpmRes = await fetch(`/api/vehicles/${vehicleId}/engine-data/Avg_RPM`);
        const avgRpm = await rpmRes.json();
        document.getElementById('avgRpm').textContent = avgRpm !== null ? avgRpm : 'N/A';

        // Fetch average Temp
        const tempRes = await fetch(`/api/vehicles/${vehicleId}/engine-data/Avg_Temp`);
        const avgTemp = await tempRes.json();
        document.getElementById('avgTemp').textContent = avgTemp !== null ? avgTemp + '°C' : 'N/A';
    } catch (error) {
        document.getElementById('avgRpm').textContent = 'N/A';
        document.getElementById('avgTemp').textContent = 'N/A';
        console.error('Error loading engine averages:', error);
    }
}

// Load and display fuel averages
async function loadFuelAverages() {
    try {
        // Total Distance
        const distRes = await fetch(`/api/vehicles/${vehicleId}/fuel-data/totaldistance`);
        const totalDistance = await distRes.json();
        document.getElementById('totalDistance').textContent = totalDistance !== null ? totalDistance : 'N/A';

        // Total Fuel Used
        const fuelRes = await fetch(`/api/vehicles/${vehicleId}/fuel-data/totalfuel`);
        const totalFuel = await fuelRes.json();
        document.getElementById('totalFuel').textContent = totalFuel !== null ? totalFuel : 'N/A';

        // Fuel Efficiency
        const effRes = await fetch(`/api/vehicles/${vehicleId}/fuel-data/fuelefficiency`);
        const fuelEfficiency = await effRes.json();
        document.getElementById('fuelEfficiency').textContent = fuelEfficiency !== null ? fuelEfficiency : 'N/A';
    } catch (error) {
        document.getElementById('totalDistance').textContent = 'N/A';
        document.getElementById('totalFuel').textContent = 'N/A';
        document.getElementById('fuelEfficiency').textContent = 'N/A';
        console.error('Error loading fuel averages:', error);
    }
}

// Engine Data Functions
// ---------------------
async function loadEngineData() {
    try {
        const response = await fetch(`/api/vehicles/${vehicleId}/engine-data`);
        const data = await response.json();

        updateEngineChart(data);
        updateEngineTable(data);
    } catch (error) {
        console.error('Error loading engine data:', error);
        showError('Failed to load engine data');
    }
}

function updateEngineTable(data) {
    const tbody = document.getElementById('engineDataBody');
    tbody.innerHTML = data.map(item => `
        <tr>
            <td>${new Date(item.timestamp).toLocaleString()}</td>
            <td>${item.rpm}</td>
            <td>${item.temperature}°C</td>
            <td>
                <button onclick="editEngineData('${item.engineId}')" class="btn-primary">
                    <i class="fas fa-edit"></i>
                </button>
                <button onclick="deleteEngineData('${item.engineId}')" class="btn-secondary">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

// Update engine chart
function updateEngineChart(data) {
    const ctx = document.getElementById('engineChart').getContext('2d');

    if (engineChart) {
        engineChart.destroy();
    }

    const timestamps = data.map(item => new Date(item.timestamp).toLocaleString());
    const rpms = data.map(item => item.rpm);
    const temperatures = data.map(item => item.temperature);

    engineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: timestamps,
            datasets: [
                {
                    label: 'RPM',
                    data: rpms,
                    borderColor: '#4CAF50',
                    yAxisID: 'rpm'
                },
                {
                    label: 'Temperature (°C)',
                    data: temperatures,
                    borderColor: '#F44336',
                    yAxisID: 'temp'
                }
            ]
        },
        options: {
            responsive: true,
            scales: {
                rpm: {
                    type: 'linear',
                    position: 'left',
                    title: {
                        display: true,
                        text: 'RPM'
                    }
                },
                temp: {
                    type: 'linear',
                    position: 'right',
                    title: {
                        display: true,
                        text: 'Temperature (°C)'
                    }
                }
            }
        }
    });
}

// Fuel Data Functions
// -------------------
async function loadFuelData() {
    try {
        const response = await fetch(`/api/vehicles/${vehicleId}/fuel-data`);
        const data = await response.json();

        updateFuelChart(data);
        updateFuelTable(data);
    } catch (error) {
        console.error('Error loading fuel data:', error);
        showError('Failed to load fuel data');
    }
}

function updateFuelTable(data) {
    const tbody = document.getElementById('fuelDataBody');
    tbody.innerHTML = data.map(item => `
        <tr>
            <td>${new Date(item.timestamp).toLocaleString()}</td>
            <td>${item.liters_used}</td>
            <td>${item.distance_km}</td>
            <td>
                <button onclick="editFuelData('${item.fuelId}')" class="btn-primary">
                    <i class="fas fa-edit"></i>
                </button>
                <button onclick="deleteFuelData('${item.fuelId}')" class="btn-secondary">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

// Update fuel chart
function updateFuelChart(data) {
    const ctx = document.getElementById('fuelChart').getContext('2d');

    if (fuelChart) {
        fuelChart.destroy();
    }

    const timestamps = data.map(item => new Date(item.timestamp).toLocaleString());
    const consumption = data.map(item => (item.liters_used / item.distance_km) * 100); // L/100km

    fuelChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: timestamps,
            datasets: [{
                label: 'Fuel Consumption (L/100km)',
                data: consumption,
                borderColor: '#2196F3',
                fill: false
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'L/100km'
                    }
                }
            }
        }
    });
}

// Modal Functions
// ---------------
function showAddEngineDataForm() {
    document.getElementById('engineModalTitle').textContent = 'Add Engine Data';
    document.getElementById('engineDataId').value = '';
    document.getElementById('engineDataForm').reset();
    document.getElementById('engineDataModal').style.display = 'block';
}

function showAddFuelDataForm() {
    document.getElementById('fuelModalTitle').textContent = 'Add Fuel Data';
    document.getElementById('fuelDataId').value = '';
    document.getElementById('fuelDataForm').reset();
    document.getElementById('fuelDataModal').style.display = 'block';
}

// Edit and delete functions for engine and fuel data
// --------------------------------------------------
async function editEngineData(id) {
    try {
        const response = await fetch(`/api/vehicles/${vehicleId}/engine-data/${id}`);
        const data = await response.json();

        document.getElementById('engineModalTitle').textContent = 'Edit Engine Data';
        document.getElementById('engineDataId').value = data.engineId;;
        document.getElementById('engineTimestamp').value = data.timestamp.slice(0, 16);
        document.getElementById('engineRpm').value = data.rpm;
        document.getElementById('engineTemp').value = data.temperature;

        document.getElementById('engineDataModal').style.display = 'block';
    } catch (error) {
        console.error('Error loading engine data for edit:', error);
        showError('Failed to load engine data for editing');
    }
}

async function editFuelData(id) {
    try {
        const response = await fetch(`/api/vehicles/${vehicleId}/fuel-data/${id}`);
        const data = await response.json();

        document.getElementById('fuelModalTitle').textContent = 'Edit Fuel Data';
        document.getElementById('fuelDataId').value = data.fuelId;
        document.getElementById('fuelTimestamp').value = data.timestamp.slice(0, 16);
        document.getElementById('fuelLiters').value = data.liters_used;
        document.getElementById('fuelDistance').value = data.distance_km;

        document.getElementById('fuelDataModal').style.display = 'block';
    } catch (error) {
        console.error('Error loading fuel data for edit:', error);
        showError('Failed to load fuel data for editing');
    }
}

function padDateTime(val) {
    // If already has seconds, return as is
    if (val.length === 19) return val;
    // If missing seconds, add ":00"
    if (val.length === 16) return val + ":00";
    return val;
}

// Handle add/edit engine data form submit
async function handleEngineDataSubmit(event) {
    event.preventDefault();

    const id = document.getElementById('engineDataId').value;
    const data = {
        // vehicleId: vehicleId,
        timestamp: padDateTime(document.getElementById('engineTimestamp').value),
        rpm: parseInt(document.getElementById('engineRpm').value),
        temperature: parseFloat(document.getElementById('engineTemp').value)
    };

    try {
        // const url = id ? `/api/engine-data/${id}` : '/api/engine-data';
        const url = id
            ? `/api/vehicles/${vehicleId}/engine-data/${id}`
            : `/api/vehicles/${vehicleId}/engine-data`;
        const method = id ? 'PUT' : 'POST';

        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            closeEngineModal();
            loadEngineData();
        } else {
            throw new Error('Failed to save engine data');
        }
    } catch (error) {
        console.error('Error saving engine data:', error);
        showError('Failed to save engine data');
    }
}

// Handle add/edit fuel data form submit
async function handleFuelDataSubmit(event) {
    event.preventDefault();

    const id = document.getElementById('fuelDataId').value;
    const data = {
        // vehicleId: vehicleId,
        timestamp: padDateTime(document.getElementById('fuelTimestamp').value),
        liters_used: parseFloat(document.getElementById('fuelLiters').value),
        distance_km: parseFloat(document.getElementById('fuelDistance').value)
    };

    try {
        // const url = id ? `/api/fuel-data/${id}` : '/api/fuel-data';
        const url = id
            ? `/api/vehicles/${vehicleId}/fuel-data/${id}`
            : `/api/vehicles/${vehicleId}/fuel-data`;
        const method = id ? 'PUT' : 'POST';

        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            closeFuelModal();
            loadFuelData();
        } else {
            throw new Error('Failed to save fuel data');
        }
    } catch (error) {
        console.error('Error saving fuel data:', error);
        showError('Failed to save fuel data');
    }
}

// Delete engine or fuel data
// --------------------------
async function deleteEngineData(id) {
    if (confirm('Are you sure you want to delete this engine data record?')) {
        try {
            const response = await fetch(`/api/vehicles/${vehicleId}/engine-data/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                loadEngineData();
            } else {
                throw new Error('Failed to delete engine data');
            }
        } catch (error) {
            console.error('Error deleting engine data:', error);
            showError('Failed to delete engine data');
        }
    }
}

async function deleteFuelData(id) {
    if (confirm('Are you sure you want to delete this fuel data record?')) {
        try {
            const response = await fetch(`/api/vehicles/${vehicleId}/fuel-data/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                loadFuelData();
            } else {
                throw new Error('Failed to delete fuel data');
            }
        } catch (error) {
            console.error('Error deleting fuel data:', error);
            showError('Failed to delete fuel data');
        }
    }
}

// Utility Functions
// -----------------
function closeEngineModal() {
    document.getElementById('engineDataModal').style.display = 'none';
}

function closeFuelModal() {
    document.getElementById('fuelDataModal').style.display = 'none';
}

function openTab(tabName) {
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active-tab');
    });

    document.querySelectorAll('.tab-btn').forEach(btn => {
        btn.classList.remove('active');
    });

    document.getElementById(`${tabName}-tab`).classList.add('active-tab');
    event.currentTarget.classList.add('active');
}

function showError(message) {
    // You can implement a proper error notification system here
    alert(message);
}