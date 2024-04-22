CREATE TABLE users (
    Userid INTEGER auto_increment,
    Name   varchar(50),
    primary key (Userid)
);

CREATE table wishlist (
    Wishlistid Integer auto_increment,
    Userid     integer,
    Name       varchar(100),
    primary key (Wishlistid),
    foreign key (Userid) references users (Userid)
);

create table wish (
    Wishid      integer auto_increment,
    Name        varchar(100),
    Description varchar(500),
    Link        varchar(500),
    Price       integer,
    Wishlistid  integer,
    primary key (Wishid),
    foreign key (Wishlistid) references wishlist (Wishlistid)
);

INSERT INTO
    users(Name)
VALUES
    ('Marie'),
    ('Thea'),
    ('Usman'),
    ('Nikolaj');

INSERT INTO
    wishlist(Userid, Name)
VALUES
    (1, 'Julegaver'),
    (2, 'Fødselsdag'),
    (3, 'Fødselsdag'),
    (4, 'Julegaver');

INSERT INTO
    wish(Name, Description, Link, Price, Wishlistid)
VALUES
    ('Air Jordans', 'De vildeste kicks', 'www.Nike/airjoardans.com', 1500, 1),
    ('Kaffe', 'Det smager sygt lækkert', 'www.Thebestcoffeeintheworld.com', 500, 1),
    ('Macbook', 'Bærbar pc', 'www.apple.com', 8000, 2),
    ('Barista kursus', 'Kaffen bliver endnu vildere!', 'www.Verdensbedstebarista.com', 1000, 2),
    ('Dota+', 'Hvorfor skal man spille 100 timer?', 'www.Whyyoudothisdota.com', 100, 3),
    ('Bærbar', 'Man kan fixe alt med gaffa og en stjerneskruetrækker', 'wwww.nybærbar.com', 7000, 3),
    ('Monster', 'Et livs forbrug af monster', 'www.monster.com', 500000, 4),
    ('Snus', 'Man er vel lidt afhænging', 'www.snus.com', 2000, 3);