create TABLE topics(
    id bigint not null auto_increment,
    title varchar(100) not null,
    message varchar(500) not null,
    date_creation datetime not null,
    status varchar(100),
    user_id bigint not null,
    course_id bigint not null,
    is_deleted boolean not null,

    primary key(id),

    constraint fk_topics_user_id foreign key(user_id) references users(id),
    constraint fk_topics_course_id foreign key(course_id) references courses(id)
)