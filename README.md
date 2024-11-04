# Grocery Booking API
A Spring Boot application for managing grocery items and user orders with role-based access control.

## Features
- By default 1 user and 1 admin are created.
- CRUD operations for grocery items
- Order management with multiple items
- Filtering options for grocery items
- Caching for performance
- Dockerized for easy deployment

## Technologies
- Java 17
- Spring Boot
- Basic Spring Security
- Hibernate (JPA)
- h2
- Docker

## Setup
## Docker Setup

To run the Grocery application in a Docker container, run the below commands:

1. docker build -t grocery .
2. docker run -p 8080:8080 grocery

## API Credentials

For testing the API, you can use the following default credentials:

### Admin Credentials
- **Username:** admin
- **Password:** admin

### User Credentials
- **Username:** user
- **Password:** user

Use these credentials to authenticate when making requests to the API endpoints that require user roles.

### Postman collection
- Added [Grocery.postman_collection.json](Grocery.postman_collection.json) which has all api , you can import to get all apis with request bodies.


# API Endpoints

## Grocery Item APIs
## BasicAuth is added for all apis

1. **Add Grocery Item**
    - **Endpoint:** `POST /grocery-items` 
    - only admin can add
    - **Description:** Create a new grocery item or update an existing item by summing quantities.

2. **Update Grocery Item**
    - **Endpoint:** `PUT /grocery-items/{id}` 
    - only admin can update
    - **Description:** Update details of an existing grocery item.

3. **Delete Grocery Item**
    - **Endpoint:** `DELETE /grocery-items/{id}` 
    - only admin can delete
    - **Description:** Remove a grocery item from the inventory.

4. **View All Grocery Items**
    - **Endpoint:** `GET /grocery-items` 
    - any one can access so no credentials are required.
    - **Description:** Retrieve a list of grocery items.
    - **Query Parameters:**
        - `name` (optional): Filter by item name (can be partial).
        - `minPrice` (optional): Filter items with a price greater than or equal to this value.
        - `maxPrice` (optional): Filter items with a price less than or equal to this value.
        - `category` (optional): Filter by  category.

## Order APIs

1. **Create Order**
    - **Endpoint:** `POST /orders` 
    - both admin and user allowed
    - **Description:** Create a new order with multiple grocery items. Updates inventory based on the order.

2. **Get All Orders** 
    - **Endpoint:** `GET /orders`
    - As of now as there is only 1 user and 1 admin i have given access to both of them.
    - **Description:** Retrieve a list of all orders.
