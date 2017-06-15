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
reservation_number int(3) not null);

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
borrow_books int,
reserved_book1 long,
reserved_book2 long,
reserved_book3 long,
reserved_book4 long,
reserved_book5 long,
reserved_book6 long,
reserved_book7 long,
reserved_book8 long,
reserved_book9 long,
reserved_book10 long,
reserved_book11 long,
reserved_book12 long,
reserved_book13 long,
reserved_book14 long,
reserved_book15 long,
reserved_book16 long,
reserved_book17 long,
reserved_book18 long,
reserved_book19 long,
reserved_book20 long,
reserved_time1 TIMESTAMP,
reserved_time2 TIMESTAMP,
reserved_time3 TIMESTAMP,
reserved_time4 TIMESTAMP,
reserved_time5 TIMESTAMP,
reserved_time6 TIMESTAMP,
reserved_time7 TIMESTAMP,
reserved_time8 TIMESTAMP,
reserved_time9 TIMESTAMP,
reserved_time10 TIMESTAMP,
reserved_time11 TIMESTAMP,
reserved_time12 TIMESTAMP,
reserved_time13 TIMESTAMP,
reserved_time14 TIMESTAMP,
reserved_time15 TIMESTAMP,
reserved_time16 TIMESTAMP,
reserved_time17 TIMESTAMP,
reserved_time18 TIMESTAMP,
reserved_time19 TIMESTAMP,
reserved_time20 TIMESTAMP);

create table library.libraries(
id int AUTO_INCREMENT NOT NULL PRIMARY KEY UNIQUE,
name varchar(20));

create table library.shelves(
id int,
name varchar(20));

create table library.kinds(
id int AUTO_INCREMENT NOT NULL PRIMARY KEY UNIQUE,
name varchar(20));
