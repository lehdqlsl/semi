
/* Drop Triggers */

DROP TRIGGER TRI_board_m_num;
DROP TRIGGER TRI_board_num;
DROP TRIGGER TRI_f_category_num;
DROP TRIGGER TRI_gameinfo_g_num;
DROP TRIGGER TRI_gamereview_greviewnum;
DROP TRIGGER TRI_game_g_num;
DROP TRIGGER TRI_g_review_greviewnum;
DROP TRIGGER TRI_g_review_gr_num;
DROP TRIGGER TRI_members_num;
DROP TRIGGER TRI_movie_m_num;
DROP TRIGGER TRI_music_num;
DROP TRIGGER TRI_m_review_r_num;
DROP TRIGGER TRI_r_review_num;
DROP TRIGGER TRI_s_category_num;
DROP TRIGGER TRI_z_num;
DROP TRIGGER TRI__review_num;



/* Drop Tables */

DROP TABLE reply CASCADE CONSTRAINTS;
DROP TABLE r_review CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE s_category CASCADE CONSTRAINTS;
DROP TABLE f_category CASCADE CONSTRAINTS;
DROP TABLE g_review CASCADE CONSTRAINTS;
DROP TABLE game CASCADE CONSTRAINTS;
DROP TABLE music CASCADE CONSTRAINTS;
DROP TABLE m_review CASCADE CONSTRAINTS;
DROP TABLE recv_msg CASCADE CONSTRAINTS;
DROP TABLE send_msg CASCADE CONSTRAINTS;
DROP TABLE members CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;
DROP TABLE movie CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_board_m_num;
DROP SEQUENCE SEQ_board_num;
DROP SEQUENCE SEQ_f_category_num;
DROP SEQUENCE SEQ_game_g_num;
DROP SEQUENCE SEQ_g_review_gr_num;
DROP SEQUENCE SEQ_members_num;
DROP SEQUENCE SEQ_member_m_num;
DROP SEQUENCE SEQ_member_num;
DROP SEQUENCE SEQ_movie_m_num;
DROP SEQUENCE SEQ_music_num;
DROP SEQUENCE SEQ_m_review_r_num;
DROP SEQUENCE SEQ_r_review_num;
DROP SEQUENCE SEQ_s_category_num;




/* Create Sequences */

CREATE SEQUENCE SEQ_board_m_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_board_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_f_category_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_game_g_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_g_review_gr_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_members_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_member_m_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_member_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_movie_m_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_music_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_m_review_r_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_r_review_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_s_category_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_reply_r_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_send_msg_num INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_recv_msg_num INCREMENT BY 1 START WITH 1;


/* Create Tables */

CREATE TABLE board
(
	num number NOT NULL,
	title_name varchar2(30),
	up number(3,0),
	hits number(10,0),
	orgfilename varchar2(50),
	savefilename varchar2(50),
	content clob,
	regdate date,
	writer varchar2(20) NOT NULL UNIQUE,
	f_num number(2,0) NOT NULL,
	s_num number(2,0) NOT NULL,
	blind number(2,0),
	report number(2,0),
	top number(2,0),
	PRIMARY KEY (num)
);


CREATE TABLE f_category
(
	num number(2,0) NOT NULL,
	title_name varchar2(30),
	PRIMARY KEY (num)
);


CREATE TABLE game
(
	-- 시퀀스 자동증가
	g_num number(5,0) NOT NULL,
	g_name varchar2(30),
	g_jenre varchar2(20),
	flatform varchar2(30),
	company varchar2(20),
	l_date date,
	PRIMARY KEY (g_num)
);


CREATE TABLE grade
(
	grade varchar2(10) NOT NULL,
	exp number(10,0),
	PRIMARY KEY (grade)
);


CREATE TABLE g_review
(
	-- 시퀀스 자동증가
	gr_num number(5,0) NOT NULL,
	-- 시퀀스 자동증가
	g_num number(5,0) NOT NULL,
	m_nick varchar2(20) NOT NULL UNIQUE,
	-- 10점 만점
	score number(2,1),
	r_date date,
	PRIMARY KEY (gr_num)
);


