CREATE TABLE "criteria" (
    id      serial primary key,
    name        VARCHAR(36) NOT NULL
);

CREATE TABLE animal_type (
    id      serial primary key,
    name   VARCHAR(36) NOT NULL,
    breeds TEXT []
);

CREATE TABLE criteria_value (
    id serial primary key,
    value VARCHAR(36) NOT NULL,
    criteria_id serial not null,
    FOREIGN KEY(criteria_id)
    REFERENCES criteria(id)
);

CREATE TABLE f_animal_criteria_value (
    id serial primary key,
    criteria_val_Id serial not null,
    animal_Id serial not null,
    FOREIGN KEY(criteria_val_Id) REFERENCES criteria_value(id),
    FOREIGN KEY(animal_Id) REFERENCES animal_type(id)
);

create table organization(
    id serial primary key,
    name character varying not null,
    pf_id varchar(36) not null,
    address1 character varying,
    address2 character varying,
    city character varying,
    state character varying,
    postcode character varying,
    country character varying
);





