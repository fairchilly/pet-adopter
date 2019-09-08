create table news (id bigint not null auto_increment, author varchar(255), created_at date, html_content TEXT, subtitle varchar(255), title varchar(255), updated_at date, visible bit, primary key (id)) engine=InnoDB;
create table page (id bigint not null auto_increment, html_content TEXT, path varchar(255), priority integer, title varchar(255), primary key (id)) engine=InnoDB;
create table pet (id bigint not null auto_increment, birth_date date, declawed bit, description varchar(255), featured bit, name varchar(255), sex integer, special_needs bit, type_id bigint, primary key (id)) engine=InnoDB;
create table pet_type (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
alter table pet add constraint FKrem5eg7ygof60lwmbo8gif3qg foreign key (type_id) references pet_type (id);
create table news (id bigint not null auto_increment, author varchar(255), created_at date, html_content TEXT, subtitle varchar(255), title varchar(255), updated_at date, visible bit, primary key (id)) engine=InnoDB
create table page (id bigint not null auto_increment, html_content TEXT, path varchar(255), priority integer, title varchar(255), primary key (id)) engine=InnoDB
create table pet (id bigint not null auto_increment, birth_date date, declawed bit, description varchar(255), featured bit, name varchar(255), sex integer, special_needs bit, type_id bigint, primary key (id)) engine=InnoDB
create table pet_type (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
alter table pet add constraint FKrem5eg7ygof60lwmbo8gif3qg foreign key (type_id) references pet_type (id)
