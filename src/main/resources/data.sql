

INSERT INTO clinics (id,name,address,phone, program_start,program_end) VALUES
  (1, 'Aliko', 'Dangote', '07054203',9,18),
  (2,'Bill', 'Gaterea', '020131',9,18),
  (3,'Folrunsho', 'Alakija', '0123231',10,20);


insert into  owners (id, address, city, cnp, email, first_name,last_name,mobile) values
 (1,'Str 1','City 1','1','email1','firstname1','lastname1','mobile1'),
 (2,'Str 2','City 2','2','email2','firstname2','lastname2','mobile2');

 insert into pets (id,name,age,owner_cnp) values
 (1,'puck',2,'1'),
 (2,'john',2,'2'),
 (3,'jug',10,'1');

 insert into specialties(id, name, description) values
 (1,'special1','desc1'),
 (2,'special2','desc2'),
 (3,'special3','desc3');

 insert into vets (id,last_name,first_name, age, clinic_id) values
 (1,'last1','first1',25,1),
 (2,'last2','first2',35,2),
 (3,'last3','first3',28,3);

insert into vet_specialty (vet_id,specialty_id) values
    (1,1),(1,2),(2,1),(2,3),(3,1),(3,2);
INSERT INTO appointments (id, date,description, clinic_id, owner_cnp,pet_id,vet_id) values
    (1,'2019-10-09 12:00','desc1',1,'1',1,1),
    (2,'019-12-01 10:30','desc2',1,'1',1,1),
    (3,'2019-7-12 16:00','desc3',2,'2',2,2),
    (4,'2019-10-12 09:45','desc4',1,'1',3,2);

