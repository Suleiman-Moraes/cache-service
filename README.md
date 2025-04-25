---

# ☕️ cache-service

A **Java Spring Boot** application demonstrating **manual and automatic caching** using **Caffeine** and **Redis** to enhance performance, reduce database load, and keep things fast.

## 🚀 Purpose

The goal of this project is to **showcase practical caching strategies** using two powerful technologies:

- 🧠 **Caffeine** — blazing fast, in-memory local cache
- 🧰 **Redis** — distributed cache for scalability and shared access

This service simulates a student system and illustrates **when and how to apply caching**, either manually (via `Caffeine`) or automatically (via `Spring Cache + Redis`), to minimize repeated database hits.

## 🧩 Tech Stack

- Java 21
- Spring Boot 3.4.5
- Caffeine Cache
- Redis (optional, if you want to test distributed caching)
- H2 Database (for demo data)
- Java Faker (to seed mock data)
- OpenAPI (Swagger UI)
- Lombok

## 📦 Features

- Manual caching using Caffeine
- Spring Boot integration with Redis cache (WIP / optional)
- Easy-to-read service layer for educational purposes
- Auto-generated API docs with Swagger UI
- Lightweight setup (H2 + Faker) for quick testing

## 🧪 Example Endpoints

- `POST /students` – create new student
- `GET /students/{id}` – fetch student by ID (uses cache)
- `GET /students` – list all students
- `POST /students/populate` – populate the DB with fake data

## 🔄 Caching Strategies

| Technology | Scope      | Use Case                          |
|------------|------------|-----------------------------------|
| Caffeine   | Local      | Low-latency caching on a single node |
| Redis      | Distributed| Shared cache across multiple nodes (future-proof) |

> 📝 All caching is time-based with auto-expiration to ensure consistency.

## 🛠️ How to Run

```bash
git clone https://github.com/Suleiman-Moraes/cache-service.git
cd cache-service
./mvnw spring-boot:run
```

Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🎯 Future Enhancements

- Add Redis-backed caching with Spring Cache annotations
- Expose cache stats via Actuator
- Toggle cache strategies via profiles (`dev`, `prod`, etc.)

## 👤 Developer

**Suleiman Moraes**  
📧 suleimandevtech@gmail.com  
🔗 [GitHub Profile](https://github.com/suleiman-moraes)

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---