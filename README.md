# Bike Shop API

A REST API for managing a bike shop built with Spring Boot 3.2 and Java 21.

## Features

- Bike management (create, read, search, delete)
- Accessory management with price filtering
- User-based shopping cart system
- Search and filter functionality
- Request validation with error messages
- Pre-loaded sample data on startup

## Tech Stack

- Java 21
- Spring Boot 3.2.1
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Maven

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Running the Application

Clone the repository:

```bash
git clone <repository-url>
cd bike-shop
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Authentication

All endpoints under `/api/**` require authentication. Include this header in all requests:

| Header         | Value                       |
| -------------- | --------------------------- |
| Authentication | bike-shop-secret-token-2026 |

### Bikes

| Method | Endpoint                                               | Description       |
| ------ | ------------------------------------------------------ | ----------------- |
| GET    | /api/bikes                                             | Get all bikes     |
| GET    | /api/bikes/{id}                                        | Get bike by ID    |
| GET    | /api/bikes/search?category={category}&maxPrice={price} | Search bikes      |
| POST   | /api/bikes                                             | Create a new bike |
| DELETE | /api/bikes/{id}                                        | Delete a bike     |

### Accessories

| Method | Endpoint                                              | Description                 |
| ------ | ----------------------------------------------------- | --------------------------- |
| GET    | /api/accessories                                      | Get all accessories         |
| GET    | /api/accessories/{id}                                 | Get accessory by ID         |
| GET    | /api/accessories/filter?minPrice={min}&maxPrice={max} | Filter accessories by price |
| POST   | /api/accessories                                      | Create a new accessory      |
| DELETE | /api/accessories/{id}                                 | Delete an accessory         |

### Cart

| Method | Endpoint                                      | Description                |
| ------ | --------------------------------------------- | -------------------------- |
| GET    | /api/carts/{userId}                           | Get user's cart            |
| POST   | /api/carts/{userId}/bikes/{bikeId}            | Add bike to cart           |
| DELETE | /api/carts/{userId}/bikes/{bikeId}            | Remove bike from cart      |
| POST   | /api/carts/{userId}/accessories/{accessoryId} | Add accessory to cart      |
| DELETE | /api/carts/{userId}/accessories/{accessoryId} | Remove accessory from cart |

## Example Requests

Create a new bike:

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

Search bikes by category:

```bash
curl -H "Authentication: bike-shop-secret-token-2026" \
  "http://localhost:8080/api/bikes/search?category=Mountain&maxPrice=1500"
```

Add bike to cart:

```bash
curl -X POST -H "Authentication: bike-shop-secret-token-2026" \
  http://localhost:8080/api/carts/user123/bikes/{bikeId}
```

Get all accessories:

```bash
curl -H "Authentication: bike-shop-secret-token-2026" \
  http://localhost:8080/api/accessories
```

Filter accessories by price:

```bash
curl -H "Authentication: bike-shop-secret-token-2026" \
  "http://localhost:8080/api/accessories/filter?minPrice=20&maxPrice=100"
```

## Sample Data

The application comes with pre-loaded bikes:

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

And pre-loaded accessories:

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

## API Documentation (Swagger UI)

Interactive API docs are available via Swagger UI:

- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Notes
- If you encounter a 500 error on the docs, ensure you are using springdoc-openapi-starter-webmvc-ui version 2.5.0 or higher for compatibility with Spring Boot 3.2+.
- Entity relationships are properly annotated with `@JsonIgnore` to prevent serialization recursion issues in the OpenAPI spec.

## Database

The application uses an in-memory H2 database that resets on each restart.

### H2 Console

Access the database console at: http://localhost:8080/h2-console

Connection details:

- JDBC URL: jdbc:h2:mem:bikeshopdb
- Username: sa
- Password: (leave empty)

## Project Structure

```
src/main/java/com/bikeshop/
├── BikeShopApplication.java
├── config/
│   ├── AuthenticationFilter.java
│   ├── DataInitializer.java
│   └── SecurityProperties.java
├── controller/
│   ├── AccessoryController.java
│   ├── BikeController.java
│   └── CartController.java
├── dto/
│   ├── AccessoryCreateDto.java
│   ├── AccessoryDto.java
│   ├── ApiErrorResponse.java
│   ├── BikeCreateDto.java
│   ├── BikeDto.java
│   ├── BikeSearchCriteria.java
│   └── CartDto.java
├── entity/
│   ├── Accessory.java
│   ├── Bike.java
│   └── Cart.java
├── exception/
│   ├── AccessoryAlreadyInCartException.java
│   ├── BikeAlreadyInCartException.java
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── UnauthorizedException.java
├── mapper/
│   ├── AccessoryMapper.java
│   ├── BikeMapper.java
│   └── CartMapper.java
├── repository/
│   ├── AccessoryRepository.java
│   ├── BikeRepository.java
│   └── CartRepository.java
└── service/
    ├── AccessoryService.java
    ├── BikeService.java
    ├── CartService.java
    └── impl/
        ├── AccessoryServiceImpl.java
        ├── BikeServiceImpl.java
        └── CartServiceImpl.java
```

## License

This project is open source and available under the MIT License.
