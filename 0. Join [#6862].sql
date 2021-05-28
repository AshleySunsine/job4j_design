create table departments(
id serial primary key,
name varchar(255)
);
create table emploers(
id serial primary key,
name varchar(255),
dep_id int references departments(id)
);

insert into departments(name) values ('dep_1');
insert into departments(name) values ('dep_2');
insert into departments(name) values ('dep_3');
insert into departments(name) values ('dep_4');
insert into departments(name) values ('dep_5');
insert into departments(name) values ('dep_6');

insert into emploers(name, dep_id) values ('emp_1', 1);
insert into emploers(name, dep_id) values ('emp_2', 2);
insert into emploers(name, dep_id) values ('emp_3', 2);
insert into emploers(name, dep_id) values ('emp_4', 3);
insert into emploers(name, dep_id) values ('emp_5', 4);
insert into emploers(name, dep_id) values ('emp_6', 4);
insert into emploers(name, dep_id) values ('emp_7', null);
insert into emploers(name, dep_id) values ('emp_8', null);

--select * from emploers as e left join departments as d on e.dep_id = d.id;
--select * from departments as d right join emploers as e on e.dep_id = d.id;
--select * from emploers as e full join departments as d on e.dep_id = d.id;
--select * from emploers as e cross join departments as d;
--select * from departments as d left join emploers as e on e.dep_id = d.id where e.dep_id is null;

--select * from emploers as e left join departments as d on e.dep_id = d.id;
--select * from departments as d right join emploers as e on e.dep_id = d.id;



create table teens(
id serial primary key,
name varchar(255),
gender varchar(255)
);

insert into teens(name, gender) values ('t_1', 'M');
insert into teens(name, gender) values ('t_2', 'M');
insert into teens(name, gender) values ('t_3', 'M');
insert into teens(name, gender) values ('t_4', 'M');
insert into teens(name, gender) values ('t_5', 'W');
insert into teens(name, gender) values ('t_6', 'W');
insert into teens(name, gender) values ('t_7', 'W');
insert into teens(name, gender) values ('t_8', 'W');

select t1.name as m, t2.name as w, (t1.gender, t2.gender) from teens as t1 cross join teens as t2 where t1.gender != t2.gender;