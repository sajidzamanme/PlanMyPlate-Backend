# PlanMyPlate - Smart Meal & Grocery Assistant

A comprehensive REST API for meal planning, recipe management, and grocery/inventory tracking.

## Features

✅ **Authentication**
- User signup and signin with JWT tokens
- Forgot password with reset functionality
- Secure password encryption using BCrypt

✅ **Recipe Management**
- Search recipes by name
- Filter recipes by calorie range
- View detailed recipe information

✅ **Meal Planning**
- Create personalized meal plans
- Weekly meal plan generation
- Track meal plan status
- Manage meal plan duration and recipes

✅ **Grocery Management**
- Create and manage grocery lists
- Track grocery list status
- Filter groceries by status

✅ **Inventory Management**
- Track user inventory
- Add/remove inventory items
- Monitor ingredient quantities and expiry dates

✅ **User Preferences**
- Set dietary preferences (vegetarian, vegan, etc.)
- Manage allergies
- Track food dislikes
- Set budget and serving preferences

---

## Technology Stack

- **Framework:** Spring Boot 4.0.1
- **Language:** Java 21
- **Database:** MySQL 10.4.32-MariaDB
- **Authentication:** JWT (JSON Web Tokens)
- **ORM:** JPA/Hibernate
- **Build Tool:** Gradle
- **Security:** Spring Security with BCrypt

---

## Prerequisites

