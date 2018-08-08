DROP DATABASE IF EXISTS db;
CREATE DATABASE db;

DROP TABLE IF EXISTS db.user;
CREATE TABLE db.user (
  id       INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'id PK',
  email    VARCHAR(191) NOT NULL UNIQUE
  COMMENT 'email UN',
  username VARCHAR(255) NOT NULL
  COMMENT 'username',
  password VARCHAR(255) NOT NULL
  COMMENT 'password'
)
  COMMENT 'user table';


DROP TABLE IF EXISTS db.book;
CREATE TABLE db.book (
  id      INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'id PK',
  title   VARCHAR(255)  NOT NULL
  COMMENT 'title NN',
  author  VARCHAR(255)  NOT NULL
  COMMENT 'author NN',
  pubTime DATE          NOT NULL
  COMMENT 'publish time NN',
  price   DECIMAL(8, 2) NOT NULL
  COMMENT 'price NN',
  amount  INT           NOT NULL
  COMMENT 'amount NN',
  picture VARCHAR(255) COMMENT 'cover picture'
)
  COMMENT 'book table';

SELECT *
FROM db.user;

SELECT *
FROM db.book;

-- ' or 'a'='a
-- ' or 'a'='a

SELECT * FROM db.user WHERE email = '' or 'a'='a' AND password = '' or 'a'='a'; -- SQL Injection









