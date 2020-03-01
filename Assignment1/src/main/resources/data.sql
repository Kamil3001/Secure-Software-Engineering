DELETE FROM mcino_moodle.student_module;
DELETE FROM mcino_moodle.students;
DELETE FROM mcino_moodle.modules;
ALTER TABLE mcino_moodle.modules AUTO_INCREMENT = 1;
DELETE FROM mcino_moodle.coordinators;
DELETE FROM mcino_moodle.credentials;

INSERT INTO credentials(username, password) VALUES
('t.akinwale', 'Temi1234!'),
('k.cierpisz', 'Kamil1234!'),
('n.jasenko', 'Nikolaj1234!'),
('n.cvetkovic', 'Nebojsa1234!'),
('c.beenham', 'Conor1234!'),
('a.heanes', 'Andrew1234!'),
('r.collier', 'Rem1234!'),
('a.lastovsky', 'Alexey1234!');

INSERT INTO coordinators(id, name, surname, username) VALUES
('13245689', 'Rem', 'Collier', 'r.collier'),
('18293048', 'Andrew', 'Heanes', 'a.heanes'),
('17382746', 'Alexey', 'Lastovsky', 'a.lastovsky');

INSERT INTO students(id, name, surname, address, email, is_fee_paid, phone_num, username) VALUES
('16312903', 'Nikolaj', 'Jasenko', '24 Brittas Bay', 'nikolaj.jasenko@mcino.ie', 0, '0838537281', 'n.jasenko'),
('16312904', 'Temitope', 'Akinwale', '25 Wicklow Bay', 'temitope.akinwale@mcino.ie', 0, '0830537282', 't.akinwale'),
('16312905', 'Kamil', 'Cierpisz', '26 Dublin Bay', 'kamil.cierpisz@mcino.ie', 0, '0838237283', 'k.cierpisz'),
('16312906', 'Conor', 'Beenham', '27 Laois Bay', 'conor.beenham@mcino.ie', 0, '0838137284', 'c.beenham'),
('16312907', 'Nebojsa', 'Cvetkovic', '28 German Bay', 'nebojsa.cvetkovic@mcino.ie', 0, '0838437285', 'n.cvetkovic');

INSERT INTO modules(capacity, coordinator_id, is_terminated, name, topics) VALUES
('20', '13245689', 0, 'Secure Software Engineering', 'Ethical Hacking, Network Security, SQLi'),
('30', '13245689', 0, 'Advances in Wireless Networking', 'Network Security, Wireless Standards, Protocols'),
('20', '18293048', 0, 'Object Oriented Programming', 'Ruby, OOP, Software Engineering'),
('30', '18293048', 0, 'Introduction to Tai Chi', 'Posture, Yin Yang, Movement'),
('20', '17382746', 0, 'Software Engineering Project II', 'OOP, Software Engineering, Java'),
('30', '17382746', 0, 'Graphs and Networks', 'Graphs, Mathematics, Geometry');

INSERT INTO student_module(grade, module_id, student_id) VALUES
('A','1','16312903'),
('B','6','16312904'),
('A','2','16312903'),
('A','2','16312904'),
('B','3','16312903'),
('C','3','16312904'),
('D','4','16312903'),
('A','4','16312904'),
('F','1','16312905'),
('A','2','16312906'),
('B','6','16312905'),
('C','5','16312906'),
('D','4','16312905'),
('A','3','16312906'),
('B','2','16312905'),
('D','1','16312906'),
('F','1','16312907'),
('A','3','16312907'),
('B','4','16312907'),
('C','6','16312907');
