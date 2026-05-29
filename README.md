# Hospital OOP Java Console App

A menu-driven hospital management **console application** written in Java using an object-oriented design.

## Features

- Add **Doctors**
- Add **Staff**
- Register **Patients**
- **Book Appointments** (by patient ID + doctor ID)
- Add **Medical Records**
- **Generate Bills** (consultation + medicine + room fees)
- View **all records** (patients, doctors, staff, appointments, bills, medical records)

## Project Structure

- `src/main/` — application classes (package: `main`)
  - `Main.java` — entry point + CLI menu
  - `Hospital.java` — central singleton that stores all data
  - `Patient.java`, `Doctor.java`, `Staff.java`, `Appointment.java`, `MedicalRecord.java`, `Bill.java`
  - `Admin.java` — extends `Person`
- `src/utility/` — shared utilities
  - `Billable.java` — interface used by `Bill`

## Build & Run (Windows)

> The `out/` folder is used as the compiled output directory.

1) Compile:

```powershell
cd Hospital
javac -d out src\main\*.java src\utility\*.java
```

2) Run:

```powershell
java -cp out main.Main
```

## CLI Menu Options

When the app starts, choose:

1. Add doctor
2. Add staff
3. Register patient
4. Book appointment
5. Add medical record
6. Generate bill
7. Show all records
8. view all patients details
9. view all doctor details
10. exit

## Notes

- Appointment and record IDs are generated internally.
- `Hospital` is implemented as a singleton (`Hospital.getInstance()`).

## Message
This project is a learning-focused OOP example (Hospital Management System) implemented as a plain Java console application with classes representing real-world entities like Patient, Doctor, Appointments, Medical Records, and Bills.

