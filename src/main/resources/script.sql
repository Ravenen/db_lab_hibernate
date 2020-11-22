CREATE DATABASE IF NOT EXISTS pavliyk_3;
USE pavliyk_3; 

DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS service_type;
DROP TABLE IF EXISTS terminal;
DROP TABLE IF EXISTS terminal_type;
DROP TABLE IF EXISTS manufacturer;
DROP TABLE IF EXISTS workman;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS sex;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS region;
DROP TABLE IF EXISTS country;

DROP TRIGGER IF EXISTS calculate_total_insert;
DROP TRIGGER IF EXISTS calculate_total_update;

CREATE TABLE country (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(53) NOT NULL
);

CREATE TABLE region (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    country_id INT NOT NULL
);

CREATE TABLE city (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    postal_code VARCHAR(6),
    region_id INT,
    country_id INT NOT NULL
);

CREATE TABLE address (
	id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(64) NOT NULL,
    house INT,
    city_id INT NOT NULL
);

CREATE TABLE manufacturer (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    contact_number VARCHAR(13),
    country_id INT NOT NULL
);

CREATE TABLE terminal_type (
	id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(45) NOT NULL
);

CREATE TABLE terminal (
	id INT PRIMARY KEY AUTO_INCREMENT,
    gps_coordinates VARCHAR(24),
    commissioning_date DATE,
    manufacturer_id INT NOT NULL,
    terminal_type_id INT,
    address_id INT NOT NULL
);

CREATE TABLE sex (
	id INT PRIMARY KEY AUTO_INCREMENT,
    sex VARCHAR(45) NOT NULL
);

CREATE TABLE post (
	id INT PRIMARY KEY AUTO_INCREMENT,
    post VARCHAR(45) NOT NULL
);

CREATE TABLE workman (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
	surname VARCHAR(45) NOT NULL,
    price_per_hour_uah DECIMAL(15,2) NOT NULL,
    contact_number VARCHAR(13),
    sex_id INT,
    post_id INT NOT NULL
);

CREATE TABLE service_type (
	id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(45) NOT NULL
);

CREATE TABLE service (
	id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    terminal_id INT NOT NULL,
    workman_id INT NOT NULL,
    service_type_id INT,
    duration_in_hours DECIMAL(4,2) NOT NULL,
    total_price_uah DECIMAL(15,2)
);

