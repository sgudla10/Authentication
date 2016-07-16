CREATE KEYSPACE auth_copy1
WITH durable_writes = true
AND replication = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
};


CREATE TABLE auth.user (
    email text,
    password text,
    name text,
    firstname text,
    lastname text,
    PRIMARY KEY (email)
);
INSERT INTO user (email,firstname,lastname,name,password) VALUES ('swati.malla@gmail.com','swati','gudla','swati malla','11');
