drop database rentals;
create database rentals;
use rentals;


create table person
(
    firstname varchar(250) not null,
    lastname  varchar(250) not null

);
create index firstname_idx on person(firstname);
create index lastname_idx on person(lastname);

alter table person
    add primary key (firstname);

create table employee
(
    username  varchar(250) not null,
    password  varchar(250) not null,
    role      varchar(250) not null,
    firstName varchar(250) not null,
    lastName  varchar(250) not null,
    constraint employee_username_uindex unique (username),
    foreign key (firstName) references person(firstname),
    foreign key (lastName) references person(lastname)
);

alter table employee
    add primary key (username);




create table rentalhouse
(
    city           varchar(250) not null,
    country        varchar(250) not null,
    price          int          not null,
    housing_type   varchar(250) not null,
    bedrooms       int          not null,
    usable_surface int          not null,
    garden_surface int          not null,
    house_name     varchar(250) not null
);

alter table rentalhouse
    add primary key (house_name);

create table client
(
    firstName     varchar(250) not null,
    lastName      varchar(250) not null,
    email         varchar(250) not null,
    client_status varchar(250) not null,
    houseName     varchar(250) not null,
    city          varchar(250) not null,
    housingType   varchar(250) not null,
    price         int          not null,
    constraint client_email_uindex unique (email),
    foreign key (firstName) references person (firstname),
    foreign key (lastName) references person (lastname),
    foreign key (houseName) references rentalhouse (house_name)
);

alter table client
    add primary key (email);