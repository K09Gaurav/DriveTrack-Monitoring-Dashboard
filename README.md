# DriveTrack – Vehicle Monitoring Dashboard

**DriveTrack** is a backend-focused dashboard that enables vehicle performance monitoring using Java, Spring Boot, Hibernate, and PostgreSQL. It tracks key metrics such as fuel efficiency and engine temperature with clean RESTful APIs and layered architecture.

---

## 🚀 Features

- RESTful APIs for managing vehicles, fuel logs, and engine data
- ORM integration using Hibernate with PostgreSQL backend
- Modular MVC architecture using Spring Boot and Maven
- API testing and validation with Postman
- Entity relationship design for scalable data models

---

## 🛠️ Tech Stack

| **Component**  | **Technology**        | **Purpose**                                           |
| -------------------- | --------------------------- | ----------------------------------------------------------- |
| Backend Framework    | Spring Boot 3.4.5           | Application infrastructure, RESTful API development         |
| Programming Language | Java 11                     | Backend implementation                                      |
| Database             | PostgreSQL 14               | Persistent data storage                                     |
| ORM Tool             | Hibernate / Spring Data JPA | Object-relational mapping, database interaction             |
| Frontend (basic)     | HTML5, CSS3, JavaScript     | User interface and dashboard visualization (not core focus) |
| API Documentation    | Javadocs                    | Automated backend API documentation                         |
| API Testing          | Postman                     | API endpoint validation                                     |
| Version Control      | Git                         | Source code management                                      |
| Build Tool           | Maven                       | Dependency management, build automation                     |
| Development IDE      | Visual Studio Code          | Local development environment                               |

---

## 🧱 Entity Models

- **Vehicle Entity**: The central entity representing individual vehicles with their specifications including manufacturer, model, vehicle type, fuel type, engine type, and year of manufacture.
- **FuelData Entity**: Records individual fuel consumption entries linked to specific vehicles, tracking liters used, distance traveled in kilometers, and timestamp of the entry.
- **EngineData Entity**: Captures engine performance metrics linked to specific vehicles, including RPM measurements, temperature readings, and timestamp of the recording.

---

## 📚 API Documentation

The system exposes a comprehensive set of RESTful endpoints for managing vehicles, fuel data, and engine data.

### 🚗 Vehicle APIs

**Base Path:** `/api/vehicles`

| Method | Endpoint               | Description                |
| ------ | ---------------------- | -------------------------- |
| GET    | `/api/vehicles`      | Retrieve all vehicles      |
| GET    | `/api/vehicles/{id}` | Retrieve vehicle by ID     |
| POST   | `/api/vehicles`      | Create a new vehicle       |
| PUT    | `/api/vehicles/{id}` | Update an existing vehicle |
| DELETE | `/api/vehicles/{id}` | Delete a vehicle           |

### ⛽ Fuel Data APIs

**Base Path:** `/api/vehicles/{id}/fuel-data`

| Method | Endpoint                                        | Description                          |
| ------ | ----------------------------------------------- | ------------------------------------ |
| GET    | `/api/vehicles/{id}/fuel-data`                | Retrieve all fuel data for a vehicle |
| GET    | `/api/vehicles/{id}/fuel-data/{fuel_id}`      | Retrieve specific fuel data entry    |
| GET    | `/api/vehicles/{id}/fuel-data/totalfuel`      | Get total fuel usage                 |
| GET    | `/api/vehicles/{id}/fuel-data/totaldistance`  | Get total distance                   |
| GET    | `/api/vehicles/{id}/fuel-data/fuelefficiency` | Calculate fuel efficiency            |
| POST   | `/api/vehicles/{id}/fuel-data`                | Create new fuel data                 |
| PUT    | `/api/vehicles/{id}/fuel-data/{fuel_id}`      | Update fuel data entry               |
| DELETE | `/api/vehicles/{id}/fuel-data/{fuel_id}`      | Delete fuel data entry               |

### 🔧 Engine Data APIs

**Base Path:** `/api/vehicles/{id}/engine-data`

| Method | Endpoint                                       | Description                             |
| ------ | ---------------------------------------------- | --------------------------------------- |
| GET    | `/api/vehicles/{id}/engine-data`             | Retrieve all engine data for a vehicle  |
| GET    | `/api/vehicles/{id}/engine-data/{engine_id}` | Retrieve specific engine data entry     |
| GET    | `/api/vehicles/{id}/engine-data/Avg_RPM`     | Get average RPM for the vehicle         |
| GET    | `/api/vehicles/{id}/engine-data/Avg_Temp`    | Get average temperature for the vehicle |
| POST   | `/api/vehicles/{id}/engine-data`             | Create new engine data                  |
| PUT    | `/api/vehicles/{id}/engine-data/{engine_id}` | Update engine data entry                |
| DELETE | `/api/vehicles/{id}/engine-data/{engine_id}` | Delete engine data entry                |

---

## 🖼️ Screenshots

![DB Design](https://github.com/K09Gaurav/DriveTrack-Monitoring-Dashboard/blob/main/Screenshots/DBDesign.png)

![Front image](https://github.com/K09Gaurav/DriveTrack-Monitoring-Dashboard/blob/main/Screenshots/DetailsPage.png)

![Vehicle Page](https://github.com/K09Gaurav/DriveTrack-Monitoring-Dashboard/blob/main/Screenshots/VehiclePage.png)

![Details Page](https://github.com/K09Gaurav/DriveTrack-Monitoring-Dashboard/blob/main/Screenshots/dashboard.png)
