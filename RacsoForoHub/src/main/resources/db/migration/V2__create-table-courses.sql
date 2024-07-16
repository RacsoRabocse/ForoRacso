create TABLE courses(
    id bigint not null auto_increment,
    name varchar(100) not null,
    category varchar(100) null,
    is_deleted boolean not null,

    primary key(id)
)