alter table profiles
    add constraint profiles_users_id_fk
        foreign key (id) references users (id);