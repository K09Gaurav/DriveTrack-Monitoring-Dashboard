document.addEventListener("DOMContentLoaded", loadVehicles);

let editingVehicleId = null;

async function loadVehicles() {
  try {
    const response = await fetch("/api/vehicles");
    const vehicles = await response.json();
    displayVehicles(vehicles);
  } catch (error) {
    console.error("Error loading vehicles:", error);
  }
}

function displayVehicles(vehicles) {
  const tbody = document.getElementById("vehiclesTableBody");
  tbody.innerHTML = "";

  vehicles.forEach((vehicle) => {
    const row = document.createElement("tr");
    row.innerHTML = `
            <td>${vehicle.manufacturer}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.year_of_Mfg}</td>
            <td>${vehicle.type}</td>
            <td>${vehicle.engine_Type}</td>
            <td>${vehicle.fuel_Type}</td>
            <td>
                <button class="btn-primary details-btn" data-vehicle-id="${vehicle.id}">
                    <i class="fas fa-chart-line"></i> Details
                </button>
                <button class="btn-warning edit-btn" data-vehicle-id="${vehicle.id}">
                    <i class="fa-solid fa-pen-to-square"></i>
                </button>
                <button class="btn-secondary delete-btn" data-vehicle-id="${vehicle.id}">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        `;
    tbody.appendChild(row);
  });

  //   Add even listner to buttons (since they are dynamically created)
  document.querySelectorAll(".details-btn").forEach((button) => {
    button.addEventListener("click", function () {
      const vehicleId = this.getAttribute("data-vehicle-id");
      window.location.href = `vehicle-details.html?id=${vehicleId}`;
    });
  });

  document.querySelectorAll(".edit-btn").forEach((button) => {
    button.addEventListener("click", async function () {
      const vehicleId = this.getAttribute("data-vehicle-id");
      try {
        const response = await fetch(`/api/vehicles/${vehicleId}`);
        const vehicle = await response.json();
        openEditVehicleModal(vehicle);
      } catch (error) {
        console.error("Error fetching vehicle:", error);
      }
    });
  });

  document.querySelectorAll(".delete-btn").forEach((button) => {
    button.addEventListener("click", function () {
      const vehicleId = this.getAttribute("data-vehicle-id");
      deleteVehicle(vehicleId);
    });
  });
}

//openAddVehicleModal
function openAddVehicleModal() {
  document.getElementById("addVehicleModal").style.display = "block";
}

//closeAddVehicleModal
function closeAddVehicleModal() {
  document.getElementById("addVehicleModal").style.display = "none";
  document.getElementById("addVehicleForm").reset();
  editingVehicleId = null;
}

//openEditVehicleModal
function openEditVehicleModal(vehicle) {
  editingVehicleId = vehicle.id;

  document.getElementById("manufacturer").value = vehicle.manufacturer;
  document.getElementById("model").value = vehicle.model;
  document.getElementById("year").value = vehicle.year_of_Mfg;
  document.getElementById("type").value = vehicle.type;
  document.getElementById("engineType").value = vehicle.engine_Type;
  document.getElementById("fuelType").value = vehicle.fuel_Type;

  document.getElementById("addVehicleModal").style.display = "block";
}

//deleteVehicle
async function deleteVehicle(id) {
  if (confirm("Are you sure you want to delete this vehicle?")) {
    try {
      const response = await fetch(`/api/vehicles/${id}`, { method: "DELETE" });
      if (response.ok) loadVehicles();
    } catch (error) {
      console.error("Error deleting vehicle:", error);
    }
  }
}

//handleSubmitVehicle
async function handleSubmitVehicle(event) {
  event.preventDefault();
  const formData = new FormData(event.target);
  const vehicleData = {
    manufacturer: formData.get("manufacturer"),
    model: formData.get("model"),
    year_of_Mfg: parseInt(formData.get("year_of_Mfg")),
    type: formData.get("type"),
    engine_Type: formData.get("engine_Type"),
    fuel_Type: formData.get("fuel_Type"),
  };

  
  try {
    const url = editingVehicleId
      ? `/api/vehicles/${editingVehicleId}`
      : "/api/vehicles";

    const method = editingVehicleId ? "PUT" : "POST";
    const response = await fetch(url, {
      method: method,
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(vehicleData),
    });

    if (response.ok) {
      closeAddVehicleModal();
      loadVehicles();
      event.target.reset();
      editingVehicleId = null;
    }
  } catch (error) {
    console.error(error);
  }
}

//CHART

// Chart update helpers (for details modal)
// ------------------------------------------------
function updateEngineDataChart(engineData) {
  const ctx = document.getElementById("engineDataChart").getContext("2d");

  if (engineDataChart) {
    engineDataChart.destroy();
  }

  const timestamps = engineData.map((data) =>
    new Date(data.timestamp).toLocaleString()
  );
  const rpms = engineData.map((data) => data.rpm);
  const temperatures = engineData.map((data) => data.temperature);

  engineDataChart = new Chart(ctx, {
    type: "line",
    data: {
      labels: timestamps,
      datasets: [
        {
          label: "RPM",
          data: rpms,
          borderColor: "#FF6384",
          yAxisID: "rpm",
        },
        {
          label: "Temperature (°C)",
          data: temperatures,
          borderColor: "#36A2EB",
          yAxisID: "temp",
        },
      ],
    },
    options: {
      responsive: true,
      interaction: {
        mode: "index",
        intersect: false,
      },
      scales: {
        rpm: {
          type: "linear",
          position: "left",
          title: {
            display: true,
            text: "RPM",
          },
        },
        temp: {
          type: "linear",
          position: "right",
          title: {
            display: true,
            text: "Temperature (°C)",
          },
        },
      },
    },
  });
}

function updateFuelDataChart(fuelData) {
  const ctx = document.getElementById("fuelDataChart").getContext("2d");

  if (fuelDataChart) {
    fuelDataChart.destroy();
  }

  const timestamps = fuelData.map((data) =>
    new Date(data.timestamp).toLocaleString()
  );
  const litersUsed = fuelData.map((data) => data.liters_used);
  const distanceKm = fuelData.map((data) => data.distance_km);

  fuelDataChart = new Chart(ctx, {
    type: "line",
    data: {
      labels: timestamps,
      datasets: [
        {
          label: "Liters Used",
          data: litersUsed,
          borderColor: "#FF6384",
          yAxisID: "liters",
        },
        {
          label: "Distance (km)",
          data: distanceKm,
          borderColor: "#36A2EB",
          yAxisID: "distance",
        },
      ],
    },
    options: {
      responsive: true,
      interaction: {
        mode: "index",
        intersect: false,
      },
      scales: {
        liters: {
          type: "linear",
          position: "left",
          title: {
            display: true,
            text: "Liters Used",
          },
        },
        distance: {
          type: "linear",
          position: "right",
          title: {
            display: true,
            text: "Distance (km)",
          },
        },
      },
    },
  });
}
