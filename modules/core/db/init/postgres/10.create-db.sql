-- begin RICARDUINO_INGREDIENT
create table RICARDUINO_INGREDIENT (
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
    PICTURE_ID uuid,
    AVAILABLE boolean not null,
    ACTUATOR_ID uuid,
    --
    primary key (ID)
)^
-- end RICARDUINO_INGREDIENT
-- begin RICARDUINO_ACTUATOR
create table RICARDUINO_ACTUATOR (
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
    GPIO integer not null,
    SIZE_ double precision not null,
    --
    primary key (ID)
)^
-- end RICARDUINO_ACTUATOR
-- begin RICARDUINO_COCKTAIL_LINE
create table RICARDUINO_COCKTAIL_LINE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    INGREDIENT_ID uuid not null,
    PARTS double precision not null,
    COCKTAIL_ID uuid not null,
    --
    primary key (ID)
)^
-- end RICARDUINO_COCKTAIL_LINE
-- begin RICARDUINO_COCKTAIL
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
)^
-- end RICARDUINO_COCKTAIL
-- begin RICARDUINO_GLASS
create table RICARDUINO_GLASS (
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
    PICTURE_ID uuid,
    SIZE_ double precision not null,
    --
    primary key (ID)
)^
-- end RICARDUINO_GLASS
