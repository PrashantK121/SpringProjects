
create table  persistent_logins ( 
  username varchar2(100) not null, 
  series varchar2(64) primary key, 
  token varchar2(64) not null, 
  last_used date not null
);

delete from  user_role;
delete from  roles;
delete from  users;


INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_ACTUATOR');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_USER');

INSERT INTO users (id, email, password, name) VALUES (1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin');
INSERT INTO users (id, email, password, name) VALUES (3, 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'User');



insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values(1,2);
insert into user_role(user_id, role_id) values(1,3);
insert into user_role(user_id, role_id) values(3,2);
