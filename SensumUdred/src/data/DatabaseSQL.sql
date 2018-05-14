
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
    id CHAR(10) Primary Key,
    description VARCHAR(200),
    process VARCHAR(200)
);

CREATE TABLE Inquiries (
    id CHAR(10) Primary Key,
    description VARCHAR(200),
    isCitizenInformed Boolean,
    origin VARCHAR(200)
);

CREATE TABLE RegisteredBy (
    userId CHAR(10),
    citizenId CHAR(10),
    FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (citizenId) REFERENCES Citizens(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE HasCase (
    citizenId CHAR(10),
    caseId CHAR(10),
    FOREIGN KEY (citizenId) REFERENCES Citizens(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (caseId) REFERENCES Cases(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE HasInquiry (
    citizenId CHAR(10),
    inquiryId CHAR(10),
    FOREIGN KEY (citizenId) REFERENCES Citizens(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (inquiryId) REFERENCES Inquiries(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE CreatedBy (
    userId CHAR(10),
    caseId CHAR(10),
    FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (caseId) REFERENCES Cases(id) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO Users VALUES('1234567890','Jens Jensen','jensen@gmail.com','jensen','jens','1');
INSERT INTO Users VALUES('1234567891','Monika Jensen','mon@gmail.com','monika','mon','0');
INSERT INTO Citizens VALUES('1111112222','Søren Sørensen','Shockterapi');
INSERT INTO Citizens VALUES('1111112223','Fred Frederiksen','Den fulde behandling');

