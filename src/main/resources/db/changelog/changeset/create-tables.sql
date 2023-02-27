--liquibase formatted sql
--changeset nikitalipatov:create-tables
--rollback drop table car, house, house_person, passport, person cascade

create sequence if not exists car_seq start with 1 increment by 1;
create sequence if not exists house_seq start with 1 increment by 1;
create sequence if not exists passport_seq start with 1 increment by 1;
create sequence if not exists person_seq start with 1 increment by 1;

create table car (
                    id integer not null,
                    color varchar(255),
                    gos_number varchar(255),
                    model varchar(255),
                    name varchar(255),
                    type varchar(255),
                    person_id integer not null,
                    primary key (id)
                 );

create table house (
                    id integer not null,
                    city varchar(255),
                    number varchar(255),
                    street varchar(255),
                    primary key (id)
                   );

create table house_person (
                    person_id integer not null,
                    house_id integer not null,
                    primary key (person_id, house_id)
                    );

create table passport (
                    id integer not null,
                    address varchar(255),
                    address_fact varchar(255),
                    date_of_birth timestamp(6),
                    issued timestamp(6),
                    issued_by varchar(255),
                    number integer not null,
                    place_of_birth varchar(255),
                    serial integer not null,
                    sex varchar(255),
                    primary key (id)
                      );

create table person (
                    id integer not null,
                    age integer not null,
                    full_name varchar(255),
                    passport_id integer,
                    primary key (id)
                    );

alter table if exists car
    add constraint person foreign key (person_id) references person;
alter table if exists house_person
    add constraint house foreign key (house_id) references house;
alter table if exists house_person
    add constraint person foreign key (person_id) references person;
alter table if exists person
    add constraint passport foreign key (passport_id) references passport