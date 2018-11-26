use projectdb

create table document(
id bigint not null,
name varchar(100) not null,
data blob not null,
primary key(id)
);