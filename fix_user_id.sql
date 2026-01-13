-- Run this SQL in phpMyAdmin to fix the user_id auto increment issue

ALTER TABLE `users` MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;
