create TABLE answers(
    id bigint not null auto_increment,
    message varchar(500) not null,
    solved boolean not null,
    topic_id bigint not null,
    user_id bigint not null,
    is_deleted boolean not null,

    primary key(id),

    constraint fk_answers_topic_id foreign key(topic_id) references topics(id),
    constraint fk_answers_user_id foreign key(user_id) references users(id)
)