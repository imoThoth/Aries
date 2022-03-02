drop database dfp4f6l1hjchmf;
drop user fcilyucvkvriwf;
create user fcilyucvkvriwf with password '9f12ae2911d703b831d7cf8193a4720dffa40f0f0051457b95d0dacf57b413ae';
create database dfp4f6l1hjchmf with template=template0 owner=fcilyucvkvriwf;
\connect expensetrackerdb;
alter default privileges grant all on tables to fcilyucvkvriwf;
alter default privileges grant all on sequences to fcilyucvkvriwf;

create table ET_USERS(
    user_id integer primary key not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    password text not null
);

create table ET_CATEGORIES(
    category_id integer primary key not null,
    user_id integer not null,
    title varchar(255) not null,
    description varchar(255) not null
);

alter table ET_CATEGORIES add constraint CAT_USERS_FK
foreign key (USER_ID) references ET_USERS(USER_ID);

create table ET_TRANSACTIONS(
    transactions_id integer primary key not null,
    category_id integer not null,
    user_id integer not null,
    amount numeric(10,2) not null,
    note varchar(255) not null,
    transaction_date bigint not null
);

alter table ET_TRANSACTIONS add constraint TRANS_CAT_FK
foreign key (CATEGORY_ID) references ET_CATEGORIES(CATEGORY_ID);
alter table ET_TRANSACTIONS add constraint TRANS_USERS_FK
foreign key (USER_ID) references ET_USERS(USER_ID);

create sequence ET_USERS_SET increment 1 start 1;
create sequence ET_CATEGORIES_SEQ increment 1 start 1;
create sequence ET_TRANSACTIONS_SEQ increment 1 start 1000;













