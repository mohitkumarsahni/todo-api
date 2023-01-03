---
--- The SQL commands in this file are only to create basic database & user.
--- Please customize as per your requirement or study practice.
---

create database todo-api-db;

create user 'todo-api-user'@'%' identified by 'PASSWORD_OF_YOUR_CHOICE';

grant all privileges on todo-api-db.* TO 'todo-api-user'@'%';

FLUSH PRIVILEGES;
