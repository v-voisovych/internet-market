delete from mydbfortests.user_roles;
delete from mydbfortests.user;
delete from mydbfortests.role;

insert into mydbfortests.user (id, username, password, status) values
(1, 'admin', '$2a$10$.Ea8ivGUBBnLQsIxMlBSou6oBa1wLWhHHI8qc4dGuIKm0EqgCU1Oe', 'ACTIVE'),
(2, 'seller', '$2a$10$rLSm06IaEWtmvGpFirDmYuWfmVt3CjXQ0FmHvB47cJh59d2K4Y13C', 'ACTIVE'),
(3, 'eint', '$2a$10$A5VG8Nakbf/8ijlT1Apjg.43.WWHG3gBfxxsoGRahFyI432tQI6wu', 'ACTIVE');

insert into mydbfortests.role (id, name) values
(1, 'ROLE_USER'),
(2, 'ROLE_SELLER'),
(3, 'ROLE_ADMIN');

insert into mydbfortests.user_roles (users_id, roles_id) VALUES
(1,3),
(2,3),
(3,3);
