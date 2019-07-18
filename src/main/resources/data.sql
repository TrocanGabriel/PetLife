

INSERT INTO clinics (id,name,address,phone, program_start,program_end) VALUES
  (1, 'Aliko', 'Str Motrului Nr 24', '07054203',TO_TIMESTAMP('10:00','hh24:mm'),TO_TIMESTAMP('18:00', 'hh24:mm')),
  (2,'Bill', 'Bd Colonel Oancea Nr 12', '020131',TO_TIMESTAMP('09:00', 'hh24:mm'),TO_TIMESTAMP('19:00', 'hh24:mm')),
  (3,'Folrunsho', 'Str. Soarelui Nr 13B', '0123231',TO_TIMESTAMP('10:00', 'hh24:mm'),TO_TIMESTAMP('20:00', 'hh24:mm'));

insert into  owners (id, address, city, cnp, email, first_name,last_name,mobile) values
 (1,'Str Medievala Nr. 12','Pitesti ','12345123141','pion@gmail.com','Ion','Popescu','0760832332'),
 (2,'Bd Dimitrie Cantemir Nr 7','Bucuresti','02345123141','gelena@yahoo.com','Elene','Ganea','0760821321');

 insert into pets (id,name,age,owner_id) values
 (1,'puck',2,'1'),
 (2,'john',2,'2'),
 (3,'jug',10,'1');

 insert into specialties(id, name, description) values
 (1,'Nutrition','apecialized in nutrition'),
 (2,'Microbiology','specialized in microbiology'),
 (3,'Toxicology','apecialized in toxicology');

 insert into vets (id,last_name,first_name, age, clinic_id) values
 (1,'Popa','Gabriel',25,1),
 (2,'Tanase','Marius',35,1),
 (3,'Pricop','Alexandra',28,2);

insert into vet_specialty (vet_id,specialty_id) values
    (1,1),(1,2),(2,1),(2,3),(3,1),(3,2);
INSERT INTO appointments (id, clinic_id, owner_cnp,pet_id,vet_id) values
    (1,1,'12345123141',1,1),
    (2,1,'12345123141',1,1),
    (3,2,'02345123141',2,3),
    (4,1,'12345123141',3,2);

insert into appointment_details (id, date, description, appointment_id) values
    (1,'2019-10-09 12:00','desc1',1),
    (2,'019-12-01 10:30','desc2',2),
    (3,'2019-7-12 16:00','desc3',3),
    (4,'2019-10-12 09:45','desc4',4);

