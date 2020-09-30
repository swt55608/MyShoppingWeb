CREATE TABLE IF NOT EXISTS products (
  id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL CHECK (name <> ''),
  price int NOT NULL CHECK (price > 0),
  img VARCHAR(45),
  PRIMARY KEY (id),
  UNIQUE (name)
);

INSERT INTO products (name, price, img) VALUES ('apple', 10, 'apple.jpg');
INSERT INTO products (name, price, img) VALUES ('banana', 20, 'banana.jpg');
INSERT INTO products (name, price, img) VALUES ('citrus', 30, 'citrus.jpg');
INSERT INTO products (name, price, img) VALUES ('dragon fruit', 40, 'dragon fruit.jpg');
INSERT INTO products (name, price, img) VALUES ('eggplant', 40, 'eggplant.jpg');