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


INSERT INTO bonus (name, description)
  VALUES ('Restaurant','You can use any restaurants');
INSERT INTO bonus (name, description)
  VALUES ('Games','You can play any games');
INSERT INTO bonus (name, description)
  VALUES ('Pool','You can use special pools');
INSERT INTO bonus (name, description)
  VALUES ('Cinema','You can watch fils in our cinema centre');
INSERT INTO bonus (name, description)
  VALUES ('TV','You can have TV in your room');


INSERT INTO  excursion (name, duration, price, additional_info, ship_id)
  VALUES ('Moto','1 day', '1000','Amazing moto excursion','1');
INSERT INTO  excursion (name, duration, price, additional_info, ship_id)
  VALUES ('Boat Riding','12 hours', '2000','Amazing boat riding excursion','2');
INSERT INTO  excursion (name, duration, price, additional_info, ship_id)
  VALUES ('Fishing','10 hours', '3000','Amazing fishing excursion','1');
