# PlanMyPlate Postman Collection - Quick Start Guide

## 📥 Import Instructions

1. **Open Postman**
2. Click **Import** button (top left)
3. Select **File** tab
4. Choose `PlanMyPlate_Postman_Collection.json`
5. Click **Import**

## 🚀 Getting Started

### Step 1: Configure Variables

The collection uses variables for easy testing:

- **`baseUrl`**: `http://localhost:8081/api` (default)
- **`token`**: Auto-populated after login
- **`userId`**: Auto-populated after login

To change the base URL:
1. Click on the collection name
2. Go to **Variables** tab
3. Update `baseUrl` if your server runs on a different port

### Step 2: Start Your Server

Make sure your PlanMyPlate backend is running:

```bash
cd /home/sajidzaman/Study/dbms/PlanMyPlate
./gradlew bootRun
```

Server should start on: `http://localhost:8081`

### Step 3: Test Authentication

1. **Sign Up** (First time users):
   - Navigate to: `1. Authentication > Sign Up`
   - Click **Send**
   - The `token` and `userId` variables will be auto-set ✨

2. **Sign In** (Existing users):
   - Navigate to: `1. Authentication > Sign In`
   - Update email/password in request body
   - Click **Send**
   - The `token` and `userId` variables will be auto-set ✨

## 📚 Collection Structure

The collection is organized into 10 categories:

### 1. Authentication
- ✅ Sign Up (auto-saves token)
- ✅ Sign In (auto-saves token)
- Forgot Password
- Reset Password

### 2. Users
- Get Current User
- Get User by ID
- Update User
- Delete User

### 3. User Preferences
- Get User Preferences
- Set/Update User Preferences

### 4. Recipes
- Get All Recipes
- Get Recipe by ID
- Search Recipes by Name
- Filter Recipes by Calories
- Create Recipe
- Update Recipe
- Delete Recipe

### 5. Ingredients
- Get All Ingredients
- Get Ingredient by ID
- Search Ingredients by Name
- Filter Ingredients by Price

### 6. Meal Plans
- Get Weekly Meal Plans
- Get All Meal Plans by User
- Get Meal Plan by ID
- Create Meal Plan (Simple)
- Create Meal Plan with Recipes (21 recipes for 7 days)
- Update Meal Plan
- Delete Meal Plan

### 7. Grocery Lists
- Get Grocery Lists by User
- Get Grocery List by ID
- Create Grocery List
- Update Grocery List Item
- Purchase Items (moves to inventory)
- Delete Grocery List

### 8. Inventory (Pantry)
- Get User Inventory
- Get Inventory Items
- Add Item to Inventory
- Update Inventory Item
- Remove Item from Inventory

### 9. Reference Data
- Get All Diets
- Get All Allergies
- Get All Dislikes (Ingredients)

### 10. File Uploads
- Upload Image (for recipes)

## 🔐 Authentication

Most endpoints require authentication. The collection automatically:
- Saves your JWT token after Sign Up/Sign In
- Includes the token in all authenticated requests
- Uses the format: `Authorization: Bearer {{token}}`

## 💡 Pro Tips

### Auto-Token Management
The Sign Up and Sign In requests have **test scripts** that automatically:
1. Extract the `token` from the response
2. Save it to the collection variable
3. Extract the `userId` and save it

This means you don't need to manually copy/paste tokens!

### Using Variables
You'll see `{{userId}}` and `{{token}}` in many requests. These are automatically replaced with your actual values.

To change the userId for testing:
1. Click on the collection
2. Go to **Variables** tab
3. Update the `userId` current value

### Testing Workflow

**Recommended testing flow:**

1. **Setup**:
   - Sign Up or Sign In
   - Get All Ingredients (to see available ingredient IDs)
   - Get All Recipes (to see available recipe IDs)

2. **User Preferences**:
   - Set User Preferences (diet, allergies, budget)

3. **Create Meal Plan**:
   - Create Meal Plan with Recipes (use 21 recipe IDs)
   - This auto-generates grocery list

4. **Grocery Shopping**:
   - Get Grocery Lists by User
   - Purchase Items (moves ingredients to inventory)

5. **Inventory Management**:
   - Get User Inventory
   - Update/Remove items as needed

### File Upload Example

For the **Upload Image** endpoint:
1. Select the request
2. Go to **Body** tab
3. Click on **Select Files** next to the `file` key
4. Choose an image file (JPG, PNG, etc.)
5. Send the request
6. Copy the returned URL to use in recipe creation

## 🧪 Sample Test Scenarios

### Scenario 1: New User Journey
```
1. Sign Up
2. Set User Preferences
3. Get All Recipes
4. Create Meal Plan with Recipes (use recipe IDs from step 3)
5. Get Grocery Lists by User
6. Purchase Items
7. Get User Inventory
```

### Scenario 2: Create Custom Recipe
```
1. Sign In
2. Get All Ingredients (note ingredient IDs)
3. Upload Image (optional)
4. Create Recipe (use ingredient IDs and image URL)
5. Get Recipe by ID (verify creation)
```

### Scenario 3: Manage Inventory
```
1. Sign In
2. Get User Inventory
3. Add Item to Inventory
4. Update Inventory Item (change quantity)
5. Remove Item from Inventory
```

## 🐛 Troubleshooting

### "Unauthorized" Error
- Make sure you've signed in first
- Check that the `token` variable is set (click collection > Variables)
- Token expires after 24 hours - sign in again

### "404 Not Found" Error
- Verify the server is running on port 8081
- Check the `baseUrl` variable is correct
- Ensure the resource ID exists (e.g., recipe ID, user ID)

### "400 Bad Request" Error
- Check the request body format matches the examples
- Ensure all required fields are provided
- Verify data types (numbers vs strings)

### Database Connection Issues
- Ensure MySQL is running on port 8050
- Check database `plan_my_plate` exists
- Verify credentials in `application.properties`

## 📝 Notes

- **Recipe IDs**: When creating meal plans, you need 21 recipe IDs (7 days × 3 meals)
- **Ingredient IDs**: Get these from the "Get All Ingredients" endpoint
- **Image URLs**: Can be external URLs or uploaded via the File Upload endpoint
- **Dates**: Use ISO format: `YYYY-MM-DD` (e.g., `2026-02-15`)

## 🔗 Related Files

- **API Documentation**: `API_DOCUMENTATION.md`
- **Database Schema**: `src/main/resources/database_sql/plan_my_plate.sql`
- **Sample Data**: `src/main/resources/database_sql/entry_sample_data.sql`

---

Happy Testing! 🎉
