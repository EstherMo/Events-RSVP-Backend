INSERT INTO PROGRAMS
	(EVENT_NAME,PROGRAM_DATE,CATEGORY,LOCATION)
VALUES
	('Moho Martial Arts','2018-03-10','social','New york'),
	('Mindful Munchies','2018-03-19','service','New york'),
	('Syrian Shabbat Meal','2018-03-17', 'culture','New york'),
	('Gender in Judaism Workshop with Abby Stein', '2018-03-03','learning','New york'),
	('Queer Arabic Dance Night','2018-02-14','culture','New york');

INSERT INTO PARTICIPANTS
	(FIRST_NAME,LAST_NAME,EMAIL_ADDRESS)
VALUES
	('Esther','Mohadeb','123@gmail.com'),
	('Chana','Prus','456@gmail.com'),
	('Eli','Reiter','789@gmail.com'),
	('Nisi', 'Goldstein',' 345@gmail.com'),
	('kate', 'black','897@gmail.com'),
	('Shiven', 'Nayee',' kljl@gmail.com'),
	('Benjamin', 'Li',' bli@gmail.com'),
	('Luke', 'McDonald',' lmc@gmail.com'),
	('David', 'Lazarus',' tmq@gmail.com'),
	('William', 'Rupertis','897@gmail.com'),
    ('Mke', 'Black',' kljl@gmail.com'),
    ('Dario', 'Lunas',' bli@gmail.com'),
    ('Louis', 'Porteous',' lmc@gmail.com'),
    ('Dylan', 'Zibel',' tmq@gmail.com'),
    ('Eric', 'Getter',' lmc@gmail.com'),
    ('Jacqui', 'Brooke',' tmq@gmail.com');

INSERT INTO PROGRAMS_PARTICIPANTS
	(PROGRAM_ID,PARTICIPANT_ID)
VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(2,5),
	(2,6),
	(2,7),
	(2,8),
	(3,9),
	(3,10),
	(3,11),
	(3,12),
	(4,13),
	(4,14),
	(4,15),
	(4,16);



--	A date Format: YYYY-MM-DD



