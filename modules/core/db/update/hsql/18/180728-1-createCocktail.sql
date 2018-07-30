create table RICARDUINO_COCKTAIL (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION varchar(500),
    HOW_TO varchar(500),
    PICTURE_ID varchar(36),
    --
    primary key (ID)
);
