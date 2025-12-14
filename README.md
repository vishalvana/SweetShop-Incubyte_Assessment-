#  Sweet Shop Management System – Incubyte Assessment

A full-stack Sweet Shop Management System developed as part of the **Incubyte Assessment**.  
The application enables users to browse and purchase sweets, while administrators manage inventory and stock.  
It is built using **Spring Boot + MongoDB** for the backend and **React** for the frontend, with **JWT-based authentication** and **role-based authorization**.

---
# Live Link : 
https://sweet-shop-frontend-beta.vercel.app

---

## 📌 Project Overview

This project is a **full-stack Sweet Shop Management System** developed as part of the **TDD Kata – Sweet Shop Management System assessment**. The objective of this kata is to evaluate backend API design, frontend development, database integration, testing practices, and the effective use of AI tools in a modern development workflow.

The system allows **users** to browse, search, and purchase sweets, while **admin users** can manage sweets and inventory through secure, role-based access. The backend exposes a set of RESTful APIs secured with **JWT-based authentication**, and the frontend is implemented as a **modern single-page application (SPA)** that consumes these APIs.

The project follows **Test-Driven Development (TDD)** principles, with backend functionality developed using a **Red–Green–Refactor** approach. Tests were written to validate authentication, inventory management, and purchase workflows, ensuring correctness and reliability of core business logic.

Special attention was given to:
- Clean and maintainable code following SOLID principles
- Clear separation of concerns between controllers, services, and repositories
- Proper error handling and validation
- Transparent and responsible usage of AI tools during development

This repository contains **separate backend and frontend codebases**, making the project easy to understand, run, and extend. The project is designed to be interview-ready, with clear documentation, test coverage, and an explicit explanation of AI usage.


---

##  Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security (JWT)
- MongoDB
- Lombok
- Maven

### Frontend
- React (Vite)
- Axios
- Tailwind CSS
- Framer Motion

---

## 📂 Repository Structure

```
SweetShop-Incubyte_Assessment-
│
├── backend/     # Spring Boot application
│
└── frontend/    # React application
```

---

## ⚙️ Setup & Run Instructions

### 🔹 Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- MongoDB (local or MongoDB Atlas)
- Maven

---

## 🖥 Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Backend runs on:
```
http://localhost:8080
```

### MongoDB Configuration
Update `application.properties` or `application.yml` if required:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/sweetshop
jwt.secret=your_jwt_secret
jwt.expiration=86400000
```

---

## 🌐 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

### Frontend runs on:
```
http://localhost:5173
```

---

## 🔐 Application Features

### 👤 Authentication
- User registration and login
- JWT-based authentication
- Secure token-based sessions

### 🧑‍💼 Role-Based Access
- **USER**
  - View available sweets
  - Add sweets to cart
  - Purchase sweets
- **ADMIN**
  - Add, update, and delete sweets
  - Restock sweets
  - Manage inventory

### 🍭 Inventory Management
- Stock validation before purchase
- Automatic quantity updates
- Admin-only restock functionality

---

## 📡 Key API Endpoints

| Endpoint | Method | Access |
|--------|--------|--------|
| `/api/auth/register` | POST | Public |
| `/api/auth/login` | POST | Public |
| `/api/auth/me` | GET | Authenticated |
| `/api/sweets` | GET | Authenticated |
| `/api/sweets/{id}/purchase` | POST | USER |
| `/api/sweets/{id}/restock` | POST | ADMIN |

---

## 🧪 Test Report

The backend includes unit and integration tests covering:
- Authentication logic
- Inventory service
- Purchase and restock workflows

### Run Tests
```bash
cd backend
mvn test
```

### Test Result
✅ All tests passed successfully

---

## 📸 Here's Demo 
## 📸 Application Screenshots

### 🔐 Authentication (Login / Register)
<img src="https://raw.githubusercontent.com/vishalvana/SweetShop-Incubyte_Assessment-/main/Screenshot%202025-11-28%20021529.jpg" width="800" />

---

### 🏠 User Dashboard – Sweets Listing
<img src="https://raw.githubusercontent.com/vishalvana/SweetShop-Incubyte_Assessment-/main/Screenshot%202025-12-14%20213313.jpg" width="800" />

---

### 🔍 Search & Filter Functionality
<img src="https://raw.githubusercontent.com/vishalvana/SweetShop-Incubyte_Assessment-/main/Screenshot%202025-12-14%20213350.jpg" width="800" />

---

### 🛒 Purchase Flow
<img src="https://raw.githubusercontent.com/vishalvana/SweetShop-Incubyte_Assessment-/main/Screenshot%202025-12-14%20213419.jpg" width="800" />

---

### 🧑‍💼 Admin Dashboard – Inventory Management
<img src="https://raw.githubusercontent.com/vishalvana/SweetShop-Incubyte_Assessment-/main/Screenshot%202025-12-14%20213456.jpg" width="800" />

---

### 📦 Admin – Restock & Sweet Management
<img src="https://raw.githubusercontent.com/vishalvana/SweetShop-Incubyte_Assessment-/main/Screenshot%202025-12-14%20213519.jpg" width="800" />





---

##  My AI Usage

### AI Tools Used
- **ChatGPT (OpenAI)**
- **GitHub Copilot**

---

### How I Used AI

I used AI tools as **development assistants** to support my workflow:

- Used **ChatGPT** to:
  - Understand Spring Security and JWT authentication concepts
  - Debug backend issues such as request mapping mismatches
  - Validate REST API design and role-based authorization
  - Improve code structure and documentation clarity

- Used **GitHub Copilot** to:
  - Generate boilerplate code (DTOs, constructors)
  - Speed up repetitive coding tasks
  - Assist with method and class suggestions

---

### Reflection on AI Usage

AI tools helped me improve productivity and reduce development time.  
All architectural decisions, business logic, and final implementations were **fully understood, reviewed, and validated by me**.

I used AI responsibly as a **learning and productivity tool**, similar to documentation or online resources, and I am confident explaining or modifying any part of this project during an interview.

---

## 🔗 Repository Link

GitHub Repository:  
https://github.com/vishalvana/SweetShop-Incubyte_Assessment-.git

---


Thank you for reviewing my submission.
