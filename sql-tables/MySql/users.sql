CREATE TABLE IF NOT EXISTS users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL CHECK (username <> ''),
  password VARCHAR(45) NOT NULL CHECK (password <> ''),
  PRIMARY KEY (id),
  UNIQUE (username)
);

INSERT INTO users (username, password) VALUES ('tester', 'tester');