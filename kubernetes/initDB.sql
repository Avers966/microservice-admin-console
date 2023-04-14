create user "admin-console";
alter user "admin-console" with PASSWORD 'admin-console';
create schema admin_console; 
alter schema admin_console owner to "admin-console";