CREATE TABLE members
(
	num number NOT NULL,
	id varchar2(20),
	u_pw varchar2(20) NOT NULL,
	m_nick varchar2(20) NOT NULL UNIQUE,
	m_mail varchar2(30) NOT NULL,
	m_orgfilename varchar2(20),
	m_savefilename varchar2(20),
	grade varchar2(10) NOT NULL,
	exp number,
	reg_date date,
	stpo number(2,0),
	limit_date date,
	PRIMARY KEY (num)
);


CREATE TABLE movie
(
	m_num number NOT NULL,
	m_name varchar2(50),
	m_genre varchar2(40),
	m_country varchar2(20),
	m_rt varchar2(10),
	m_release date,
	m_rate varchar2(30),
	m_director varchar2(50),
	m_actor varchar2(50),
	PRIMARY KEY (m_num)
);


CREATE TABLE music
(
	num number NOT NULL,
	title varchar2(40),
	m_nick varchar2(20) NOT NULL UNIQUE,
	singer varchar2(40),
	lyrics clob,
	songwriter varchar2(40),
	lyricist varchar2(40),
	orgming varchar2(50),
	saveming varchar2(50),
	PRIMARY KEY (num)
);


CREATE TABLE m_review
(
	r_num number NOT NULL,
	m_num number NOT NULL,
	m_nick varchar2(20) NOT NULL UNIQUE,
	r_gpa number(2,1),
	r_comm varchar2(100),
	PRIMARY KEY (r_num)
);


CREATE TABLE recv_msg
(
	num number NOT NULL,
	send varchar2(20) NOT NULL UNIQUE,
	recv varchar2(20) NOT NULL UNIQUE,
	content clob,
	regdate date,
	m_read number(2,0),
	PRIMARY KEY (num)
);


CREATE TABLE reply
(
	r_num number NOT NULL,
	nick varchar2(20) NOT NULL UNIQUE,
	content number(100),
	up number(3,0),
	reg_date date,
	num number NOT NULL,
	report number(2,0),
	PRIMARY KEY (r_num)
);


CREATE TABLE r_review
(
	-- 시퀀스 자동증가
	num number(5,0) NOT NULL,
	b_num number NOT NULL,
	m_nick varchar2(20) NOT NULL UNIQUE,
	-- 10점 만점
	score number(2,1),
	r_date date,
	PRIMARY KEY (num)
);


CREATE TABLE send_msg
(
	num number NOT NULL,
	send varchar2(20) NOT NULL UNIQUE,
	recv varchar2(20) NOT NULL UNIQUE,
	content clob,
	regdate date,
	s_read number(2,0),
	PRIMARY KEY (num)
);


CREATE TABLE s_category
(
	num number(2,0) NOT NULL,
	title_name varchar2(30),
	f_num number(2,0) NOT NULL,
	PRIMARY KEY (num)
);



/* Create Foreign Keys */

ALTER TABLE reply
	ADD FOREIGN KEY (num)
	REFERENCES board (num)
;


ALTER TABLE r_review
	ADD FOREIGN KEY (b_num)
	REFERENCES board (num)
;


ALTER TABLE board
	ADD FOREIGN KEY (f_num)
	REFERENCES f_category (num)
;


ALTER TABLE s_category
	ADD FOREIGN KEY (f_num)
	REFERENCES f_category (num)
;


ALTER TABLE g_review
	ADD FOREIGN KEY (g_num)
	REFERENCES game (g_num)
;


ALTER TABLE members
	ADD FOREIGN KEY (grade)
	REFERENCES grade (grade)
;


ALTER TABLE board
	ADD FOREIGN KEY (writer)
	REFERENCES members (m_nick)
;


ALTER TABLE g_review
	ADD FOREIGN KEY (m_nick)
	REFERENCES members (m_nick)
;


ALTER TABLE music
	ADD FOREIGN KEY (m_nick)
	REFERENCES members (m_nick)
;


ALTER TABLE m_review
	ADD FOREIGN KEY (m_nick)
	REFERENCES members (m_nick)
;


ALTER TABLE recv_msg
	ADD FOREIGN KEY (recv)
	REFERENCES members (m_nick)
