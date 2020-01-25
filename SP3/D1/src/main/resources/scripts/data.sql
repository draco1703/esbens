
INSERT INTO BOOK (TITLE) VALUES ('book-3');
SET @book1 = LAST_INSERT_ID();
INSERT INTO BOOK (TITLE) VALUES ('book-4');
SET @book2 = LAST_INSERT_ID();

INSERT INTO CUSTOMER (first_name, last_name) VALUES ('P', 'Erson');
INSERT INTO CUSTOMER (first_name, last_name) VALUES ('Endnu en', 'Person');
INSERT INTO CUSTOMER (first_name, last_name) VALUES ('Nummer', 'Tre');
