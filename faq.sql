/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/9/9 21:15:00                            */
/*==============================================================*/


drop table if exists admin_info;

drop table if exists category_info;

drop table if exists problem_info;

drop table if exists reply_info;

drop table if exists user_info;

/*==============================================================*/
/* Table: admin_info                                            */
/*==============================================================*/
create table admin_info
(
   admin_id             int not null,
   admin_account        varchar(30) not null,
   admin_pwd            varchar(100),
   primary key (admin_id)
);

/*==============================================================*/
/* Table: category_info                                         */
/*==============================================================*/
create table category_info
(
   cate_id              int not null auto_increment,
   cate_name            varchar(50),
   primary key (cate_id)
);

/*==============================================================*/
/* Table: problem_info                                          */
/*==============================================================*/
create table problem_info
(
   prob_id              int not null auto_increment,
   prob_cate_id         int,
   prob_title           varchar(50),
   prob_level           tinyint,
   score                tinyint,
   content              text,
   pic_id               varchar(30),
   pic_file_name        varchar(50),
   pic_file_type        varchar(50),
   status               tinyint,
   submit_user_id       int,
   submit_time          datetime,
   primary key (prob_id)
);

/*==============================================================*/
/* Table: reply_info                                            */
/*==============================================================*/
create table reply_info
(
   reply_id             int not null auto_increment,
   prob_id              int,
   reply_user_id        int,
   content              text,
   reply_time           datetime,
   pic_id               varchar(30),
   file_type            varchar(30),
   is_best_reply        tinyint,
   primary key (reply_id)
);

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   user_id              int not null auto_increment,
   user_account         varchar(30),
   alias                varchar(30),
   user_pwd             varchar(100),
   qq                   varchar(20),
   email                varchar(30),
   mobile               varchar(20),
   college              varchar(30),
   major                varchar(30),
   idcard               varchar(30),
   dob                  date,
   level                tinyint,
   jobStatus            tinyint,
   company              varchar(50),
   score                int,
   primary key (user_id)
);

alter table problem_info add constraint FK_prob_cate foreign key (prob_cate_id)
      references category_info (cate_id) on delete restrict on update restrict;

alter table problem_info add constraint FK_prob_user foreign key (submit_user_id)
      references user_info (user_id) on delete restrict on update restrict;

alter table reply_info add constraint FK_reply_prob foreign key (prob_id)
      references problem_info (prob_id) on delete restrict on update restrict;

alter table reply_info add constraint FK_reply_user foreign key (reply_user_id)
      references user_info (user_id) on delete restrict on update restrict;

