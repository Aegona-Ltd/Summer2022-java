DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT(10) auto_increment,
    email varchar(500) NOT NULL,
    password varchar(250) NOT NULL,
    PRIMARY KEY (id)
);

-- password: 123
INSERT INTO users (email, password)
VALUES
    ('abc@abc.com', '$2a$10$JySzkZT89cT.ZJXXcY/y8e/9EoYyizOXgEiHyT9au6oh2BpFvzX9K'),
    ('admin@admin.com', '$2a$10$JySzkZT89cT.ZJXXcY/y8e/9EoYyizOXgEiHyT9au6oh2BpFvzX9K'),
    ('user@user.com', '$2a$10$JySzkZT89cT.ZJXXcY/y8e/9EoYyizOXgEiHyT9au6oh2BpFvzX9K');