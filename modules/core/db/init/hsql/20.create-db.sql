-- begin RICARDUINO_INGREDIENT
alter table RICARDUINO_INGREDIENT add constraint FK_RICARDUINO_INGREDIENT_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID)^
alter table RICARDUINO_INGREDIENT add constraint FK_RICARDUINO_INGREDIENT_ON_ACTUATOR foreign key (ACTUATOR_ID) references RICARDUINO_ACTUATOR(ID)^
create unique index IDX_RICARDUINO_INGREDIENT_UNIQ_NAME on RICARDUINO_INGREDIENT (NAME) ^
create index IDX_RICARDUINO_INGREDIENT_ON_PICTURE on RICARDUINO_INGREDIENT (PICTURE_ID)^
create index IDX_RICARDUINO_INGREDIENT_ON_ACTUATOR on RICARDUINO_INGREDIENT (ACTUATOR_ID)^
-- end RICARDUINO_INGREDIENT
-- begin RICARDUINO_ACTUATOR
create unique index IDX_RICARDUINO_ACTUATOR_UNIQ_NAME on RICARDUINO_ACTUATOR (NAME) ^
create unique index IDX_RICARDUINO_ACTUATOR_UNIQ_GPIO on RICARDUINO_ACTUATOR (GPIO) ^
-- end RICARDUINO_ACTUATOR
-- begin RICARDUINO_COCKTAIL_LINE
alter table RICARDUINO_COCKTAIL_LINE add constraint FK_RICARDUINO_COCKTAIL_LINE_ON_INGREDIENT foreign key (INGREDIENT_ID) references RICARDUINO_INGREDIENT(ID)^
alter table RICARDUINO_COCKTAIL_LINE add constraint FK_RICARDUINO_COCKTAIL_LINE_ON_COCKTAIL foreign key (COCKTAIL_ID) references RICARDUINO_COCKTAIL(ID)^
create index IDX_RICARDUINO_COCKTAIL_LINE_ON_INGREDIENT on RICARDUINO_COCKTAIL_LINE (INGREDIENT_ID)^
create index IDX_RICARDUINO_COCKTAIL_LINE_ON_COCKTAIL on RICARDUINO_COCKTAIL_LINE (COCKTAIL_ID)^
-- end RICARDUINO_COCKTAIL_LINE
-- begin RICARDUINO_COCKTAIL
alter table RICARDUINO_COCKTAIL add constraint FK_RICARDUINO_COCKTAIL_ON_GLASS foreign key (GLASS_ID) references RICARDUINO_GLASS(ID)^
alter table RICARDUINO_COCKTAIL add constraint FK_RICARDUINO_COCKTAIL_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID)^
create unique index IDX_RICARDUINO_COCKTAIL_UNIQ_NAME on RICARDUINO_COCKTAIL (NAME) ^
create index IDX_RICARDUINO_COCKTAIL_ON_GLASS on RICARDUINO_COCKTAIL (GLASS_ID)^
create index IDX_RICARDUINO_COCKTAIL_ON_PICTURE on RICARDUINO_COCKTAIL (PICTURE_ID)^
-- end RICARDUINO_COCKTAIL
-- begin RICARDUINO_GLASS
alter table RICARDUINO_GLASS add constraint FK_RICARDUINO_GLASS_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID)^
create unique index IDX_RICARDUINO_GLASS_UNIQ_NAME on RICARDUINO_GLASS (NAME) ^
create index IDX_RICARDUINO_GLASS_ON_PICTURE on RICARDUINO_GLASS (PICTURE_ID)^
-- end RICARDUINO_GLASS
