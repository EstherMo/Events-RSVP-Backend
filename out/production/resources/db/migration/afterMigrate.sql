INSERT INTO PROGRAMS
	(EVENT_NAME,PROGRAM_DATE,CATEGORY)
VALUES
	('Moho Martial Arts','2018-03-10','social'),
	('Mindful Munchies','2018-03-19','service'),
	('Syrian Shabbat Meal','2018-03-17', 'culture'),
	('Gender in Judiasm Workshop with Abby Stein', '2018-03-03',' learning');

INSERT INTO PARTICIPANTS
	(FIRST_NAME,LAST_NAME,EMAIL_ADDRESS)
VALUES
	('Esther','Mohadeb','123@gmail.com'),
	('Chana','Prus','456@gmail.com'),
	('Eli','Reiter','789@gmail.com'),
	('Nisi', 'Goldstein',' 345@gmail.com');

INSERT INTO PROGRAMS_PARTICIPANTS
	(PROGRAM_ID,PARTICIPANT_ID)
VALUES
	(1,1);


--	A date Format: YYYY-MM-DD



