Sistema de Guardado de una Librería Web 

El objetivo de este ejercicio consiste en el desarrollo de un sistema web de guardado de 
libros  en  JAVA  utilizando  una  base  de  datos  MySQL,  JPA  Repository  para  persistir 
objetos y Spring Boot como framework de desarrollo web.    

Paquetes del Proyecto 
Los paquetes que se deben utilizar para el proyecto se deben estructurar de la siguiente 
manera:  
?  vistas: en este paquete se almacenarán aquellas clases que se utilizarán como vistas 
con el usuario. 
?  controladores: en este paquete se almacenarán aquellas clases que se utilizarán para 
mediar entre la vista con el usuario y las capas inferiores. 
?  servicios:  en  este  paquete  se  almacenarán  aquellas  clases  que  llevarán  adelante 
lógica del negocio.    
?  repositorios: en este paquete se crearán los repositorios que servirán como interfaces 
entre el modelo de objetos y la base de datos relacional. 
?  entidades: en este paquete se almacenarán aquellas clases que es necesario persistir 
en la base de datos. 

