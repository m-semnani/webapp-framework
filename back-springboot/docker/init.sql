CREATE DATABASE IF NOT EXISTS linkitdb;

grant all on linkitdb.* to 'linkit'@'172.27.1.13' identified by 'secret12';
grant all on linkitdb.* to 'root'@'172.27.1.13' identified by 'secret';
flush privileges; 

use linkitdb;
-- app_user
Drop TABLE app_user;
CREATE TABLE app_user (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,first_name VARCHAR(30) NOT NULL,last_name VARCHAR(30) NOT NULL,email VARCHAR(50),username VARCHAR(30) UNIQUE NOT NULL,password LONGTEXT NOT NULL );
INSERT INTO app_user (id, first_name, last_name, email, username, password) VALUES (1, 'Mohammad', 'Semnani', 'mohammad.semnani@linkit.nl', 'mohammad', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');
INSERT INTO app_user (id, first_name, last_name, email, username, password) VALUES (2, 'Vlad', 'Burca', 'vlad.burca@linkit.nl', 'vlad', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

-- product
Drop TABLE product;
CREATE TABLE product (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,name VARCHAR(30) NOT NULL,quantity INT NOT NULL);

-- authority
Drop TABLE authority;
CREATE TABLE authority (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,name VARCHAR(30) NOT NULL);
INSERT INTO authority (name, id) VALUES ('ROLE_ADMIN', 1);
INSERT INTO authority (name, id) VALUES ('ROLE_USER', 2);

-- user_authority
Drop TABLE user_authority;
CREATE TABLE user_authority (user_id INT(6) UNSIGNED NOT NULL, authority_id INT(6) UNSIGNED NOT NULL);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);