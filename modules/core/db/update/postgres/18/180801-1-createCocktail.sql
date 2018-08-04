create table RICARDUINO_COCKTAIL (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    GLASS_ID uuid,
    DESCRIPTION varchar(500),
    HOW_TO varchar(500),
    PICTURE_ID uuid,
    --
    primary key (ID)
);
