create table roles(
id serial primary key,
role_field varchar(255)
);

create table users(
id serial primary key,
username varchar(255),
role_id int references roles(id) 
);

create table rules(
id serial primary key,
rule_field varchar(255)
);

create table categories(
id serial primary key,
category varchar(255)
);

create table states(
id serial primary key,
state varchar(255)
);

create table items(
id serial primary key,
item text,
user_id int references users(id),
categories_id int references categories(id),
state_id int references states(id)
); 

create table attachs(
id serial primary key,
attach varchar(255),
item_id int references items(id)
);

create table comments(
id serial primary key,
comment_field text,
item_id int references items(id)
);

create table roles_rules(
id serial primary key,
role_id int references roles(id),
rule_id int references rules(id)
);


create table states(
id serial primary key,
state varchar(255)
);

create table categories(
id serial primary key,
category varchar(255)
);


create table roles_rules(
id serial primary key,
role_id int references roles(id),
rule_id int references rule(id)
);
