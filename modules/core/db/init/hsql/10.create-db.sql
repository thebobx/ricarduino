-- begin RICARDUINO_INGREDIENT
create table RICARDUINO_INGREDIENT (
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
    PICTURE_ID varchar(36),
    AVAILABLE boolean not null,
    ACTUATOR_ID varchar(36),
    --
    primary key (ID)
)^
-- end RICARDUINO_INGREDIENT
-- begin RICARDUINO_ACTUATOR
create table RICARDUINO_ACTUATOR (
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
    GPIO integer not null,
    SIZE_ double precision not null,
    --
    primary key (ID)
)^
-- end RICARDUINO_ACTUATOR
-- begin RICARDUINO_COCKTAIL_LINE
create table RICARDUINO_COCKTAIL_LINE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    INGREDIENT_ID varchar(36) not null,
    PARTS double precision not null,
    COCKTAIL_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end RICARDUINO_COCKTAIL_LINE
-- begin RICARDUINO_COCKTAIL
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
)^
-- end RICARDUINO_COCKTAIL
