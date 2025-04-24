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
- .idea: folder to store project libraries and gitignore
- out: output folder
- enums: package for enumerations
- sg.com.ntu.group3: source files
- controllers: package for high-level controller classes
- services: package for interface services
- models: package for persistence model classes
- roles: package for user classes
- views: package for view classes that interact with the user


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

- A comprehensive UML class diagram is included in `SC2002 Group 3 UML Class Diagram.svg`
- [View UML Class Diagram on Mermaid Chart for clarity](https://www.mermaidchart.com/app/projects/c26eeb08-da84-4a8c-92fb-def5791c37e4/diagrams/252a44d1-8a9d-4fac-ab40-cbab9f2fc403/version/v0.1/edit)

---

## Authors
- Module: SC2002 – Object-Oriented Design and Programming
- Academic Year: 2024/2025, Semester 2
