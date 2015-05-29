-- Must be run under ihlas_web

insert into users(name, password, left_login_tries, full_name) values ('admin', 'D82494F05D6917BA02F7AAA29689CCB444BB73F20380876CB05D1F37537B7892', 3, 'Admin');
insert into users_groups(user_id, name) values ((select id from users where name = 'admin'), 'admin');
