create table user_info
(
    userId         bigint auto_increment primary key,
    userName       varchar(20)    ,
    userPwd        varchar(300)   ,
    userSex        varchar(20)    ,
    userPhone      varchar(30)    ,
    userMail       varchar(50)    ,
    userAddress    varchar(100)   ,
    userBalance    decimal(19, 4) ,
    userAvatarUrl  varchar(300)   ,
    userCreateTime datetime
);
create table if not exists user_token
(
	userId bigint ,
	userToken varchar(300),
	userTokenExpire datetime ,
	foreign key (userId) references user_info (userId)
);
create table if not exists private_key
(
	privateKey varchar(200) 
);
insert into private_key values ('5c43da9c6fb9a049df245f96')