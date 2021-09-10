# Library / Biblioteca 

#- [Instrucciones en Español](README_ES.md)
#- [Instructions in English](README_EN.md)

# LibREST1.0 

## Descripción

Proyecto desarrollado en Java JDK 8 utilizando Java Persistence para la persistencia y mapeo de los datos y Java API for RESTful Web Services (JAX-RS).

Se utilizó para el desarrollo el IDE Netbeans 12.0 en sistema operativo Windows 10 64bit.


## <a name="requirements"></a>Requerimeinto

 - Servidor de base de datos [MySQL Server 8.0.23](https://www.mysql.com/downloads).

 - [GlassFish Server 4.1.2](https://javaee.github.io/glassfish/download) para el despliegue o implementación del API REST **LibREST**.
    > Es requerido establecer un Pool de Conexión la base de datos (*JDBC Connection Pools*) y un Recurso JDBC (*JDBC Resource*) vinculado al Pool definido.

 
## <a name="uso"></a>Uso

A continuación se indican los usos de los Endpoints definido por el requerimiento del cliente.

- **Ver listado de libros**: Se mostrarán los libros registrados en la base de datos.
    ```JavaScript
    /books
    ```
- **Visualizar un libro**: Mostrar un libro especificando un valor numérico.
    ```
    /books/2
    ```
- **Visualizar por página de un libro en el formato deseado usando friendly routes (ej; /book/1 ó /book/1 /page/11/html).**: Mostrar las páginas de un libro especificando el formato de visualización sea en texto plano (plain) o formato HTML (html).
    
    ```
    /books/4/pages/1/plain
    ```
    ```
    /books/4/pages/1/html
    ```

## Ejecutando desde el IDE 
- Cumplir con los [requerimientos](#requirements) solicitados anteriormente.
- Es necesario tener el servidor de base de datos MySQL en ejecución y establecer los datos de conexión en el proyecto. 

    > Tener en cuenta se utilizará con fines de pruebas pudiendo afectar objetos coincidentes tales como base de datos o tablas.

- Al abrir el proyecto y compilarlo se creará la base de datos con las tablas a utilizar (*library.book* y *library.page*)

- Al finalizar la compilación se generará un archivo *librest-1.0.war* en la ruta *\librest\target* que debe ser desplegado al servidor *GlasshFish*  para proceder con las [pruebas de uso]()

    > Si al compilar por segunda vez presenta inconvenientes para ejecución, entonces se debe remover manualmente la etiqueta *< executions >*  del pluging *liquibase-maven-plugin* en el archivo *pom.xml*. (bug por corregir)

    ```XML
    <executions>
        <execution>
            <phase>process-resources</phase>
            <goals>
                <goal>update</goal>
            </goals>
        </execution>
    </executions>
    ```


## Librearías utilizadas

- Liquibase 4.2.0 http://www.liquibase.org/
- Apache Log4J 2.12.0 http://logging.apache.org/log4j/2.x/maven-artifacts.html

