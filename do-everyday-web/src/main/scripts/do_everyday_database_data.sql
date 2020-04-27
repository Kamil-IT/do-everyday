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

INSERT INTO  budget (name, description) values ('Home budget', 'All expenses depends on house');
INSERT INTO  budget (name, description) values ('Party budget', 'All expenses spend on parties');

INSERT INTO  transaction (value, description, currency, budget_id, date) values (25, 'Shots','PLN', 2, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (50, 'Ticked to party','PLN', 2, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (10, 'Bread','EUR', 1, CURRENT_DATE);
INSERT INTO  transaction (value, description, currency, budget_id, date) values (700, 'Fridge','PLN', 1, CURRENT_DATE);

INSERT INTO user (id, username, password, role) values ('c0a8010d715f1f3881715f2f3f910000' ,'admin', '$2a$10$NCmlTyGgin1jeNqh/7oZ7uxQTeWlmSaiJP44vce0YiglRcBeYFjxi', 2);
INSERT INTO user (id, username, password, role) values ('c0a8010d715f13b481715f33bb2f0000' ,'employee', '$2a$10$NCmlTyGgin1jeNqh/7oZ7uxQTeWlmSaiJP44vce0YiglRcBeYFjxi', 1);
INSERT INTO user (id, username, password, role) values ('c0a8010d715f1d8f81715f3d964e0000' ,'user', '$2a$10$NCmlTyGgin1jeNqh/7oZ7uxQTeWlmSaiJP44vce0YiglRcBeYFjxi', 0);

INSERT INTO task_member (app_user_id) values ('c0a8010d715f1f3881715f2f3f910000');