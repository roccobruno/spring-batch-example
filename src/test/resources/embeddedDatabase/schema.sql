CREATE TABLE UTENZE_AXWAY (
  NOME                      VARCHAR(100) NOT NULL,
  COGNOME                   VARCHAR(100) NOT NULL,
  EMAIL                     VARCHAR(100) ,
  COD_FISCALE               VARCHAR(20) NOT NULL ,
  DATA_RICHIESTA            DATE,
  DATA_INOLTRO_RICHIESTA    DATE,
  FLAG_RICHIESTA_INOLTRATA  VARCHAR(1),
  CONSTRAINT UTENZE_AXWAY_pkey PRIMARY KEY (COD_FISCALE)
);



