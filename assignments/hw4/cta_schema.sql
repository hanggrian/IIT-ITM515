CREATE SCHEMA IF NOT EXISTS cta;
USE cta;

DROP TABLE IF EXISTS track_station;
DROP TABLE IF EXISTS track;
DROP TABLE IF EXISTS station;

CREATE TABLE station(
  station_name VARCHAR(50) NOT NULL,
  address VARCHAR(200) NOT NULL,
  lat DECIMAL(8, 6) NOT NULL,
  lng DECIMAL(9, 6) NOT NULL,
  has_elevator BOOLEAN NOT NULL DEFAULT 0,
  has_parking BOOLEAN NOT NULL DEFAULT 0,
  since YEAR,
  PRIMARY KEY(station_name)
);

CREATE TABLE track(
  track_color VARCHAR(20) NOT NULL,
  is_24h BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY(track_color)
);

CREATE TABLE track_station(
  station_name VARCHAR(50) NOT NULL,
  track_color VARCHAR(20) NOT NULL,
  PRIMARY KEY(station_name, track_color),
  KEY idx_fk_track_color(track_color),
  CONSTRAINT fk_track_station_station FOREIGN KEY(station_name) REFERENCES station(station_name)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_track_station_track FOREIGN KEY(track_color) REFERENCES track(track_color)
    ON DELETE RESTRICT ON UPDATE CASCADE
);
