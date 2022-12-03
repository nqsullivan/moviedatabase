CREATE SCHEMA MEDIA_DATABASE;

USE MEDIA_DATABASE;

CREATE TABLE MEDIA
(
    ReleaseDate DATE        NOT NULL,
    Title       VARCHAR(50) NOT NULL,
    Rating      INT         NOT NULL,
    CHECK (Rating >= 0 AND Rating <= 10),
    PRIMARY KEY (ReleaseDate, Title)
);

CREATE TABLE MOVIE
(
    GrossRevenue INT         NOT NULL,
    ReleaseType  VARCHAR(10) NOT NULL,
    ReleaseDate  DATE        NOT NULL,
    Title        VARCHAR(50) NOT NULL,
    CHECK (ReleaseType IN ('Theatrical', 'Streaming', 'DVD')),
    CHECK (GrossRevenue >= 0),
    PRIMARY KEY (ReleaseDate, Title),
    FOREIGN KEY (ReleaseDate, Title) REFERENCES MEDIA (ReleaseDate, Title)
);

CREATE TABLE TV_SHOW
(
    NumSeasons  INT         NOT NULL,
    NumEpisodes INT         NOT NULL,
    ReleaseDate DATE        NOT NULL,
    Title       VARCHAR(50) NOT NULL,
    CHECK (NumSeasons >= 0),
    CHECK (NumEpisodes >= 0),
    PRIMARY KEY (ReleaseDate, Title),
    FOREIGN KEY (ReleaseDate, Title) REFERENCES MEDIA (ReleaseDate, Title)
);

CREATE TABLE USER
(
    Email    VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    PRIMARY KEY (Email)
);

CREATE TABLE WATCH_LIST
(
    UserEmail   VARCHAR(50) NOT NULL,
    ReleaseDate DATE        NOT NULL,
    Title       VARCHAR(50) NOT NULL,
    PRIMARY KEY (UserEmail, ReleaseDate, Title),
    FOREIGN KEY (UserEmail) REFERENCES USER (Email),
    FOREIGN KEY (ReleaseDate, Title) REFERENCES MEDIA (ReleaseDate, Title)
);

CREATE TABLE GENRE
(
    ReleaseDate DATE        NOT NULL,
    Title       VARCHAR(50) NOT NULL,
    Genre       VARCHAR(50) NOT NULL,
    PRIMARY KEY (ReleaseDate, Title, Genre),
    FOREIGN KEY (ReleaseDate, Title) REFERENCES MEDIA (ReleaseDate, Title)
);

CREATE TABLE PLATFORM
(
    Name                     VARCHAR(50)   NOT NULL,
    SubscriptionPriceMonthly DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (Name)
);

CREATE TABLE OFFERS
(
    PlatformName VARCHAR(50) NOT NULL,
    ReleaseDate  DATE        NOT NULL,
    Title        VARCHAR(50) NOT NULL,
    PRIMARY KEY (PlatformName, ReleaseDate, Title),
    FOREIGN KEY (PlatformName) REFERENCES PLATFORM (Name),
    FOREIGN KEY (ReleaseDate, Title) REFERENCES MEDIA (ReleaseDate, Title)
);

INSERT INTO MEDIA (`ReleaseDate`, `Title`, `Rating`)
VALUES ('2019-1-1', 'Star Wars: The Rise of Skywalker', 6),
       ('2019-2-3', 'Jumanji: The Next Level', 6),
       ('2019-3-5', 'Frozen II', 7),
       ('2019-4-7', 'Knives Out', 8),
       ('2019-5-9', 'Ford v Ferrari', 8),
       ('2019-6-11', 'The Irishman', 8),
       ('2019-7-13', 'Jojo Rabbit', 8),
       ('2019-8-15', 'Little', 6),
       ('2019-9-17', 'Uncut Gems', 8),
       ('2019-10-19', 'Bombshell', 7),
       ('2019-11-21', 'Just Mercy', 7),
       ('2019-12-23', 'Queen & Slim', 7),
       ('2019-1-25', 'The Two Popes', 7),
       ('2019-2-27', 'Marriage Story', 8),
       ('2019-3-29', 'Dolemite Is My Name', 7),
       ('2019-4-2', 'Harriet', 7),
       ('2019-5-4', 'The Report', 7),
       ('2019-6-8', 'A Beautiful Day in the Neighborhood', 7),
       ('2019-7-10', 'The Lighthouse', 8),
       ('2019-8-12', 'The Farewell', 8);

INSERT INTO MOVIE (`GrossRevenue`, `ReleaseType`, `ReleaseDate`, `Title`)
VALUES ('1000000', 'Theatrical', '2019-1-1', 'Star Wars: The Rise of Skywalker'),
       ('2000000', 'Streaming', '2019-2-3', 'Jumanji: The Next Level'),
       ('3000000', 'DVD', '2019-3-5', 'Frozen II'),
       ('4000000', 'Theatrical', '2019-4-7', 'Knives Out'),
       ('5000000', 'Streaming', '2019-5-9', 'Ford v Ferrari'),
       ('6000000', 'DVD', '2019-6-11', 'The Irishman'),
       ('7000000', 'Theatrical', '2019-7-13', 'Jojo Rabbit'),
       ('8000000', 'Streaming', '2019-8-15', 'Little'),
       ('9000000', 'DVD', '2019-9-17', 'Uncut Gems'),
       ('10000000', 'Theatrical', '2019-10-19', 'Bombshell'),
       ('11000000', 'Streaming', '2019-11-21', 'Just Mercy');

