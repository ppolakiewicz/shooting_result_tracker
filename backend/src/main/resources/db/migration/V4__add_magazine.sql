create table srt_magazine
(
    id          uuid primary key,
    version     numeric                  not null,
    owner_id    uuid                     not null references srt_user (id),
    name        text                     not null,
    capacity    numeric                  not null,
    create_date timestamp with time zone not null
);

-- Table is not yet used
alter table weapon
    add magazine_id uuid not null;