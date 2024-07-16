create TABLE users (
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(300) not null,
    is_active boolean not null,

    primary key(id)
)