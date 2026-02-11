# Repository Table Name Fixes - Summary

## Issue
Repository classes were using incorrect table names in native SQL queries that didn't match the actual database schema defined in `plan_my_plate.sql`.

## Database Schema (Correct Table Names)
Based on `/src/main/resources/database_sql/plan_my_plate.sql`:

- `allergies` (not `allergy`)
- `diets` (not `diet`)
- `ingredients` (not `ingredient`)
- `ingredient_tags` (not `ingredient_tag`)
- `grocery_list_ingredients` (not `grocery_list_item`)
- `recipe_ingredients` (not `recipe_ingredient`)

## Files Fixed

### 1. IngredientRepository.java
**Lines changed**: 17, 23, 29

| Before | After |
|--------|-------|
| `FROM ingredient WHERE` | `FROM ingredients WHERE` |

**Queries affected**:
- `findByNameContainingIgnoreCase()`
- `findByPriceBetween()`
- `findByName()`

### 2. GroceryListItemRepository.java
**Lines changed**: 14

| Before | After |
|--------|-------|
| `FROM grocery_list_item` | `FROM grocery_list_ingredients` |

**Queries affected**:
- `findAllItems()`

### 3. RecipeIngredientRepository.java
**Lines changed**: 14

| Before | After |
|--------|-------|
| `FROM recipe_ingredient` | `FROM recipe_ingredients` |

**Queries affected**:
- `findAllIngredients()`

### 4. AllergyRepository.java
**Lines changed**: 14

| Before | After |
|--------|-------|
| `FROM allergy WHERE` | `FROM allergies WHERE` |

**Queries affected**:
- `findByAllergyName()`

### 5. DietRepository.java
**Lines changed**: 14

| Before | After |
|--------|-------|
| `FROM diet WHERE` | `FROM diets WHERE` |

**Queries affected**:
- `findByDietName()`

### 6. IngredientTagRepository.java
**Lines changed**: 13

| Before | After |
|--------|-------|
| `FROM ingredient_tag WHERE` | `FROM ingredient_tags WHERE` |

**Queries affected**:
- `findByTagName()`

## Verification

✅ **Compilation**: `BUILD SUCCESSFUL in 3s`

All repository queries now correctly reference the actual database table names as defined in the schema.

## Impact

These fixes ensure that:
- All native SQL queries will work correctly with the database
- No "table not found" errors will occur
- The application can properly query reference data (diets, allergies, ingredient tags)
- Ingredient and recipe-ingredient queries function properly
