CREATE TABLE users (
  `email` VARCHAR(255) PRIMARY KEY,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  `enabled` BOOLEAN
);

CREATE TABLE user_roles (
  `user_email` VARCHAR(255) PRIMARY KEY,
  `role` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `user_authorities_ix` (`user_email`, `role`),
  CONSTRAINT `user_email_fk` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`)
);


/* password: admin */
INSERT INTO users (email, password, first_name, last_name, enabled) VALUES ('admin@admin.com', '$2a$10$begNn3Ty71z4ctIMUmdlEOCD...AT1J2bX9bPWw1cdSBNCaeiFrI.', 'Max', 'Morin', true);
INSERT INTO user_roles(user_email, role) VALUES ('admin@admin.com', 'SUPER_USER');