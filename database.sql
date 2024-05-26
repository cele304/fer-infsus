-- Table for users
CREATE TABLE Users (
    user_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255),
    email VARCHAR(255)
);


-- Table for cities
CREATE TABLE City (
    city_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    city_name VARCHAR(255)
);


-- Table for cinemas
CREATE TABLE Cinema (
    cinema_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    cinema_name VARCHAR(255),
    city_id INT,
    FOREIGN KEY (city_id) REFERENCES City(city_id)
);

-- Table for movies
CREATE TABLE Film (
    film_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    cinema_id INT,
    film_name VARCHAR(255),
    synopsis VARCHAR(3000),
    film_page VARCHAR(255),
    original_title VARCHAR(255),
    genre VARCHAR(50),
    nationality VARCHAR(50),
    duration INT,
    release_year INT,
    distributor VARCHAR(255),
    director VARCHAR(255),
    actors VARCHAR(3000),
    classification VARCHAR(10),
    other_data VARCHAR(3000),
    FOREIGN KEY (cinema_id) REFERENCES Cinema(cinema_id)
);

-- Table for theaters
CREATE TABLE Sala (
    sala_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    cinema_id INT,
    theater_name VARCHAR(255),
    rows_count INT,
    columns_count INT,
    FOREIGN KEY (cinema_id) REFERENCES Cinema(cinema_id)
);


-Tabla para Sesiones
CREATE TABLE Sesiones (
    sesion_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    cinema_id INT,
    film_id INT,
    sala_id INT,
    fecha DATE,
    hora TIME,
    FOREIGN KEY (cinema_id) REFERENCES Cinema(cinema_id),
    FOREIGN KEY (film_id) REFERENCES Film(film_id),
    FOREIGN KEY (sala_id) REFERENCES Sala(sala_id)   
);


--Nueva tabla para tickets
-- Table for tickets
CREATE TABLE Ticket (
    ticket_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    sesion_id INT,
    row_num INT,
    column_number INT,
    FOREIGN KEY (sesion_id) REFERENCES Sesiones(sesion_id)
    
);



-- Table for reservations
CREATE TABLE Reservation (
    reservation_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id INT,
    ticket_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id)
);

-- Table for comments
CREATE TABLE Comment (
    comment_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    comment_text VARCHAR(30000),
    rating INT,
    user_id INT,
    film_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (film_id) REFERENCES Film(film_id)
);






-- Dodaj prvog korisnika
INSERT INTO Users (first_name, last_name, username, password, email)
VALUES ('Filip', 'Celepirovic', 'filip', 'filip', 'filip@gmail.com');

-- Dodaj drugog korisnika
INSERT INTO Users (first_name, last_name, username, password, email)
VALUES ('admin', 'admin', 'admin', 'admin', 'admin@gmail.com');





-- Ubaci gradove
INSERT INTO City (city_name) VALUES ('Zagreb');
INSERT INTO City (city_name) VALUES ('Split');



INSERT INTO Cinema (cinema_name, city_id) VALUES ('Cineplex Zagreb', 1);
INSERT INTO Cinema (cinema_name, city_id) VALUES ('Arena Cineplex Split', 2);


-- Ubaci filmove
INSERT INTO Film (cinema_id, film_name, synopsis, film_page, original_title, genre, nationality, duration, release_year, distributor, director, actors, classification, other_data) 
VALUES (1, 'Film 1', 'Ovo je sinopsis filma 1', 'link-do-stranice-filma-1', 'Originalni naslov filma 1', 'Akcija', 'Američki', 120, 2022, 'Distributer 1', 'Režiser 1', 'Glumci filma 1', 'PG-13', 'Dodatni podaci o filmu 1');


-- Ubaci sale
INSERT INTO Sala (cinema_id, theater_name, rows_count, columns_count) VALUES (1, 'Sala 1', 10, 15);
INSERT INTO Sala (cinema_id, theater_name, rows_count, columns_count) VALUES (2, 'Sala 2', 8, 12);
-- Dodajte koliko god sala želite

-- Ubaci sesije
INSERT INTO Sesiones (cinema_id, film_id, sala_id, fecha, hora) VALUES (1, 1, 1, '2024-01-07', '18:00:00');
INSERT INTO Sesiones (cinema_id, film_id, sala_id, fecha, hora) VALUES (2, 1, 2, '2024-01-08', '20:00:00');
-- Dodajte koliko god sesija želite

-- Ubaci karte
INSERT INTO Ticket (sesion_id, row_num, column_number) VALUES (1, 5, 8);
INSERT INTO Ticket (sesion_id, row_num, column_number) VALUES (2, 3, 6);
-- Dodajte koliko god karata želite

-- Ubaci rezervacije
INSERT INTO Reservation (user_id, ticket_id) VALUES (2, 1);
-- Dodajte koliko god rezervacija želite

