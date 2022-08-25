CREATE TABLE refreshtokens (
    id BIGINT(20) auto_increment,
    user_id int(10) NOT NULL,
    token varchar(250) NOT NULL,
    expiry_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES users(id)
)