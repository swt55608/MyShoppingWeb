CREATE TABLE IF NOT EXISTS users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL CHECK (username <> ''),
  password VARCHAR(65) NOT NULL CHECK (password <> ''),
  PRIMARY KEY (id),
  UNIQUE (username)
);

