insert into t_book (id, title, author) values
(1, 'Spring Cloud', 'Spring'),
(2, 'Spring Boot', 'Spring'),
(3, 'Hibernate JPA in Spring', 'Max Mustermann'),
(4, 'Cloud Security', 'Dieter Bolz');

insert into t_role(role_id, name, description) values
(1, 'ROLE_ADMIN', 'Admin role'),
(2, 'ROLE_USER', 'User Role' );

insert into t_user(user_id, first_name, last_name, user_name, user_pwd) values
(1, 'Ngoc', 'Nguyen', 'nn', '$2a$12$5iFVbeooOseTTe4EUU4FyOT/vaJwbozBcQD7B.UZJD6OCzanOAydG'),
(2, 'Max', 'Mustermann', 'mm', '$2a$12$jOqY9fitVf1cxUdYhq4xDOVQwun/j/fkpeq4zVtBt5w/9NwTM6tWe'),
(3, 'Peter', 'Müller', 'pm', '$2a$12$jOqY9fitVf1cxUdYhq4xDOVQwun/j/fkpeq4zVtBt5w/9NwTM6tWe');

insert into t_user_role(user_id, role_id) values
(1, 1), -- nn admin
(1, 2), -- nn user
(2, 2), -- mm user
(3, 2); -- pm user