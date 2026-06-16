
    set client_min_messages = WARNING;

    alter table if exists bicycles 
       drop constraint if exists fk_vehicle_bicycle;

    alter table if exists cars 
       drop constraint if exists fk_vehicle_car;

    alter table if exists motorcycles 
       drop constraint if exists fk_vehicle_motorcycle;

    drop table if exists bicycles cascade;

    drop table if exists cars cascade;

    drop table if exists motorcycles cascade;

    drop table if exists vehicles cascade;
