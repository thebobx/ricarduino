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
);
