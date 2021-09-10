# Library API

#### Descripcion

El proyecto fue desarrollado en `Node`, utilizando `TypeScript` para la codigicación. No se empleó ningún framework, mas sí se creó un sencillo `IoC container`, con la finalidad de que éste se encargue de la inyección de dependencias `(DI)` y por ende, poder implementar una estructura basada en los principios de `Hexagonal Architecture`, que al final no es más que otra forma de `Clean Architecture`.

El proyecto está estructurado de la siguiente forma:


# ![Estructura](https://gitlab.com/gbh-candidates/jerson-pena---jersonsw---2020-2-10-library-api---js/-/raw/jerson-pena/project-structure.png)

Cada directorio en el proyecto está destinado para lo siguiente:

* **core**: Contiene las entidades de dominio, los usecases y demás cosas relativas a la lógica de negocios. Es agnóstico de framewokrs y no posee dependencias de terceros. En una arquitectura hexagonal, este es el hexágono o centro de todo. Además, este contiene los puertos (ports), que son interfaces cuya funcionalidad es la de abstraer la comunicación entre el core y los adaptadores, que no son más que implementaciones basadas en el core, pero que sí dependen de frameworks o librerías de terceros.
* **data**: Adaptador que contiene todo lo relativo a la persistencia de datos: entidades, migraciones, seeds, factories, repositorios y data mappers. Este módulo depende de `TypeORM`, `FakerJS` y  `TypeORM Seeding`.
* **commons**: Contiene clases y funciones genéricas que pueden ser usadas en cualquier módulo y en cualquier proyecto.  
* **framework**: Contiene todo lo relativo a la parte de reflexión (decorators), el IoC container y el routes mapping (se realiza un mapping entre los controllers y las rutas o endpoints)
* **main**: Se puede definir como el root element de la aplicación, donde se encuentra el módulo principal, a partir del cual se realiza bootstrap. En este módulo se configuran los controllers y los usecases, de modo que puedan ser manejados por el IoC container e inyectados en los sitios desde donde son requeridos.
* **index.ts**: Entry point de la aplicación, desde donde se crea una instancia de la clase `Application` (contenida en el directorio `framework`), pasando como parámetro el módulo principal (localizado en el directorio `main`).
* **nodemon.json**: Contiene las configuraciones para `nodemon`, utilidad empleada para auto-recargar los cambios en tiempo de desarrollo.
* **ormconfig.js**: Contiene datos de configuración para `TypeORM`, entre ellos las credeciales para la base de datos.


#### Requerimientos Técnicos 

- `Node`
- `NPM`
- `PostgreSQL`

**Nota:** *Este proyecto fue creado en Windows 10 y las versiones usadas para este proyecto, fueron: Node (v12.14.1), NPM (6.13.7) y PostgreSQL (11.4)*

### Configuracion, Instalación y ejecución

- Configurar las credenciales de la base de datos, localizadas en el archivo `ormconfig.js`
- Correr `npm run bootstrap` para instalar las dependencias, ejecutar las migraciones, los seeds y la aplicación

Si en vez de ejecutar todos los comandos de una sola vez, como lo hace `npm run bootstrap`, puede correrlos uno por uno del modo siguiente:

- `npm install`: instala todas las dependencias del proyecto
- `npm run migration:run` o su equivalente `typeorm migration:run`: ejecuta las migraciones.
- `npm run seed`: ejecuta los seeds.
- `npm run start:dev` o `npm start` para correr la aplicacion en modo de desarrollo o producción, respectivamrente.

### Consumo del API 

El API estará expuesto a través del puerto `3000`, configurable en el archivo `app.config.ts`, localizado en el directorio `main`.

*Se recomienda usar postman para probar los endpoints.*

Basado en el requerimiento, los endpints expuestos son los siguientes:

##### **Requerimiento #1 - Ver listado de libros**


* **`GET /books`**:

Retorna el listado de libros paginados (por defecto 10). Los parámetros para paginación y ordenamiento deben ser pasados como un query string, del modo siguiente: 
   
   `/books?from=0&size=50&orderField=id&orderDirection=DESC`
   
   - `from`: es el record a partir del cual se desean los resultados. 
   - `size`: es la cantidad de records que se desea obtener.
   - `orderField`: es el campo por el cual deseamos hacer el ordenamiento.
   - `orderDirection`: es la para indicar si deseamos ordenar en modo ascendente o descendente.


Al realizar un request, recibiríamos una respuesta como esta:
   
   
    ```
       {
         "content": {
            "data": [
                {
                    "id": 1,
                    "title": "Don Quijote De La Mancha",
                    "isbn": "322-13240968",
                    "pages": [
                        {...},
                        {...}
                    ]
                },
                ...
             ],
             "from": 0,
             "returnedItems": 10,
             "totalItems": 210
           },
         "message": "",
         "timestamp": 1581791126404
       }
    ```
   
Debido a que el payload seria muy largo para mostrar aqui, solo se muestra la estructura con algunos datos. Como se puede ver, la respuesta es un JSON con el listado de libros disponibles.
   
   
##### **Requerimiento #2 - Visualizar un libro**


Para este caso cree 2 endpoints: uno para obtener las informaciones del libro en formato JSON y otro para poder leer el libro completo en formato HTML.


* **`GET /books/{id}`**:


Similar a la respuesta del endpoint anterior, pero sin el arreglo `data`, el cual contenia la información de paginación y los datos, pero para este caso no es necesario paginar los resultados:

    
    ```
       {
         "content": {
            "id": 1,
            "title": "Don Quijote De La Mancha",
            "isbn": "322-13240968",
            "pages": [
                {...},
                {...}
            ]
         },
         "message": "",
         "timestamp": 1581791126404
       }
    ```
    
* **`GET /books/{id}/{format}`**:

    
`format`: por ahora, solo tiene soporte para `html` y `text`, sin embargo, se puede extender.
    
Para agregar un nuevo formato se requiere: crear un nuevo tipo en el enum `FormatType`, crear una nueva estrategia para el nuevo formato implementando la interface `BookFormatter` y agregar el soporte para dicha estrategia en `FormatterFactory`, de modo que se pueda obtener una instancia de la misma pasando el tipo como parámetro. 
    
Luego de esto, a traves del mismo endpoint, se podrá obtener cualquier libro con el nuevo formato.  

Dependiendo del formato especificado en el parámetro, asi sera la respuesta. Si es `html`, retornará el contenido del libro formateado en HTML y si es `text`, lo retornará en texto plano.
    

##### **Requerimiento #3 - Visualizar por página de un libro en el formato deseado**

* **`GET /{bookId}/pages/{pageNumber}/{format}`**:

    
Funciona similar al endpoint anterior, pero en vez de retornar el libro completo, retorna solo una página del mismo.

#### Resumen 

El proyecto fue desarollado siguiendo los principios de `Hexagonal Architecture`, conocida también como `Ports And Adapters`. Ya que este tipo de arquitectura sigue los principios de `Clean Architecture`, también sigue los principios `SOLID`, los cuales a su vez incluye `Dependency Inversion Principle`, por lo que tuve que crear una solución para manejar la inyección de dependencias de modo automatico: un `IoC Container`. 

En este proyecto empleo patrones de diseño como `Factory Method`, `Adapter`, `Strategy` y `Dependency Injection`. 
