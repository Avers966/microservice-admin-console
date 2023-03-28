create
user "admin-console";
alter
user "admin-console" with PASSWORD 'admin-console';
create schema "admin-console";
alter
schema "admin-console" owner to "admin-console";
