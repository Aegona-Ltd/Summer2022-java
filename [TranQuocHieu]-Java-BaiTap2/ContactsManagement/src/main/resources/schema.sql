create table if not exists customer (
	email varchar(500) PRIMARY KEY,
	password varchar(50)
);

create table if not exists contact (
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	datatime varchar(100),
	fullname varchar(255),
	email varchar(500),
	phone varchar(10),
	subject varchar(255),
	message varchar(500)
);



