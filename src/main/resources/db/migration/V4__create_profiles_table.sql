create table profiles
(
    id             bigint       not null,
    bio            varchar(255) not null,
    phone_number   varchar(255) not null,
    date_of_birth  date         not null,
    loyalty_points int          default 0
);