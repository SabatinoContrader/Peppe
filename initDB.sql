drop database pcarpet;
create database pcarpet;
create table pcarpet.user (username varchar(50) not null, password varchar(50) not null, type varchar(15) not null, name varchar(25), surname varchar(25), birthdate date, birthplace varchar(25), address varchar(80), handicapped boolean, primary key (username));
create table pcarpet.car (id_car int not null, license_plate varchar(10) not null, name varchar(20) not null, size varchar(10) not null, username varchar(50) not null, primary key (id_car), foreign key (username) references user (username));
create table pcarpet.report (id_report int not null, type int not null, description varchar(30), username varchar(50) not null, primary key (id_report),  foreign key (username) references user (username));
create table pcarpet.slot (id_slot int not null, latitude double, longitude double, address varchar(50), price float, type varchar(10), username varchar(50) not null, primary key (id_slot), foreign key (username) references user (username));
create table pcarpet.carplace (id_carplace int not null, latitude double, longitude double, type boolean, id_slot int not null, primary key (id_carplace), foreign key (id_slot) references slot (id_slot));
create table pcarpet.stop(id_stop int not null, start timestamp, finish timestamp, id_car int not null, id_carplace int not null, primary key (id_stop), foreign key (id_car) references car (id_car), foreign key (id_carplace) references carplace (id_carplace));
create table pcarpet.payment(id_payment int not null, quantity float, username varchar(50) not null, id_stop int not null, primary key (id_payment), foreign key (username) references user (username), foreign key (id_stop) references stop (id_stop));
create table pcarpet.book(id_book int not null, username varchar(50) not null, id_carplace int not null, id_payment int not null, primary key (id_book), foreign key (username) references user (username), foreign key (id_carplace) references carplace (id_carplace), foreign key (id_payment) references payment (id_payment));

