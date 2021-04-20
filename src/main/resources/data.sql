DROP TABLE IF EXISTS visit;
DROP TABLE IF EXISTS doctor_patient;
DROP TABLE IF EXISTS member_rule;
DROP TABLE IF EXISTS member;

CREATE TABLE member (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  phone VARCHAR(250),
  address VARCHAR(250),
  email VARCHAR(250),
  gender VARCHAR(1),
  birthday DATE,    
  legal_id VARCHAR(250) NOT NULL,
  anonymous_enabled VARCHAR(1) DEFAULT 'F'
);

CREATE TABLE member_rule (
	id INT AUTO_INCREMENT  PRIMARY KEY,
	member_id INT,
	rule_id VARCHAR(250)		
);

CREATE TABLE doctor_patient (
	id INT AUTO_INCREMENT  PRIMARY KEY,
	doctor_id INT,
	patient_id INT		
);

CREATE TABLE visit (
	id INT AUTO_INCREMENT  PRIMARY KEY,
	patient_id INT,
	doctor_id INT,
	visit_time DATETIME,
	visit_type VARCHAR(250),
	height INT,
	weight INT,
	blood_pressure INT,
	heart_rate INT,
	diagnostic LONGTEXT,
	treatment LONGTEXT,
	note VARCHAR(250)
);

INSERT INTO member (name, phone, address, legal_id, email, gender, birthday, anonymous_enabled) VALUES 
('Jonathan', '212-222-1111', 'New York City', '1111', 'Jonathan@nydig.com', 'M', '2001-01-01', 'T'),
('Yibing', '212-222-2222', 'Boston', '2222', 'Yibing@nydig.com', 'F', '2002-01-02', 'F'),
('Jonni', '212-222-3333', 'Los Angeles', '3333', 'Jonni@nydig.com', 'M', '2003-01-03', 'T'),
('Li', '212-222-4444', 'San Antonio', '4444', 'Li@nydig.com', 'M', '2002-01-04', 'F'),
('Megan', '212-222-5555', 'Dallas', '5555', 'Megan@nydig.com', 'F', '2001-01-05', 'T');





INSERT INTO member_rule (member_id, rule_id) 
SELECT id, 'DOCTOR' from member where legal_id = '1111';

INSERT INTO member_rule (member_id, rule_id) 
SELECT id, 'DOCTOR' from member where legal_id = '2222';

INSERT INTO member_rule (member_id, rule_id) 
SELECT id, 'PATIENT' from member where legal_id = '3333';

INSERT INTO member_rule (member_id, rule_id) 
SELECT id, 'PATIENT' from member where legal_id = '4444';

INSERT INTO member_rule (member_id, rule_id) 
SELECT id, 'PATIENT' from member where legal_id = '5555';


INSERT INTO doctor_patient (doctor_id, patient_id) 
SELECT A.id, B.id FROM member A, member B  WHERE A.legal_id = '1111' AND B.legal_id = '2222';
INSERT INTO doctor_patient (doctor_id, patient_id) 
SELECT A.id, B.id FROM member A, member B  WHERE A.legal_id = '1111' AND B.legal_id = '3333';
INSERT INTO doctor_patient (doctor_id, patient_id) 
SELECT A.id, B.id FROM member A, member B  WHERE A.legal_id = '1111' AND B.legal_id = '4444';
INSERT INTO doctor_patient (doctor_id, patient_id) 
SELECT A.id, B.id FROM member A, member B  WHERE A.legal_id = '1111' AND B.legal_id = '5555';


INSERT INTO visit (patient_id, doctor_id, visit_time, blood_pressure, heart_rate, height, weight, diagnostic, treatment) 
SELECT A.id, B.id, '2005-01-01', 110, 70, 170, 72, 'normal', 'nothing'
FROM member A, member B  WHERE A.legal_id = '3333' AND B.legal_id = '1111';

INSERT INTO visit (patient_id, doctor_id, visit_time, blood_pressure, heart_rate, height, weight, diagnostic, treatment) 
SELECT A.id, B.id, '2006-01-01', 120, 80, 170, 82, 'Gain Weight', 'Eat Less'
FROM member A, member B  WHERE A.legal_id = '3333' AND B.legal_id = '1111';

INSERT INTO visit (patient_id, doctor_id, visit_time, blood_pressure, heart_rate, height, weight, diagnostic, treatment) 
SELECT A.id, B.id, '2007-02-01', 130, 90, 170, 92, 'No Good', 'Take medicine'
FROM member A, member B  WHERE A.legal_id = '3333' AND B.legal_id = '1111';


