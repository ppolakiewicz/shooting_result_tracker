create table srt_user
(
    id       uuid primary key,
    email    varchar not null,
    password varchar not null,
    role     varchar not null,
    active   boolean not null default false
)
;