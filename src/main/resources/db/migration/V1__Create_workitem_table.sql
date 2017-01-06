create table WORK_ITEM (
    ID int not null IDENTITY,
    JIRA_ID varchar(15) not null,
    SUMMARY varchar(250) not null,
    DESCRIPTION varchar(250) null,
    PRODUCT varchar(50) not null,
    COMPLETION_DATE TIMESTAMP null,
    IN_PROGRESS BOOLEAN not null,
    IS_DONE BOOLEAN not null,
    WORK_TYPE varchar(50) not null,
    PEOPLE varchar(250) null,
    PRIORITY DOUBLE null,
    WORK_KIND varchar(50) null
);
