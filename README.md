This sample assumes Two MySql Databases with the following schema / datasets

Database One
============
create table PRODUCT(`id` integer, `name` varchar(255));
insert into PRODUCT(id, name) values (1, 'XBox')

Database Two
============
create table CUSTOMER(`id` integer, `name` varchar(255));
insert into CUSTOMER(id, name) values (1, 'Daphne Jefferson')
