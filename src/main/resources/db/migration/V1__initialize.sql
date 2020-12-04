drop table if exists statuses cascade;
create table statuses (
                          id          bigint auto_increment,
                          `name`      varchar(30) not null,
                          primary key (id)
);

drop table if exists users cascade;
create table users (
                       id              bigint auto_increment,
                       username        varchar(30) not null,
                       password        varchar(80) not null,
                       email           varchar(50) unique,
                       phone           varchar(30) unique,
                       status          bigint,
                       primary key (id),
                       foreign key (status) references statuses (id)
);

drop table if exists roles cascade;
create table roles (
                       id         int auto_increment,
                       `name`     varchar(50) not null,
                       primary key (id)
);

drop table if exists users_roles cascade;
create table users_roles (
                             user_id             bigint not null,
                             role_id             bigint not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references users (id),
                             foreign key (role_id) references roles (id)
);

insert into roles (`name`) values
('admin'),
('user');

insert into statuses (`name`) values
('active'),
('blocked');

insert into users (username, password, email, phone,status) values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com', '999-333-34-34', 1),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com', '999-333-34-35', 1),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user3@gmail.com', '999-333-34-36', 2),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user4@gmail.com', '999-333-34-37', 1),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user5@gmail.com', '999-333-34-38', 1),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user6@gmail.com', '999-333-34-39', 2),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user7@gmail.com', '999-333-34-40', 1),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user8@gmail.com', '999-333-34-41', 1),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user9@gmail.com', '999-333-34-42', 2),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user10@gmail.com', '999-333-34-43', 1),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user11@gmail.com', '999-333-34-44', 1),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user12@gmail.com', '999-333-34-45', 2),
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user13@gmail.com', '999-333-34-46', 1);

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