🏥 Hospital Management System

A console-based Hospital Management System developed using Java, JDBC, MySQL, DAO Pattern, Singleton Pattern, and BCrypt Authentication. The system helps manage hospital operations such as patient registration, doctor management, appointment scheduling, medical records, billing, and secure user authentication.

🚀 Features

👨‍💼 Admin Module

- Admin Login
- Add/View/Delete Patients
- Add/View/Delete Doctors
- Add/View Staff
- Schedule Appointments
- Create Medical Records
- Generate Bills
- View Bills
- View Appointments

👨‍⚕️ Doctor Module

- Doctor Login
- View Assigned Appointments
- Add Medical Records
- View Patient Medical History

🧑‍🤝‍🧑 Patient Module

- Patient Login
- View Appointments
- View Medical Records
- View Bills
- Pay Bills

💰 Billing Module

- Generate Bills
- Track Bill Status
- Mark Bills as Paid

📅 Appointment Module

- Book Appointments
- View Appointments by Patient
- View Appointments by Doctor
- Cancel Appointments

🔒 Security

- BCrypt Password Hashing
- Role-Based Access Control
- PreparedStatement Usage to Prevent SQL Injection

🛠️ Technologies Used

- Java
- JDBC
- MySQL
- BCrypt
- OOP
- DAO Pattern
- Singleton Pattern
- Git & GitHub

📊 Database Tables

- ADMINS
- PATIENTS
- DOCTORS
- STAFF
- APPOINTMENTS
- MEDICALRECORDS
- BILLS

🎯 Design Patterns Used

DAO Pattern

Separates database operations from business logic.

Singleton Pattern

Ensures a single database connection instance throughout the application.

🔐 Authentication

Passwords are stored securely using BCrypt hashing.

👨‍💻 Author

Girish T

Bachelor of Engineering (Data Science)

Aspiring Java Backend Developer

🚀 Future Improvements

- Spring Boot Migration
- REST APIs
- Hibernate/JPA
- Frontend Integration
- Email Notifications
- Dashboard UI
- JWT Authentication
