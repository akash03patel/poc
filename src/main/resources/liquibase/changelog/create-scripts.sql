--liquibase formatted sql

--changeset script:1 runOnChange:false runAlways:false
CREATE TABLE cars (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name varchar(100) NULL,
	manufacture_name varchar(100) NULL,
	model varchar(100) NULL,
	manufacturing_year varchar(100) NULL,
	color varchar(100) NULL,
	CONSTRAINT cars_pk PRIMARY KEY (id)
);