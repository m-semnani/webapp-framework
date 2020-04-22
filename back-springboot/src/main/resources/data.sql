
-- app_user
INSERT INTO linkitdb.app_user (id, first_name, last_name, email, username, password) VALUES (1, 'Mohammad', 'HiBye', 'mohammad@linkit.nl', 'mohammad', '$2a$10$baJmhjrVkHeRLBCtri2/weWqp4/kM4WLU8iBfQYwbjMVEAl1CR6K.');
INSERT INTO linkitdb.app_user (id, first_name, last_name, email, username, password) VALUES (2, 'Vlad', 'HiBye', 'vlad@linkit.nl', 'vlad', '$2a$10$baJmhjrVkHeRLBCtri2/weWqp4/kM4WLU8iBfQYwbjMVEAl1CR6K.');

-- product
INSERT INTO linkitdb.product (id, name, quantity, user_id) VALUES (1, 'Book', 12, 1);
INSERT INTO linkitdb.product (id, name, quantity, user_id) VALUES (2, 'TV', 5, 1);
INSERT INTO linkitdb.product (id, name, quantity, user_id) VALUES (3, 'Chair', 28, 1);
INSERT INTO linkitdb.product (id, name, quantity, user_id) VALUES (4, 'Table', 22, 2);
INSERT INTO linkitdb.product (id, name, quantity, user_id) VALUES (5, 'Laptop', 16, 2);
INSERT INTO linkitdb.product (id, name, quantity, user_id) VALUES (6, 'PC', 2, 2);

-- authority
INSERT INTO linkitdb.authority (name, id) VALUES ('ADMIN_ROLE', 1);
INSERT INTO linkitdb.authority (name, id) VALUES ('USER_ROLE', 2);

-- user_authority
INSERT INTO linkitdb.user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO linkitdb.user_authority (user_id, authority_id) VALUES (2, 2);

-- app_config
INSERT INTO linkitdb.app_config (id, name, value) VALUES (1, 'LACK', '10');
INSERT INTO linkitdb.app_config (id, name, value) VALUES (2, 'EXCESS', '20');
