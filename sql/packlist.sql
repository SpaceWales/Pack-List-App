begin transaction;

drop table if exists users cascade;
drop table if exists packlist cascade;
drop table if exists trip cascade;
drop table if exists item cascade;

create table users (
    user_id serial not null,
    username varchar,
    password_hash varchar,
    constraint pk_user_id primary key(user_id)
);

create table packlist (
    pack_id serial not null,
    user_id integer not null,
    constraint pk_packlist_id primary key (pack_id)
);

create table item (
    item_id serial not null,
    pack_id integer not null,
    item_name varchar, 
    item_weight decimal,
    weight_unit varchar,
    constraint pk_item_id primary key (item_id)
);

create table trip (
    trip_id serial not null,
    region varchar,
    date_start date,
    date_end date,
    constraint pk_trip_id primary key (trip_id)
);

create table user_trip (
    user_id integer not null,
    trip_id integer not null,
    constraint pk_user_trip_id primary key (user_id,trip_id)
);

alter table user_trip
add foreign key (user_id)
references users (user_id);

alter table user_trip 
add foreign key (trip_id)
references trip (trip_id);

alter table packlist 
add foreign key (user_id)
references users (user_id);

alter table item 
add foreign key (pack_id)
references packlist (pack_id);

commit; 