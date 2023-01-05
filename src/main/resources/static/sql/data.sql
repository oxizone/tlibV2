INSERT INTO Role values (1, "ROLE_USER");
INSERT INTO Role values (2, "ROLE_ADMIN");
INSERT INTO Role values (3, "ROLE_MANAGER");

INSERT INTO user(id,username,password,role_id, enabled) values (1,'manager','pass',3,true);