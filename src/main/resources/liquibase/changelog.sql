--liquibase formatted sql

--changeset commit:1
CREATE TABLE IF NOT EXISTS voter (
   voter_id serial primary key,
   college_id varchar(20) unique not null,
   first_name varchar(20) not null,
   last_name varchar(20),
   password varchar(100) not null,
   branch varchar(50) not null,
   role varchar(10) not null,
   created_on timestamp not null
);

CREATE TABLE IF NOT EXISTS designation (
   designation_id serial primary key,
   designation_name varchar(20) unique not null,
   created_on timestamp not null
);

CREATE TABLE IF NOT EXISTS candidate (
   candidate_id serial primary key,
   first_name varchar(20) not null,
   last_name varchar(20),
   branch varchar(50) not null,
   symbol varchar(20) unique not null,
   img_path varchar(50) unique not null,
   campaign_quote varchar(100) unique not null,
   designation_id int not null,
   created_on timestamp not null,
   constraint fk_designation foreign key(designation_id) references designation(designation_id)
);

CREATE TABLE IF NOT EXISTS vote_record (
   vote_id serial primary key,
   voter_id int not null,
   candidate_id int not null,
   created_on timestamp not null,
   constraint fk_voter foreign key(voter_id) references voter(voter_id),
   constraint fk_candidate foreign key(candidate_id) references candidate(candidate_id)
);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C001', 'Chandler', 'Bing', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Computer Science', 'VOTER', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C002', 'Joey', 'Tribbiani', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Mechanical Engineering', 'VOTER', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C003', 'Monica', 'Geller', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Computer Science', 'VOTER', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C004', 'Rachel', 'Greene', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Electronics Communication', 'VOTER', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C005', 'Ross', 'Geller', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Electronics Communication', 'VOTER', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C006', 'Pheobe', 'Buffay', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Civil Engineering', 'VOTER', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C007', 'Gunther', 'Cafe', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Civil Engineering', 'ADMIN', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C008', 'Janice', 'Bing', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Computer Science', 'ADMIN', CURRENT_TIMESTAMP);

insert into voter (college_id, first_name, last_name, password, branch, role, created_on)
values ('C009', 'Mike', 'Hannigan', '$2a$10$aeIyYeJkhkC9gYWD4amw7eSNA.356NGGt3.9bdH0R9qwjbyDUEZRa', 'Civil Engineering', 'ADMIN', CURRENT_TIMESTAMP);