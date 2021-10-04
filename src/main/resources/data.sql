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

