CREATE SCHEMA IF NOT EXISTS cta;
USE cta;

DELETE FROM track_station;
DELETE FROM track;
DELETE FROM station;

INSERT INTO track VALUES
  ('Red', 0),
  ('Blue', 1),
  ('Brown', 0),
  ('Green', 0),
  ('Orange', 0),
  ('Pink', 0),
  ('Purple', 0),
  ('Yellow', 0);

INSERT INTO station VALUES
  ('Linden', NULL, NULL, '349 Linden Avenue, 60091', 1, 1, 1962),
  ('Foster', NULL, NULL, '900 Foster Street, 60201', 0, 0, 1994),
  ('Howard', NULL, NULL, '7519 North Paulina Street, 60626', 1, 1, 1978),
  ('Dempster-Skokie', NULL, NULL, '5005 Dempster Street, 60077', 1, 1, 2001),
  ('Kimball', NULL, NULL, '4755 North Kimball Avenue, 60625', 1, 1, 1955),
  ('Paulina', NULL, NULL, '3410 North Lincoln Avenue, 60657', 1, 0, 1987),
  ('Belmont', NULL, NULL, '945 West Belmont Avenue, 60657', 1, 0, 2006),
  ('Sheridan', NULL, NULL, '3940 North Sheridan Road, 60613', 0, 0, 1969),
  ('Garfield', NULL, NULL, '220 West Garfield Boulevard, 60609', 1, 0, 1997),
  ('Midway', NULL, NULL, '4612 West 59th Street, 60629', 1, 1, 1973),
  ('Halsted', NULL, NULL, '2520 South Archer Avenue, 60608', 1, 1, 2003),
  ('Roosevelt', NULL, NULL, '1167 South State Street, 60605', 1, 0, 1958),
  ('Clark-Lake', NULL, NULL, '100-124 West Lake Street, 60601', 1, 0, 1989),
  ('Ashland/63rd', NULL, NULL, '1167 South State Street, 60636', 1, 1, 2009),
  ("O'Hare", NULL, NULL, "1000 O'Hare Drive, 60666", 1, 1, 1965),
  ('Irving Park', NULL, NULL, '4131 West Irving Park Road, 60641', 0, 0, 1992),
  ('LaSalle', NULL, NULL, '150 West Ida B. Wells Drive, 60605', 0, 0, 1976),
  ('Polk', NULL, NULL, '1713 West Polk Street, 60612', 1, 0, 2004),
  ('Pulaski', NULL, NULL, '5106 South Pulaski Road, 60632', 1, 0, 1953);

INSERT INTO track_station VALUES
  ('Linden', 'Purple'),
  ('Foster', 'Purple'),
  ('Howard', 'Purple'),
  ('Howard', 'Yellow'),
  ('Howard', 'Red'),
  ('Dempster-Skokie', 'Yellow'),
  ('Kimball', 'Brown'),
  ('Paulina', 'Brown'),
  ('Belmont', 'Brown'),
  ('Belmont', 'Red'),
  ('Sheridan', 'Red'),
  ('Garfield', 'Red'),
  ('Midway', 'Orange'),
  ('Halsted', 'Orange'),
  ('Roosevelt', 'Orange'),
  ('Roosevelt', 'Green'),
  ('Clark-Lake', 'Green'),
  ('Clark-Lake', 'Blue'),
  ('Ashland/63rd', 'Green'),
  ("O'Hare", 'Blue'),
  ('Irving Park', 'Blue'),
  ('LaSalle', 'Blue'),
  ('LaSalle', 'Pink'),
  ('Polk', 'Pink'),
  ('Pulaski', 'Pink');
