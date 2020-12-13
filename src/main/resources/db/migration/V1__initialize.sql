-- USERS TABLE
drop table if exists users cascade;
create table users (
                       id              bigint auto_increment,
                       username        varchar(30) not null,
                       password        varchar(80) not null,
                       email           varchar(50) unique not null,
                       phone           varchar(30) unique not null,
                       role            varchar(30) not null,
                       status          varchar(30) not null,
                       primary key (id)
);

-- PRODUCTS TABLE
drop table if exists products cascade;
create table products (
                          id              bigint auto_increment,
                          `name`          varchar(50) not null,
                          price           decimal not null,
                          count           decimal,
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
                    condition               varchar(30),
                    primary key (id),
                    foreign key (details_id) references details (id)
);

-- BUCKETS
drop table if exists buckets cascade;
create table buckets (
                    id              bigint auto_increment,
                    user_id         bigint not null,
                    primary key (id),
                    foreign key (user_id) references users (id)
);

-- BUCKET_PRODUCTS
drop table if exists buckets_products cascade;
create table buckets_products (
                    bucket_id       bigint not null,
                    product_id      bigint not null,
                    foreign key (bucket_id) references buckets (id),
                    foreign key (product_id) references products (id)
);

-- USER SESSION PATH LOGGER
drop table if exists user_session_path_log cascade;
create table user_session_path_log (
                    id                  bigint auto_increment,
                    principal_name      varchar(100) not null,
                    `date`              varchar(200),
                    path                varchar(200)
);

-- INIT USERS
insert into users (username, password, email, phone, role, status) values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com', '999-333-34-34', 'ADMIN', 'ACTIVE'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com', '999-333-34-35', 'USER', 'ACTIVE'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user3@gmail.com', '999-333-34-36', 'MANAGER', 'ACTIVE'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user4@gmail.com', '999-333-34-37', 'USER', 'BLOCKED'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user5@gmail.com', '999-333-34-38', 'USER', 'ACTIVE'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user6@gmail.com', '999-333-34-39', 'USER', 'ACTIVE'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user7@gmail.com', '999-333-34-40', 'USER', 'ACTIVE'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user8@gmail.com', '999-333-34-41', 'USER', 'ACTIVE'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user9@gmail.com', '999-333-34-42', 'USER', 'ACTIVE'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user10@gmail.com', '999-333-34-43', 'USER', 'ACTIVE'),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user11@gmail.com', '999-333-34-44', 'USER', 'ACTIVE'),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user12@gmail.com', '999-333-34-45', 'USER', 'ACTIVE'),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user13@gmail.com', '999-333-34-46', 'USER', 'ACTIVE');

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

-- INIT BUCKETS
insert into buckets (user_id) values
(1),
(2);

-- INIT BUCKETS_PRODUCTS
insert into buckets_products (bucket_id, product_id) values
(1, 1),
(1, 1),
(2, 1),
(2, 4),
(2, 6),
(2, 4);