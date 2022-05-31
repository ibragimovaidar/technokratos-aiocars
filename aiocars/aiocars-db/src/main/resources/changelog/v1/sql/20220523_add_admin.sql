INSERT INTO account(id, username, email, hash_password, first_name, last_name)
VALUES ('1fed81b6-da29-11ec-9d64-0242ac120002', 'admin', 'admin@aiocars.com', '$2y$10$NOWC5JtrnZi/DTV1mENz9.garEy1x0S5A.d15DTe33YzlEtECVAbG', 'Admin', 'Admin');

INSERT INTO account_roles(account_id, role_id)
VALUES ('1fed81b6-da29-11ec-9d64-0242ac120002', 'a6fe08b5-56de-4507-9d43-14e0321d2d2b');