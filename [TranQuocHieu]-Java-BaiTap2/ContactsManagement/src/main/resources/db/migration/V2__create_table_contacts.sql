DROP TABLE IF EXISTS contacts;
CREATE TABLE contacts (
    id INT(2) NOT NULL auto_increment,
    datetime DATETIME,
    fullname varchar(500) NOT NULL,
    email varchar(500) NOT NULL,
    phone varchar(10) NOT NULL,
    subject varchar(500) NOT NULL,
    message varchar(500) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO contacts (datetime, fullname, email, phone, subject, message)
VALUES
('2022-08-01 00:00:00', 'ADMIN', 'admin@gmail.com', '0123456789', 'Test Contact us', 'Hello word'),
('2022-08-01 00:00:00', 'USER', 'user@gmail.com', '0123456789', 'INTERN JAVA', 'Hello my company'),
('2022-08-01 00:00:00', 'John Witch', 'john@gmail.com', '0123456789', 'Assassin', 'Hello Assasin'),
('2022-08-01 00:00:00', 'Monkey D Luffy', 'luffy@gmail.com', '0123456789', 'One Piece', 'Gomu Gomu Gomu Gomu Gomu Gomu'),
('2022-08-01 00:00:00', 'Roronoa Zoro', 'zoro@gmail.com', '0123456789', 'Roronoa Zoro', 'In the story, Zoro is the first crewmate to join Monkey D. Luffy combatant,[4] and one of the two swordsmen of the Straw Hat Pirates, the other being Brook. He also has a habit of frequently going into the wrong locations, which is a running gag throughout the whole series.'),
('2022-08-01 00:00:00', 'Sanji', 'sanji@gmail.com', '0123456789', 'Black Leg', 'born as Vinsmoke Sanji,[26][27][28] is the cook of the Straw Hat Pirates and one of the Nine Senior Officers of the Straw Hat Grand Fleet.[22] He is the fifth member of the crew and the fourth to join, doing so at the end of the Baratie Arc.');