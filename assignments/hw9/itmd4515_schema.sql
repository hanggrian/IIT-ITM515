CREATE SCHEMA IF NOT EXISTS itmd4515;
USE itmd4515;

DROP TABLE IF EXISTS student_role;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS department;

CREATE TABLE department(
   department_name VARCHAR(50) NOT NULL,
   website VARCHAR(254) NOT NULL,
   email VARCHAR(254) NOT NULL,
   phone_number VARCHAR(10) NOT NULL,
   building VARCHAR(100),
   description TEXT,
   PRIMARY KEY(department_name)
);

CREATE TABLE role(
  role_title VARCHAR(15) NOT NULL,
  PRIMARY KEY(role_title)
);

CREATE TABLE student(
  student_id VARCHAR(15) NOT NULL,
  email VARCHAR(254) NOT NULL,
  password VARCHAR(115) NOT NULL,
  first_name VARCHAR(50),
  last_name VARCHAR(100),
  department_name VARCHAR(50),
  PRIMARY KEY(student_id),
  KEY idx_fk_department_name(department_name),
  CONSTRAINT fk_student_department FOREIGN KEY(department_name) REFERENCES department(department_name)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE student_role(
  role_title VARCHAR(15) NOT NULL,
  student_id VARCHAR(15) NOT NULL,
  PRIMARY KEY(role_title, student_id),
  KEY idx_fk_student_id(student_id),
  CONSTRAINT fk_student_role_role FOREIGN KEY(role_title) REFERENCES role(role_title)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_student_role_student FOREIGN KEY(student_id) REFERENCES student(student_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
);
