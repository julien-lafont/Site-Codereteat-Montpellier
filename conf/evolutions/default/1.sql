# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table config (
  id                        bigint not null,
  inscription_active        boolean,
  inscription_url           varchar(255),
  constraint pk_config primary key (id))
;

create sequence config_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists config;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists config_seq;

