create table user_tags
(
    user_id bigint not null,
    tag_id  bigint null,
    constraint user_tags_tags_id_fk
        foreign key (tag_id) references tags (id),
    constraint user_tags_users_id_fk
        foreign key (user_id) references users (id)
);

