---

# ☕️ cache-service

A **Java Spring Boot** application demonstrating **manual and distributed caching** using **Caffeine** and **Redis** with a clean **proxy pattern implementation** for maximum flexibility.

## 🚀 Purpose

The goal of this project is to **showcase practical caching strategies** using two powerful technologies:

* 🧠 **Caffeine** — blazing fast, in-memory local cache
* ☁️ **Redis** — distributed cache for scalability and shared access

This service simulates a **student system** and illustrates **how to integrate and switch cache strategies** without impacting business logic, thanks to a clean proxy-based architecture.

## 🧩 Tech Stack

* Java 21
* Spring Boot 3.5.3
* Caffeine Cache
* Redis (via Docker Compose)
* H2 Database (for demo data)
* Java Faker (to seed mock data)
* OpenAPI (Swagger UI)
* Lombok

## 📦 Features

* Manual caching using Caffeine with explicit proxy implementation
* Distributed caching using Redis with Spring Data Redis
* **Proxy pattern** for cache implementation, enabling:

  * Easy switching between cache providers (Caffeine ↔ Redis ↔ No Cache)
  * Zero impact on application code when changing cache strategies
* Profiles:

  * `caffeine`: enables Caffeine cache
  * `redis`: enables Redis cache
  * `default`: runs without cache for baseline testing
* Auto-generated API docs with Swagger UI
* Lightweight setup with H2 + Faker for quick testing

## 🧪 Example Endpoints

* `POST /students` – create new student
* `GET /students/{id}` – fetch student by ID (uses cache depending on profile)
* `GET /students` – list all students
* `POST /students/populate` – populate the DB with fake data

## 🔄 Caching Strategies

| Technology | Scope       | Profile  | Use Case                             |
| ---------- | ----------- | -------- | ------------------------------------ |
| Caffeine   | Local       | caffeine | Low-latency caching on a single node |
| Redis      | Distributed | redis    | Shared cache across multiple nodes   |
| None       | -           | default  | Baseline performance, no cache       |

> 📝 All caching implementations include **time-based auto-expiration** for data consistency.

## 🛠️ How to Run

1. **Clone the project**

```bash
git clone https://github.com/Suleiman-Moraes/cache-service.git
cd cache-service
```

2. **Start Redis via Docker Compose**

```bash
docker-compose -f compose.yaml up -d
```

3. **Run with Maven using a specific profile**

* For **Caffeine** cache:

  ```bash
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=caffeine
  ```

* For **Redis** cache:

  ```bash
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=redis
  ```

* For **no cache (default)**:

  ```bash
  ./mvnw spring-boot:run
  ```

4. **Access Swagger UI**

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 💡 Architectural Highlights

* Uses the **proxy pattern** to wrap `IStudentService` with cache implementations.
* Switching or disabling cache requires **no changes in controllers or services**, only profile changes.
* Clean, decoupled design following **SOLID principles**.

## 🎯 Future Enhancements

* Benchmark performance differences between profiles
* Expose cache stats via Actuator endpoints
* Implement cache invalidation strategies based on business rules

## 👤 Developer

**Suleiman Moraes**
📧 [suleimandevtech@gmail.com](mailto:suleimandevtech@gmail.com)
🔗 [GitHub Profile](https://github.com/suleiman-moraes)

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---
