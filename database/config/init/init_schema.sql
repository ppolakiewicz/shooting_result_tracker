
CREATE USER u_srt WITH
LOGIN
NOSUPERUSER
NOCREATEDB
NOCREATEROLE
INHERIT
NOREPLICATION
CONNECTION LIMIT -1
PASSWORD 'p_srt';

CREATE SCHEMA srt
    AUTHORIZATION u_srt;