CREATE SCHEMA IF NOT EXISTS itmd4515;
USE itmd4515;

DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;

CREATE TABLE role(
  role_title VARCHAR(15) NOT NULL,
  PRIMARY KEY(role_title)
);

CREATE TABLE user(
  user_id VARCHAR(15) NOT NULL,
  email VARCHAR(254) NOT NULL,
  password VARCHAR(115) NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(100),
  PRIMARY KEY(user_id)
);

CREATE TABLE user_role(
  role_title VARCHAR(15) NOT NULL,
  user_id VARCHAR(15) NOT NULL,
  PRIMARY KEY(role_title, user_id),
  KEY idx_fk_user_id(user_id),
  CONSTRAINT fk_user_role_role FOREIGN KEY(role_title) REFERENCES role(role_title)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_user_role_user FOREIGN KEY(user_id) REFERENCES user(user_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);
