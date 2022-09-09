CREATE TYPE gender_type AS ENUM ('MAN', 'WOMAN');
CREATE TYPE document_type AS ENUM ('PASSPORT', 'INTERNATIONAL_PASSPORT', 'DRIVER');

CREATE TABLE documents
(
    id serial primary key,
    series varchar not null,
    number varchar not null,
    type document_type not null,
    issue_date date not null
);

CREATE TABLE persons
(
    id serial primary key,
    document_id bigint not null,
    name varchar not null,
    surname varchar not null,
    patronymic varchar,
    birth_date date not null,
    gender gender_type not null,

    CONSTRAINT fk_document_person FOREIGN KEY(document_id) REFERENCES documents(id)
);