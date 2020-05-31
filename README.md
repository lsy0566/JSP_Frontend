# JSP_Frontend
Account_Jsp

## user db 테이블 생성

create table USER
(email varchar(45) primary key not null,
username varchar(45) not null,
password varchar(50) not null,
phoneNumber varchar(45) not null,
secondPhoneNumber varchar(45),
ismember boolean,
isadmin boolean
)