INSERT INTO TV_SHOW (`ReleaseDate`, `Title`, `NumSeasons`, `NumEpisodes`)
VALUES ('2019-12-23', 'Queen & Slim', 5, 20),
       ('2019-1-25', 'The Two Popes', 3, 10),
       ('2019-2-27', 'Marriage Story', 2, 8),
       ('2019-3-29', 'Dolemite Is My Name', 1, 6),
       ('2019-4-2', 'Harriet', 1, 4),
       ('2019-5-4', 'The Report', 1, 2),
       ('2019-6-8', 'A Beautiful Day in the Neighborhood', 1, 8),
       ('2019-7-10', 'The Lighthouse', 1, 10),
       ('2019-8-12', 'The Farewell', 1, 100);

INSERT INTO GENRE (`ReleaseDate`, `Title`, `Genre`)
VALUES ('2019-1-1', 'Star Wars: The Rise of Skywalker', 'Sci-Fi'),
       ('2019-1-1', 'Star Wars: The Rise of Skywalker', 'Action'),
       ('2019-2-3', 'Jumanji: The Next Level', 'Action'),
       ('2019-2-3', 'Jumanji: The Next Level', 'Adventure'),
       ('2019-3-5', 'Frozen II', 'Animation'),
       ('2019-3-5', 'Frozen II', 'Family'),
       ('2019-4-7', 'Knives Out', 'Mystery'),
       ('2019-4-7', 'Knives Out', 'Thriller'),
       ('2019-5-9', 'Ford v Ferrari', 'Action'),
       ('2019-5-9', 'Ford v Ferrari', 'Drama'),
       ('2019-6-11', 'The Irishman', 'Crime'),
       ('2019-6-11', 'The Irishman', 'Drama'),
       ('2019-7-13', 'Jojo Rabbit', 'Comedy'),
       ('2019-7-13', 'Jojo Rabbit', 'Drama'),
       ('2019-8-15', 'Little', 'Comedy'),
       ('2019-8-15', 'Little', 'Fantasy'),
       ('2019-9-17', 'Uncut Gems', 'Crime'),
       ('2019-9-17', 'Uncut Gems', 'Drama'),
       ('2019-10-19', 'Bombshell', 'Drama'),
       ('2019-10-19', 'Bombshell', 'Thriller'),
       ('2019-11-21', 'Just Mercy', 'Drama'),
       ('2019-11-21', 'Just Mercy', 'Thriller'),
       ('2019-12-23', 'Queen & Slim', 'Crime'),
       ('2019-12-23', 'Queen & Slim', 'Drama'),
       ('2019-1-25', 'The Two Popes', 'Biography'),
       ('2019-1-25', 'The Two Popes', 'Drama'),
       ('2019-2-27', 'Marriage Story', 'Drama'),
       ('2019-2-27', 'Marriage Story', 'Romance'),
       ('2019-3-29', 'Dolemite Is My Name', 'Biography'),
       ('2019-3-29', 'Dolemite Is My Name', 'Comedy'),
       ('2019-4-2', 'Harriet', 'Biography'),
       ('2019-4-2', 'Harriet', 'Drama'),
       ('2019-5-4', 'The Report', 'Biography'),
       ('2019-5-4', 'The Report', 'Drama'),
       ('2019-6-8', 'A Beautiful Day in the Neighborhood', 'Biography'),
       ('2019-6-8', 'A Beautiful Day in the Neighborhood', 'Drama'),
       ('2019-7-10', 'The Lighthouse', 'Drama'),
       ('2019-7-10', 'The Lighthouse', 'Fantasy'),
       ('2019-8-12', 'The Farewell', 'Comedy'),
       ('2019-8-12', 'The Farewell', 'Drama');


INSERT INTO USER (`Email`, `Password`)
VALUES ('SampleEmail1@sample.com', 'SamplePassword1'),
       ('SampleEmail2@sample.com', 'SamplePassword2'),
       ('SampleEmail3@sample.com', 'SamplePassword3');

INSERT INTO WATCH_LIST (`UserEmail`, `ReleaseDate`, `Title`)
VALUES ('SampleEmail1@sample.com', '2019-1-1', 'Star Wars: The Rise of Skywalker'),
       ('SampleEmail1@sample.com', '2019-2-3', 'Jumanji: The Next Level'),
       ('SampleEmail1@sample.com', '2019-3-5', 'Frozen II'),
       ('SampleEmail1@sample.com', '2019-4-7', 'Knives Out'),
       ('SampleEmail2@sample.com', '2019-1-1', 'Star Wars: The Rise of Skywalker'),
       ('SampleEmail2@sample.com', '2019-2-3', 'Jumanji: The Next Level'),
       ('SampleEmail3@sample.com', '2019-1-1', 'Star Wars: The Rise of Skywalker'),
       ('SampleEmail3@sample.com', '2019-12-23', 'Queen & Slim'),
       ('SampleEmail3@sample.com', '2019-1-25', 'The Two Popes'),
       ('SampleEmail3@sample.com', '2019-2-27', 'Marriage Story'),
       ('SampleEmail3@sample.com', '2019-3-29', 'Dolemite Is My Name'),
       ('SampleEmail3@sample.com', '2019-4-2', 'Harriet');

