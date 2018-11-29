insert into SEC_FILTER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, COMPONENT, NAME, CODE, XML, USER_ID, GLOBAL_DEFAULT)
values ('c3bf868c-274c-43b5-d072-092a34d918e8', 17, '2018-08-03 23:14:28', 'admin', '2018-08-09 13:56:18', 'admin', null, null, '[ricarduino$Cocktail.browse].filter', 'Available', '69', '<?xml version="1.0" encoding="UTF-8"?>

<filter>
  <and>
    <c name="utHSseLjYa" unary="true" width="1" type="CUSTOM" locCaption="Only available ?" entityAlias="e"><![CDATA[{E}.id not in (select e.id from ricarduino$Cocktail e join e.cocktailLines li where li.ingredient.available = false)]]>
      <param name="component$filter.utHSseLjYa52527" javaClass="java.lang.Boolean">NULL</param>
    </c>
  </and>
</filter>
', null, true);