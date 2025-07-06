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

- **Backend:** Java, Spring Boot, Hibernate, Maven
- **Database:** PostgreSQL
- **Testing:** Postman
- **IDE:** IntelliJ / VS Code (optional)
- **Architecture:** MVC (Model–View–Controller)

---

## 🧱 Entity Models

- `Vehicle`: Basic vehicle info (ID, model, registration, etc.)
- `FuelLog`: Tracks fuel efficiency and consumption per vehicle
- `EngineData`: Logs engine performance parameters

---

## 🧪 API Testing

All major endpoints were tested and validated using **Postman**. Sample collection and test cases are included in the `/postman/` folder (optional).

---

## ⚙️ Setup Instructions

```bash
# 1. Clone the repo
git clone https://github.com/K09Gaurav/DriveTrack-Vehicle-Monitoring.git
cd DriveTrack-Vehicle-Monitoring

# 2. Set up PostgreSQL
# Create a DB and update your DB credentials in src/main/resources/application.properties

# 3. Build and run the app
mvn spring-boot:run

# 4. Test APIs
# Use Postman or browser at: http://localhost:8080/api/vehicles (example endpoint)
