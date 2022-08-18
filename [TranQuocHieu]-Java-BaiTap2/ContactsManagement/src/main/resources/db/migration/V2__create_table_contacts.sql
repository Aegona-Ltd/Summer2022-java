DROP TABLE IF EXISTS contact;

CREATE TABLE contacts (
    id INT(2) NOT NULL auto_increment,
    datatime DATETIME,
    fullname varchar(500) NOT NULL,
    email varchar(500) NOT NULL,
    phone varchar(10) NOT NULL,
    subject varchar(500) NOT NULL,
    message varchar(500) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO contacts (datatime, fullname, email, phone, subject, message)
VALUES
('2022-08-01 00:00:00', 'ADMIN', 'admin@gmail.com', '0123456789', 'Test Contact us', 'Hello word'),
('2022-08-01 00:00:00', 'USER', 'user@gmail.com', '0123456789', 'INTERN JAVA', 'Hello my company'),
('2022-08-01 00:00:00', 'John Witch', 'john@gmail.com', '0123456789', 'Assassin', 'Hello Assasin'),
('2022-08-01 00:00:00', 'Monkey D Luffy', 'luffy@gmail.com', '0123456789', 'One Piece', 'Gomu Gomu Gomu Gomu Gomu Gomu');