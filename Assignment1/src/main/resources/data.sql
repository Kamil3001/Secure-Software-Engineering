DELETE FROM mcino_moodle.student_module;
DELETE FROM mcino_moodle.students;
DELETE FROM mcino_moodle.modules;
ALTER TABLE mcino_moodle.modules AUTO_INCREMENT = 1;
DELETE FROM mcino_moodle.coordinators;
DELETE FROM mcino_moodle.credentials;

INSERT INTO credentials(username, password) VALUES
('t.akinwale', 'Root1234!'),
('k.cierpisz', 'Root1234!'),
('n.jasenko', 'Root1234!'),
('n.cvetkovic', 'Root1234!'),
('c.beenham', 'Root1234!'),
('a.heanes', 'Root1234!'),
('r.collier', 'Root1234!'),
('a.lastovsky', 'Root1234!'),
('d.aldred', 'Root1234!'),
('a.golden', 'Root1234!'),
('m.clifford', 'Root1234!'),
('n.charlton', 'Root1234!'),
('s.rasmussen', 'Root1234!'),
('s.corrigan', 'Root1234!'),
('k.kearns', 'Root1234!'),
('ava.hough', 'Root1234!'),
('sinead.lin', 'Root1234!'),
('ps.knott', 'Root1234!'),
('hum.beach', 'Root1234!');

INSERT INTO coordinators(id, name, surname, username, nationality) VALUES
('13245689', 'Rem', 'Collier', 'r.collier', 'UK'),
('18293048', 'Andrew', 'Heanes', 'a.heanes', 'IRE'),
('17382746', 'Alexey', 'Lastovsky', 'a.lastovsky', 'RUS');

INSERT INTO students(id, name, surname, address, email, is_fee_paid, phone_num, username, nationality) VALUES
('16312903', 'Nikolaj', 'Jasenko', '19 Priory Hall', 'student16312903@mcino.ie', 0, '0838537281', 'n.jasenko', 'UK'),
('16312904', 'Temitope', 'Akinwale', '25 Wicklow Bay', 'student16312904@mcino.ie', 0, '0830537282', 't.akinwale', 'IRE'),
('16312905', 'Kamil', 'Cierpisz', '26 Dublin Bay', 'student16312905@mcino.ie', 0, '0838237283', 'k.cierpisz', 'UK'),
('16312906', 'Conor', 'Beenham', '27 Laois Bay', 'student16312906@mcino.ie', 0, '0838137284', 'c.beenham', 'IRE'),
('16312907', 'Nebojsa', 'Cvetkovic', '28 German Bay', 'student16312907@mcino.ie', 0, '0838437285', 'n.cvetkovic', 'USA'),
('16312908', 'Dolores', 'Aldred', '4553 Broaddus Avenue', 'student16312908@mcino.ie', 0, '0838437285', 'd.aldred', 'IRE'),
('16312909', 'Anaiya', 'Golden', '1866 Cimmaron Road', 'student16312909@mcino.ie', 0, '0838437285', 'a.golden', 'UK'),
('16312910', 'Malak', 'Clifford', '28 German Bay', 'student16312910@mcino.ie', 0, '0838437285', 'm.clifford', 'PL'),
('16312911', 'Naveed', 'Charlton', '29 German Bay', 'student16312911@mcino.ie', 0, '0838437285', 'n.charlton', 'PL'),
('16312912', 'Sydney', 'Rasmussen', '30 German Bay', 'student16312912@mcino.ie', 0, '0838437285', 's.rasmussen', 'USA'),
('16312913', 'Shiv', 'Corrigan', '31 German Bay', 'student16312913@mcino.ie', 0, '0838437285', 's.corrigan', 'IRE'),
('16312914', 'Kody', 'Kearns', '32 German Bay', 'student16312914@mcino.ie', 0, '0838437285', 'k.kearns', 'IRE'),
('16312915', 'Ava-Rose', 'Hough', '33 German Bay', 'student16312915@mcino.ie', 0, '0838437285', 'ava.hough', 'USA'),
('16312916', 'Sinead', 'Lin', '34 German Bay', 'student16312916@mcino.ie', 0, '0838437285', 'sinead.lin', 'UK'),
('16312917', 'Precious', 'Knott', '36 German Bay', 'student16312917@mcino.ie', 0, '0838437285', 'ps.knott', 'PL'),
('16312918', 'Humaira', 'Beach', '37 German Bay', 'student16312918@mcino.ie', 0, '0838437285', 'hum.beach', 'IRE');


INSERT INTO modules(capacity, coordinator_id, is_terminated, name, topics) VALUES
('20', '13245689', 0, 'Secure Software Engineering', 'Ethical Hacking, Network Security, SQLi'),
('30', '13245689', 0, 'Advances in Wireless Networking', 'Network Security, Wireless Standards, Protocols'),
('20', '18293048', 0, 'Object Oriented Programming', 'Ruby, OOP, Software Engineering'),
('30', '18293048', 0, 'Introduction to Tai Chi', 'Posture, Yin Yang, Movement'),
('20', '17382746', 0, 'Software Engineering Project II', 'OOP, Software Engineering, Java'),
('20', '18293048', 1, 'Computer Programming I', 'C, Memory, Software Engineering'),
('30', '13245689', 0, 'Computer Programming II', 'C++, OOP, Software Engineering'),
('20', '17382746', 1, 'Software Engineering Project I', 'OOP, Software Engineering, C'),
('30', '17382746', 0, 'Graphs and Networks', 'Graphs, Mathematics, Geometry');

INSERT INTO student_module(grade, module_id, student_id) VALUES
('', '1', '16312903'),
('', '1', '16312904'),
('', '1', '16312905'),
('', '2', '16312906'),
('', '2', '16312907'),
('', '2', '16312908'),
('', '3', '16312909'),
('', '3', '16312910'),
('', '3', '16312911'),
('', '4', '16312912'),
('', '4', '16312913'),
('', '4', '16312914'),
('', '5', '16312915'),
('', '5', '16312916'),
('', '5', '16312917'),
('A', '6', '16312918'),
('A', '6', '16312903'),
('B', '6', '16312904'),
('', '7', '16312905'),
('', '7', '16312906'),
('', '7', '16312907'),
('', '1', '16312908'),
('', '2', '16312909'),
('', '9', '16312910'),
('', '4', '16312911'),
('', '5', '16312912'),
('B', '6', '16312913'),
('B', '8', '16312914'),
('', '9', '16312915'),
('', '7', '16312916'),
('B', '6', '16312917'),
('', '5', '16312918'),
('', '4', '16312903'),
('', '3', '16312904'),
('', '9', '16312905'),
('', '5', '16312906'),
('', '4', '16312907'),
('', '4', '16312908'),
('', '1', '16312909'),
('', '5', '16312910'),
('C', '6', '16312911'),
('', '7', '16312912'),
('A', '8', '16312913'),
('', '9', '16312914'),
('', '1', '16312915'),
('', '9', '16312916'),
('', '7', '16312917'),
('', '1', '16312918');