1. **Java 21+** - [Download](https://www.oracle.com/java/technologies/downloads/#java21)
2. **MySQL 10.4+** - [Download](https://dev.mysql.com/downloads/mysql/)
3. **Gradle 8.0+** - [Download](https://gradle.org/releases/)

---

## Setup Instructions

### 1. Database Setup

Create the database using the SQL script:

```bash
mysql -u root -p < /path/to/plan_my_plate.sql
```

Or manually:
```sql
CREATE DATABASE plan_my_plate;
USE plan_my_plate;
-- Run the SQL schema file
```

### 2. Clone and Configure Project

```bash
cd /home/sajidzaman/Study/dbms/PlanMyPlate
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:8050/plan_my_plate
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build Project

```bash
./gradlew clean build
```

### 4. Run Application

```bash
./gradlew bootRun
```

Or:
```bash
java -jar build/libs/PlanMyPlate-0.0.1-SNAPSHOT.jar
```

The API will be available at: `http://localhost:8081/api`

---

## API Endpoints Summary

### Authentication
- `POST /auth/signup` - Register new user
- `POST /auth/signin` - Login user
- `POST /auth/forgot-password` - Request password reset
- `POST /auth/reset-password` - Reset password with token

### Users
- `GET /users/me` - Get current authenticated user
- `GET /users/{userId}` - Get user by ID
- `PUT /users/{userId}` - Update user profile
- `DELETE /users/{userId}` - Delete user account

### Recipes
- `GET /recipes` - Get all recipes
- `GET /recipes/{id}` - Get recipe details
- `POST /recipes` - Create new recipe
- `PUT /recipes/{id}` - Update recipe
- `DELETE /recipes/{id}` - Delete recipe
- `GET /recipes/search?name=...` - Search recipes
- `GET /recipes/filter/calories?min=...&max=...` - Filter by calories

### Meal Plans
- `GET /meal-plans/user/{userId}` - Get user's meal plans
- `GET /meal-plans/{id}` - Get meal plan details
- `POST /meal-plans/user/{userId}` - Create meal plan
- `PUT /meal-plans/{id}` - Update meal plan
- `DELETE /meal-plans/{id}` - Delete meal plan
- `GET /meal-plans/user/{userId}/weekly` - Get weekly meal plans

### Grocery Lists
- `GET /grocery-lists/user/{userId}` - Get user's grocery lists
- `GET /grocery-lists/{id}` - Get grocery list details
- `POST /grocery-lists/user/{userId}` - Create grocery list
- `PUT /grocery-lists/{id}` - Update grocery list
- `DELETE /grocery-lists/{id}` - Delete grocery list
- `GET /grocery-lists/user/{userId}/status/{status}` - Filter by status

### Inventory
- `GET /inventory/user/{userId}` - Get user's inventory
- `GET /inventory/{id}` - Get inventory details
- `POST /inventory/user/{userId}` - Create inventory
- `PUT /inventory/{id}` - Update inventory
- `DELETE /inventory/{id}` - Delete inventory
- `GET /inventory/{inventoryId}/items` - Get inventory items
- `POST /inventory/{inventoryId}/items` - Add item to inventory
- `DELETE /inventory/items/{itemId}` - Remove item from inventory

### Ingredients
- `GET /ingredients` - Get all ingredients
- `GET /ingredients/{id}` - Get ingredient details
- `POST /ingredients` - Create ingredient
- `PUT /ingredients/{id}` - Update ingredient
- `DELETE /ingredients/{id}` - Delete ingredient
- `GET /ingredients/search?name=...` - Search ingredients
- `GET /ingredients/price/range?min=...&max=...` - Filter by price

### User Preferences
- `GET /user-preferences/{userId}` - Get user preferences
- `POST /user-preferences/{userId}` - Set user preferences

---

## Example API Usage

### 1. Sign Up
```bash
curl -X POST http://localhost:8081/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 2. Sign In
```bash
curl -X POST http://localhost:8081/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 3. Get Current User (Authenticated)
```bash
curl -X GET http://localhost:8081/api/users/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 4. Create Recipe
```bash
curl -X POST http://localhost:8081/api/recipes \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pasta Carbonara",
    "description": "Italian pasta dish",
    "calories": 450
  }'
```

### 5. Get All Recipes
```bash
curl -X GET http://localhost:8081/api/recipes
```

### 6. Create Meal Plan
```bash
curl -X POST http://localhost:8081/api/meal-plans/user/1 \
  -H "Content-Type: application/json" \
  -d '{
    "duration": 7,
    "status": "active"
  }'
```

---

## Project Structure

```
src/main/java/com/teamconfused/planmyplate/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── UserController.java
│   ├── RecipeController.java
│   ├── MealPlanController.java
│   ├── GroceryListController.java
│   ├── InventoryController.java
│   └── IngredientController.java
├── service/
│   ├── AuthService.java
│   ├── UserService.java
│   ├── RecipeService.java
│   ├── MealPlanService.java
│   ├── GroceryListService.java
│   ├── InventoryService.java
│   └── IngredientService.java
├── repository/
│   ├── UserRepository.java
│   ├── RecipeRepository.java
│   ├── MealPlanRepository.java
│   ├── GroceryListRepository.java
│   ├── InventoryRepository.java
│   └── IngredientRepository.java
├── entity/
│   ├── User.java
│   ├── Recipe.java
│   ├── MealPlan.java
│   ├── GroceryList.java
│   ├── Inventory.java
│   ├── InvItem.java
│   └── Ingredient.java
├── dto/
│   ├── AuthDto.java
│   ├── UserDto.java
│   ├── RecipeDto.java
│   ├── GroceryListDto.java
│   └── InventoryDto.java
├── security/
│   ├── JwtUtil.java
│   └── JwtAuthFilter.java
├── exception/
│   └── GlobalExceptionHandler.java
└── PlanMyPlateApplication.java

src/main/resources/
├── application.properties
└── templates/
```

---

## Error Handling

All API errors follow a consistent format:

```json
{
  "timestamp": "2026-01-28T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Detailed error message"
}
```

---

## Authentication Flow

1. **Register:** POST `/auth/signup` → Get JWT token
2. **Login:** POST `/auth/signin` → Get JWT token
3. **Use Token:** Add `Authorization: Bearer <token>` to headers
4. **Forgot Password:** POST `/auth/forgot-password` → Get reset code
5. **Reset:** POST `/auth/reset-password` → Set new password

---

## Database Schema

Main tables:
- `users` - User accounts
- `recipe` - Recipe information
- `meal_plan` - Meal plans for users
- `grocery_list` - Shopping lists
- `inventory` - User inventory
- `inv_item` - Items in inventory
- `ingredients` - Available ingredients
- `user_preferences` - User dietary preferences

---

## Troubleshooting

### Port Already in Use
```bash
# Change port in application.properties
server.port=8082
```

### Database Connection Failed
- Verify MySQL is running
- Check database credentials
- Ensure database `plan_my_plate` exists

### JWT Token Issues
- Ensure token is in `Authorization: Bearer <token>` format
- Check token expiration (24 hours by default)
- Regenerate token if expired

---

## Future Enhancements

- [ ] Email notifications for password reset
- [ ] Dietary recommendations based on preferences
- [ ] Nutritional analysis of meal plans
- [ ] Shopping list optimization
- [ ] Mobile app integration
- [ ] Integration with external recipe APIs
- [ ] Social features (sharing meal plans)
- [ ] Machine learning recommendations

---

## License

This project is part of the Team Confused initiative.

---

## Support

For API documentation, see `API_DOCUMENTATION.md`

For issues or questions, contact: support@planmyplate.com

---

**Last Updated:** January 28, 2026
**Version:** 1.0.0
