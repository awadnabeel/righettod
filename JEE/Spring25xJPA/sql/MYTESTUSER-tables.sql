CREATE TABLE "MYTESTUSER"."DEVELOPER"
(
   ID_DEVELOPER decimal(22) PRIMARY KEY NOT NULL,
   NAME_DEVELOPER varchar2(50),
   ID_DEVELOPER_LEVEL decimal(22) NOT NULL,
   ID_DEVELOPER_EMPLOYER decimal(22) NOT NULL
)
;
CREATE TABLE "MYTESTUSER"."DEVELOPER_EMPLOYER"
(
   ID_EMPLOYER decimal(22) PRIMARY KEY NOT NULL,
   NAME_EMPLOYER varchar2(50)
)
;
CREATE TABLE "MYTESTUSER"."DEVELOPER_LEVEL"
(
   ID_LEVEL decimal(22) PRIMARY KEY NOT NULL,
   LABEL_LEVEL varchar2(50)
)
;
ALTER TABLE DEVELOPER
ADD CONSTRAINT DEVELOPER_FK2
FOREIGN KEY (ID_DEVELOPER_EMPLOYER)
REFERENCES DEVELOPER_EMPLOYER(ID_EMPLOYER) ON DELETE NO ACTION ON UPDATE CASCADE

;
ALTER TABLE DEVELOPER
ADD CONSTRAINT DEVELOPER_FK2
FOREIGN KEY (ID_DEVELOPER_EMPLOYER)
REFERENCES DEVELOPER_EMPLOYER(ID_EMPLOYER) ON DELETE NO ACTION ON UPDATE CASCADE
ALTER TABLE DEVELOPER
ADD CONSTRAINT DEVELOPER_FK1
FOREIGN KEY (ID_DEVELOPER_LEVEL)
REFERENCES DEVELOPER_LEVEL(ID_LEVEL) ON DELETE NO ACTION ON UPDATE CASCADE

;
CREATE UNIQUE INDEX DEVELOPER_PK ON DEVELOPER(ID_DEVELOPER)
;
CREATE UNIQUE INDEX DEVELOPER_EMPLOYER_PK ON DEVELOPER_EMPLOYER(ID_EMPLOYER)
;
CREATE UNIQUE INDEX DEVELOPER_LEVEL_PK ON DEVELOPER_LEVEL(ID_LEVEL)
;
