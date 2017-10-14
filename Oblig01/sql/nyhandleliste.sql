DROP SCHEMA IF EXISTS handleliste CASCADE;
DROP TABLE IF EXISTS handleliste.bruker CASCADE;
DROP TABLE IF EXISTS handleliste.varer CASCADE;
DROP TABLE IF EXISTS handleliste.kurv CASCADE;

CREATE SCHEMA handleliste;

CREATE TABLE handleliste.kurv (
  kurv_id          SERIAL      NOT NULL,
  beskrivelse      VARCHAR(50),
  PRIMARY KEY(kurv_id)
);
CREATE TABLE handleliste.bruker (
  bruker_id     SERIAL      NOT NULL,
  brukernavn    VARCHAR(20),
  passord       VARCHAR(20),
  kurv_id       INTEGER     REFERENCES handleliste.kurv(kurv_id),
  PRIMARY KEY(bruker_id)
);

CREATE TABLE handleliste.vare (
  vare_id          SERIAL      NOT NULL,
  navn             VARCHAR(30) NOT NULL,
  kurv_id          INTEGER     REFERENCES handleliste.kurv(kurv_id),
  PRIMARY KEY(vare_id)
);

ALTER TABLE handleliste.bruker ADD UNIQUE (brukernavn);

INSERT INTO handleliste.kurv (beskrivelse) VALUES ('Peder sin kurv');
INSERT INTO handleliste.kurv (beskrivelse) VALUES ('olav sin kurv');
INSERT INTO handleliste.bruker (brukernavn, passord, kurv_id) VALUES ('peder','123',1);
INSERT INTO handleliste.bruker (brukernavn, passord, kurv_id) VALUES ('olav','123',2);
INSERT INTO handleliste.vare (navn, kurv_id) VALUES ('Melk', 1);
INSERT INTO handleliste.vare (navn, kurv_id) VALUES ('Vann', 1);
INSERT INTO handleliste.vare (navn, kurv_id) VALUES ('Saft', 2);
INSERT INTO handleliste.vare (navn, kurv_id) VALUES ('Øl', 2);



