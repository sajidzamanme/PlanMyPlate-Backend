INSERT INTO allergies (allergy_name) VALUES
('Peanuts'),
('Milk'),
('Eggs'),
('Fish'),
('Shellfish'),
('Soy'),
('Gluten');

INSERT INTO diets (diet_name) VALUES
('Omnivore'),
('Vegetarian'),
('Vegan'),
('Pescatarian'),
('Low Carb'),
('High Protein'),
('Diabetic Friendly');

INSERT INTO ingredient_tags (tag_name) VALUES
('Protein'),
('Vegetable'),
('Carb'),
('Dairy'),
('Fat'),
('Seafood'),
('Spice'),
('Plant-Based');

INSERT INTO ingredients (name, price) VALUES
('Rice',1.50),
('Chicken',4.50),
('Beef',7.50),
('Mutton',9.00),
('Hilsa Fish',12.00),
('Rui Fish',6.50),
('Shrimp',8.00),
('Eggs',3.00),
('Milk',1.50),
('Yogurt',1.20),
('Lentils (Dal)',2.00),
('Potato',0.80),
('Onion',0.60),
('Garlic',0.50),
('Ginger',0.50),
('Green Chili',0.30),
('Tomato',0.70),
('Spinach',1.00),
('Cauliflower',1.20),
('Cabbage',1.10),
('Eggplant',1.30),
('Mustard Oil',2.50),
('Soybean Oil',2.00),
('Turmeric',0.20),
('Cumin',0.20),
('Coriander Powder',0.20),
('Red Chili Powder',0.20),
('Salt',0.10),
('Sugar',0.20),
('Bread',1.50),
('Puffed Rice (Muri)',0.80),
('Chickpeas',2.00),
('Peanuts',1.50),
('Tofu',2.50);

INSERT INTO recipe
(name, description, calories, prep_time, cook_time, servings, instructions, image_url)
VALUES
('Chicken Bhuna','Spicy dry chicken curry',520,15,35,3,
'Wash and clean chicken thoroughly. Heat oil in a pan and add sliced onions. Fry until golden brown. Add ginger-garlic paste and sauté until fragrant. Add turmeric, chili powder and salt. Mix well and add chicken pieces. Cook on medium heat until chicken releases water. Continue cooking on low heat, stirring frequently, until oil separates and chicken becomes dry and tender.',
'https://images.unsplash.com/photo-1604908177522'),

('Beef Bhuna','Slow cooked beef curry',650,20,90,4,
'Cut beef into medium pieces and wash well. Heat oil and fry onions until dark brown. Add ginger-garlic paste and spices, cook until oil separates. Add beef and mix thoroughly. Cover and cook on very low heat, stirring occasionally. Add small amounts of water if needed. Cook until beef is soft and gravy becomes thick.',
'https://images.unsplash.com/photo-1601050690597'),

('Mutton Rezala','Mughlai style mutton',720,25,80,4,
'Marinate mutton with yogurt, ginger-garlic paste and spices for 30 minutes. Heat oil and fry whole spices lightly. Add marinated mutton and cook on low heat. Stir gently and add water as needed. Cook until mutton becomes very soft and gravy turns creamy.',
'https://images.unsplash.com/photo-1600628422019'),

('Hilsa Bhapa','Steamed hilsa with mustard',600,15,25,3,
'Clean hilsa pieces carefully. Make mustard paste with green chili and salt. Coat fish with mustard paste and mustard oil. Place in a covered bowl and steam for 20–25 minutes until fish is fully cooked.',
'https://images.unsplash.com/photo-1589308078054'),

('Rui Machher Jhol','Light fish curry',450,15,30,4,
'Marinate fish with turmeric and salt. Lightly fry fish and set aside. In the same oil fry onion and spices. Add water to make thin gravy. Gently add fish pieces and simmer for 10 minutes.',
'https://images.unsplash.com/photo-1589647363585'),

('Chingri Malai Curry','Shrimp coconut curry',580,20,30,3,
'Clean shrimp and marinate lightly with salt. Fry onions in oil until soft. Add ginger paste and spices. Pour coconut milk and bring to gentle boil. Add shrimp and cook briefly until tender.',
'https://images.unsplash.com/photo-1628294895950'),

('Dal Tadka','Spiced lentil curry',320,10,25,4,
'Wash lentils and boil until soft. Heat oil in another pan and fry garlic, cumin and chili. Pour this tempering over the lentils. Mix well and simmer for a few minutes.',
'https://images.unsplash.com/photo-1613844237701'),

