-- Generado por Oracle SQL Developer Data Modeler 18.2.0.179.0756
--   en:        2018-09-28 21:06:14 CEST
--   sitio:      Oracle Database 12c
--   tipo:      Oracle Database 12c



CREATE TABLE empleado (
    id_empleado      NUMBER NOT NULL,
    nombre           VARCHAR2(500) NOT NULL,
    apellido1        VARCHAR2(500) NOT NULL,
    apellido2        VARCHAR2(500) NOT NULL,
    fecha_nac        VARCHAR2(500) NOT NULL,
    fecha_contrato   VARCHAR2(500) NOT NULL,
    fecha_fin        VARCHAR2(500) NOT NULL,
    nacionalidad     VARCHAR2(500) NOT NULL,
    cargo            VARCHAR2(500) NOT NULL,
    activo           NUMBER NOT NULL
);

ALTER TABLE empleado ADD CONSTRAINT empleado_pk PRIMARY KEY ( id_empleado );

CREATE TABLE historico (
    fecha_emision          VARCHAR2(500) NOT NULL,
    horario                VARCHAR2(500) NOT NULL,
    sala_id_sala           NUMBER NOT NULL,
    empleado_id_empleado   NUMBER NOT NULL,
    pelicula_id_pelicula   NUMBER NOT NULL
);

ALTER TABLE historico
    ADD CONSTRAINT historico_pk PRIMARY KEY ( fecha_emision,
                                              horario,
                                              sala_id_sala );

CREATE TABLE pelicula (
    id_pelicula    NUMBER NOT NULL,
    titulo         VARCHAR2(500) NOT NULL,
    anyo_estreno   VARCHAR2(500) NOT NULL,
    director       VARCHAR2(500) NOT NULL,
    actor_princi   VARCHAR2(500) NOT NULL,
    actor_secun    VARCHAR2(500) NOT NULL,
    duracion       VARCHAR2(500) NOT NULL,
    trailer        VARCHAR2(500) NOT NULL
);

ALTER TABLE pelicula ADD CONSTRAINT pelicula_pk PRIMARY KEY ( id_pelicula );

CREATE TABLE sala (
    id_sala      NUMBER NOT NULL,
    capacidad    NUMBER NOT NULL,
    pantalla     NUMBER NOT NULL,
    apertura     VARCHAR2(500) NOT NULL,
    horario      VARCHAR2(500) NOT NULL,
    disponible   NUMBER NOT NULL
);

ALTER TABLE sala ADD CONSTRAINT sala_pk PRIMARY KEY ( id_sala );

ALTER TABLE historico
    ADD CONSTRAINT historico_empleado_fk FOREIGN KEY ( empleado_id_empleado )
        REFERENCES empleado ( id_empleado );

ALTER TABLE historico
    ADD CONSTRAINT historico_pelicula_fk FOREIGN KEY ( pelicula_id_pelicula )
        REFERENCES pelicula ( id_pelicula );

ALTER TABLE historico
    ADD CONSTRAINT historico_sala_fk FOREIGN KEY ( sala_id_sala )
        REFERENCES sala ( id_sala );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                              7
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
