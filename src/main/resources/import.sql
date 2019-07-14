insert into course(id, name, started_date) values (10001, 'JPA in 50 steps', now());
insert into course(id, name, started_date)  values (10002, 'Spring Boot in 100 steps', now());
insert into course(id, name, started_date) values (10003, 'Java web services in 100 steps', now());

insert into passport(id, number) values (40001, 'A23123');
insert into passport(id, number) values (40002, 'A33423');
insert into passport(id, number) values (40003, 'A23432');
insert into passport(id, number) values (40004, 'A33567');


insert into student(id, name, passport_id) values (20001, 'Kaushik', 40001);
insert into student(id, name, passport_id) values (20002, 'Shan', 40002);
insert into student(id, name, passport_id) values (20003, 'Sunil', 40003);
insert into student(id, name, passport_id) values (20004, 'Prem', 40004);

insert into course_student (course_id, student_id) values (10001, 20001);
insert into course_student (course_id, student_id) values (10001, 20003);
insert into course_student (course_id, student_id) values (10003, 20002);

insert into review(id, rating, description, course_id) values (50001, '5', 'Great Course', 10002);
insert into review(id, rating, description, course_id) values (50002, '4', 'Wonderful Course', 10002);
insert into review(id, rating, description, course_id) values (50003, '5', 'Awesome Course', 10003);
insert into review(id, rating, description, course_id) values (50004, '5', 'Unbelievable Course', 10001);

insert into employee(id, name, salary, employee_type) values (60001, 'Kaushik', 7000, 'FullTimeEmployee');
insert into employee(id, name, hourly_wage, employee_type) values (60002, 'Shan', 100, 'PartTimeEmployee');

insert into full_time_teacher(id, name, salary) values (70001, 'Kaushik', 20000);

insert into animal(id, name) values (80001, 'Home Frog');
insert into amphibian(id, type) values (80001, 'FROG');

insert into book(id, title, number_of_pages) values (90001, 'Java', 100);
insert into blog_post(id, title, url) values (90002, 'Hibernate', 'http://www.hibernate.com');