('Khichuri','Rice and lentils',500,15,40,4,
'Wash rice and lentils. Heat oil and fry spices lightly. Add rice, lentils and water. Cook covered on medium heat until soft and mushy.',
'https://images.unsplash.com/photo-1608032077018'),

('Egg Curry','Spicy egg curry',420,10,25,3,
'Boil eggs, peel and lightly fry them. Prepare onion-tomato gravy with spices. Add eggs and simmer until gravy thickens.',
'https://images.unsplash.com/photo-1589302168068'),

('Chicken Biryani','Traditional biryani',700,30,60,5,
'Marinate chicken with yogurt and spices. Parboil rice with whole spices. Layer rice and chicken in a pot. Seal tightly and cook on low heat (dum) until aroma develops.',
'https://images.unsplash.com/photo-1563379091339'),

('Tehari','Beef rice dish',680,25,70,4,
'Cook beef with spices until partially tender. Add washed rice and water. Cook together until rice is fully cooked and beef is soft.',
'https://images.unsplash.com/photo-1600628421055'),

('Vegetable Khichuri','Veg rice & dal',450,15,35,4,
'Cook rice and lentils with mixed vegetables, spices and water until soft.',
'https://images.unsplash.com/photo-1598515213692'),

('Begun Bhorta','Mashed eggplant',280,10,20,2,
'Roast eggplant over flame or oven. Remove skin and mash flesh. Mix with onion, green chili, salt and mustard oil.',
'https://images.unsplash.com/photo-1604908812284'),

('Alu Bhorta','Mashed potato',260,10,15,2,
'Boil potatoes, peel and mash. Mix with onion, chili, salt and oil.',
'https://images.unsplash.com/photo-1601050690927'),

('Shak Bhaji','Spinach fry',200,5,15,2,
'Wash spinach and chop. Fry garlic in oil, add spinach and cook until soft.',
'https://images.unsplash.com/photo-1598514982755'),

('Mixed Vegetable Curry','Vegetable curry',300,15,30,4,
'Cook mixed vegetables with spices and water until tender.',
'https://images.unsplash.com/photo-1589647363589'),

('Chickpea Curry','Chola dal',350,20,40,4,
'Soak chickpeas overnight. Boil until soft. Cook with onion, tomato and spices until thick.',
'https://images.unsplash.com/photo-1598511723816'),

('Chicken Korma','Mild chicken curry',540,20,45,4,
'Cook chicken with yogurt, onion paste and mild spices on low heat until creamy.',
'https://images.unsplash.com/photo-1600628421039'),

('Beef Curry','Traditional beef curry',620,20,80,5,
'Cook beef slowly with spices and water until meat is tender and gravy thickens.',
'https://images.unsplash.com/photo-1601050690604'),

('Fish Fry','Pan fried fish',400,10,15,3,
'Marinate fish with spices and shallow fry until golden.',
'https://images.unsplash.com/photo-1589302168054'),

('Chicken Jhal Fry','Spicy chicken fry',550,15,35,3,
'Fry chicken with onions, chilies and spices until dry and spicy.',
'https://images.unsplash.com/photo-1604908554024'),

('Egg Bhurji','Spicy scrambled eggs',380,5,15,2,
'Fry onion and chili, add beaten eggs and scramble until cooked.',
'https://images.unsplash.com/photo-1589302168042'),

('Vegetable Pulao','Rice with vegetables',430,15,30,4,
'Cook rice with vegetables and aromatic spices.',
'https://images.unsplash.com/photo-1598514982473'),

('Plain Rice and Dal','Daily staple meal',360,10,25,3,
'Serve steamed rice with simply cooked lentils.',
'https://images.unsplash.com/photo-1613844237892'),

('Muri Mix','Puffed rice snack',250,5,10,2,
'Mix puffed rice with onion, chili, oil and spices.',
'https://images.unsplash.com/photo-1600628422034'),

('Chicken Soup','Light chicken soup',300,15,30,3,
'Boil chicken with vegetables and seasoning. Strain if needed.',
'https://images.unsplash.com/photo-1604908812231'),

('Vegetable Soup','Healthy soup',220,15,25,3,
'Boil vegetables and blend lightly. Season and simmer.',
'https://images.unsplash.com/photo-1598515214143'),

('Chicken Cutlet','Fried chicken patty',480,20,20,3,
'Mix minced chicken with spices. Shape patties and shallow fry.',
'https://images.unsplash.com/photo-1601050692311'),

