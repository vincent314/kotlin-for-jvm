CREATE TABLE USERS
(
  ID     INT PRIMARY KEY,
  NAME   VARCHAR(255),
  EMAIL  VARCHAR(255),
  AMOUNT NUMBER
);

INSERT INTO USERS
VALUES (1, 'Kayte Howlett', 'kayte.howlett@email.com', 2000);
INSERT INTO USERS
VALUES (2, 'Erroll Lydon', 'erroll.lydon@email.com', null);
INSERT INTO USERS
VALUES (3, 'Sanjiv Rizzuto', 'sanjiv.rizzuto@email.com', 3000);
