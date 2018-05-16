
CREATE TABLE Users (
    id CHAR(10) UNIQUE,
    name VARCHAR(100),
    mail VARCHAR(50),
    username CHAR(16) UNIQUE,
    password CHAR(16),
    role INT
);

CREATE TABLE Citizens (
    id CHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    needs VARCHAR(200)
);

CREATE TABLE Cases (
    id CHAR(10) PRIMARY KEY,
    description VARCHAR(200),
    process VARCHAR(200)
);

CREATE TABLE Inquiries (
    id CHAR(10) PRIMARY KEY,
    description VARCHAR(200),
    isCitizenInformed Boolean,
    origin VARCHAR(200)
);

CREATE TABLE LogData (
    dateLogged VARCHAR(50),
    username CHAR(16),
    dataline VARCHAR(200),
    PRIMARY KEY(dateLogged, username)
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

