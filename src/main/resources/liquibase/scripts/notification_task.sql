-- liquibase formatted sql

-- changeset Nik:1

create table cat
(
    id         bigserial not null primary key,
    name       varchar(255),
    age        integer
        constraint check_age check (age > 0),
    is_healthy boolean
);