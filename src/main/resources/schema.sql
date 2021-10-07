CREATE DATABASE cruise;
use cruise;
CREATE TABLE USER_ROLE (
ID SERIAL PRIMARY KEY,
LOGIN VARCHAR (30) NOT NULL,
PASSWORD VARCHAR (30) NOT NULL,
ROLE VARCHAR (30) NOT NULL,
NAME VARCHAR (30) NOT NULL,
SURNAME VARCHAR (30) NOT NULL,
BALANCE FLOAT
);

CREATE TABLE SHIP (
ID SERIAL PRIMARY KEY,
NAME VARCHAR (30) NOT NULL,
PASSENGER_AMOUNT INTEGER NOT NULL,
ROUTE_TO VARCHAR(30) NOT NULL,
ROUTE_FROM VARCHAR(30) NOT NULL,
AMOUNT_PORTS INTEGER NOT NULL,
TRAVEL_START DATE,
TRAVEL_END DATE,
STANDART_PRICE FLOAT
);

CREATE TABLE CRUISE_INFO (
ID SERIAL PRIMARY KEY,
SHIP_ID INTEGER REFERENCES SHIP(ID),
ROOM_TYPE VARCHAR (30),
USER_ID INTEGER REFERENCES USER_ROLE(ID),
TOTAL_PRICE FLOAT
);

INSERT INTO user_role (login, password, role, name, surname, balance)
VALUES ('ivan','123','USER','Ivan','Ivanov','20000');
INSERT INTO user_role (login, password, role, name, surname, balance)
VALUES ('petro','222','USER','Petro','Petrov','20000');
INSERT INTO user_role (login, password, role, name, surname, balance)
VALUES ('admin','admin','ADMIN','admin','admin','20000');

INSERT INTO ship (name, passenger_amount, route_to, route_from, amount_ports, travel_start, travel_end, standart_price)
VALUES ('Victoria','100','America','France','2',DATE '19-02-10',DATE '19-03-10','2000');
INSERT INTO ship (name, passenger_amount, route_to, route_from, amount_ports, travel_start, travel_end, standart_price)
VALUES ('Future','200','England','Africe','2',DATE '19-04-01',DATE '19-04-10','4000');