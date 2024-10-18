INSERT INTO role (id,name)
VALUES (1,'ROLE_USER');

INSERT INTO role (id,name)
VALUES (2,'ROLE_ADMIN');

INSERT INTO user (username, password)
values ('jluque', '$2a$12$sMuH2QJ/u8VAnklXfUz1v.Vkwl1VIviMvYDi6ijgDvOU8.euMHt2C');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);

