
##############################JDBCDemo1 sql for mysql start#####################################
#创建数据表 websites

  
DROP TABLE websites;    
CREATE TABLE websites(
	id int(11) NOT NULL AUTO_INCREMENT,
	name char(20) NOT NULL DEFAULT '' COMMENT '站点名称',
	url varchar(255) NOT NULL DEFAULT ''  COMMENT '网址' ,
	alexa int(11) NOT NULL DEFAULT '0' COMMENT 'Alexa 排名' ,
	country char(10) NOT NULL DEFAULT '' COMMENT '国家' ,
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT =10 DEFAULT CHARSET =utf8;
# 插入测试数据
INSERT INTO websites values
	(1,'百度','www.baidu.com',10,'中国'),
	(2,'Google','www.google.com',1,'美国'),
	(3,'新浪','www.sina.com',23,'中国'),
	(4,'有道','www.youdao.com',1000,'中国');
# 查询数据
select * from websites;                                           

##############################JDBCDemo1 sql for mysql end#####################################
         


##############################JDBCDemo3&C3P0Demo sql for mysql end#####################################
#创建表 test_user

drop table test_user;
create table test_user(
 user_id int(11) primary key not null auto_increment ,
 username varchar(255),
 passwd   varchar(255)
)engine = InnoDB auto_increment =10 default charset = utf8 ;

#插入测试数据
insert into test_user values
(1,'张三','12345678'),
(2,'李四','23242'),
(3,'王五','23wer');
#查询数据
select * from test_user;    

#查询时，SQL注入
#没有钱六，但是可以通过SQL注入查询到所有用户的名称和密码         
select * from test_user where username='钱六' and passwd='sfsd'or'1'= '1'; 

##############################JDBCDemo3&C3P0Demo sql for mysql end#####################################

/***********C3P0Demo sql for oracle start**************/
--创建sequence
drop sequence seq_on_test_user;
create sequence seq_on_test_user
increment by 1
start with 1
nomaxvalue
nocycle
nocache;
--创建表 test_user

drop table test_user;
create table test_user(
 user_id int primary key not null  ,
 username varchar(255),
 passwd   varchar(255)
);

--插入测试数据
insert into test_user values(seq_on_test_user.nextval,'张三','12345678');
insert into test_user values(seq_on_test_user.nextval,'李四','23242');
insert into test_user values(seq_on_test_user.nextval,'王五','23wer');
commit;
--查询数据
select * from test_user; 
select count(*)  countUser from test_user where username = '张三';
/***********C3P0Demo sql for oracle end**************/


/*********** DaoDemo sql for oracle start**************/
--创建表
 create table test_emp(
        emp_id number primary key,
        emp_name varchar2(20),
        salary number(8,2)
);
--插入数据  
     insert into test_emp(
        emp_id,
        emp_name,
        salary
     )values(
        1,
        'king',
        4521
     );
     commit;
     insert into test_emp(
        emp_id,
        emp_name,
        salary
     )values(
         2,
        'tomcat',
         8888
     );
     commit;
-- 查询数据
	select * from test_emp;
/***********DaoDemo sql for oracle end**************/
