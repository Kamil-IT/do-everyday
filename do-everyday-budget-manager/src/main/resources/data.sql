INSERT INTO  budget (name, description) values ('Home budget', 'All expenses depends on house');
INSERT INTO  budget (name, description) values ('Party budget', 'All expenses spend on parties');

INSERT INTO  transaction (value, description, currency, budget_id) values (25, 'Shots','PLN', 2);
INSERT INTO  transaction (value, description, currency, budget_id) values (50, 'Ticked to party','PLN', 2);
INSERT INTO  transaction (value, description, currency, budget_id) values (10, 'Bread','EUR', 1);
INSERT INTO  transaction (value, description, currency, budget_id) values (700, 'Fridge','PLN', 1);