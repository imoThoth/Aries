drop database dfp4f6l1hjchmf;
drop user fcilyucvkvriwf;
create user fcilyucvkvriwf with password '9f12ae2911d703b831d7cf8193a4720dffa40f0f0051457b95d0dacf57b413ae';
create database dfp4f6l1hjchmf with template=template0 owner=fcilyucvkvriwf;
\connect expensetrackerdb;
alter default privileges grant all on tables to fcilyucvkvriwf;
alter default privileges grant all on sequences to fcilyucvkvriwf;

create table et_users(
    user_id integer primary key not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    password text not null
);

create table et_categories(
    category_id integer primary key not null,
    user_id integer not null,
    title varchar(255) not null,
    description varchar(255) not null
);

alter table et_categories add constraint cat_users_fk
foreign key (user_id) references et_users(user_id);

create table et_transactions(
    transactions_id integer primary key not null,
    category_id integer not null,
    user_id integer not null,
    amount numeric(10,2) not null,
    note varchar(255) not null,
    transaction_date bigint not null
);

alter table et_transactions add constraint trans_cat_fk
foreign key (category_id) references et_categories(category_id);
alter table et_transactions add constraint trans_users_fk
foreign key (user_id) references et_users(user_id);

create sequence et_users_seq increment 1 start 1;
create sequence et_categories_seq increment 1 start 1;
create sequence et_transactions_seq increment 1 start 1000;













