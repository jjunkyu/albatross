create table UserCategory(
cId varchar2(100) primary key,
cName varchar2(100) not null
)
drop table UserCategory;
insert into UserCategory(cId,cName) values(0,'일반회원');
insert into UserCategory(cId,cName) values(1,'관리자');
select * from UserCategory;
create table question(
	qId varchar2(100) primary key,
	query varchar2(100) not null
)
drop table question;
insert into question(qId,query) values(0,'가장 힘들어 보이는 직업?');
insert into question(qId,query) values(1,'초등학교시절 가장 기억에 남는 선생님 이름은?');
insert into question(qId,query) values(2,'칼로리가 0이었으면 싶은 음식은?');
insert into question(qId,query) values(3,'무인도에 딱 하나만 가져갈 수 있다면?');
insert into question(qId,query) values(4,'내가 가장 좋아하는 인스턴트 음식은?');
insert into question(qId,query) values(5,'첫사랑 이름은?');
insert into question(qId,query) values(6,'첫키스 장소는?');
select *from question;

create table semi_member(
id varchar2(100) primary key,
password varchar2(100) not null,
address varchar2(100) not null,
name varchar2(100) not null,
eMail varchar2(100) not null,
answer varchar2(100) not null,
cId varchar2(100) default 0,
qId varchar2(100) not null,
constraint fk_cId foreign key(cId) references UserCategory(cId),
constraint fk_qId foreign key(qId) references question(qId)
)
drop table semi_member;
insert into semi_member(id,password,address,name,eMail,answer,qId) values('java','1','판교','송중기','java@naver.com','개발자',0);
insert into semi_member(id,password,address,name,eMail,answer,cId,qId) values('admin','1','판교','admin','admin@naver.com','admin','1',0);
insert into semi_member(id,password,address,name,eMail,answer,qId) values('spring','1','성남','아이유','spring@google.com','서정우',1);

delete from semi_member where id='admin';
select * from semi_member;
delete from semi_member where id='admin';
create table semi_book(
bNo number primary key,
title varchar2(100) not null,
author varchar2(100) not null,
content clob not null,
publisher varchar2(100) not null,
isRented number default 0,
rentCount number default 0,
imagePath varchar2(200)
)
create sequence semi_book_seq;
drop table semi_book
drop sequence semi_book_seq;
insert into semi_book(bNo,title,author,content,publisher) values(semi_book_seq.nextval,'해리포터2','롤링','해리포터라는 마법사가 커가는 이야기2','영국출판사');
insert into semi_book(bNo,title,author,content,publisher) values(semi_book_seq.nextval,'해리포터','롤링','해리포터라는 마법사가 커가는 이야기','영국출판사');
select *from SEMI_BOOK;
<<<<<<< HEAD
delete from semi_book where bNo = 2
delete from semi_book;
drop table SEMI_RENT_BOOK;
create table semi_rent_book(
rId number primary key,
id varchar2(100) not null,
bNo number not null,
rentDate date not null,
returnDate date,
constraint fk_id foreign key(id) references semi_member(id),
constraint fk_bNo foreign key(bNo) references semi_book(bNo) ON DELETE CASCADE
)
create sequence semi_rent_book_seq;
drop sequence semi_rent_book_seq;
drop table semi_rent_book;
insert into semi_rent_book(rId,id,bNo,rentDate) values(semi_rent_book_seq.nextval,'java','1',sysdate);
select *from semi_rent_book;
delete from semi_rent_book WHERE id='java'
commit

create table semi_post(
	pNo number primary key,
	title varchar2(100) not null,
	content clob not null,
	hits number default 0,
	timeposted date not null,
	id varchar2(100) not null,
	constraint fk_pid foreign key(id) references semi_member(id)
)
create sequence semi_post_seq;
drop sequence semi_post_seq;
drop table semi_post;
insert into semi_post(pNo,title,content,timeposted,id) values(semi_post_seq.nextval,'자유게시판의 제목','자유게시판 내용이 들어갑니다',sysdate,'java');
select * from SEMI_POST;

