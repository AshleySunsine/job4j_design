create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('vidik', 4500);
insert into devices(name, price) values('phone', 8000);
insert into devices(name, price) values('teaport', 2200);
insert into devices(name, price) values('clock', 1100);

insert into people(name) values('Vasya');
insert into people(name) values('Petya');
insert into people(name) values('Lesha');
insert into people(name) values('Sasha');

insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(1, 3);

insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(2, 3);
insert into devices_people(device_id, people_id) values(2, 4);

insert into devices_people(device_id, people_id) values(3, 1);
insert into devices_people(device_id, people_id) values(3, 3);
insert into devices_people(device_id, people_id) values(3, 4);

insert into devices_people(device_id, people_id) values(4, 3);
insert into devices_people(device_id, people_id) values(4, 4);

select avg(d.price) from devices as d;
select p.name, avg(d.price) from people as p, devices as d, devices_people as dp where dp.people_id=p.id and dp.device_id=d.id group by p.name;
select * FROM (select p.name, avg(d.price) as av from people as p, devices as d, devices_people as dp where dp.people_id=p.id and dp.device_id=d.id group by p.name) as main_table where av > 5000;
