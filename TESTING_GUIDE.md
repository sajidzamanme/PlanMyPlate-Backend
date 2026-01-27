# PlanMyPlate - API Testing Guide

This guide provides step-by-step instructions to test all API endpoints.

## Prerequisites

- Application running on `http://localhost:8081`
- Use Postman, cURL, or any REST client

## Test Scenarios

### 1. Authentication Flow

#### 1.1 Sign Up New User
```
POST http://localhost:8081/api/auth/signup
Content-Type: application/json

{
  "name": "Alice Johnson",
  "email": "alice@example.com",
  "password": "Alice@123"
}
```

**Expected Response (201/200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "email": "alice@example.com",
  "name": "Alice Johnson",
  "userId": 1
}
```

#### 1.2 Sign In
```
POST http://localhost:8081/api/auth/signin
Content-Type: application/json

{
  "email": "alice@example.com",
  "password": "Alice@123"
}
```

**Save the token from response for further authenticated requests.**

#### 1.3 Forgot Password
```
POST http://localhost:8081/api/auth/forgot-password
Content-Type: application/json

{
  "email": "alice@example.com"
}
```

**Response:**
```json
{
  "message": "Password reset token sent to email. Token: 7234"
}
```

**Note the reset token returned.**

#### 1.4 Reset Password
```
POST http://localhost:8081/api/auth/reset-password
Content-Type: application/json

{
  "resetToken": "7234",
  "newPassword": "NewPassword@123"
}
```

**Expected Response:**
```json
{
  "message": "Password reset successfully"
}
```

---

### 2. User Management

#### 2.1 Get Current User (Authenticated)
```
GET http://localhost:8081/api/users/me
Authorization: Bearer <YOUR_JWT_TOKEN>
```

**Expected Response:**
```json
{
  "userId": 1,
  "email": "alice@example.com",
  "name": "Alice Johnson",
  "userName": null
}
```

#### 2.2 Get User by ID
```
GET http://localhost:8081/api/users/1
```

#### 2.3 Update User Profile
```
PUT http://localhost:8081/api/users/1
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{
  "name": "Alice Smith",
  "userName": "alicesmith",
  "age": 28,
  "weight": 65.5,
  "budget": 5000.00
}
```

#### 2.4 Delete User
```
DELETE http://localhost:8081/api/users/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

---

### 3. Recipe Management

#### 3.1 Create Recipe
```
POST http://localhost:8081/api/recipes
Content-Type: application/json

{
  "name": "Vegetable Stir Fry",
  "description": "Healthy vegetable stir fry with garlic and ginger",
  "calories": 320
}
```

**Save the recipe ID for further tests.**

#### 3.2 Get All Recipes
```
GET http://localhost:8081/api/recipes
```

#### 3.3 Get Recipe by ID
```
GET http://localhost:8081/api/recipes/1
```

#### 3.4 Update Recipe
```
PUT http://localhost:8081/api/recipes/1
Content-Type: application/json

{
  "name": "Spicy Vegetable Stir Fry",
  "description": "Spicy healthy vegetable stir fry",
  "calories": 350
}
```

#### 3.5 Search Recipes by Name
```
GET http://localhost:8081/api/recipes/search?name=stir
```

#### 3.6 Filter Recipes by Calories
```
GET http://localhost:8081/api/recipes/filter/calories?minCalories=300&maxCalories=500
```

#### 3.7 Delete Recipe
```
DELETE http://localhost:8081/api/recipes/1
```

---

### 4. Ingredient Management

#### 4.1 Create Ingredient
```
POST http://localhost:8081/api/ingredients
Content-Type: application/json

{
  "name": "Tomato",
  "price": 30.00
}
```

#### 4.2 Get All Ingredients
```
GET http://localhost:8081/api/ingredients
```

#### 4.3 Get Ingredient by ID
```
GET http://localhost:8081/api/ingredients/1
```

#### 4.4 Update Ingredient
```
PUT http://localhost:8081/api/ingredients/1
Content-Type: application/json

{
  "name": "Fresh Tomato",
  "price": 35.00
}
```

#### 4.5 Search Ingredients
```
GET http://localhost:8081/api/ingredients/search?name=tomato
```

#### 4.6 Filter Ingredients by Price
```
GET http://localhost:8081/api/ingredients/price/range?minPrice=20&maxPrice=50
```

#### 4.7 Delete Ingredient
```
DELETE http://localhost:8081/api/ingredients/1
```

---

### 5. Meal Plan Management

#### 5.1 Create Meal Plan
```
POST http://localhost:8081/api/meal-plans/user/1
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{
  "duration": 7,
  "status": "active"
}
```

**Save the meal plan ID.**

#### 5.2 Get All User's Meal Plans
```
GET http://localhost:8081/api/meal-plans/user/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 5.3 Get Meal Plan by ID
```
GET http://localhost:8081/api/meal-plans/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 5.4 Update Meal Plan
```
PUT http://localhost:8081/api/meal-plans/1
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{
  "duration": 14,
  "status": "active"
}
```

