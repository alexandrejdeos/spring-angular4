CREATE DATABASE db_lds;
USE db_lds;

CREATE TABLE users (
	username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (username),
    UNIQUE KEY username (username));
    
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

INSERT INTO users(username,password,enabled)
VALUES ('admin','21232f297a57a5a743894a0e4a801fc3', true);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');