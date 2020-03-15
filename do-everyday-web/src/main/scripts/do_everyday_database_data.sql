INSERT INTO board (name, color_hex) values ('Home', '#282828');
INSERT INTO board (name, color_hex) values ('Studies', '#282828');
INSERT INTO board (name, color_hex) values ('Shopping list', '#7F0000');

INSERT INTO task (name, description, board_id) values ('Clean room', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Throw bottle to trash been', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Make Experiment', 'Experiment for physics', 2);
INSERT INTO task (name, description, board_id) values ('Milk', '0,5L', 3);
INSERT INTO task (name, description, board_id) values ('Bread', '2', 3);
INSERT INTO task (name, description, board_id) values ('Chicken', '1kg', 3);

INSERT INTO task_manager (priority, task_id, is_done) values (2, 1, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values (3, 2, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values (0, 3, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values (0, 4, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values (1, 5, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values (0, 6, FALSE);

INSERT INTO task_member (name, surname) values ('Krzysztof', 'Kowalski');

INSERT INTO  budget (name, description) values ('Home budget', 'All expenses depends on house');
INSERT INTO  budget (name, description) values ('Party budget', 'All expenses spend on parties');

INSERT INTO  transaction (value, description, currency, budget_id, date) values (25, 'Shots','PLN', 2, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (50, 'Ticked to party','PLN', 2, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (10, 'Bread','EUR', 1, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (700, 'Fridge','PLN', 1, CURRENT_DATE);