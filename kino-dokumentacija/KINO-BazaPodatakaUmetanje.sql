INSERT INTO Users (userId, username, firstName, lastName, password, email)
VALUES (1, 'filip', 'Filip', 'Celepirovic', 'filip', 'filip@gmail.com');

INSERT INTO Users (userId, username, firstName, lastName, password, email)
VALUES (2, 'admin', 'admin', 'admin', 'admin', 'admin@gmail.com');



INSERT INTO City (cityId, cityName) VALUES (1, 'Zagreb');
INSERT INTO City (cityId, cityName) VALUES (2, 'Split');



INSERT INTO Cinema (cinemaId, cinemaName, cityId) VALUES (1, 'Cineplex Zagreb', 1);
INSERT INTO Cinema (cinemaId, cinemaName, cityId) VALUES (2, 'Arena Cineplex Split', 2);



INSERT INTO Film (filmId, filmName, synopsis, filmPage, originalTitle, genre, nationality, duration, releaseYear, distributor, director, actors, classification, otherData) 
VALUES (1, 'Film 1', 'Ovo je sinopsis filma 1', 'link-do-stranice-filma-1', 'Originalni naslov filma 1', 'Akcija', 'Američki', 120, 2022, 'Distributer 1', 'Režiser 1', 'Glumci filma 1', 'PG-13', 'Dodatni podaci o filmu 1');

INSERT INTO Film (filmId, filmName, synopsis, filmPage, originalTitle, genre, nationality, duration, releaseYear, distributor, director, actors, classification, otherData) 
VALUES (2, 'Film 2', 'Ovo je sinopsis filma 2', 'link-do-stranice-filma-2', 'Originalni naslov filma 2', 'Akcija', 'Američki', 120, 2022, 'Distributer 2', 'Režiser 2', 'Glumci filma 2', 'PG-13', 'Dodatni podaci o filmu 2');


INSERT INTO Theatre (theatreId, theatreName, rowsCount, columnsCount) VALUES (1, 'Theatre 1', 10, 15);
INSERT INTO Theatre (theatreId, theatreName, rowsCount, columnsCount) VALUES (2, 'Theatre 2', 8, 12);


INSERT INTO Session (sessionId, dateProjection, hourProjection, filmId, theatreId) VALUES (1, '2024-01-07', '18:00:00', 1, 1);
INSERT INTO Session (sessionId, dateProjection, hourProjection, filmId, theatreId) VALUES (2, '2024-01-08', '20:00:00', 1, 2);





INSERT INTO Reservation (reservationId, userId) VALUES (1, 2);
INSERT INTO Reservation (reservationId, userId) VALUES (2, 1);


INSERT INTO Ticket (ticketId, rowNumber, columnNumber, ticketDate, ticketTime, reservationId, theatreId, userId) VALUES (1, 1, 1, '2024-01-07', '18:00:00', 1, 1, 2);
INSERT INTO Ticket (ticketId, rowNumber, columnNumber, ticketDate, ticketTime, reservationId, theatreId, userId) VALUES (2, 2, 2, '2024-01-08', '20:00:00', 2, 2, 1);



INSERT INTO Users (userId, username, firstName, lastName, password, email)
VALUES (3, 'ivan', 'Ivan', 'Orestijevic', 'ivan', 'ivan@gmail.com');

INSERT INTO City (cityId, cityName) VALUES
(3, 'Rijeka'),
(4, 'Osijek'),
(5, 'Dubrovnik'),
(6, 'Zadar'),
(7, 'Šibenik'),
(8, 'Varaždin'),
(9, 'Slavonski Brod'),
(10, 'Pula'),
(11, 'Karlovac'),
(12, 'Sisak');


INSERT INTO Cinema (cinemaId, cinemaName, cityId) VALUES
(3, 'CineStar Rijeka', 3),
(4, 'Cinema City Osijek', 4),
(5, 'Dubrovnik Cinemas', 5),
(6, 'Zadar Cineplex', 6),
(7, 'Šibenik Movie Arena', 7),
(8, 'Varaždin Film House', 8),
(9, 'Brod Motion Pictures', 9),
(10, 'Pula Cinema Complex', 10),
(11, 'Karlovac Film Hub', 11),
(12, 'Sisak Screens', 12);



INSERT INTO Theatre (theatreId, theatreName, rowsCount, columnsCount) VALUES
(3, 'Dvorana 3', 12, 10),
(4, 'Dvorana 4', 9, 14),
(5, 'Dvorana 5', 8, 8),
(6, 'Dvorana 6', 15, 15),
(7, 'Dvorana 7', 10, 20);



INSERT INTO Reservation (reservationId, userId) VALUES
(3, 3),
(4, 2),
(5, 2),
(6, 3),
(7, 1);



INSERT INTO Ticket (ticketId, rowNumber, columnNumber, ticketDate, ticketTime, reservationId, theatreId, userId) VALUES
(3, 3, 3, '2024-01-07', '18:00:00', 3, 3, 3),
(4, 4, 4, '2024-01-07', '18:00:00', 4, 4, 2),
(5, 5, 5, '2024-01-07', '18:00:00', 5, 5, 2),
(6, 6, 6, '2024-01-07', '18:00:00', 6, 6, 3),
(7, 7, 7, '2024-01-07', '18:00:00', 7, 7, 1);