INSERT INTO PLATFORM (`Name`, `SubscriptionPriceMonthly`)
VALUES ('Netflix', 10.00),
       ('Hulu', 5.99),
       ('Disney+', 7.89),
       ('Amazon Video', 8.50),
       ('Peacock', 4.99);

INSERT INTO OFFERS (`ReleaseDate`, `Title`, `PlatformName`)
VALUES ('2019-1-1', 'Star Wars: The Rise of Skywalker', 'Netflix'),
       ('2019-1-1', 'Star Wars: The Rise of Skywalker', 'Amazon Video'),
       ('2019-1-1', 'Star Wars: The Rise of Skywalker', 'Peacock'),
       ('2019-2-3', 'Jumanji: The Next Level', 'Netflix'),
       ('2019-2-3', 'Jumanji: The Next Level', 'Amazon Video'),
       ('2019-2-3', 'Jumanji: The Next Level', 'Disney+'),
       ('2019-3-5', 'Frozen II', 'Netflix'),
       ('2019-3-5', 'Frozen II', 'Amazon Video'),
       ('2019-3-5', 'Frozen II', 'Peacock'),
       ('2019-3-5', 'Frozen II', 'Hulu'),
       ('2019-4-7', 'Knives Out', 'Netflix'),
       ('2019-4-7', 'Knives Out', 'Peacock'),
       ('2019-4-7', 'Knives Out', 'Disney+'),
       ('2019-5-9', 'Ford v Ferrari', 'Netflix'),
       ('2019-5-9', 'Ford v Ferrari', 'Peacock'),
       ('2019-5-9', 'Ford v Ferrari', 'Hulu'),
       ('2019-6-11', 'The Irishman', 'Netflix'),
       ('2019-6-11', 'The Irishman', 'Amazon Video'),
       ('2019-6-11', 'The Irishman', 'Hulu'),
       ('2019-7-13', 'Jojo Rabbit', 'Netflix'),
       ('2019-7-13', 'Jojo Rabbit', 'Amazon Video'),
       ('2019-7-13', 'Jojo Rabbit', 'Peacock'),
       ('2019-8-15', 'Little', 'Netflix'),
       ('2019-8-15', 'Little', 'Hulu'),
       ('2019-9-17', 'Uncut Gems', 'Netflix'),
       ('2019-9-17', 'Uncut Gems', 'Amazon Video'),
       ('2019-9-17', 'Uncut Gems', 'Peacock'),
       ('2019-10-19', 'Bombshell', 'Netflix'),
       ('2019-10-19', 'Bombshell', 'Amazon Video'),
       ('2019-10-19', 'Bombshell', 'Hulu'),
       ('2019-11-21', 'Just Mercy', 'Netflix'),
       ('2019-11-21', 'Just Mercy', 'Peacock'),
       ('2019-11-21', 'Just Mercy', 'Hulu'),
       ('2019-12-23', 'Queen & Slim', 'Netflix'),
       ('2019-12-23', 'Queen & Slim', 'Amazon Video'),
       ('2019-12-23', 'Queen & Slim', 'Peacock'),
       ('2019-12-23', 'Queen & Slim', 'Hulu'),
       ('2019-1-25', 'The Two Popes', 'Netflix'),
       ('2019-1-25', 'The Two Popes', 'Peacock'),
       ('2019-1-25', 'The Two Popes', 'Hulu'),
       ('2019-2-27', 'Marriage Story', 'Netflix'),
       ('2019-2-27', 'Marriage Story', 'Hulu'),
       ('2019-3-29', 'Dolemite Is My Name', 'Netflix'),
       ('2019-3-29', 'Dolemite Is My Name', 'Amazon Video'),
       ('2019-4-2', 'Harriet', 'Netflix'),
       ('2019-5-4', 'The Report', 'Netflix'),
       ('2019-5-4', 'The Report', 'Peacock'),
       ('2019-5-4', 'The Report', 'Hulu'),
       ('2019-6-8', 'A Beautiful Day in the Neighborhood', 'Netflix'),
       ('2019-6-8', 'A Beautiful Day in the Neighborhood', 'Amazon Video'),
       ('2019-6-8', 'A Beautiful Day in the Neighborhood', 'Peacock'),
       ('2019-7-10', 'The Lighthouse', 'Netflix'),
       ('2019-7-10', 'The Lighthouse', 'Peacock'),
       ('2019-7-10', 'The Lighthouse', 'Hulu'),
       ('2019-8-12', 'The Farewell', 'Netflix'),
       ('2019-8-12', 'The Farewell', 'Peacock');
