# Todo Web Application

This repository contains a simple Todo application with a Spring Boot backend and a React-based frontend.

## Backend

The backend is located in the `backend` directory and is a Maven-based Spring Boot project.

### Build & Run

```bash
cd backend
mvn spring-boot:run
```

The application will start on port `8080` and exposes REST endpoints under `/api/todos`.

## Frontend

A minimal React application is provided in the `frontend` directory. No build step is required.
Open `frontend/index.html` in your browser while the backend is running to interact with the API.

## Features

- Add, update, delete and list todos
- Persistence with an in-memory H2 database
- Simple web interface using React and Axios
