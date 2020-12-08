-- STATUSES TABLE
drop table if exists statuses cascade;
create table statuses (
                          id          bigint auto_increment,
                          `name`      varchar(30) not null,
                          primary key (id)
);

-- CONDITIONS TABLE
drop table if exists conditions cascade;
create table conditions (
                          id          bigint auto_increment,
                          `name`      varchar(30) not null,
                          primary key (id)
);

-- USERS TABLE
drop table if exists users cascade;
create table users (
                       id              bigint auto_increment,
                       username        varchar(30) not null,
                       password        varchar(80) not null,
                       email           varchar(50) unique,
                       phone           varchar(30) unique,
                       primary key (id)
);

-- ROLES TABLE
drop table if exists roles cascade;
create table roles (
                       id         bigint auto_increment,
                       `name`     varchar(50) not null,
                       primary key (id)
);

-- USERS_ROLES TABLE
drop table if exists users_roles cascade;
create table users_roles (
                             user_id             bigint not null,
                             role_id             bigint not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references users (id),
                             foreign key (role_id) references roles (id)
);

-- USERS_STATUSES TABLE
drop table if exists users_statuses cascade;
create table users_statuses (
                             user_id               bigint not null,
                             status_id             bigint not null,
                             primary key (user_id, status_id),
                             foreign key (user_id) references users (id),
                             foreign key (status_id) references statuses (id)
);

-- PRODUCTS TABLE
drop table if exists products cascade;
create table products (
                          id          bigint auto_increment,
                          `name`      varchar(50) not null,
                          price       decimal not null,
                          count       int,
                          primary key (id)
);

-- CATEGORIES TABLE
drop table if exists categories cascade;
create table categories (
                            id          bigint auto_increment,
                            `name`      varchar(30) not null,
                            primary key (id)
);

-- PRODUCTS_CATEGORIES TABLE
drop table if exists products_categories cascade;
create table products_categories (
                                     product_id          bigint not null ,
                                     category_id          bigint not null,
                                     primary key (product_id, category_id),
                                     foreign key (product_id) references products (id),
                                     foreign key (category_id) references categories (id)
);

-- DETAILS TABLE
drop table if exists details cascade;
create table details (
                                id                 bigint auto_increment,
                                product_id         bigint not null,
                                amount             int,
                                price              numeric(19, 2),
                                primary key (id),
                                foreign key (product_id) references products (id)
);

-- ORDERS TABLE
drop table if exists orders cascade;
create table orders (
                    id                      bigint auto_increment,
                    changed                 timestamp,
                    created                 timestamp,
                    sum                     numeric(19, 2),
                    details_id              bigint not null,
                    primary key (id),
                    foreign key (details_id) references details (id)
);

-- ORDERS_USERS TABLE
drop table if exists orders_users cascade;
create table orders_users (
                    order_id            bigint not null,
                    user_id             bigint not null,
                    primary key (order_id, user_id),
                    foreign key (order_id) references orders (id),
                    foreign key (user_id) references users (id)

);

-- ORDERS_CONDITIONS TABLE
drop table if exists orders_conditions cascade;
create table orders_conditions (
                    order_id            bigint not null,
                    condition_id     bigint not null,
                    primary key (order_id, condition_id),
                    foreign key (order_id) references orders (id),
                    foreign key (condition_id) references conditions (id)
);

-- DETAILS_PRODUCTS TABLE
drop table if exists details_products cascade;
create table details_products (
                    detail_id           bigint not null,
                    product_id          bigint not null,
                    primary key (detail_id, product_id),
                    foreign key (detail_id) references details (id),
                    foreign key (product_id) references products (id)
);

-- INIT ROLES
insert into roles (`name`) values
('admin'),
('user');

-- INIT STATUSES
insert into statuses (`name`) values
('active'),
('blocked');

-- INIT STATUSES_ORDERS
insert into conditions (`name`) values
('created'),
('confirmed'),
('paid'),
('delivered'),
('closed'),
('canceled');

-- INIT USERS
insert into users (username, password, email, phone) values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com', '999-333-34-34'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com', '999-333-34-35'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user3@gmail.com', '999-333-34-36'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user4@gmail.com', '999-333-34-37'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user5@gmail.com', '999-333-34-38'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user6@gmail.com', '999-333-34-39'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user7@gmail.com', '999-333-34-40'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user8@gmail.com', '999-333-34-41'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user9@gmail.com', '999-333-34-42'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user10@gmail.com', '999-333-34-43'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user11@gmail.com', '999-333-34-44'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user12@gmail.com', '999-333-34-45'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user13@gmail.com', '999-333-34-46');

-- INIT USERS_ROLES
insert into users_roles values
(1, 1),
(2, 2),
(3, 2),
(4, 1),
(5, 2),
(6, 2),
(7, 1),
(8, 2),
(9, 2),
(10, 1),
(11, 2),
(12, 2),
(13, 2);

-- INIT USERS_STATUSES
insert into users_statuses values
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2);

-- INIT PRODUCTS
insert into products (`name`, price, count) values
('bread', 7.0, 11),
('cheese', 70.0, 20),
('orange', 25.4, 10),
('potato', 15.3, 45),
('cucumber', 35.9, 44),
('coconut', 29.8, 15),
('pan', 4.2, 30),
('banana', 3.5, 100),
('rum', 56.0, 13),
('vodka', 24.5, 20),
('gin', 17.7, 14);

-- INIT CATEGORIES
insert into categories (`name`) values
('bakery'),
('fruits'),
('vegetables'),
('beverages'),
('dairy');

-- INIT PRODUCTS_CATEGORIES
insert into products_categories (product_id, category_id) values
(1, 1),
(2, 5),
(3, 2),
(4, 3),
(5, 3),
(6, 2),
(7, 1),
(8, 2),
(9, 4),
(10, 4),
(11, 4);