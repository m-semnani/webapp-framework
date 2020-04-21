CREATE DATABASE IF NOT EXISTS linkitdb;

grant all on linkitdb.* to 'linkit'@'172.27.1.13' identified by 'secret12';
grant all on linkitdb.* to 'root'@'172.27.1.13' identified by 'secret';
flush privileges;