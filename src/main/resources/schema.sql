create table t_book (book_id bigint not null, author varchar(255), title varchar(255), primary key (book_id))
create table t_book_rate (book_id bigint not null, rate_avg double, rate_cnt bigint, primary key (book_id))

alter table t_book_rate add constraint fk_book_rate_book foreign key (book_id) references t_book

create table t_role (role_id bigint not null, description varchar(255), name varchar(255), primary key (role_id))
create table t_user (user_id bigint not null, first_name varchar(255), last_name varchar(255), user_name varchar(255), user_pwd varchar(255), primary key (user_id))
create table t_user_role (user_id bigint not null, role_id bigint not null)

alter table t_user_role add constraint fk_user_role_role foreign key (role_id) references t_role
alter table t_user_role add constraint fk_user_role_user foreign key (user_id) references t_user