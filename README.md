# 🚲 Bike Shop API

A RESTful API for managing a bike shop, built with Spring Boot 3.2 and Java 21.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Sample Data](#sample-data)
- [Database](#database)
- [Project Structure](#project-structure)

## ✨ Features

- **Bike Management**: Create, read, search, and delete bikes
- **Shopping Cart**: User-based cart system to add/remove bikes (carts are created automatically when adding the first item)
- **Search Functionality**: Filter bikes by category and maximum price
- **Validation**: Request validation with meaningful error messages
- **Sample Data**: Pre-loaded bike inventory on startup

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot 3.2.1**
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Lombok** - Boilerplate reduction
- **Maven** - Build tool

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Running the Application

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd bike-shop
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`

## 📡 API Endpoints

### Authentication

All API endpoints under `/api/**` require authentication. Include the following header in all requests:

| Header           | Value                         |
| ---------------- | ----------------------------- |
| `Authentication` | `bike-shop-secret-token-2026` |

### Bikes

| Method   | Endpoint                                                 | Description       |
| -------- | -------------------------------------------------------- | ----------------- |
| `GET`    | `/api/bikes`                                             | Get all bikes     |
| `GET`    | `/api/bikes/{id}`                                        | Get bike by ID    |
| `GET`    | `/api/bikes/search?category={category}&maxPrice={price}` | Search bikes      |
| `POST`   | `/api/bikes`                                             | Create a new bike |
| `DELETE` | `/api/bikes/{id}`                                        | Delete a bike     |

### Accessories

| Method   | Endpoint                                                | Description                 |
| -------- | ------------------------------------------------------- | --------------------------- |
| `GET`    | `/api/accessories`                                      | Get all accessories         |
| `GET`    | `/api/accessories/{id}`                                 | Get accessory by ID         |
| `GET`    | `/api/accessories/filter?minPrice={min}&maxPrice={max}` | Filter accessories by price |
| `POST`   | `/api/accessories`                                      | Create a new accessory      |
| `DELETE` | `/api/accessories/{id}`                                 | Delete an accessory         |

### Cart

| Method   | Endpoint                                        | Description                |
| -------- | ----------------------------------------------- | -------------------------- |
| `GET`    | `/api/carts/{userId}`                           | Get user's cart            |
| `POST`   | `/api/carts/{userId}/bikes/{bikeId}`            | Add bike to cart           |
| `DELETE` | `/api/carts/{userId}/bikes/{bikeId}`            | Remove bike from cart      |
| `POST`   | `/api/carts/{userId}/accessories/{accessoryId}` | Add accessory to cart      |
| `DELETE` | `/api/carts/{userId}/accessories/{accessoryId}` | Remove accessory from cart |

### Example Requests

**Create a new bike:**

```bash
curl -X POST http://localhost:8080/api/bikes \
  -H "Authentication: bike-shop-secret-token-2026" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Mountain Bike Pro",
    "category": "Mountain",
    "description": "Professional mountain bike",
    "price": 999.99
  }'
```

**Search bikes by category:**

```bash
curl -H "Authentication: bike-shop-secret-token-2026" \
  "http://localhost:8080/api/bikes/search?category=Mountain&maxPrice=1500"
```

**Add bike to cart:**

```bash
curl -X POST -H "Authentication: bike-shop-secret-token-2026" \
  http://localhost:8080/api/carts/user123/bikes/{bikeId}
```

**Get all accessories:**

```bash
curl -H "Authentication: bike-shop-secret-token-2026" \
  http://localhost:8080/api/accessories
```

**Filter accessories by price:**

```bash
curl -H "Authentication: bike-shop-secret-token-2026" \
  "http://localhost:8080/api/accessories/filter?minPrice=20&maxPrice=100"
```

**Add accessory to cart:**

```bash
curl -X POST -H "Authentication: bike-shop-secret-token-2026" \
  http://localhost:8080/api/carts/user123/accessories/{accessoryId}
```

## 📦 Sample Data

The application comes pre-loaded with sample bikes across various categories:

| Name                   | Category | Price     |
| ---------------------- | -------- | --------- |
| Mountain Explorer Pro  | Mountain | $1,299.99 |
| Urban Commuter Elite   | City     | $799.99   |
| Road Racer Carbon      | Road     | $2,499.99 |
| Kids Adventure 20      | Kids     | $299.99   |
| Electric City Glide    | Electric | $1,899.99 |
| BMX Freestyle X        | BMX      | $449.99   |
| Touring Adventure Plus | Touring  | $1,599.99 |
| Gravel Master GT       | Gravel   | $1,799.99 |

### Sample Accessories

| Name                 | Price   |
| -------------------- | ------- |
| Pro Cycling Helmet   | $149.99 |
| LED Front Light      | $59.99  |
| LED Rear Light       | $29.99  |
| U-Lock Heavy Duty    | $79.99  |
| Bike Pump with Gauge | $44.99  |
| Cycling Gloves       | $34.99  |
| Water Bottle Cage    | $14.99  |
| Saddle Bag           | $24.99  |
| Bike Computer        | $89.99  |
| Repair Kit           | $19.99  |

## 💾 Database

The application uses an in-memory **H2 database** that resets on each restart.

### H2 Console

Access the H2 database console at: `http://localhost:8080/h2-console`

**Connection details:**

- JDBC URL: `jdbc:h2:mem:bikeshopdb`
- Username: `sa`
- Password: _(leave empty)_

## 📁 Project Structure

```
src/main/java/com/bikeshop/
├── BikeShopApplication.java    # Main application class
├── config/
│   └── DataInitializer.java    # Sample data initialization
├── controller/
│   ├── AccessoryController.java # Accessory REST endpoints
│   ├── BikeController.java     # Bike REST endpoints
│   └── CartController.java     # Cart REST endpoints
├── dto/
│   ├── AccessoryCreateDto.java # Accessory creation request
│   ├── AccessoryDto.java       # Accessory response
│   ├── ApiErrorResponse.java   # Error response format
│   ├── BikeCreateDto.java      # Bike creation request
│   ├── BikeDto.java            # Bike response
│   ├── BikeSearchCriteria.java # Search parameters
│   └── CartDto.java            # Cart response
├── entity/
│   ├── Accessory.java          # Accessory entity
│   ├── Bike.java               # Bike entity
│   └── Cart.java               # Cart entity
├── exception/
│   ├── AccessoryAlreadyInCartException.java
│   ├── BikeAlreadyInCartException.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── mapper/
│   ├── AccessoryMapper.java    # Entity-DTO mapping
│   ├── BikeMapper.java         # Entity-DTO mapping
│   └── CartMapper.java
├── repository/
│   ├── AccessoryRepository.java # Accessory data access
│   ├── BikeRepository.java     # Bike data access
│   └── CartRepository.java     # Cart data access
└── service/
    ├── AccessoryService.java   # Accessory business logic interface
    ├── BikeService.java        # Bike business logic interface
    ├── CartService.java        # Cart business logic interface
    └── impl/
        ├── AccessoryServiceImpl.java
        ├── BikeServiceImpl.java
        └── CartServiceImpl.java
```

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
