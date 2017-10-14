DROP SCHEMA IF EXISTS handleliste CASCADE;
DROP TABLE IF EXISTS handleliste.bruker CASCADE;
DROP TABLE IF EXISTS handleliste.varer CASCADE;
DROP TABLE IF EXISTS handleliste.kurv CASCADE;

CREATE SCHEMA handleliste;

CREATE TABLE handleliste.bruker (
  bruker_id     SERIAL      NOT NULL,
  brukernavn    VARCHAR(20),
  passord       VARCHAR(20),
  PRIMARY KEY(bruker_id)
);
CREATE TABLE handleliste.kurv (
  kurv_id          SERIAL      NOT NULL,
  bruker_id        INTEGER     REFERENCES handleliste.bruker(bruker_id),
  beskrivelse      VARCHAR(20),
  PRIMARY KEY(kurv_id)
);
CREATE TABLE handleliste.vare (
  vare_id          SERIAL      NOT NULL,
  navn             VARCHAR(20) NOT NULL,
  PRIMARY KEY(vare_id)
);
CREATE TABLE handleliste.kurv_produkt (
  kurv_id INTEGER REFERENCES handleliste.kurv(kurv_id),
  vare_id INTEGER REFERENCES handleliste.vare(vare_id) on delete cascade on update cascade,
  PRIMARY KEY (kurv_id, vare_id)
);

ALTER TABLE handleliste.bruker ADD UNIQUE (brukernavn);

INSERT INTO handleliste.bruker (brukernavn, passord) VALUES ('peder','123');
INSERT INTO handleliste.bruker (brukernavn, passord) VALUES ('Olav','123');
INSERT INTO handleliste.kurv (bruker_id, beskrivelse) VALUES (1,'Peder sin kurv');
INSERT INTO handleliste.kurv (bruker_id, beskrivelse) VALUES (2,'Olav sin kurv');

INSERT INTO handleliste.vare (navn) VALUES ('Melk');
INSERT INTO handleliste.vare (navn) VALUES ('Vann');
INSERT INTO handleliste.vare (navn) VALUES ('Saft');
INSERT INTO handleliste.vare (navn) VALUES ('Øl');

INSERT INTO handleliste.kurv_produkt (kurv_id, vare_id) VALUES (1,1);
INSERT INTO handleliste.kurv_produkt (kurv_id, vare_id) VALUES (1,2);
INSERT INTO handleliste.kurv_produkt (kurv_id, vare_id) VALUES (2,3);
INSERT INTO handleliste.kurv_produkt (kurv_id, vare_id) VALUES (2,4);


