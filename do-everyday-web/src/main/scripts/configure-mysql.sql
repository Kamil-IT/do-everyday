# Run this script if you use docker
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE do_everyday_prod;

#Create database service accounts
CREATE USER 'do_everyday_prod_user'@'localhost' IDENTIFIED BY 'root';
CREATE USER 'do_everyday_prod_user'@'%' IDENTIFIED BY 'root';

#Database grants
GRANT SELECT ON do_everyday_prod.* to 'do_everyday_prod_user'@'localhost';
GRANT INSERT ON do_everyday_prod.* to 'do_everyday_prod_user'@'localhost';
GRANT DELETE ON do_everyday_prod.* to 'do_everyday_prod_user'@'localhost';
GRANT UPDATE ON do_everyday_prod.* to 'do_everyday_prod_user'@'localhost';
GRANT SELECT ON do_everyday_prod.* to 'do_everyday_prod_user'@'%';
GRANT INSERT ON do_everyday_prod.* to 'do_everyday_prod_user'@'%';
GRANT DELETE ON do_everyday_prod.* to 'do_everyday_prod_user'@'%';
GRANT UPDATE ON do_everyday_prod.* to 'do_everyday_prod_user'@'%';