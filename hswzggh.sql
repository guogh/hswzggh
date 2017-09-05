
drop database if exists hswzggh;
create database hswzggh;
use hswzggh;

drop table if exists HswzMovie;
create table HswzMovie(
 id int(10) not null primary key auto_increment,
 name varchar(80) not null,
 path varchar(100) not null,
 netUrl varchar(100) not null
 );
 
 drop table if exists UserInfo;
 create table UserInfo(
 id int(10) not null primary key auto_increment,
 name varchar(40) not null,
 password varchar(40) not null
 );