ALTER TABLE region
	ADD CONSTRAINT fk_region_country_id
    FOREIGN KEY (country_id)
    REFERENCES country(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;
    
ALTER TABLE city 
    ADD CONSTRAINT fk_city_region_id
    FOREIGN KEY (region_id)
    REFERENCES region(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
    
    ADD CONSTRAINT fk_city_country_id
    FOREIGN KEY (country_id)
    REFERENCES country(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;
    
ALTER TABLE address 
    ADD CONSTRAINT fk_address_city_id
    FOREIGN KEY (city_id)
    REFERENCES city(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;
    
ALTER TABLE manufacturer
	ADD CONSTRAINT fk_manufacturer_country_id
    FOREIGN KEY (country_id)
    REFERENCES country(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;
    
ALTER TABLE terminal
	ADD CONSTRAINT fk_terminal_manufacturer_id
    FOREIGN KEY (manufacturer_id)
    REFERENCES manufacturer(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT fk_terminal_type_id
    FOREIGN KEY (terminal_type_id)
    REFERENCES terminal_type(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
    
	ADD CONSTRAINT fk_terminal_address_id
    FOREIGN KEY (address_id)
    REFERENCES address(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;
    
ALTER TABLE workman
	ADD CONSTRAINT fk_workman_sex_id
    FOREIGN KEY (sex_id)
    REFERENCES sex(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
    
    ADD CONSTRAINT fk_workman_post_id
    FOREIGN KEY (post_id)
    REFERENCES post(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE service
	ADD CONSTRAINT fk_service_terminal_id
    FOREIGN KEY (terminal_id)
    REFERENCES terminal(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT fk_service_workman_id
    FOREIGN KEY (workman_id)
    REFERENCES workman(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    
    ADD CONSTRAINT fk_service_type_id
    FOREIGN KEY (service_type_id)
    REFERENCES service_type(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL;

DELIMITER $$
CREATE TRIGGER calculate_total_insert BEFORE INSERT ON service
FOR EACH ROW BEGIN
  SET NEW.total_price_uah = (SELECT (NEW.duration_in_hours * workman.price_per_hour_uah) FROM workman WHERE NEW.workman_id = workman.id);
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER calculate_total_update BEFORE UPDATE ON service
FOR EACH ROW BEGIN
  SET NEW.total_price_uah = (SELECT (NEW.duration_in_hours * workman.price_per_hour_uah) FROM workman WHERE NEW.workman_id = workman.id);
END$$
DELIMITER ;

INSERT INTO country (name) VALUES
('Ukraine'),
('United States'),
('United Kingdom'),
('Germany'),
('China'),
('France'),
('Spain'),
('Poland'),
('Japan'),
('Australia');

INSERT INTO region (name, country_id) VALUES
('Lvivska', 1),
('Kyivska', 1),
('Ternopilska', 1),
('Rivnenska', 1),
('Volynska', 1),
('Vynnytska', 1),
('Dnipropetrovska', 1),
('Donetska', 1),
('Odeska', 1),
('Chernivetska', 1);

INSERT INTO city (name, postal_code, region_id, country_id) VALUES
('Lviv', '79000', 1, 1),
('Sambir', '81400', 1, 1),
('Chervonohrad', '80100', 1, 1),
('Kyiv', '01000', NULL, 1),
('Varash', NULL, 4, 1),
('Sokal', '80200', 1, 1),
('Odesa', '65000', 9, 1),
('Zhovkva', NULL, 1, 1),
('London', NULL, NULL, 2),
('Drohobych', NULL, 1, 1);

INSERT INTO address (street, house, city_id) VALUES
('Sherptitskoho', 1, 3),
('Stusa', 15, 1),
('Krushelnitskoi', 24, 2),
('Shevchenka', 8, 4),
('Zelena', 2, 1),
('Heroiv Maydany', 1, 6),
('Franka', 34, 5),
('Bandery', 12, 1),
('Lychakivska', 5, 1),
('Bandery', 6, 3);

INSERT INTO manufacturer (name, contact_number, country_id) VALUES
('iBox',  0800300102 , 1),
('PrivatBank', 3700, 1),
('OshchadBank', 380939315252, 1),
('ATM Master', 45671231, 3),
('Notemachine', +441873811634, 3),
('ChangeGroup', +445511440010, 3),
('Allpoint', +156748291123, 2),
('CitiBank', 145678219384, 2),
('JPMORGAN CHASE', 178923719219, 2),
('JFCU', 367193182894, 4);

INSERT INTO terminal_type (type) VALUES
('withdraw'),
('add'),
('send'),
('services'),
('check');

INSERT INTO terminal (gps_coordinates, commissioning_date, manufacturer_id, terminal_type_id, address_id) VALUES
('49.8357642,24.0120998', '2018-09-20', '1', '1', '1'),
('49.836837,24.012347', '2017-01-15', '1', '2', '2'),
('50.3987155,24.2379207', '2020-08-11', '2', '3', '2'),
('49.527462,23.201803', '2019-11-14', '2', '4', '3'),
('49.8260972,24.0244902', '1999-01-03', '3', '4', '4'),
('49.8254327,24.0192116', '2015-03-28', '3', '3', '6'),
('51.5033477,-0.1016059', '2014-05-09', '5', '5', '7'),
('51.5033477,-0.1016059', '2018-07-01', '5', '2', '8'),
('52.21673,0.1607873', '2014-02-20', '5', '5', '9'),
('52.226142,0.1459815', '2020-03-14', '5', '1', '1');

INSERT INTO sex (sex) VALUES
('male'),
('female'),
('military helicopter');

INSERT INTO post (post) VALUES
('master'),
('junior'),
('middle'),
('senior'),
('trainee'),
('shief'),
('master-shief'),
('helper'),
('director');

INSERT INTO workman (name, surname, price_per_hour_uah, contact_number, sex_id, post_id) VALUES
('Jonathan', 'Martinez', '2800', NULL, '1', '1'),
('Daniel', 'Fetzer', '1500', NULL, '1', '2'),
('Margie', 'Patterson', '1000', NULL, '2', '3'),
('Donna', 'Hundley', '890', '720-980-9794', '2', '6'),
('Angel', 'Yoshida', '2100', '562-630-5976', '1', '2'),
('William', 'Sellers', '1300', '408-273-5505', '2', '1'),
('Lila', 'Mead', '2200', '708-798-1265', '1', '7'),
('Jerry', 'Hill', '1100', NULL, '1', '9'),
('Louie', 'Burgess', '250', NULL, '1', '2'),
('Jonathan', 'Collins', '1300', NULL, '1', '3');

INSERT INTO service_type (type) VALUES
('ordinary'),
('overhaul'),
('cash withdrawal'),
('diagnostics'),
('display replacement'),
('lock replacement'),
('cardreader replacemnt'),
('buttons repairment'),
('antenna repairment'),
('antenna replacement');

INSERT INTO service (date, terminal_id, workman_id, service_type_id, duration_in_hours) VALUES
('2016-02-20', 2, 4, 3, 2),
('2016-03-13', 1, 1, 1, 1),
('2017-03-24', 5, 5, 7, 1),
('2017-08-13', 7, 9, 8, 2.5),
('2017-09-11', 9, 10, 1, 1),
('2018-03-03', 1, 2, 2, 0.5),
('2018-12-14', 6, 3, 1, 1.5),
('2019-02-12', 3, 7, 7, 2),
('2020-04-29', 10, 8, 10, 3),
('2020-05-30', 1, 4, 1, 1);

CREATE INDEX service_date ON service(date);
CREATE INDEX service_workman ON service(workman_id);
