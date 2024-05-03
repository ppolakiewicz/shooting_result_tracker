create table weapon(
    id uuid not null primary key,
    name text not null,
    type text not null,
    caliber text not null,
    model text not null,
    production_date timestamp with time zone,
    purchase_date timestamp with time zone
)