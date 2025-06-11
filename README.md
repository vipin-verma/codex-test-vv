# Todo Web App

This repository contains a simple Todo application with a Spring Boot backend and a React frontend.

## Backend

The backend is located in the `backend` directory and uses Maven.

### Build and run

```bash
cd backend
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/todos`.

## Frontend

The frontend is a single HTML file located in the `frontend` directory. It uses React from a CDN, so no build step is required.

### Run

Simply open `frontend/index.html` in your browser. The page expects the backend to be running on `localhost:8080`.

