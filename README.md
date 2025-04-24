# HDB BTO Application Management System

This repository contains the full implementation of a CLI-based Java system designed for the **SC2002 Object-Oriented Design & Programming** module at **Nanyang Technological University (NTU)**.

The system simulates and manages the end-to-end process of Singapore’s Build-To-Order (BTO) public housing scheme, supporting distinct user roles such as Applicants, HDB Officers, and HDB Managers.

---

## Project Objectives

- Demonstrate application of **Object-Oriented Programming (OOP)** principles
- Apply **SOLID**, **MVC**, and **BCE** architecture patterns in practice
- Implement role-based interaction, state management, and file persistence using Java
- Integrate CLI-based user interaction via text-based Views

---

## Directory Structure

```
SC2002-Project-main/
├── controllers/         # Control logic for user roles and business flows
├── views/               # CLI views handling user interaction
├── models/              # Core business entities (Project, Application, Enquiry, etc.)
├── roles/               # Applicant, HDBOfficer, HDBManager classes
├── services/            # Service and interface abstractions
├── data/                # CSV data files (persistent storage)
├── Main.java            # Entry point to the system
├── utils/               # Utilities for data parsing and formatting
```

---

## User Roles and Functional Overview

### Applicant
- View and apply for eligible projects
- Withdraw applications
- View status updates and submit enquiries

### HDB Officer
- Register for projects (with eligibility checks)
- Respond to project enquiries
- Book flats for successful applicants

### HDB Manager
- Create/edit/delete projects
- Approve/reject officer registrations and applications
- Generate reports and view application summaries

---

## Design Patterns and Architecture

- **MVC Architecture:** Logical separation of responsibilities
- **SOLID Principles:** Modular, maintainable, and extendable design
- **Boundary-Control-Entity (BCE):** Logical flow applied to user actions

---

## Compilation and Execution

### Requirements
- Java Development Kit (JDK) 11 or later

### Steps
1. Open the project in IntelliJ IDEA or any Java IDE.
2. Compile using:
   ```
   javac Main.java
   ```
3. Run the system:
   ```
   java Main
   ```
4. Navigate the menu based on your assigned role.

---

## UML Diagram

- A comprehensive UML class diagram is included in `uml_colored_classdiagram.mmd`
- Uses **Mermaid syntax**, Grouped by namespace (Views, Models, Controllers, Roles, Enumeration)
- [UML class Diagram](https://www.mermaidchart.com/app/projects/c26eeb08-da84-4a8c-92fb-def5791c37e4/diagrams/252a44d1-8a9d-4fac-ab40-cbab9f2fc403/version/v0.1/edit)

---

## Authors
- Module: SC2002 – Object-Oriented Design and Programming
- Academic Year: 2024/2025, Semester 2
