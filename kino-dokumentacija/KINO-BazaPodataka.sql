CREATE TABLE Users
(
  userId INT NOT NULL,
  username VARCHAR(100) NOT NULL,
  firstName VARCHAR(200) NOT NULL,
  lastName VARCHAR(200) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(200) NOT NULL,
  PRIMARY KEY (userId)
);

CREATE TABLE City
(
  cityId INT NOT NULL,
  cityName VARCHAR(200) NOT NULL,
  PRIMARY KEY (cityId)
);

CREATE TABLE Film
(
  filmId INT NOT NULL,
  filmName VARCHAR(500) NOT NULL,
  synopsis VARCHAR(5000) NOT NULL,
  filmPage VARCHAR(500) NOT NULL,
  originalTitle VARCHAR(500),
  genre VARCHAR(100) NOT NULL,
  nationality VARCHAR(100) NOT NULL,
  duration INT NOT NULL,
  releaseYear INT NOT NULL,
  distributor VARCHAR(200),
  director VARCHAR(200) NOT NULL,
  actors VARCHAR(5000) NOT NULL,
  classification VARCHAR(100) NOT NULL,
  otherData VARCHAR(5000),
  PRIMARY KEY (filmId)
);

CREATE TABLE Theatre
(
  theatreId INT NOT NULL,
  theatreName VARCHAR(500) NOT NULL,
  rowsCount INT NOT NULL,
  columnsCount INT NOT NULL,
  PRIMARY KEY (theatreId)
);

CREATE TABLE Reservation
(
  reservationId INT NOT NULL,
  userId INT NOT NULL,
  PRIMARY KEY (reservationId, userId),
  FOREIGN KEY (userId) REFERENCES Users(userId)
);

CREATE TABLE Comment
(
  commentId INT NOT NULL,
  commentText VARCHAR(5000) NOT NULL,
  rating INT NOT NULL,
  filmId INT NOT NULL,
  userId INT NOT NULL,
  PRIMARY KEY (commentId),
  FOREIGN KEY (filmId) REFERENCES Film(filmId),
  FOREIGN KEY (userId) REFERENCES Users(userId)
);

CREATE TABLE Session
(
  sessionId INT NOT NULL,
  dateProjection DATE NOT NULL,
  hourProjection TIME NOT NULL,
  filmId INT NOT NULL,
  theatreId INT NOT NULL,
  PRIMARY KEY (sessionId),
  FOREIGN KEY (filmId) REFERENCES Film(filmId),
  FOREIGN KEY (theatreId) REFERENCES Theatre(theatreId)
);

CREATE TABLE Cinema
(
  cinemaId INT NOT NULL,
  cinemaName VARCHAR(500) NOT NULL,
  cityId INT NOT NULL,
  PRIMARY KEY (cinemaId),
  FOREIGN KEY (cityId) REFERENCES City(cityId)
);

CREATE TABLE Ticket
(
  ticketId INT NOT NULL,
  rowNumber INT NOT NULL,
  columnNumber INT NOT NULL,
  ticketDate DATE NOT NULL,
  ticketTime TIME NOT NULL,
  theatreId INT NOT NULL,
  reservationId INT NOT NULL,
  userId INT NOT NULL,
  PRIMARY KEY (ticketId),
  FOREIGN KEY (theatreId) REFERENCES Theatre(theatreId),
  FOREIGN KEY (reservationId, userId) REFERENCES Reservation(reservationId, userId)
);

CREATE TABLE FilmInCinema
(
  cinemaId INT NOT NULL,
  filmId INT NOT NULL,
  PRIMARY KEY (cinemaId, filmId),
  FOREIGN KEY (cinemaId) REFERENCES Cinema(cinemaId),
  FOREIGN KEY (filmId) REFERENCES Film(filmId)
);

CREATE TABLE CinemaSession
(
  cinemaId INT NOT NULL,
  sessionId INT NOT NULL,
  PRIMARY KEY (cinemaId, sessionId),
  FOREIGN KEY (cinemaId) REFERENCES Cinema(cinemaId),
  FOREIGN KEY (sessionId) REFERENCES Session(sessionId)
)












