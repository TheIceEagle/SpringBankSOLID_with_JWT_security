create table Account (
                         id NVARCHAR(250) NULL ,
                         account_type NVARCHAR(250) NOT NULL,
                         clientID NVARCHAR(250) NOT NULL,
                         balance FLOAT NOT NULL,
                         withdraw_allowed BOOLEAN,
                         CONSTRAINT PK_id PRIMARY KEY (id)
);

create table Transaction (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             account_id NVARCHAR(250) NOT NULL,
                             amount FLOAT NOT NULL,
                             transaction NVARCHAR(250) NOT NULL,
                             date NVARCHAR(250) NOT NULL,
                             type NVARCHAR(250) NOT NULL, -- Fixed the typo here
                             CONSTRAINT PK_transaction_id PRIMARY KEY (id) -- Changed the constraint name to make it unique
);




CREATE TABLE user_table
(
    id INTEGER NOT NULL auto_increment,
    username NVARCHAR(MAX)  NOT NULL UNIQUE,
    password NVARCHAR(MAX)  NOT NULL,
    role NVARCHAR(255),
    CONSTRAINT PK_users PRIMARY KEY  (id)
);