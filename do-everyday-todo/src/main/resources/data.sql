INSERT INTO board (name, color_hex) values ('Home', '#282828');
INSERT INTO board (name, color_hex) values ('Studies', '#282828');
INSERT INTO board (name, color_hex) values ('Shopping list', '#7F0000');


INSERT INTO task (name, description, board_id) values ('Clean room', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Throw bottle to trash been', 'mum will be angry when you dont do this', 1);
INSERT INTO task (name, description, board_id) values ('Make Experiment', 'Experiment for physics', 2);
INSERT INTO task (name, description, board_id) values ('Milk', '0,5L', 3);
INSERT INTO task (name, description, board_id) values ('Bread', '2', 3);
INSERT INTO task (name, description, board_id) values ('Chicken', '1kg', 3);

INSERT INTO task_member (name, surname) values ('Krzysztof', 'Kowalski');
