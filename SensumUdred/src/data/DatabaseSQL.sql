
CREATE TABLE Users (
    userId CHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    mail VARCHAR(50),
    username CHAR(16) UNIQUE,
    password CHAR(16),
    role INT
);

CREATE TABLE Citizens (
    citizenId CHAR(10) PRIMARY KEY,
    citizenName VARCHAR(100),
    needs VARCHAR(200)
);

CREATE TABLE Cases (
    caseId CHAR(13) PRIMARY KEY,
    caseDescription VARCHAR(200),
    process VARCHAR(200)
);

CREATE TABLE Inquiries (
    inquiryId CHAR(13) PRIMARY KEY,
    inquiryDescription VARCHAR(200),
    isCitizenInformed BOOLEAN,
    origin VARCHAR(200)
);

CREATE TABLE LogData (
    dateLogged VARCHAR(50),
    username CHAR(16),
    dataLine VARCHAR(200),
    PRIMARY KEY(dateLogged, username)
);

CREATE TABLE RegisteredBy (
    userId CHAR(10),
    citizenId CHAR(10),
    FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (citizenId) REFERENCES Citizens(citizenId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE HasCase (
    citizenId CHAR(10),
    caseId CHAR(13),
    FOREIGN KEY (citizenId) REFERENCES Citizens(citizenId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (caseId) REFERENCES Cases(caseId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE HasInquiry (
    citizenId CHAR(10),
    inquiryId CHAR(13),
    FOREIGN KEY (citizenId) REFERENCES Citizens(citizenId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (inquiryId) REFERENCES Inquiries(inquiryId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE CreatedBy (
    userId CHAR(10),
    caseId CHAR(13),
    FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (caseId) REFERENCES Cases(caseId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Users VALUES('0000000000', 'Admin', 'admin@sensum.dk', 'admin', 'super', 0);
INSERT INTO Users VALUES('1111111111', 'Social Worker', 'sw@sensum.dk', 'social', 'worker', 1);
INSERT INTO citizens VALUES('010250xxxx', 'Grethe Hansen', 'Terapi');

