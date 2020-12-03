drop table if exists statuses cascade;
create table statuses (
                          id          int auto_increment,
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
                       status          int,
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
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user3@gmail.com', '999-333-34-36', 2);

insert into users_roles values
(1, 1),
(2, 2),
(3, 2);