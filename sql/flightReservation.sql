create database reservation

use reservation

create table user (
id int not null auto_increment,
first_name varchar(20),
last_name varchar(20),
email varchar(20),
password varchar(256), 
primary key (id),
unique key (email)
)

create table flight(
  id int  not null auto_increment,
  flight_number varchar(20)  not null, 
  operating_airlines varchar(20)  not null,
  departure_city varchar(20)  not null,
  arrival_city varchar(20)  not null,
  date_of_departure date  not null,
  estimated_departure_time timestamp default current_timestamp,  
  primary key (id)
)

create table passenger(
  id         int not null auto_increment,
  first_name       varchar(256),
  last_name    varchar(256),
  middle_name   varchar(256),
  email varchar(50),
  phone varchar(10),
  primary key (id)
)

create table reservation(
  id int not null auto_increment,
  checked_in tinyint(1),
  number_of_bags int,
  passenger_id int,
  flight_id int,
  created timestamp default current_timestamp,
  primary key (id),
  foreign key (passenger_id) references passenger(id) on delete cascade,
  foreign key (flight_id) references flight(id)
)

select * from user

select * from passenger 

select * from flight

select * from reservation

drop table user
drop table reservation
drop table passenger
drop table flight

drop database reservation