CREATE TABLE contact (
    id INT(2) NOT NULL auto_increment,
    datatime DATETIME,
    fullname varchar(500) NOT NULL,
    email varchar(500) NOT NULL,
    phone varchar(10) NOT NULL,
    subject varchar(500) NOT NULL,
    message varchar(500) NOT NULL,
    PRIMARY KEY (id)
)