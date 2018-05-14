
CREATE TABLE Users (
    id CHAR(10) Unique,
    name VARCHAR(100),
    mail VARCHAR(50),
    username CHAR(16) Unique,
    password CHAR(16),
    role INT
);

CREATE TABLE Citizens (
    id CHAR(10) Primary Key,
    name VARCHAR(100),
    needs VARCHAR(200)
);

CREATE TABLE Cases (
    description VARCHAR
);




DROP TABLE Users

INSERT INTO Users VALUES('1234567890','Jens Jensen','jensen@gmail.com','jensen','jens','1');

