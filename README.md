# ExamiNation: School Examination System

## Overview
**ExamiNation** is a platform designed to simplify the examination process for educational institutions. It has role based authentication to display two separate views for teachers and students.

## Technologies Used

### Backend
- **Spring Boot**: Backend framework for building the application.
- **Spring Security**: For authentication and authorization.
- **Spring Data JPA**: For database interaction.
- **Thymeleaf**: For server-side rendering of HTML views.
- **Hibernate**: ORM tool for interacting with the database.
- **Maven**: Build and dependency management tool.

### Database
- **MySQL**: Relational database for data persistence.

### Frontend
- **Bootstrap**: CSS framework for styling and responsive design.
- **Tailwind CSS**: CSS framework for styling.
- **HTML5 & CSS3**: For creating views rendered by Thymeleaf.

### Deployment & Build
- **Embedded Tomcat Server**: Comes with Spring Boot for running the application locally.

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/hmyunis/exami-nation.git
   cd exami-nation
   ```

2. **Backend Setup**
   - Navigate to the backend directory:
     ```bash
     cd backend
     ```
   - Build the project using Maven:
     ```bash
     mvn clean install
     ```
   - Run the Spring Boot application:
     ```bash
     mvn spring-boot:run
     ```

3. **Launch**
   - Open your browser and search:
     ```bash
     http://localhost:8080/
     ```
