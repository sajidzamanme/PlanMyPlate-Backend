-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 27, 2026 at 09:58 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `plan_my_plate`
--

-- --------------------------------------------------------

--
-- Table structure for table `allergies`
--

CREATE TABLE `allergies` (
  `allergy_id` int(11) NOT NULL,
  `allergy_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `allergies`
--

INSERT INTO `allergies` (`allergy_id`, `allergy_name`) VALUES
(3, 'Eggs'),
(6, 'Gluten'),
(2, 'Milk'),
(1, 'Peanuts'),
(4, 'Shellfish'),
(5, 'Soy'),
(7, 'Tree Nuts');

-- --------------------------------------------------------

--
-- Table structure for table `diets`
--

CREATE TABLE `diets` (
  `diet_id` int(11) NOT NULL,
  `diet_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `diets`
--

INSERT INTO `diets` (`diet_id`, `diet_name`) VALUES
(7, 'High Protein'),
(4, 'Keto'),
(6, 'Low Carb'),
(1, 'Omnivore'),
(5, 'Paleo'),
(3, 'Vegan'),
(2, 'Vegetarian');

-- --------------------------------------------------------

--
-- Table structure for table `grocery_list`
--

CREATE TABLE `grocery_list` (
  `list_id` int(11) NOT NULL,
  `date_created` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `grocery_list`
--

INSERT INTO `grocery_list` (`list_id`, `date_created`, `status`, `user_id`) VALUES
(1, '2026-01-10', 'PENDING', 1),
(2, '2026-01-11', 'PENDING', 2),
(3, '2026-01-12', 'COMPLETED', 3),
(4, '2026-01-13', 'PENDING', 4),
(5, '2026-01-14', 'COMPLETED', 5),
(6, '2026-01-15', 'PENDING', 6),
(7, '2026-01-16', 'PENDING', 7);

-- --------------------------------------------------------

--
-- Table structure for table `grocery_list_ingredients`
--

CREATE TABLE `grocery_list_ingredients` (
  `list_id` int(11) NOT NULL,
  `ing_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `budget` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `grocery_list_ingredients`
--

INSERT INTO `grocery_list_ingredients` (`list_id`, `ing_id`, `quantity`, `budget`) VALUES
(1, 2, 2, 10.00),
(2, 7, 1, 8.00),
(3, 3, 3, 6.00),
(4, 5, 2, 5.00),
(5, 1, 1, 6.00),
(6, 9, 2, 7.00),
(7, 6, 1, 5.00);

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `ing_id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`ing_id`, `name`, `price`) VALUES
(1, 'Chicken Breast', 5.50),
(2, 'Brown Rice', 2.20),
(3, 'Broccoli', 1.80),
(4, 'Milk', 1.50),
(5, 'Eggs', 3.00),
(6, 'Olive Oil', 4.50),
(7, 'Salmon Fillet', 7.90),
(8, 'Peanuts', 2.00),
(9, 'Tofu', 3.20);

-- --------------------------------------------------------

--
-- Table structure for table `ingredient_tags`
--

CREATE TABLE `ingredient_tags` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredient_tags`
--

INSERT INTO `ingredient_tags` (`tag_id`, `tag_name`) VALUES
(3, 'Carb'),
(4, 'Dairy'),
(5, 'Fat'),
(7, 'Plant-Based'),
(1, 'Protein'),
(6, 'Seafood'),
(2, 'Vegetable');

-- --------------------------------------------------------

--
-- Table structure for table `ingredient_tag_map`
--

CREATE TABLE `ingredient_tag_map` (
  `ing_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredient_tag_map`
--

INSERT INTO `ingredient_tag_map` (`ing_id`, `tag_id`) VALUES
(1, 1),
(2, 3),
(3, 2),
(4, 4),
(5, 1),
(6, 5),
(7, 6),
(8, 1),
(9, 7);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `inv_id` int(11) NOT NULL,
  `last_update` date DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`inv_id`, `last_update`, `user_id`) VALUES
(1, '2026-01-10', 1),
(2, '2026-01-10', 2),
(3, '2026-01-11', 3),
(4, '2026-01-11', 4),
(5, '2026-01-12', 5),
(6, '2026-01-12', 6),
(7, '2026-01-13', 7);

-- --------------------------------------------------------

--
-- Table structure for table `inv_item`
--

CREATE TABLE `inv_item` (
  `item_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `date_added` date DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `inv_id` int(11) NOT NULL,
  `ing_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inv_item`
--

INSERT INTO `inv_item` (`item_id`, `quantity`, `date_added`, `expiry_date`, `inv_id`, `ing_id`) VALUES
(1, 2, '2026-01-10', '2026-01-15', 1, 1),
(2, 1, '2026-01-10', '2026-01-18', 2, 7),
(3, 5, '2026-01-11', '2026-02-01', 3, 2),
(4, 3, '2026-01-11', '2026-01-20', 4, 5),
(5, 2, '2026-01-12', '2026-01-22', 5, 3),
(6, 4, '2026-01-12', '2026-02-05', 6, 9),
(7, 1, '2026-01-13', '2026-01-25', 7, 6);

-- --------------------------------------------------------

--
-- Table structure for table `meal_plan`
--

CREATE TABLE `meal_plan` (
  `mp_id` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meal_plan`
--

INSERT INTO `meal_plan` (`mp_id`, `start_date`, `duration`, `status`, `user_id`) VALUES
(1, '2026-01-01', 7, 'ACTIVE', 1),
(2, '2026-01-05', 5, 'ACTIVE', 2),
(3, '2026-01-10', 7, 'COMPLETED', 3),
(4, '2026-01-12', 3, 'ACTIVE', 4),
(5, '2026-01-15', 7, 'PLANNED', 5),
(6, '2026-01-18', 5, 'ACTIVE', 6),
(7, '2026-01-20', 7, 'PLANNED', 7);

-- --------------------------------------------------------

--
-- Table structure for table `meal_plan_recipe`
--

CREATE TABLE `meal_plan_recipe` (
  `mp_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meal_plan_recipe`
--

INSERT INTO `meal_plan_recipe` (`mp_id`, `recipe_id`) VALUES
(1, 1),
(1, 4),
(2, 3),
(3, 2),
(4, 5),
(5, 6),
(6, 7);

-- --------------------------------------------------------

--
-- Table structure for table `recipe`
--

CREATE TABLE `recipe` (
  `recipe_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` text DEFAULT NULL,
  `calories` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipe`
--

INSERT INTO `recipe` (`recipe_id`, `name`, `description`, `calories`) VALUES
(1, 'Grilled Chicken Bowl', 'Chicken with rice and vegetables', 550),
(2, 'Veggie Stir Fry', 'Mixed vegetables with tofu', 420),
(3, 'Salmon Salad', 'Fresh salmon with greens', 480),
(4, 'Egg Omelette', 'Egg omelette with olive oil', 350),
(5, 'Vegan Buddha Bowl', 'Rice, tofu, veggies', 500),
(6, 'Keto Chicken Plate', 'Low-carb chicken meal', 600),
(7, 'Peanut Tofu Curry', 'Spicy curry with tofu', 530);

-- --------------------------------------------------------

--
-- Table structure for table `recipe_ingredients`
--

CREATE TABLE `recipe_ingredients` (
  `recipe_id` int(11) NOT NULL,
  `ing_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipe_ingredients`
--

INSERT INTO `recipe_ingredients` (`recipe_id`, `ing_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 3),
(2, 9),
(3, 6),
(3, 7),
(4, 5),
(4, 6),
(5, 2),
(5, 9),
(6, 1),
(6, 6),
(7, 8),
(7, 9);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `budget` decimal(10,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `name`, `email`, `password`, `age`, `weight`, `budget`, `created_at`) VALUES
(1, 'sajid', 'Sajid Zaman', 'sajid@mail.com', 'hashed_pw_1', 24, 68.50, 120.00, '2026-01-27 20:54:54'),
(2, 'rahim', 'Rahim Uddin', 'rahim@mail.com', 'hashed_pw_2', 30, 75.00, 150.00, '2026-01-27 20:54:54'),
(3, 'karim', 'Karim Hasan', 'karim@mail.com', 'hashed_pw_3', 28, 70.00, 130.00, '2026-01-27 20:54:54'),
(4, 'anika', 'Anika Rahman', 'anika@mail.com', 'hashed_pw_4', 22, 55.00, 100.00, '2026-01-27 20:54:54'),
(5, 'tania', 'Tania Islam', 'tania@mail.com', 'hashed_pw_5', 26, 60.00, 110.00, '2026-01-27 20:54:54'),
(6, 'farhan', 'Farhan Ahmed', 'farhan@mail.com', 'hashed_pw_6', 35, 82.00, 180.00, '2026-01-27 20:54:54'),
(7, 'nabila', 'Nabila Noor', 'nabila@mail.com', 'hashed_pw_7', 29, 58.00, 140.00, '2026-01-27 20:54:54');

-- --------------------------------------------------------

--
-- Table structure for table `user_allergies`
--

CREATE TABLE `user_allergies` (
  `user_id` int(11) NOT NULL,
  `allergy_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_allergies`
--

INSERT INTO `user_allergies` (`user_id`, `allergy_id`) VALUES
(1, 6),
(2, 1),
(3, 2),
(4, 5),
(5, 3),
(6, 4),
(7, 7);

-- --------------------------------------------------------

--
-- Table structure for table `user_dislikes`
--

CREATE TABLE `user_dislikes` (
  `user_id` int(11) NOT NULL,
  `ing_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_dislikes`
--

INSERT INTO `user_dislikes` (`user_id`, `ing_id`) VALUES
(1, 8),
(2, 4),
(3, 9),
(4, 1),
(5, 7),
(6, 3),
(7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user_preferences`
--

CREATE TABLE `user_preferences` (
  `pref_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `diet_id` int(11) DEFAULT NULL,
  `servings` int(11) NOT NULL,
  `budget` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_preferences`
--

INSERT INTO `user_preferences` (`pref_id`, `user_id`, `diet_id`, `servings`, `budget`) VALUES
(1, 1, 7, 3, 120.00),
(2, 2, 1, 4, 150.00),
(3, 3, 6, 3, 130.00),
(4, 4, 2, 2, 100.00),
(5, 5, 3, 2, 110.00),
(6, 6, 4, 4, 180.00),
(7, 7, 5, 3, 140.00);

-- --------------------------------------------------------

--
-- Table structure for table `user_preferences_allergies`
--

CREATE TABLE `user_preferences_allergies` (
  `pref_id` int(11) NOT NULL,
  `allergy_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_preferences_allergies`
--

INSERT INTO `user_preferences_allergies` (`pref_id`, `allergy_id`) VALUES
(1, 6),
(2, 1),
(3, 2),
(4, 5),
(5, 3),
(6, 4),
(7, 7);

-- --------------------------------------------------------

--
-- Table structure for table `user_preferences_dislikes`
--

CREATE TABLE `user_preferences_dislikes` (
  `pref_id` int(11) NOT NULL,
  `ing_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_preferences_dislikes`
--

INSERT INTO `user_preferences_dislikes` (`pref_id`, `ing_id`) VALUES
(1, 8),
(2, 4),
(3, 9),
(4, 1),
(5, 7),
(6, 3),
(7, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `allergies`
--
ALTER TABLE `allergies`
  ADD PRIMARY KEY (`allergy_id`),
  ADD UNIQUE KEY `allergy_name` (`allergy_name`);

--
-- Indexes for table `diets`
--
ALTER TABLE `diets`
  ADD PRIMARY KEY (`diet_id`),
  ADD UNIQUE KEY `diet_name` (`diet_name`);

--
-- Indexes for table `grocery_list`
--
ALTER TABLE `grocery_list`
  ADD PRIMARY KEY (`list_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`ing_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `ingredient_tags`
--
ALTER TABLE `ingredient_tags`
  ADD PRIMARY KEY (`tag_id`),
  ADD UNIQUE KEY `tag_name` (`tag_name`);

--
-- Indexes for table `ingredient_tag_map`
--
ALTER TABLE `ingredient_tag_map`
  ADD PRIMARY KEY (`ing_id`,`tag_id`),
  ADD KEY `tag_id` (`tag_id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`inv_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `inv_item`
--
ALTER TABLE `inv_item`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `inv_id` (`inv_id`),
  ADD KEY `ing_id` (`ing_id`);

--
-- Indexes for table `meal_plan`
--
ALTER TABLE `meal_plan`
  ADD PRIMARY KEY (`mp_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `meal_plan_recipe`
--
ALTER TABLE `meal_plan_recipe`
  ADD PRIMARY KEY (`mp_id`,`recipe_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`recipe_id`);

--
-- Indexes for table `recipe_ingredients`
--
ALTER TABLE `recipe_ingredients`
  ADD PRIMARY KEY (`recipe_id`,`ing_id`),
  ADD KEY `ing_id` (`ing_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_allergies`
--
ALTER TABLE `user_allergies`
  ADD PRIMARY KEY (`user_id`,`allergy_id`),
  ADD KEY `allergy_id` (`allergy_id`);

--
-- Indexes for table `user_dislikes`
--
ALTER TABLE `user_dislikes`
  ADD PRIMARY KEY (`user_id`,`ing_id`),
  ADD KEY `ing_id` (`ing_id`);

--
-- Indexes for table `user_preferences`
--
ALTER TABLE `user_preferences`
  ADD PRIMARY KEY (`pref_id`),
  ADD UNIQUE KEY `user_id` (`user_id`),
  ADD KEY `diet_id` (`diet_id`);

--
-- Indexes for table `user_preferences_allergies`
--
ALTER TABLE `user_preferences_allergies`
  ADD PRIMARY KEY (`pref_id`,`allergy_id`),
  ADD KEY `allergy_id` (`allergy_id`);

--
-- Indexes for table `user_preferences_dislikes`
--
ALTER TABLE `user_preferences_dislikes`
  ADD PRIMARY KEY (`pref_id`,`ing_id`),
  ADD KEY `ing_id` (`ing_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `allergies`
--
ALTER TABLE `allergies`
  MODIFY `allergy_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `diets`
--
ALTER TABLE `diets`
  MODIFY `diet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `grocery_list`
--
ALTER TABLE `grocery_list`
  MODIFY `list_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `ing_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `ingredient_tags`
--
ALTER TABLE `ingredient_tags`
  MODIFY `tag_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `inv_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `inv_item`
--
ALTER TABLE `inv_item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `meal_plan`
--
ALTER TABLE `meal_plan`
  MODIFY `mp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user_preferences`
--
ALTER TABLE `user_preferences`
  MODIFY `pref_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
