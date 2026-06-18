
    set client_min_messages = WARNING;

    alter table if exists bicycles 
       drop constraint if exists fk_vehicle_braketype;

    alter table if exists bicycles 
       drop constraint if exists fk_vehicle_suspensiontype;

    alter table if exists bicycles 
       drop constraint if exists fk_vehicle_bicycle;

    alter table if exists cars 
       drop constraint if exists fk_vehicle_car;

    alter table if exists motorcycles 
       drop constraint if exists fk_vehicle_motorcycle;

    alter table if exists vehicles 
       drop constraint if exists fk_vehicle_category;

    alter table if exists vehicles 
       drop constraint if exists fk_vehicle_fueltype;

    alter table if exists vehicles 
       drop constraint if exists fk_vehicle_vehicletype;

    drop table if exists bicycles cascade;

    drop table if exists brake_types cascade;

    drop table if exists cars cascade;

    drop table if exists category cascade;

    drop table if exists fuel_types cascade;

    drop table if exists motorcycles cascade;

    drop table if exists suspension_types cascade;

    drop table if exists vehicle_types cascade;

    drop table if exists vehicles cascade;
