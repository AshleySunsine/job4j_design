drop table cat; --на случай, если таблица уже существует
create table cat(
    id serial primary key,
    nameis varchar(255),
    characteristic text,
    age int,
    male bool
);
insert into cat(nameis, characteristic, age, male) values('One', 'good boy', 3, true);
insert into cat(nameis, characteristic, age, male) values('Lady', 'good girl', 2, false);
insert into cat(nameis, characteristic, age, male) values('Lady-One', 'child', 1, false);
insert into cat(nameis, characteristic, age, male) values('wait', 'child', 0, true);

update cat set nameis = 'Lady-New', male = false where (nameis='wait');

select * from cat;

--delete from cat;