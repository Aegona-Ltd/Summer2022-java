DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT(10) auto_increment,
    email varchar(500) NOT NULL,
    password varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users (email, password)
VALUES
    ('abc@abc.com', '123'),
    ('admin@admin.com', '123'),
    ('user@user.com', '123');