INSERT INTO FilmInCinema (cinemaId, filmId) VALUES
(3, 1),
(4, 2),
(5, 1),
(6, 2),
(7, 2);




INSERT INTO CinemaSession (cinemaId, sessionId) VALUES
(3, 2),
(4, 1),
(5, 2),
(6, 2),
(7, 1);







INSERT INTO Users (userId, username, firstName, lastName, password, email) VALUES
(6, 'lara', 'Lara', 'Ivić', 'lpassword', 'lara@gmail.com'),
(7, 'marko', 'Marko', 'Jurić', 'mpassword', 'marko@gmail.com');


INSERT INTO City (cityId, cityName) VALUES
(15, 'Bjelovar'),
(16, 'Kutina');


INSERT INTO Cinema (cinemaId, cinemaName, cityId) VALUES
(15, 'Bjelovar Cinema Complex', 15),
(16, 'Kutina Movie Palace', 16);


INSERT INTO Film (filmId, filmName, synopsis, filmPage, originalTitle, genre, nationality, duration, releaseYear, distributor, director, actors, classification, otherData) VALUES
(5, 'The Silent Echo', 'A thriller that explores the depths of human psyche.', 'link-to-film-5', 'The Silent Echo Original', 'Thriller', 'Swedish', 105, 2023, 'Nordic Thrillers', 'Erik Svensson', 'Alicia Vikander, Bill Skarsgård', 'R', 'Award-winning soundtrack'),
(6, 'Galactic Wars: Return of the Darkness', 'The saga continues as heroes fight against the returning dark forces.', 'link-to-film-6', 'Galactic Wars Original', 'Sci-Fi', 'American', 150, 2023, 'Space Films', 'Lucy George', 'Chris Pine, Zoe Saldana', 'PG-13', 'Visual effects by Framestore');


INSERT INTO Theatre (theatreId, theatreName, rowsCount, columnsCount) VALUES
(10, 'The Grand Theatre', 12, 14),
(11, 'Mini Cineplex', 5, 8);


INSERT INTO Session (sessionId, dateProjection, hourProjection, filmId, theatreId) VALUES
(5, '2024-03-01', '18:00:00', 5, 10),
(6, '2024-03-02', '20:00:00', 6, 11);


INSERT INTO Reservation (reservationId, userId) VALUES
(10, 6),
(11, 7);


INSERT INTO Ticket (ticketId, rowNumber, columnNumber, ticketDate, ticketTime, reservationId, theatreId, userId) VALUES
(10, 1, 2, '2024-03-01', '18:00:00', 10, 10, 6),
(11, 2, 3, '2024-03-02', '20:00:00', 11, 11, 7);


INSERT INTO FilmInCinema (cinemaId, filmId) VALUES
(15, 5),
(16, 6);


INSERT INTO CinemaSession (cinemaId, sessionId) VALUES
(15, 5),
(16, 6);



INSERT INTO Users (userId, username, firstName, lastName, password, email) VALUES
(4, 'marta', 'Marta', 'Kovač', 'marta2024', 'marta@gmail.com'),
(5, 'petar', 'Petar', 'Novak', 'petarSecure', 'petar@gmail.com');
INSERT INTO City (cityId, cityName) VALUES
(13, 'Vinkovci'),
(14, 'Koprivnica');
INSERT INTO Cinema (cinemaId, cinemaName, cityId) VALUES
(13, 'Vinkovci V-Max', 13),
(14, 'Koprivnica CineDome', 14);
INSERT INTO Film (filmId, filmName, synopsis, filmPage, originalTitle, genre, nationality, duration, releaseYear, distributor, director, actors, classification, otherData) VALUES
(3, 'Mystery Island', 'An adventure awaits as explorers find an unknown island.', 'link-to-film-page-3', 'Mystery Island Original', 'Adventure', 'British', 140, 2023, 'Explorer Films', 'Jane Doe', 'Explorer Cast', 'PG', 'Shot on location in the Pacific'),
(4, 'Future Past', 'A time-travel paradox brings unforeseen consequences.', 'link-to-film-page-4', 'Future Past Original', 'Sci-Fi', 'Canadian', 130, 2023, 'Time Warp Studios', 'John Smith', 'Time Travelers', 'PG-13', 'Featuring a cameo by a famous scientist');

INSERT INTO Theatre (theatreId, theatreName, rowsCount, columnsCount) VALUES
(8, 'Auditorium 8', 9, 11),
(9, 'Hall 9', 10, 10);

INSERT INTO Session (sessionId, dateProjection, hourProjection, filmId, theatreId) VALUES
(3, '2024-02-15', '19:00:00', 3, 8),
(4, '2024-02-16', '20:30:00', 4, 9);
INSERT INTO Reservation (reservationId, userId) VALUES
(8, 4),
(9, 5);
INSERT INTO Ticket (ticketId, rowNumber, columnNumber, ticketDate, ticketTime, reservationId, theatreId, userId) VALUES
(8, 2, 3, '2024-02-15', '19:00:00', 8, 8, 4),
(9, 3, 4, '2024-02-16', '20:30:00', 9, 9, 5);

INSERT INTO FilmInCinema (cinemaId, filmId) VALUES
(13, 3),
(14, 4);
INSERT INTO CinemaSession (cinemaId, sessionId) VALUES
(13, 3),
(14, 4);

