create table UserCategory(
cId number primary key,
cName varchar2(100) not null
)

select * from UserCategory
insert into UserCategory(cId,cName) values(0,'일반회원')



create table semi_member(
id varchar2(100) primary key,
password varchar2(100) not null,
address varchar2(100) not null,
name varchar2(100) not null,
cId number default 0,
constraint fk_cId foreign key(cId) references UserCategory(cId)
)

select * from semi_member;
drop table semi_member;

create table semi_book(
bNo number primary key,
title varchar2(100) not null,
author varchar2(100) not null,
content clob not null,
publisher varchar2(100) not null,
isRented number default 0
)
select *from SEMI_BOOK
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

drop sequence semi_post_seq;
drop table semi_post;
select *from SEMI_POST;


insert into semi_member(id,password,name,address) values('java','1','아이유','판교');
insert into semi_member(id,password,name,address) values('spring','1','공유','성남');
select *from semi_member;

insert into semi_book(bNo,title,content,author,publisher) values(semi_book_seq.nextval,'반지의제왕','반지이야기','모름','캐나다');
insert into semi_book(bNo,title,content,author,publisher) values(semi_book_seq.nextval,'해리포터','마법사이야기','롤링','영국');
insert into semi_book(bNo,title,content,author,publisher) values(semi_book_seq.nextval,'하얀고양이','고양이가있다','아작','학산');
insert into semi_book(bNo,title,content,author,publisher) values(semi_book_seq.nextval,'까만고양이','고양이가없다','아작','학산');
insert into semi_book(bNo,title,content,author,publisher) values(semi_book_seq.nextval,'창의력','스케치노트','김충원','우리집');
select *from semi_book;

insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'반지의제왕','반지이야기',sysdate,'spring');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'해리포터','마법사이야기',sysdate,'spring');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'하얀고양이','고양이가있다',sysdate,'java');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'까만고양이','고양이가없다',sysdate,'java');
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'창의력','스케치노트',sysdate,'java');
select *from semi_post;

SELECT bNo,title,content,author,publisher FROM semi_book
select pNo,title,content,timeposted from SEMI_POST;

insert into SEMI_RENT_BOOK(id,bNo,rentdate,returndate) values('java',1,sysdate,sysdate+7);
select *from SEMI_RENT_BOOK;
delete from SEMI_RENT_BOOK where bNo = 1;
select returndate-sysdate from SEMI_RENT_BOOK where bNo = 1;



select * from semi_book where author like '%아작%';

select * from semi_member where id=? and password=?


select pNo,title,content,to_char(timeposted, 'yyyy.mm.dd') from SEMI_POST


SELECT p.pNo,p.title,p.timeposted,p.hits,p.id,m.name FROM( 
SELECT row_number() over(order by pNo desc) as rnum, pNo,title,hits, 
to_char(timeposted,'YYYY.MM.DD') as timeposted,id 
FROM semi_post
) p,semi_member m where p.id=m.id and rnum between '1' and '5' 
order by pNo desc

SELECT b.bNo,b.title,b.author,b.content,b.publisher
FROM(SELECT row_number() OVER(ORDER BY bNo DESC) AS rnum,bNo,title,author,content,publisher
FROM semi_book)b WHERE rnum BETWEEN 1 AND 3
ORDER BY bNo DESC

SELECT b.bNo,b.title,b.author,b.content,b.publisher
FROM SEMI_BOOK b, SEMI_RENT_BOOK rb
WHERE rb.id = 'java' and b.bNo = rb.bNo