('Vegetable Cutlet','Veg patty',350,20,20,3,
'Mix mashed vegetables with spices, shape and fry.',
'https://images.unsplash.com/photo-1589302168002'),

('Shrimp Bhuna','Spicy shrimp curry',560,15,30,3,
'Cook shrimp with spices until thick and oil separates.',
'https://images.unsplash.com/photo-1628294895902'),

('Fish Curry with Potato','Fish potato curry',470,15,35,4,
'Cook fish and potatoes in light spiced gravy.',
'https://images.unsplash.com/photo-1589647363587'),

('Spinach Dal','Dal with spinach',330,10,25,3,
'Cook lentils and spinach together with spices.',
'https://images.unsplash.com/photo-1598514981983'),

('Eggplant Curry','Begun jhol',310,15,30,3,
'Cook eggplant pieces with spices and light gravy.',
'https://images.unsplash.com/photo-1604908812001'),

('Cabbage Fry','Stir fried cabbage',240,10,20,2,
'Stir fry cabbage with garlic and spices.',
'https://images.unsplash.com/photo-1598515214209'),

('Cauliflower Curry','Phulkopi jhol',290,15,30,3,
'Cook cauliflower with spices and water until soft.',
'https://images.unsplash.com/photo-1589647363601'),

('Chicken Fried Rice','Rice with chicken',620,20,25,3,
'Stir fry rice with chicken, egg and vegetables.',
'https://images.unsplash.com/photo-1600628421951'),

('Vegetable Fried Rice','Rice with veggies',520,15,25,3,
'Stir fry rice with vegetables and seasoning.',
'https://images.unsplash.com/photo-1598515214018'),

('Beef Fried Rice','Rice with beef',670,20,30,3,
'Stir fry rice with beef slices and vegetables.',
'https://images.unsplash.com/photo-1601050692442'),

('Egg Fried Rice','Rice with egg',590,10,20,2,
'Stir fry rice with scrambled eggs.',
'https://images.unsplash.com/photo-1589302168035'),

('Tofu Vegetable Curry','Plant based curry',420,15,30,3,
'Cook tofu and vegetables with spices.',
'https://images.unsplash.com/photo-1598511723784'),

('Vegan Dal Khichuri','Plant protein meal',480,15,40,4,
'Cook rice and lentils using oil only, no animal products.',
'https://images.unsplash.com/photo-1598515214111'),

('Low Carb Chicken Bowl','Chicken & veggies',400,15,25,2,
'Grill chicken and sauté vegetables lightly.',
'https://images.unsplash.com/photo-1604908812456'),

('High Protein Egg Bowl','Egg focused meal',430,10,20,2,
'Boil eggs and serve with vegetables.',
'https://images.unsplash.com/photo-1589302168021'),

('Diabetic Fish Curry','Low oil fish curry',390,15,30,3,
'Cook fish with very little oil and mild spices.',
'https://images.unsplash.com/photo-1589647363551'),

('Mustard Fish Curry','Fish in mustard',460,15,30,3,
'Cook fish in mustard seed paste gravy.',
'https://images.unsplash.com/photo-1589647363529'),

('Chicken Vegetable Stir Fry','Healthy stir fry',410,10,20,2,
'Stir fry chicken and vegetables on high heat.',
'https://images.unsplash.com/photo-1604908812354'),

('Beef Vegetable Stir Fry','Protein veg dish',520,15,25,3,
'Stir fry beef with vegetables and spices.',
'https://images.unsplash.com/photo-1601050692374'),

('Simple Omelette','Egg breakfast',300,5,10,1,
'Beat eggs with salt and cook omelette in a pan.',
'https://images.unsplash.com/photo-1589302168011');



INSERT INTO recipe_ingredients (recipe_id, ing_id) VALUES

