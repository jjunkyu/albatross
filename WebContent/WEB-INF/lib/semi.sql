create table UserCategory(
cId number primary key,
cName varchar2(100) not null
)

create table semi_member(
id varchar2(100) primary key,
password varchar2(100) not null,
address varchar2(100) not null,
name varchar2(100) not null,
cId number not null,
constraint fk_cId foreign key(cId) references UserCategory(cId)
)
drop table semi_member

create table semi_book(
bNo number primary key,
title varchar2(100) not null,
author varchar2(100) not null,
content clob not null,
publisher varchar2(100) not null,
isRented number default 0
)
drop table semi_book
create sequence semi_book_seq;
drop sequence semi_book_seq;

create table semi_rent_book(
id varchar2(100) not null,
bNo number not null,
rentdate date not null,
returndate date not null,
constraint fk_id foreign key(id) references semi_member(id),
constraint fk_bNo foreign key(bNo) references semi_book(bNo),
constraint pk_rent primary key(id,bNo) --복합pk
)
drop table semi_rent_book
select *from semi_rent_book


create table semi_post(
pNo number primary key,
title varchar2(100) not null,
content clob not null,
hits number default 0,
timeposted date not null,
id varchar2(100) not null,
constraint fk_pid foreign key(id) references semi_member(id)
)
create sequence semi_post_seq
drop sequence semi_post_seq
drop table semi_post

insert into semi_member(id,password,name,address) values('java','1','아이유','판교')
insert into semi_member(id,password,name,address) values('spring','1','공유','성남')
select *from semi_member;

insert into semi_book(bNo,title,content,author,publisher,id) values(semi_book_seq.nextval,'반지의제왕','반지이야기','모름','캐나다','spring');
insert into semi_book(bNo,title,content,author,publisher,id) values(semi_book_seq.nextval,'해리포터','마법사이야기','롤링','영국','spring');
insert into semi_book(bNo,title,content,author,publisher,id) values(semi_book_seq.nextval,'하얀고양이','고양이가있다','아작','학산','java');
insert into semi_book(bNo,title,content,author,publisher,id) values(semi_book_seq.nextval,'까만고양이','고양이가없다','아작','학산','java');
insert into semi_book(bNo,title,content,author,publisher,id) values(semi_book_seq.nextval,'창의력','스케치노트','김충원','우리집','java');
select *from semi_book;

insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'반지의제왕','반지이야기',sysdate,'spring');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'해리포터','마법사이야기',sysdate,'spring');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'하얀고양이','고양이가있다',sysdate'java');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'까만고양이','고양이가없다',sysdate'java');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'창의력','스케치노트',sysdate,'java');
select *from semi_post;