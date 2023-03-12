CREATE DATABASE IF NOT EXISTS tinyurldb;
CREATE DATABASE classicmodels;
SHOW DATABASES;
USE tinyurldb;


CREATE TABLE uri_store (
    id  BIGINT,  
    short_url  VARCHAR(7)  NOT NULL,
    source_url  VARCHAR(2048)  NOT NULL,
    custom_url VARCHAR(50) ,
    expiry BOOLEAN DEFAULT FALSE,
    user_id   VARCHAR(50),
    creation_date Date,
    expiration_date Date,
    count mediumint(9) unsigned,
    PRIMARY KEY (id) 
	-- INDEX (shorturl)
    -- INDEX (longurl)
    -- INDEX (shorturl, customer)
    );
