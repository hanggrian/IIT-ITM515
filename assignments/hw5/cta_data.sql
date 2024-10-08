CREATE SCHEMA IF NOT EXISTS cta;
USE cta;

DELETE FROM train_car;
DELETE FROM car;
DELETE FROM train;
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
  ('Linden', NULL, NULL, '349 Linden Avenue, 60091', 1, 1),
  ('Foster', NULL, NULL, '900 Foster Street, 60201', 0, 0),
  ('Howard', NULL, NULL, '7519 North Paulina Street, 60626', 1, 1),
  ('Dempster-Skokie', NULL, NULL, '5005 Dempster Street, 60077', 1, 1),
  ('Kimball', NULL, NULL, '4755 North Kimball Avenue, 60625', 1, 1),
  ('Paulina', NULL, NULL, '3410 North Lincoln Avenue, 60657', 1, 0),
  ('Belmont', NULL, NULL, '945 West Belmont Avenue, 60657', 1, 0),
  ('Sheridan', NULL, NULL, '3940 North Sheridan Road, 60613', 0, 0),
  ('Garfield', NULL, NULL, '220 West Garfield Boulevard, 60609', 1, 0),
  ('Midway', NULL, NULL, '4612 West 59th Street, 60629', 1, 1),
  ('Halsted', NULL, NULL, '2520 South Archer Avenue, 60608', 1, 1),
  ('Roosevelt', NULL, NULL, '1167 South State Street, 60605', 1, 0),
  ('Clark-Lake', NULL, NULL, '100-124 West Lake Street, 60601', 1, 0),
  ('Ashland/63rd', NULL, NULL, '1167 South State Street, 60636', 1, 1),
  ("O'Hare", NULL, NULL, "1000 O'Hare Drive, 60666", 1, 1),
  ('Irving Park', NULL, NULL, '4131 West Irving Park Road, 60641', 0, 0),
  ('LaSalle', NULL, NULL, '150 West Ida B. Wells Drive, 60605', 0, 0),
  ('Polk', NULL, NULL, '1713 West Polk Street, 60612', 1, 0),
  ('Pulaski', NULL, NULL, '5106 South Pulaski Road, 60632', 1, 0);

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

INSERT INTO train VALUES
  (1, NULL, 1920, 'Red'),
  (2, '7X-9L3-4Q2-R', 1987, 'Red'),
  (3, 'R8T-5M-1P', 1946, 'Red'),
  (4, '3G-9H2-W7F-5K', 1953, 'Red'),
  (5, 'LK4-J8N-6D-2', 1995, 'Blue'),
  (6, '5C3F-X1-Y9-Z', 2002, 'Blue'),
  (7, NULL, 1967, 'Blue'),
  (8, 'P7-Q2-R9T-3M-6', 1978, 'Blue'),
  (9, '2B6V-8G-4H', 1932, 'Brown'),
  (10, 'W1S-5D9-F3-J7L', 1913, 'Brown'),
  (11, '6N-4K-8M-2', 1981, 'Brown'),
  (12, '9Z7-X5C-3V-1B', 2021, 'Green'),
  (13, NULL, 1907, 'Green'),
  (14, 'T6Y-8U-2I-4O', 1958, 'Green'),
  (15, NULL, 1971, 'Orange'),
  (16, '1A3-E5G7J9', 2006, 'Orange'),
  (17, '8Q-6W4R2', 1939, 'Orange'),
  (18, 'D2F4-H6K8L', 1925, 'Pink'),
  (19, NULL, 1961, 'Pink'),
  (20, '3M5-N7P9S1', 1990, 'Pink'),
  (21, '7B9D-1F3H5J', 1942, 'Purple'),
  (22, 'X2Z4-C6V8', 1974, 'Purple'),
  (23, '5G-7I9K1M3', 1917, 'Purple'),
  (24, 'Q8W-6E4R2T', 1984, 'Yellow'),
  (25, NULL, 1951, 'Yellow');

INSERT INTO car VALUES
  ('0101', 50), ('0102', 50),
  ('0201', 60),
  ('0301', 65), ('0302', 50),
  ('0401', 45), ('0402', 60), ('0403', 40),
  ('0501', 45), ('0502', 40),
  ('0601', 30), ('0602', 35), ('0603', 35),
  ('0701', 50), ('0702', 45),
  ('0801', 30), ('0802', 45),
  ('0901', 25), ('0902', 20), ('0903', 35), ('0904', 25),
  ('1001', 45), ('1002', 50), ('1003', 65),
  ('1101', 35), ('1102', 60),
  ('1201', 45), ('1202', 45), ('1203', 55),
  ('1301', 50), ('1302', 50),
  ('1401', 35), ('1402', 50),
  ('1501', 40),
  ('1601', 50), ('1602', 60),
  ('1701', 35), ('1702', 50), ('1703', 50),
  ('1801', 45), ('1802', 40),
  ('1901', 50), ('1902', 60),
  ('2001', 55),
  ('2101', 35),
  ('2201', 50), ('2202', 45),
  ('2301', 35), ('2302', 50),
  ('2401', 65), ('2402', 40), ('2403', 40),
  ('2501', 40), ('2502', 50);

INSERT INTO train_car VALUES
  (1, '0101'), (1, '0102'),
  (2, '0201'),
  (3, '0301'), (3, '0302'),
  (4, '0401'), (4, '0402'), (4, '0403'),
  (5, '0501'), (5, '0502'),
  (6, '0601'), (6, '0602'), (6, '0603'),
  (7, '0701'), (7, '0702'),
  (8, '0801'), (8, '0802'),
  (9, '0901'), (9, '0902'), (9, '0903'), (9, '0904'),
  (10, '1001'), (10, '1002'), (10, '1003'),
  (11, '1101'), (11, '1102'),
  (12, '1201'), (12, '1202'), (12, '1203'),
  (13, '1301'), (13, '1302'),
  (14, '1401'), (14, '1402'),
  (15, '1501'),
  (16, '1601'), (16, '1602'),
  (17, '1701'), (17, '1702'), (17, '1703'),
  (18, '1801'), (18, '1802'),
  (19, '1901'), (19, '1902'),
  (20, '2001'),
  (21, '2101'),
  (22, '2201'), (22, '2202'),
  (23, '2301'), (23, '2302'),
  (24, '2401'), (24, '2402'), (24, '2403'),
  (25, '2501'), (25, '2502');
