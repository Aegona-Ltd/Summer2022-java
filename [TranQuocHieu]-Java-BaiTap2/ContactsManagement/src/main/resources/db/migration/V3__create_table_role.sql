DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id INT auto_increment,
    name varchar(250) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO roles(name) VALUES
('USER'),
('ADMIN'),
('MANAGER');