INSERT INTO board (name, color_hex) values ('Home', '#282828');
INSERT INTO board (name, color_hex) values ('Studies', '#282828');
INSERT INTO board (name, color_hex) values ('Shopping list', '#7F0000');

INSERT INTO task (name, description, board_id) values ('Clean room', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Throw bottle to trash been', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Make Experiment', 'Experiment for physics', 2);
INSERT INTO task (name, description, board_id) values ('Milk', '0,5L', 3);
INSERT INTO task (name, description, board_id) values ('Bread', '2', 3);
INSERT INTO task (name, description, board_id) values ('Chicken', '1kg', 3);

INSERT INTO task_manager (priority, task_id, is_done) values ('IMPORTANT', 1, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values ('VERY_IMPORTANT', 2, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values ('NORMAL', 3, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values ('NORMAL', 4, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values ('WARING', 5, FALSE);
INSERT INTO task_manager (priority, task_id, is_done) values ('NORMAL', 6, FALSE);

INSERT INTO task_member (app_user_id) values ('c0a8010d715f1f3881715f2f3f910000');
