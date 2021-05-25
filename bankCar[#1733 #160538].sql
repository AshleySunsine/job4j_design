create table body(
id serial primary key,
name varchar(255)
);

create table engine(
id serial primary key,
name varchar(255)
);
create table transmission(
id serial primary key,
name varchar(255)
);

create table car(
id serial primary key,
name varchar(255),
body_id int references body(id),
engine_id int references engine(id),
trans_id int references transmission(id)
);

insert into body(name) values ('red');
insert into body(name) values ('blue');
insert into body(name) values ('green');
insert into body(name) values ('pinky');

insert into engine(name) values ('ferra');
insert into engine(name) values ('lamba');
insert into engine(name) values ('lada');
insert into engine(name) values ('morgenshtern');

insert into transmission(name) values ('4-steps');
insert into transmission(name) values ('5-steps');
insert into transmission(name) values ('8-steps');
insert into transmission(name) values ('timati-steps');

insert into car(name, body_id, engine_id, trans_id) values ('Car_1', 1, 1, 1);
insert into car(name, body_id, engine_id, trans_id) values ('Car_2', 2, 2, 2);
insert into car(name, body_id, engine_id, trans_id) values ('Car_3', 3, 3, 3);

select * from car left join body on car.body_id = body.id left join engine on car.engine_id = engine.id left join transmission on car.trans_id = transmission.id;
select * from body left join car on car.body_id = body.id where car.body_id is null;
select * from engine left join car on car.engine_id=engine.id where car is null;
select * from transmission left join car on car.trans_id = transmission.id where car is null;
