CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(45) NOT NULL CHECK (username <> ''),
    password VARCHAR(45) NOT NULL CHECK (password <> ''),
    UNIQUE (username)
);

INSERT INTO users (username, password) VALUES ('tester', 'tester');

