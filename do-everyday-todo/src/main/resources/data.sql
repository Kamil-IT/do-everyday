INSERT INTO board (name, color_name) values ('Home', 'blue');
INSERT INTO board (name, color_name) values ('Studies', 'red');


INSERT INTO task (name, description, board_id) values ('Clean room', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Throw bottle to trash been', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Make Experiment', 'Experiment for physics', 2);

INSERT INTO task_manager (task_id, is_done) values (1, FALSE);

INSERT INTO task_member (name, surname) values ('Krzysztof', 'Kowalski');

INSERT INTO task_member_task_manager (task_member_id, task_manager_id) values (1, 1);