;


ALTER TABLE recv_msg
	ADD FOREIGN KEY (send)
	REFERENCES members (m_nick)
;


ALTER TABLE reply
	ADD FOREIGN KEY (nick)
	REFERENCES members (m_nick)
;


ALTER TABLE r_review
	ADD FOREIGN KEY (m_nick)
	REFERENCES members (m_nick)
;


ALTER TABLE send_msg
	ADD FOREIGN KEY (recv)
	REFERENCES members (m_nick)
;


ALTER TABLE send_msg
	ADD FOREIGN KEY (send)
	REFERENCES members (m_nick)
;


ALTER TABLE m_review
	ADD FOREIGN KEY (m_num)
	REFERENCES movie (m_num)
;


ALTER TABLE board
	ADD FOREIGN KEY (s_num)
	REFERENCES s_category (num)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_board_m_num BEFORE INSERT ON board
FOR EACH ROW
BEGIN
	SELECT SEQ_board_m_num.nextval
	INTO :new.m_num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_board_num BEFORE INSERT ON board
FOR EACH ROW
BEGIN
	SELECT SEQ_board_num.nextval
	INTO :new.num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_f_category_num BEFORE INSERT ON f_category
FOR EACH ROW
BEGIN
	SELECT SEQ_f_category_num.nextval
	INTO :new.num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_gameinfo_g_num BEFORE INSERT ON gameinfo
FOR EACH ROW
BEGIN
	SELECT SEQ_gameinfo_g_num.nextval
	INTO :new.g_num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_gamereview_greviewnum BEFORE INSERT ON gamereview
FOR EACH ROW
BEGIN
	SELECT SEQ_gamereview_greviewnum.nextval
	INTO :new.greviewnum
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_game_g_num BEFORE INSERT ON game
FOR EACH ROW
BEGIN
	SELECT SEQ_game_g_num.nextval
	INTO :new.g_num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_g_review_greviewnum BEFORE INSERT ON g_review
FOR EACH ROW
BEGIN
	SELECT SEQ_g_review_greviewnum.nextval
	INTO :new.greviewnum
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_g_review_gr_num BEFORE INSERT ON g_review
FOR EACH ROW
BEGIN
	SELECT SEQ_g_review_gr_num.nextval
	INTO :new.gr_num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_members_num BEFORE INSERT ON members
FOR EACH ROW
BEGIN
	SELECT SEQ_members_num.nextval
	INTO :new.num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_movie_m_num BEFORE INSERT ON movie
FOR EACH ROW
BEGIN
	SELECT SEQ_movie_m_num.nextval
	INTO :new.m_num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_music_num BEFORE INSERT ON music
FOR EACH ROW
BEGIN
	SELECT SEQ_music_num.nextval
	INTO :new.num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_m_review_r_num BEFORE INSERT ON m_review
FOR EACH ROW
BEGIN
	SELECT SEQ_m_review_r_num.nextval
	INTO :new.r_num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_r_review_num BEFORE INSERT ON r_review
FOR EACH ROW
BEGIN
	SELECT SEQ_r_review_num.nextval
	INTO :new.num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_s_category_num BEFORE INSERT ON s_category
FOR EACH ROW
BEGIN
	SELECT SEQ_s_category_num.nextval
	INTO :new.num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_z_num BEFORE INSERT ON z
FOR EACH ROW
BEGIN
	SELECT SEQ_z_num.nextval
	INTO :new.num
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI__review_num BEFORE INSERT ON _review
FOR EACH ROW
BEGIN
	SELECT SEQ__review_num.nextval
	INTO :new.num
	FROM dual;
END;

/




/* Comments */

COMMENT ON COLUMN game.g_num IS '시퀀스 자동증가';
COMMENT ON COLUMN g_review.gr_num IS '시퀀스 자동증가';
COMMENT ON COLUMN g_review.g_num IS '시퀀스 자동증가';
COMMENT ON COLUMN g_review.score IS '10점 만점';
COMMENT ON COLUMN r_review.num IS '시퀀스 자동증가';
COMMENT ON COLUMN r_review.score IS '10점 만점';



