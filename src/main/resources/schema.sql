DROP SCHEMA IF EXISTS wishlist;
CREATE SCHEMA wishlist;

DROP TABLE if exists wish;
DROP TABLE if exists wishlist;
DROP TABLE if exists users;

CREATE table users
(
    Userid Integer NOT NULL auto_increment,
    Name   varchar(50),
    primary key (Userid)
);
CREATE table wishlist
(
    Wishlistid Integer NOT NULL auto_increment,
    Userid     integer,
    Name       varchar(100),
    primary key (Wishlistid),
    foreign key (Userid) references users (Userid)
);

create table wish
(
    Wishid      integer NOT NULL auto_increment,
    Name        varchar(100),
    Description varchar(500),
    Link        varchar(500),
    Price       integer,
    Wishlistid  integer,
    primary key (Wishid),
    foreign key (Wishlistid) references wishlist (Wishlistid)
);