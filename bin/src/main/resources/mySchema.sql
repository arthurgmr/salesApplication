CREATE TABLE CLIENT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR,
    CPF VARCHAR(11)
);

CREATE TABLE PRODUCT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRIPTION VARCHAR(100),
    UNIT_PRICE NUMERIC(20,0)
);

CREATE TABLE CLIENT_ORDER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID INTEGER REFERENCES CLIENT (ID),
    TOTAL NUMERIC(20,0),
    STATUS VARCHAR(20),
    CREATED_AT TIMESTAMP
);

CREATE TABLE ITEM_ORDER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ORDER_ID INTEGER REFERENCES CLIENT_ORDER (ID),
    PRODUCT_ID INTEGER REFERENCES PRODUCT (ID),
    QUANTITY INTEGER,
    CREATED_AT TIMESTAMP
);