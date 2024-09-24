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
  ('Linden', '349 Linden Avenue, 60091', 42.0737, 87.6905, 1, 1, NULL),
  ('Foster', '900 Foster Street, 60201', 42.0541, 87.6836, 0, 0, NULL),
  ('Howard', '7519 North Paulina Street, 60626', 42.0188, 87.6725, 1, 1, NULL),
  ('Dempster-Skokie', '5005 Dempster Street, 60077', 42.0403, 87.7523, 1, 1, NULL),
  ('Kimball', '4755 North Kimball Avenue, 60625', 41.9676, 87.7129, 1, 1, NULL),
  ('Paulina', '3410 North Lincoln Avenue, 60657', 41.9437, 87.6708, 1, 0, NULL),
  ('Belmont', '945 West Belmont Avenue, 60657', 41.9395, 87.6533, 1, 0, NULL),
  ('Sheridan', '3940 North Sheridan Road, 60613', 41.9538, 87.6552, 0, 0, NULL),
  ('Garfield', '220 West Garfield Boulevard, 60609', 41.7954, 87.6311, 1, 0, NULL),
  ('Midway', '4612 West 59th Street, 60629', 41.7866, 87.7378, 1, 1, NULL),
  ('Halsted', '2520 South Archer Avenue, 60608', 41.8467, 87.6480, 1, 1, NULL),
  ('Roosevelt', '1167 South State Street, 60605', 41.8674, 87.6266, 1, 0, NULL),
  ('Clark-Lake', '100-124 West Lake Street, 60601', 41.8857, 87.6308, 1, 0, NULL),
  ('Ashland/63rd', '1167 South State Street, 60636', 41.7794, 87.6639, 1, 1, NULL),
  ("O'Hare", "1000 O'Hare Drive, 60666", 41.9811, 87.9008, 1, 1, NULL),
  ('Irving Park', '4131 West Irving Park Road, 60641', 41.9529, 87.7292, 0, 0, NULL),
  ('LaSalle', '150 West Ida B. Wells Drive, 60605', 41.8755, 87.6317, 0, 0, NULL),
  ('Polk', '1713 West Polk Street, 60612', 41.8715, 87.6695, 1, 0, NULL),
  ('Pulaski', '5106 South Pulaski Road, 60632', 41.7997, 87.7244, 1, 0, NULL);

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
