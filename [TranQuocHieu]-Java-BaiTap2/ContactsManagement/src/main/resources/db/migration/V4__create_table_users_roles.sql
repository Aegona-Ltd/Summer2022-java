DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(role_id) REFERENCES roles(id),
    PRIMARY KEY(user_id, role_id)
);

INSERT INTO users_roles(user_id, role_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(3, 1);