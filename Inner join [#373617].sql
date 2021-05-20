create table levels(
id serial primary key,
name varchar(255)
);

create table slesar(
id serial primary key,
name varchar(255),
level int references levels(id)
);

insert into levels(name) values('level_1');
insert into levels(name) values('level_2');
insert into levels(name) values('level_3');
insert into slesar(name, level) values('Vasya', 1);
insert into slesar(name, level) values('Petya', 2);
insert into slesar(name, level) values('Sasha', 3);

select * from slesar join levels as l on l.id >= 2;
select * from slesar as s join levels on s.name = 'Petya';
select * from slesar as s join levels as l on s.name = 'Petya' and l.id = 2;