#### 5.5 Get Weekly Meal Plans
```
GET http://localhost:8081/api/meal-plans/user/1/weekly
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 5.6 Get Meal Plans by Status
```
GET http://localhost:8081/api/meal-plans/user/1/status/active
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 5.7 Delete Meal Plan
```
DELETE http://localhost:8081/api/meal-plans/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

---

### 6. Grocery List Management

#### 6.1 Create Grocery List
```
POST http://localhost:8081/api/grocery-lists/user/1
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{
  "status": "active"
}
```

**Save the grocery list ID.**

#### 6.2 Get All User's Grocery Lists
```
GET http://localhost:8081/api/grocery-lists/user/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 6.3 Get Grocery List by ID
```
GET http://localhost:8081/api/grocery-lists/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 6.4 Update Grocery List
```
PUT http://localhost:8081/api/grocery-lists/1
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{
  "status": "completed"
}
```

#### 6.5 Get Grocery Lists by Status
```
GET http://localhost:8081/api/grocery-lists/user/1/status/active
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 6.6 Delete Grocery List
```
DELETE http://localhost:8081/api/grocery-lists/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

---

### 7. Inventory Management

#### 7.1 Create Inventory for User
```
POST http://localhost:8081/api/inventory/user/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

**Save the inventory ID.**

#### 7.2 Get User's Inventory
```
GET http://localhost:8081/api/inventory/user/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 7.3 Get Inventory by ID
```
GET http://localhost:8081/api/inventory/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 7.4 Add Item to Inventory
```
POST http://localhost:8081/api/inventory/1/items
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{
  "quantity": 5,
  "expiryDate": "2026-02-28",
  "ingredient": {
    "ingId": 1
  }
}
```

**Save the item ID.**

#### 7.5 Get All Inventory Items
```
GET http://localhost:8081/api/inventory/1/items
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 7.6 Remove Item from Inventory
```
DELETE http://localhost:8081/api/inventory/items/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

#### 7.7 Update Inventory
```
PUT http://localhost:8081/api/inventory/1
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{}
```

#### 7.8 Delete Inventory
```
DELETE http://localhost:8081/api/inventory/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

---

### 8. User Preferences

#### 8.1 Set User Preferences
```
POST http://localhost:8081/api/user-preferences/1
Content-Type: application/json
Authorization: Bearer <YOUR_JWT_TOKEN>

{
  "diet": "Vegetarian",
  "allergies": ["peanuts", "shellfish"],
  "dislikes": ["mushrooms"],
  "servings": 2,
  "budget": 500.00
}
```

#### 8.2 Get User Preferences
```
GET http://localhost:8081/api/user-preferences/1
Authorization: Bearer <YOUR_JWT_TOKEN>
```

---

## Test Data Setup Script

Use this sequence to set up test data:

1. Sign up a new user (Test 1.1)
2. Create 3 recipes (Test 3.1)
3. Create 5 ingredients (Test 4.1)
4. Create user preferences (Test 8.1)
5. Create meal plan (Test 5.1)
6. Create grocery list (Test 6.1)
7. Create inventory (Test 7.1)
8. Add items to inventory (Test 7.4)

---

## Error Testing

### Test Invalid Email on Signup
```
POST http://localhost:8081/api/auth/signup
Content-Type: application/json

{
  "name": "Test User",
  "email": "invalid-email",
  "password": "password123"
}
```

**Expected:** 400 Bad Request

### Test Duplicate Email Signup
```
POST http://localhost:8081/api/auth/signup
Content-Type: application/json

{
  "name": "Another User",
  "email": "alice@example.com",
  "password": "password123"
}
```

**Expected:** 400 Bad Request - "Email already exists"

### Test Invalid Credentials on Signin
```
POST http://localhost:8081/api/auth/signin
Content-Type: application/json

{
  "email": "alice@example.com",
  "password": "wrongpassword"
}
```

**Expected:** 400 Bad Request - "Invalid credentials"

### Test Resource Not Found
```
GET http://localhost:8081/api/recipes/99999
```

**Expected:** 400 Bad Request - "Recipe not found"

---

## Performance Testing

### Test High Volume Recipe Creation
```bash
for i in {1..100}; do
  curl -X POST http://localhost:8081/api/recipes \
    -H "Content-Type: application/json" \
    -d "{
      \"name\": \"Recipe $i\",
      \"description\": \"Test recipe $i\",
      \"calories\": $((200 + i))
    }"
done
```

---

## Postman Collection

Import this as a Postman collection for easier testing:

[Download Postman Collection](./postman_collection.json)

Or create manually in Postman:
1. Create a new collection "PlanMyPlate"
2. Add each endpoint as documented above
3. Set up authentication using the JWT token
4. Use pre-request scripts to handle token management

---

## Common Issues & Solutions

### 401 Unauthorized
- **Cause:** Missing or invalid JWT token
- **Solution:** Ensure token is included in Authorization header as `Bearer <token>`

### 404 Not Found
- **Cause:** Resource doesn't exist
- **Solution:** Verify the resource ID exists

### 400 Bad Request
- **Cause:** Invalid request body
- **Solution:** Check JSON format and required fields

### 500 Internal Server Error
- **Cause:** Server error
- **Solution:** Check application logs

---

## Testing Checklist

- [ ] Authentication (signup, signin, forgot password, reset)
- [ ] User management (CRUD operations)
- [ ] Recipe management (CRUD + search + filter)
- [ ] Ingredient management (CRUD + search + filter)
- [ ] Meal plan management (CRUD + weekly + status filter)
- [ ] Grocery list management (CRUD + status filter)
- [ ] Inventory management (CRUD + items management)
- [ ] User preferences (set and get)
- [ ] Error handling (invalid inputs)
- [ ] Authentication enforcement (protected endpoints)

---

**Last Updated:** January 28, 2026
**Version:** 1.0.0
