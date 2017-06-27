create database library;

create table books
(id integer primary key not null auto_increment,
library_id int(1) not null,
shelf_id int(2) not null,
isbn varchar(17) not null,
name varchar(255) not null,
author varchar(255) not null,
publisher varchar(255) not null,
kind int(1) not null,
status int(1) not null,
borrower int(11) not null,
borrowed_time TimeStamp not null,
returned_time TimeStamp not null,
reservation_number int(3) not null
due_date TimeStamp not null);

create table library.users(
id int AUTO_INCREMENT NOT NULL PRIMARY KEY UNIQUE,
card_number long NOT NULL,
name varchar(40) NOT NULL,
address varchar(100) NOT NULL,
tel long NOT NULL,
mail varchar(50) NOT NULL,
password varchar(255) NOT NULL,
is_admin int(1) NOT NULL,
register_date TIMESTAMP,
library_id int(1) NOT NULL,
borrow_books int default 0,
reservation_books int default 0);

create table library.libraries(
id int AUTO_INCREMENT NOT NULL PRIMARY KEY UNIQUE,
name varchar(20));

create table library.shelves(
id int,
name varchar(20));

create table library.kinds(
id int AUTO_INCREMENT NOT NULL PRIMARY KEY UNIQUE,
name varchar(20));

これはsql文微妙なので自分で打ってください
CREATE  TABLE `library`.`reservations` (
  `book_id` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  `reservation_type` INT  ,
  `reservation_number` AUTO_INCREMENT　PRIMARY KEY);
