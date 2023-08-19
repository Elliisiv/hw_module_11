INSERT INTO Client (name) VALUES
('Fisher Lee'),
('Giovanni Hall'),
('Anderson Sanders'),
('Harold Kelly'),
('Gentry Walker'),
('Oona Allen'),
('Joanna Torres'),
('Ophelia Hughes'),
('Pearl Barnes'),
('Bernadette Price');

INSERT INTO Planet (id, name) VALUES
('MERC1', 'Mercury'),
('VEN2', 'Venus'),
('ERH3', 'Earth'),
('MARS4', 'Mars'),
('JUP5', 'Jupiter');

INSERT INTO Ticket (client_id, from_planet_id,  to_planet_id ) VALUES
(1,'MERC1', 'VEN2'),
(2,'VEN2', 'MARS4'),
(3,'ERH3', 'MERC1'),
(4,'MARS4', 'JUP5'),
(5,'JUP5', 'MERC1'),
(6,'MERC1', 'ERH3'),
(7,'VEN2', 'MARS4'),
(8,'ERH3', 'VEN2'),
(9,'MARS4', 'VEN2'),
(10,'JUP5', 'ERH3');