select * from semi_book where author like '%아작%';
select pNo,title,content,to_char(timeposted, 'yyyy.mm.dd') from SEMI_POST

SELECT p.pNo,p.title,p.timeposted,p.hits,p.id,m.name FROM( 
SELECT row_number() over(order by pNo desc) as rnum, pNo,title,hits, 
to_char(timeposted,'YYYY.MM.DD') as timeposted,id 
FROM semi_post
) p,semi_member m where p.id=m.id and rnum between '1' and '10' 
order by pNo desc

SELECT b.bNo,b.title,b.author,b.content,b.publisher,b.isRented
FROM(SELECT row_number() OVER(ORDER BY bNo DESC) AS rnum,bNo,title,author,content,publisher,isRented
FROM semi_book)b WHERE rnum BETWEEN 1 AND 3
ORDER BY bNo DESC

SELECT bNo, title
FROM SEMI_BOOK
WHERE title LIKE '%의%' OR AUTHOR LIKE '%의%';

SELECT pNo, id, title
FROM SEMI_POST
WHERE id = 'java'

--  드랍순서 : 밑에서 부터 차례대로-----
drop table UserCategory;
drop table semi_member;
drop table semi_book;
drop sequence semi_book_seq;
drop table semi_rent_book;
drop table semi_post;
drop sequence semi_post_seq;
----------------------------------
UPDATE SEMI_BOOK s
SET imagePath = (
	SELECT substr(imagePath,2) newPath 
	FROM SEMI_BOOK p WHERE s.bNo = p.bNo
	)

SELECT * FROM SEMI_BOOK;

SELECT b.bNo, b.title, b.author, b.content, b.publisher,b.isRented
FROM(SELECT row_number() OVER(ORDER BY bNo DESC)
AS rnum,bNo,title,author,content,publisher,isRented
FROM semi_book where title like '%해리%') b WHERE rnum BETWEEN 1 AND 10
ORDER BY bNo DESC 

SELECT bNo, title, author, content, publisher, isRented FROM semi_book where title like '%해리%';

SELECT p.pNo,p.title,p.timeposted,p.hits,p.id,m.name FROM(
SELECT row_number() over(order by pNo desc) as bNo, title, author, content, publisher, isRented
FROM semi_book where author like '') p,semi_member m 
where p.id=m.id and rnum between 1 and 20
order by pNo desc

SELECT count(*) FROM semi_post WHERE ID = 'java';

select count(*) from semi_book where title like '2' or author like '1';

SELECT b.bNo, b.title, b.author, b.content, b.publisher,b.isRented
FROM(SELECT row_number() OVER(ORDER BY bNo DESC)
AS rnum,bNo,title,author,content,publisher,isRented 
FROM semi_book where title like '%2%' or author like '%2%') b WHERE rnum BETWEEN 1 AND 10
ORDER BY bNo DESC;

select count(*) from semi_book where title like '%해리%';

select count(*) from semi_rent_book where bNo=35 and returndate is null;

select *from SEMI_RENT_BOOK;
select *from semi_member;

SELECT b.rnum,b.bNo, b.title, b.author,b.publisher, 
br.id,br.rentdate,br.returndate
FROM (SELECT row_number() over(order by bNo desc)
as rnum, bNo,title,author,publisher
FROM semi_book) b,semi_rent_book br 
WHERE br.id = 'java' AND b.bNo=br.bNo AND rnum BETWEEN 1 AND 10
ORDER BY rentdate DESC

select *from semi_book order by rentdate desc



select b.bNo,b.title,b.author,b.publisher,b.isrented,
br.id, br.rentdate, br.returndate
from (SELECT row_number() over(order by rentdate desc)
as rnum, id, bNo, rentdate, returndate
from semi_rent_book) br, semi_book b
where br.id='java' and br.bno=b.bno and rnum between 1 and 10
order by rentdate desc





