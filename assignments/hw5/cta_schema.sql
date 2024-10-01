CREATE SCHEMA IF NOT EXISTS cta;
USE cta;

DROP TABLE IF EXISTS train_car;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS train;
DROP TABLE IF EXISTS track_station;
DROP TABLE IF EXISTS track;
DROP TABLE IF EXISTS station;

CREATE TABLE station(
  station_name VARCHAR(50) NOT NULL,
  lat DECIMAL(8, 6),
  lng DECIMAL(9, 6),
  address VARCHAR(200) NOT NULL,
  has_elevator BOOLEAN NOT NULL DEFAULT 0,
  has_parking BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY(station_name)
);

CREATE TABLE track(
  track_color VARCHAR(10) NOT NULL,
  is_24h BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY(track_color)
);

CREATE TABLE track_station(
  station_name VARCHAR(50) NOT NULL,
  track_color VARCHAR(10) NOT NULL,
  PRIMARY KEY(station_name, track_color),
  KEY idx_fk_track_color(track_color),
  CONSTRAINT fk_track_station_station FOREIGN KEY(station_name) REFERENCES station(station_name)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_track_station_track FOREIGN KEY(track_color) REFERENCES track(track_color)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE train(
  train_id INT AUTO_INCREMENT,
  locomotive_serial VARCHAR(20),
  since YEAR NOT NULL,
  track_color VARCHAR(10) NOT NULL,
  PRIMARY KEY(train_id),
  KEY idx_fk_track_color(track_color),
  CONSTRAINT fk_train_track FOREIGN KEY(track_color) REFERENCES track(track_color)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE car(
  car_no VARCHAR(4) NOT NULL,
  seats INT NOT NULL,
  PRIMARY KEY(car_no)
);

CREATE TABLE train_car(
  train_id INT NOT NULL,
  car_no VARCHAR(4) NOT NULL,
  PRIMARY KEY(train_id, car_no),
  KEY idx_fk_car_no(car_no),
  CONSTRAINT fk_train_car_train FOREIGN KEY(train_id) REFERENCES train(train_id)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_train_car_car FOREIGN KEY(car_no) REFERENCES car(car_no)
    ON DELETE RESTRICT ON UPDATE CASCADE
);
