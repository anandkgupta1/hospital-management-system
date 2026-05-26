# 🏥 Hospital Management System

A full-featured **Hospital Management System** built in **Java** with a live web-based UI — designed to manage patients, doctors, and appointments efficiently.

🔗 **Live Demo:** [github-hospital-management-system.netlify.app](https://github-hospital-management-system.netlify.app)

---

## ✨ Features

- 👥 **Patient Management** — Add new patients, view all records with search functionality
- 👨‍⚕️ **Doctor Management** — View doctors with specializations
- 📅 **Appointment Booking** — Book appointments with doctor availability conflict detection
- 📊 **Dashboard** — Live stats for patients, doctors, appointments and specializations

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java (JDBC) |
| Database | MySQL |
| Frontend UI | HTML, CSS, JavaScript |
| IDE | IntelliJ IDEA |
| Hosting | Netlify |

---

## 📁 Project Structure

```
hospital-management-system/
├── src/
│   └── HospitalManagementSystem/
│       ├── HospitalManagementSystem.java   # Main entry point & appointment logic
│       ├── Patient.java                    # Patient CRUD operations
│       └── Doctor.java                     # Doctor operations
├── index.html                              # Live Web UI
└── hospital.iml
```

---

## 🗄️ Database Setup

Create a MySQL database named `hospital` and run the following:

```sql
CREATE DATABASE hospital;

USE hospital;

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patientId INT NOT NULL,
    doctorId INT NOT NULL,
    appointmentDate DATE NOT NULL,
    FOREIGN KEY (patientId) REFERENCES patients(id),
    FOREIGN KEY (doctorId) REFERENCES doctors(id)
);
```

---

## 🚀 How to Run Locally

### Java Backend
1. Clone the repository
   ```bash
   git clone https://github.com/anandkgupta1/hospital-management-system.git
   ```
2. Open in **IntelliJ IDEA**
3. Add **MySQL JDBC Driver** to your classpath
4. Update DB credentials in `HospitalManagementSystem.java`
5. Run `HospitalManagementSystem.java`

### Web UI
Simply open `index.html` in any browser — no setup needed!

---

## 📸 Preview

> 🔗 Live at: [github-hospital-management-system.netlify.app](https://github-hospital-management-system.netlify.app)

---

## 👨‍💻 Author

**Anand Kumar Gupta**
- GitHub: [@anandkgupta1](https://github.com/anandkgupta1)

---

⭐ If you found this project helpful, please give it a **star** on GitHub!
