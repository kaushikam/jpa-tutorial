insert into course(id, name, started_date) values (10001, 'JPA in 50 steps', now());
insert into course(id, name, started_date)  values (10002, 'Spring Boot in 100 steps', now());
insert into course(id, name, started_date) values (10003, 'Java web services in 100 steps', now());
insert into passport(id, number) values (40001, 'A23123');
insert into passport(id, number) values (40002, 'A33423');
insert into passport(id, number) values (40003, 'A23432');
insert into student(id, name, passport_id) values (20001, 'Kaushik', 40001);
insert into student(id, name, passport_id) values (20002, 'Shan', 40002);
insert into student(id, name, passport_id) values (20003, 'Sunil', 40003);

insert into review(id, rating, description) values (50001, '5', 'Great Course');
insert into review(id, rating, description) values (50002, '4', 'Wonderful Course');
insert into review(id, rating, description) values (50003, '5', 'Awesome Course');