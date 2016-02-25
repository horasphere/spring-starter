CREATE TABLE users (
  `id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255),
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  `enabled` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`email`)
);

CREATE TABLE user_roles (
  `user_id` VARCHAR(255) NOT NULL,
  `role` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `user_authorities_ix` (`user_id`, `role`),
  CONSTRAINT `user_email_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);


/* password: admin */
INSERT INTO users (id, email, password, first_name, last_name, enabled) VALUES ('1', 'admin@admin.com', '$2a$10$begNn3Ty71z4ctIMUmdlEOCD...AT1J2bX9bPWw1cdSBNCaeiFrI.', 'Max', 'Morin', true);
INSERT INTO user_roles(user_id, role) VALUES ('1', 'SUPER_USER');