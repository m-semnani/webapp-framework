CREATE DATABASE IF NOT EXISTS linkitdb;
grant all on linkitdb.* to 'linkit'@'172.27.1.13' identified by 'secret12';
grant all on linkitdb.* to 'root'@'172.27.1.13' identified by 'secret';
flush privileges; 
use linkitdb;
CREATE TABLE IF NOT EXISTS user (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,first_name VARCHAR(30) NOT NULL,last_name VARCHAR(30) NOT NULL,email VARCHAR(50));
CREATE TABLE IF NOT EXISTS product (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,name VARCHAR(30) NOT NULL,quantity INT NOT NULL);