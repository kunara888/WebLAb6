# Lab 7: Spring Boot REST Controller

This project is a simple Event Registration API built with Spring Boot. It uses HTTP requests to manage events and supports full CRUD operations.

## Technologies Used
- Java
- Spring Boot
- Gradle
- Postman (for testing APIs)

## API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/events` | Get all events (you can also use `?title=` to search) |
| **GET** | `/api/events/{id}` | Get a specific event by ID[cite: 1] |
| **POST** | `/api/events` | Create a new event using a JSON body[cite: 1] |
| **PUT** | `/api/events/{id}` | Update an existing event by ID[cite: 1] |
| **DELETE** | `/api/events/{id}` | Delete an event by ID[cite: 1] |

## Example JSON for POST / PUT
```json
{
  "title": "Web Design Workshop",
  "location": "Room 101"
}
