INSERT INTO  budget (name, description) values ('Home budget', 'All expenses depends on house');
INSERT INTO  budget (name, description) values ('Party budget', 'All expenses spend on parties');

INSERT INTO  transaction (value, description, currency, budget_id, date) values (25, 'Shots','PLN', 2, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (50, 'Ticked to party','PLN', 2, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (10, 'Bread','EUR', 1, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (700, 'Fridge','PLN', 1, CURRENT_DATE);