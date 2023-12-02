create table srt_training
(
    id           uuid primary key,
    create_date  timestamp with time zone not null,
    created_by   uuid                     not null,
    name         varchar,
    session_date timestamp with time zone not null,
    place        varchar,
    result_count numeric                  not null default 0
);

create table srt_training_result
(
    id            uuid primary key,
    create_date   timestamp with time zone not null,
    created_by    uuid                     not null,
    training_id   uuid                     not null,
    weapon_id     uuid                     not null,
    weapon_name   varchar                  not null,
    shots_results jsonb,
    files_ids     jsonb
);