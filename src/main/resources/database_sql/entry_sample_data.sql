-- Static Data Entry Script
-- Run this AFTER the schema is created but BEFORE creating users/meal plans if you want a clean slate for static data.
-- Or run it to populate a fresh database.

-- Clear existing static data (optional, be careful with FKs)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE recipe_ingredients;
TRUNCATE TABLE ingredient_tag_map;
TRUNCATE TABLE user_preferences_allergies;
TRUNCATE TABLE user_preferences_dislikes;
TRUNCATE TABLE user_allergies;
TRUNCATE TABLE user_dislikes;
TRUNCATE TABLE inv_item;
TRUNCATE TABLE grocery_list_ingredients;
TRUNCATE TABLE meal_slot; -- New table
TRUNCATE TABLE allergies;
TRUNCATE TABLE diets;
TRUNCATE TABLE ingredients;
TRUNCATE TABLE ingredient_tags;
TRUNCATE TABLE recipe;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Allergies
INSERT INTO `allergies` (`allergy_name`) VALUES
('Peanuts'), ('Milk'), ('Eggs'), ('Shellfish'), ('Soy'), ('Gluten'), ('Tree Nuts');

-- 2. Diets
INSERT INTO `diets` (`diet_name`) VALUES
('Omnivore'), ('Vegetarian'), ('Vegan'), ('Keto'), ('Paleo'), ('Low Carb'), ('High Protein');

-- 3. Ingredient Tags
INSERT INTO `ingredient_tags` (`tag_name`) VALUES
('Protein'), ('Vegetable'), ('Carb'), ('Dairy'), ('Fat'), ('Seafood'), ('Plant-Based');

-- 4. Ingredients
INSERT INTO `ingredients` (`name`, `price`) VALUES
('Chicken Breast', 5.50),
('Brown Rice', 2.20),
('Broccoli', 1.80),
('Milk', 1.50),
('Eggs', 3.00),
('Olive Oil', 4.50),
('Salmon Fillet', 7.90),
('Peanuts', 2.00),
('Tofu', 3.20),
('Beef Steak', 10.00),
('Spinach', 2.50),
('Pasta', 1.50),
('Tomato Sauce', 2.00),
('Cheese', 4.00),
('Bread', 2.50),
('Avocado', 1.50),
('Bacon', 5.00),
('Lettuce', 1.00),
('Onion', 0.80),
('Garlic', 0.50);

-- 5. Ingredient Tag Map (Sample mappings)
INSERT INTO `ingredient_tag_map` (`ing_id`, `tag_id`) VALUES
((SELECT ing_id FROM ingredients WHERE name='Chicken Breast'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Protein')),
((SELECT ing_id FROM ingredients WHERE name='Brown Rice'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Carb')),
((SELECT ing_id FROM ingredients WHERE name='Broccoli'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Vegetable')),
((SELECT ing_id FROM ingredients WHERE name='Milk'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Dairy')),
((SELECT ing_id FROM ingredients WHERE name='Eggs'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Protein')),
((SELECT ing_id FROM ingredients WHERE name='Salmon Fillet'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Seafood')),
((SELECT ing_id FROM ingredients WHERE name='Tofu'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Plant-Based')),
((SELECT ing_id FROM ingredients WHERE name='Beef Steak'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Protein')),
((SELECT ing_id FROM ingredients WHERE name='Spinach'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Vegetable')),
((SELECT ing_id FROM ingredients WHERE name='Pasta'), (SELECT tag_id FROM ingredient_tags WHERE tag_name='Carb'));


-- 6. Recipes
INSERT INTO `recipe` (`name`, `description`, `calories`) VALUES
('Grilled Chicken Bowl', 'Chicken with rice and vegetables', 550),
('Veggie Stir Fry', 'Mixed vegetables with tofu', 420),
('Salmon Salad', 'Fresh salmon with greens', 480),
('Egg Omelette', 'Egg omelette with olive oil', 350),
('Vegan Buddha Bowl', 'Rice, tofu, veggies', 500),
('Keto Chicken Plate', 'Low-carb chicken meal', 600),
('Peanut Tofu Curry', 'Spicy curry with tofu', 530),
('Steak and Eggs', 'Classic high protein breakfast', 700),
('Pasta Primavera', 'Pasta with fresh vegetables', 450),
('BLT Sandwich', 'Bacon, lettuce, tomato on bread', 500),
('Avocado Toast', 'Toast topped with avocado and egg', 400),
('Spinach Salad', 'Fresh spinach with cheese and nuts', 300);

-- 7. Recipe Ingredients (Linking recipes to ingredients)
-- Grilled Chicken Bowl
INSERT INTO `recipe_ingredients` (`recipe_id`, `ing_id`) VALUES
((SELECT recipe_id FROM recipe WHERE name='Grilled Chicken Bowl'), (SELECT ing_id FROM ingredients WHERE name='Chicken Breast')),
((SELECT recipe_id FROM recipe WHERE name='Grilled Chicken Bowl'), (SELECT ing_id FROM ingredients WHERE name='Brown Rice')),
((SELECT recipe_id FROM recipe WHERE name='Grilled Chicken Bowl'), (SELECT ing_id FROM ingredients WHERE name='Broccoli'));

-- Veggie Stir Fry
INSERT INTO `recipe_ingredients` (`recipe_id`, `ing_id`) VALUES
((SELECT recipe_id FROM recipe WHERE name='Veggie Stir Fry'), (SELECT ing_id FROM ingredients WHERE name='Broccoli')),
((SELECT recipe_id FROM recipe WHERE name='Veggie Stir Fry'), (SELECT ing_id FROM ingredients WHERE name='Tofu')),
((SELECT recipe_id FROM recipe WHERE name='Veggie Stir Fry'), (SELECT ing_id FROM ingredients WHERE name='Onion'));

-- Salmon Salad
INSERT INTO `recipe_ingredients` (`recipe_id`, `ing_id`) VALUES
((SELECT recipe_id FROM recipe WHERE name='Salmon Salad'), (SELECT ing_id FROM ingredients WHERE name='Salmon Fillet')),
((SELECT recipe_id FROM recipe WHERE name='Salmon Salad'), (SELECT ing_id FROM ingredients WHERE name='Spinach')),
((SELECT recipe_id FROM recipe WHERE name='Salmon Salad'), (SELECT ing_id FROM ingredients WHERE name='Olive Oil'));

-- Egg Omelette
INSERT INTO `recipe_ingredients` (`recipe_id`, `ing_id`) VALUES
((SELECT recipe_id FROM recipe WHERE name='Egg Omelette'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),
((SELECT recipe_id FROM recipe WHERE name='Egg Omelette'), (SELECT ing_id FROM ingredients WHERE name='Cheese')),
((SELECT recipe_id FROM recipe WHERE name='Egg Omelette'), (SELECT ing_id FROM ingredients WHERE name='Olive Oil'));

-- Steak and Eggs
INSERT INTO `recipe_ingredients` (`recipe_id`, `ing_id`) VALUES
((SELECT recipe_id FROM recipe WHERE name='Steak and Eggs'), (SELECT ing_id FROM ingredients WHERE name='Beef Steak')),
((SELECT recipe_id FROM recipe WHERE name='Steak and Eggs'), (SELECT ing_id FROM ingredients WHERE name='Eggs'));