-- Chicken Bhuna
((SELECT recipe_id FROM recipe WHERE name='Chicken Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Garlic')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Ginger')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Turmeric')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Beef Bhuna
((SELECT recipe_id FROM recipe WHERE name='Beef Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Beef')),
((SELECT recipe_id FROM recipe WHERE name='Beef Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Beef Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Garlic')),
((SELECT recipe_id FROM recipe WHERE name='Beef Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Mutton Rezala
((SELECT recipe_id FROM recipe WHERE name='Mutton Rezala'), (SELECT ing_id FROM ingredients WHERE name='Mutton')),
((SELECT recipe_id FROM recipe WHERE name='Mutton Rezala'), (SELECT ing_id FROM ingredients WHERE name='Yogurt')),
((SELECT recipe_id FROM recipe WHERE name='Mutton Rezala'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Mutton Rezala'), (SELECT ing_id FROM ingredients WHERE name='Garlic')),

-- Hilsa Bhapa
((SELECT recipe_id FROM recipe WHERE name='Hilsa Bhapa'), (SELECT ing_id FROM ingredients WHERE name='Hilsa Fish')),
((SELECT recipe_id FROM recipe WHERE name='Hilsa Bhapa'), (SELECT ing_id FROM ingredients WHERE name='Green Chili')),
((SELECT recipe_id FROM recipe WHERE name='Hilsa Bhapa'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Rui Machher Jhol
((SELECT recipe_id FROM recipe WHERE name='Rui Machher Jhol'), (SELECT ing_id FROM ingredients WHERE name='Rui Fish')),
((SELECT recipe_id FROM recipe WHERE name='Rui Machher Jhol'), (SELECT ing_id FROM ingredients WHERE name='Potato')),
((SELECT recipe_id FROM recipe WHERE name='Rui Machher Jhol'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Rui Machher Jhol'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Chingri Malai Curry
((SELECT recipe_id FROM recipe WHERE name='Chingri Malai Curry'), (SELECT ing_id FROM ingredients WHERE name='Shrimp')),
((SELECT recipe_id FROM recipe WHERE name='Chingri Malai Curry'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Chingri Malai Curry'), (SELECT ing_id FROM ingredients WHERE name='Milk')),

-- Dal Tadka
((SELECT recipe_id FROM recipe WHERE name='Dal Tadka'), (SELECT ing_id FROM ingredients WHERE name='Lentils (Dal)')),
((SELECT recipe_id FROM recipe WHERE name='Dal Tadka'), (SELECT ing_id FROM ingredients WHERE name='Garlic')),
((SELECT recipe_id FROM recipe WHERE name='Dal Tadka'), (SELECT ing_id FROM ingredients WHERE name='Cumin')),
((SELECT recipe_id FROM recipe WHERE name='Dal Tadka'), (SELECT ing_id FROM ingredients WHERE name='Soybean Oil')),

-- Khichuri
((SELECT recipe_id FROM recipe WHERE name='Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Lentils (Dal)')),
((SELECT recipe_id FROM recipe WHERE name='Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Soybean Oil')),

-- Egg Curry
((SELECT recipe_id FROM recipe WHERE name='Egg Curry'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),
((SELECT recipe_id FROM recipe WHERE name='Egg Curry'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Egg Curry'), (SELECT ing_id FROM ingredients WHERE name='Tomato')),

-- Chicken Biryani
((SELECT recipe_id FROM recipe WHERE name='Chicken Biryani'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Biryani'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Biryani'), (SELECT ing_id FROM ingredients WHERE name='Yogurt')),

-- Tehari
((SELECT recipe_id FROM recipe WHERE name='Tehari'), (SELECT ing_id FROM ingredients WHERE name='Beef')),
((SELECT recipe_id FROM recipe WHERE name='Tehari'), (SELECT ing_id FROM ingredients WHERE name='Rice')),

-- Vegetable Khichuri
((SELECT recipe_id FROM recipe WHERE name='Vegetable Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Lentils (Dal)')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Potato')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),

-- Begun Bhorta
((SELECT recipe_id FROM recipe WHERE name='Begun Bhorta'), (SELECT ing_id FROM ingredients WHERE name='Eggplant')),
((SELECT recipe_id FROM recipe WHERE name='Begun Bhorta'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Begun Bhorta'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Alu Bhorta
((SELECT recipe_id FROM recipe WHERE name='Alu Bhorta'), (SELECT ing_id FROM ingredients WHERE name='Potato')),
((SELECT recipe_id FROM recipe WHERE name='Alu Bhorta'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Alu Bhorta'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Shak Bhaji
((SELECT recipe_id FROM recipe WHERE name='Shak Bhaji'), (SELECT ing_id FROM ingredients WHERE name='Spinach')),
((SELECT recipe_id FROM recipe WHERE name='Shak Bhaji'), (SELECT ing_id FROM ingredients WHERE name='Garlic')),

-- Mixed Vegetable Curry
((SELECT recipe_id FROM recipe WHERE name='Mixed Vegetable Curry'), (SELECT ing_id FROM ingredients WHERE name='Potato')),
((SELECT recipe_id FROM recipe WHERE name='Mixed Vegetable Curry'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),
((SELECT recipe_id FROM recipe WHERE name='Mixed Vegetable Curry'), (SELECT ing_id FROM ingredients WHERE name='Cabbage')),

-- Chickpea Curry
((SELECT recipe_id FROM recipe WHERE name='Chickpea Curry'), (SELECT ing_id FROM ingredients WHERE name='Chickpeas')),
((SELECT recipe_id FROM recipe WHERE name='Chickpea Curry'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Chickpea Curry'), (SELECT ing_id FROM ingredients WHERE name='Tomato')),

-- Chicken Korma
((SELECT recipe_id FROM recipe WHERE name='Chicken Korma'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Korma'), (SELECT ing_id FROM ingredients WHERE name='Yogurt')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Korma'), (SELECT ing_id FROM ingredients WHERE name='Onion')),

-- Beef Curry
((SELECT recipe_id FROM recipe WHERE name='Beef Curry'), (SELECT ing_id FROM ingredients WHERE name='Beef')),
((SELECT recipe_id FROM recipe WHERE name='Beef Curry'), (SELECT ing_id FROM ingredients WHERE name='Onion')),

-- Fish Fry
((SELECT recipe_id FROM recipe WHERE name='Fish Fry'), (SELECT ing_id FROM ingredients WHERE name='Rui Fish')),
((SELECT recipe_id FROM recipe WHERE name='Fish Fry'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Chicken Jhal Fry
((SELECT recipe_id FROM recipe WHERE name='Chicken Jhal Fry'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Jhal Fry'), (SELECT ing_id FROM ingredients WHERE name='Green Chili')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Jhal Fry'), (SELECT ing_id FROM ingredients WHERE name='Onion')),

-- Egg Bhurji
((SELECT recipe_id FROM recipe WHERE name='Egg Bhurji'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),
((SELECT recipe_id FROM recipe WHERE name='Egg Bhurji'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Egg Bhurji'), (SELECT ing_id FROM ingredients WHERE name='Green Chili')),

-- Vegetable Pulao
((SELECT recipe_id FROM recipe WHERE name='Vegetable Pulao'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Pulao'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Pulao'), (SELECT ing_id FROM ingredients WHERE name='Carrot')),

-- Plain Rice and Dal
((SELECT recipe_id FROM recipe WHERE name='Plain Rice and Dal'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Plain Rice and Dal'), (SELECT ing_id FROM ingredients WHERE name='Lentils (Dal)')),

-- Muri Mix
((SELECT recipe_id FROM recipe WHERE name='Muri Mix'), (SELECT ing_id FROM ingredients WHERE name='Puffed Rice (Muri)')),
((SELECT recipe_id FROM recipe WHERE name='Muri Mix'), (SELECT ing_id FROM ingredients WHERE name='Onion')),
((SELECT recipe_id FROM recipe WHERE name='Muri Mix'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Chicken Soup
((SELECT recipe_id FROM recipe WHERE name='Chicken Soup'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Soup'), (SELECT ing_id FROM ingredients WHERE name='Garlic')),

-- Vegetable Soup
((SELECT recipe_id FROM recipe WHERE name='Vegetable Soup'), (SELECT ing_id FROM ingredients WHERE name='Carrot')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Soup'), (SELECT ing_id FROM ingredients WHERE name='Potato')),

-- Chicken Cutlet
((SELECT recipe_id FROM recipe WHERE name='Chicken Cutlet'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Cutlet'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),

-- Vegetable Cutlet
((SELECT recipe_id FROM recipe WHERE name='Vegetable Cutlet'), (SELECT ing_id FROM ingredients WHERE name='Potato')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Cutlet'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),

-- Shrimp Bhuna
((SELECT recipe_id FROM recipe WHERE name='Shrimp Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Shrimp')),
((SELECT recipe_id FROM recipe WHERE name='Shrimp Bhuna'), (SELECT ing_id FROM ingredients WHERE name='Onion')),

-- Fish Curry with Potato
((SELECT recipe_id FROM recipe WHERE name='Fish Curry with Potato'), (SELECT ing_id FROM ingredients WHERE name='Rui Fish')),
((SELECT recipe_id FROM recipe WHERE name='Fish Curry with Potato'), (SELECT ing_id FROM ingredients WHERE name='Potato')),

-- Spinach Dal
((SELECT recipe_id FROM recipe WHERE name='Spinach Dal'), (SELECT ing_id FROM ingredients WHERE name='Spinach')),
((SELECT recipe_id FROM recipe WHERE name='Spinach Dal'), (SELECT ing_id FROM ingredients WHERE name='Lentils (Dal)')),

-- Eggplant Curry
((SELECT recipe_id FROM recipe WHERE name='Eggplant Curry'), (SELECT ing_id FROM ingredients WHERE name='Eggplant')),
((SELECT recipe_id FROM recipe WHERE name='Eggplant Curry'), (SELECT ing_id FROM ingredients WHERE name='Potato')),

-- Cabbage Fry
((SELECT recipe_id FROM recipe WHERE name='Cabbage Fry'), (SELECT ing_id FROM ingredients WHERE name='Cabbage')),
((SELECT recipe_id FROM recipe WHERE name='Cabbage Fry'), (SELECT ing_id FROM ingredients WHERE name='Garlic')),

-- Cauliflower Curry
((SELECT recipe_id FROM recipe WHERE name='Cauliflower Curry'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),
((SELECT recipe_id FROM recipe WHERE name='Cauliflower Curry'), (SELECT ing_id FROM ingredients WHERE name='Potato')),

-- Chicken Fried Rice
((SELECT recipe_id FROM recipe WHERE name='Chicken Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),

-- Vegetable Fried Rice
((SELECT recipe_id FROM recipe WHERE name='Vegetable Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Vegetable Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),

-- Beef Fried Rice
((SELECT recipe_id FROM recipe WHERE name='Beef Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Beef')),
((SELECT recipe_id FROM recipe WHERE name='Beef Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Rice')),

-- Egg Fried Rice
((SELECT recipe_id FROM recipe WHERE name='Egg Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),
((SELECT recipe_id FROM recipe WHERE name='Egg Fried Rice'), (SELECT ing_id FROM ingredients WHERE name='Rice')),

-- Tofu Vegetable Curry
((SELECT recipe_id FROM recipe WHERE name='Tofu Vegetable Curry'), (SELECT ing_id FROM ingredients WHERE name='Tofu')),
((SELECT recipe_id FROM recipe WHERE name='Tofu Vegetable Curry'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),

-- Vegan Dal Khichuri
((SELECT recipe_id FROM recipe WHERE name='Vegan Dal Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Rice')),
((SELECT recipe_id FROM recipe WHERE name='Vegan Dal Khichuri'), (SELECT ing_id FROM ingredients WHERE name='Lentils (Dal)')),

-- Low Carb Chicken Bowl
((SELECT recipe_id FROM recipe WHERE name='Low Carb Chicken Bowl'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Low Carb Chicken Bowl'), (SELECT ing_id FROM ingredients WHERE name='Spinach')),

-- High Protein Egg Bowl
((SELECT recipe_id FROM recipe WHERE name='High Protein Egg Bowl'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),
((SELECT recipe_id FROM recipe WHERE name='High Protein Egg Bowl'), (SELECT ing_id FROM ingredients WHERE name='Spinach')),

-- Diabetic Fish Curry
((SELECT recipe_id FROM recipe WHERE name='Diabetic Fish Curry'), (SELECT ing_id FROM ingredients WHERE name='Rui Fish')),
((SELECT recipe_id FROM recipe WHERE name='Diabetic Fish Curry'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Mustard Fish Curry
((SELECT recipe_id FROM recipe WHERE name='Mustard Fish Curry'), (SELECT ing_id FROM ingredients WHERE name='Rui Fish')),
((SELECT recipe_id FROM recipe WHERE name='Mustard Fish Curry'), (SELECT ing_id FROM ingredients WHERE name='Mustard Oil')),

-- Chicken Vegetable Stir Fry
((SELECT recipe_id FROM recipe WHERE name='Chicken Vegetable Stir Fry'), (SELECT ing_id FROM ingredients WHERE name='Chicken')),
((SELECT recipe_id FROM recipe WHERE name='Chicken Vegetable Stir Fry'), (SELECT ing_id FROM ingredients WHERE name='Cauliflower')),

-- Beef Vegetable Stir Fry
((SELECT recipe_id FROM recipe WHERE name='Beef Vegetable Stir Fry'), (SELECT ing_id FROM ingredients WHERE name='Beef')),
((SELECT recipe_id FROM recipe WHERE name='Beef Vegetable Stir Fry'), (SELECT ing_id FROM ingredients WHERE name='Cabbage')),

-- Simple Omelette
((SELECT recipe_id FROM recipe WHERE name='Simple Omelette'), (SELECT ing_id FROM ingredients WHERE name='Eggs')),
((SELECT recipe_id FROM recipe WHERE name='Simple Omelette'), (SELECT ing_id FROM ingredients WHERE name='Milk'));


