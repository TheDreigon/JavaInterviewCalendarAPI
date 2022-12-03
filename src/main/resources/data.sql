DROP TABLE IF EXISTS calendar;

CREATE TABLE calendar (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL
);

INSERT INTO calendar (name, description) VALUES
  ('name1', 'This is description 1'),
  ('name2', 'This is description 2'),
  ('name3', 'This is description 3');
