create table users (id bigserial, username varchar(30) not null, password varchar(80) not null, email varchar(50) unique,
  primary key (id)
);

create table roles (id serial, name varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (user_id bigint not null, role_id int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name) values
('ROLE_USER'), ('ROLE_ADMIN'), ('DELETE_USERS_PERMISSION');

insert into users (username, password, email) values
--         admin
('admin', '$2y$12$B4a6J3eGOjxcRvBxq5Pme.Dbla0zRqsw27rBMvn4Fe8ZMeoSGfgrm', 'admin@email.com');

insert into users_roles (user_id, role_id) values (1, 2);


create table orders (id bigserial, user_id bigint not null,
  primary key (id),
  foreign key (user_id) references users (id)
);


create table books (id bigserial primary key, title varchar(255), description varchar(5000), price numeric(8, 2), publish_year int, genre_value varchar(5000));
insert into books (title, description, price, publish_year, genre_value) values
('Harry Potter 1', 'Description 1', 300.0, 2000, 'Фэнтези'),
('Harry Potter 2', 'Description 2', 400.0, 2001, 'Детектив'),
('Harry Potter 3', 'Description 3', 500.0, 2002, 'Фэнтези'),
('Harry Potter 4', 'Description 4', 700.0, 2007, 'Детектив'),
('Harry Potter 5', 'Description 5', 440.0, 2004, 'Фэнтези'),
('Harry Potter 6', 'Description 6', 650.0, 2007, 'Фэнтези'),
('Harry Potter 7', 'Description 7', 200.0, 2006, 'Фэнтези'),
('LOTR 1', 'Description 8', 1200.0, 2006, 'Фантастика'),
('LOTR 2', 'Description 9', 900.0, 2004, 'Фэнтези'),
('LOTR 3', 'Description 10', 600.0, 2001, 'Фэнтези'),
('Hobbit', 'Description 11', 500.0, 2001, 'Фэнтези');

create table order_items (id bigserial, order_id bigint not null, book_id bigint not null, count int not null, price numeric(8, 2) not null,
  amount    numeric(8, 2) not null,
  primary key (id),
  foreign key (order_id) references orders (id),
  foreign key (book_id) references books (id)
);