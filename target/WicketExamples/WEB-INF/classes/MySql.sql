SHOW TABLES;

create table w_users (user_id int, user_name varchar(32), dob date, create_ts timestamp, 
update_ts timestamp, modified_by varchar(32), 
PRIMARY KEY w_user_pk1 (user_id),    UNIQUE KEY w_user_uk1 (user_name) );

insert into w_users values (1, 'New Jackmen', '1965-12-21', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin');

select * from w_users;