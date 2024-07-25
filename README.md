# Documentación del Proyecto SpringBoot con JPA y Hibernate

## 1. Introducción
Este proyecto es una aplicación de ejemplo desarrollada en Spring Boot que utiliza JPA (Java Persistence API) e Hibernate para la gestión de la persistencia de datos. La aplicación permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una entidad `Person`.

## 2. Tecnologías Utilizadas
- **Spring Boot**: Framework para la creación de aplicaciones Java basadas en Spring que simplifica el proceso de desarrollo mediante convenciones predefinidas.
- **JPA (Java Persistence API)**: Especificación de Java que facilita la gestión de datos relacionales en aplicaciones Java.
- **Hibernate**: Implementación de JPA que proporciona las funcionalidades de mapeo objeto-relacional (ORM).

## 3. Relación entre JPA, Hibernate y Spring Boot
- **JPA** define una API estándar para la persistencia de datos en aplicaciones Java.
- **Hibernate** implementa esta API, proporcionando las capacidades ORM necesarias para mapear objetos Java a tablas de bases de datos y viceversa.
- **Spring Boot** integra JPA y Hibernate, simplificando la configuración y el uso de estas tecnologías mediante el uso de anotaciones y configuraciones automáticas.

## 4. Explicación de Archivos Clave

- **`pom.xml`**: Archivo de configuración de Maven que gestiona las dependencias del proyecto.
- **`application.properties`**: Archivo de configuración de la aplicación, donde se especifican propiedades como la configuración de la base de datos.
- **`SpringbootJpaApplication.java`**: Clase principal que lanza la aplicación Spring Boot.
- **`Person.java`**: Entidad JPA que representa la tabla `Person` en la base de datos.
- **`PersonRepository.java`**: Repositorio JPA que proporciona métodos para realizar operaciones CRUD en la entidad `Person`.
- **`PersonDto.java`**: Objeto de Transferencia de Datos (DTO) utilizado para transferir datos de la entidad `Person`.
- **`import.sql`**: Script SQL utilizado para inicializar datos en la base de datos.

## 5. Anotaciones Usadas y Su Función

- **`@SpringBootApplication`**: Anotación en la clase principal que habilita la configuración automática de Spring Boot, el escaneo de componentes y la configuración de Spring.
- **`@Entity`**: Marca una clase Java como una entidad JPA, lo que indica que debe mapearse a una tabla en la base de datos.
- **`@Table(name = "person")`**: Especifica el nombre de la tabla de la base de datos que se mapeará a la entidad JPA.
- **`@Id`**: Marca un campo como la clave primaria de la entidad.
- **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**: Indica que el valor de la clave primaria será generado automáticamente por la base de datos.
- **`@Column(name = "name")`**: Especifica el nombre de la columna en la tabla de la base de datos que se mapeará al campo de la entidad.
- **`@Repository`**: Marca una clase como un repositorio JPA, lo que facilita el manejo de excepciones y la traducción de estas a excepciones de Spring.
- **`@Autowired`**: Permite la inyección de dependencias automáticas en Spring.

## 6. Funcionamiento de la Aplicación

1. **Inicialización**: Al iniciar la aplicación, Spring Boot configura automáticamente la conexión a la base de datos utilizando las propiedades definidas en `application.properties`.
2. **Carga de Datos**: Si el archivo `import.sql` está presente, se ejecuta al inicio de la aplicación para cargar datos iniciales en la base de datos.
3. **Operaciones CRUD**:
   - **Crear**: Se puede crear una nueva entidad `Person` y guardarla en la base de datos utilizando el repositorio.
   - **Leer**: Se pueden recuperar entidades `Person` desde la base de datos mediante consultas al repositorio.
   - **Actualizar**: Se pueden actualizar entidades existentes.
   - **Eliminar**: Se pueden eliminar entidades `Person` de la base de datos.

## 7. Nuevas Funcionalidades Agregadas

### Clase `Audit`
Se ha añadido una nueva clase `Audit` que maneja las operaciones de auditoría mediante anotaciones JPA.

- **`@Embeddable`**: Marca la clase `Audit` como una clase embebible, lo que significa que sus atributos pueden ser incrustados en otras entidades.

#### Anotaciones de Ciclo de Vida

- **`@PrePersist`**: Se utiliza para ejecutar un método justo antes de que una entidad sea persistida en la base de datos. Este método puede ser utilizado para inicializar valores predeterminados o realizar validaciones antes de que la entidad se inserte en la base de datos.
- **`@PreUpdate`**: Se utiliza para ejecutar un método justo antes de que una entidad sea actualizada en la base de datos. Este método puede ser utilizado para actualizar valores, realizar validaciones, o registrar cambios antes de que la entidad se actualice.
- **`@PreRemove`**: Se utiliza para ejecutar un método justo antes de que una entidad sea eliminada de la base de datos. Este método puede ser utilizado para realizar tareas de limpieza o validaciones antes de que la entidad sea eliminada.
- **`@PostPersist`**: Se utiliza para ejecutar un método justo después de que una entidad haya sido persistida en la base de datos. Este método puede ser utilizado para realizar operaciones de seguimiento o notificación una vez que la entidad ha sido insertada.
- **`@PostUpdate`**: Se utiliza para ejecutar un método justo después de que una entidad haya sido actualizada en la base de datos. Este método puede ser utilizado para realizar operaciones de seguimiento o notificación una vez que la entidad ha sido actualizada.
- **`@PostRemove`**: Se utiliza para ejecutar un método justo después de que una entidad haya sido eliminada de la base de datos. Este método puede ser utilizado para realizar operaciones de seguimiento o notificación una vez que la entidad ha sido eliminada.
- **`@PostLoad`**: Se utiliza para ejecutar un método justo después de que una entidad ha sido cargada desde la base de datos. Este método puede ser utilizado para inicializar valores que no están persistidos en la base de datos o para realizar alguna lógica adicional una vez que la entidad ha sido cargada.

### Uso de `Audit` en la clase `Person`
- **`@Embedded`**: Se utiliza en una entidad para indicar que una instancia de una clase embebible se debe almacenar como parte de esta entidad. En este caso, la clase `Person` incluye una instancia de `Audit`, lo que permite la auditoría de las operaciones de persistencia y actualización.

## 8. Conclusión
Este proyecto demuestra cómo integrar Spring Boot con JPA e Hibernate para crear una aplicación CRUD básica. La configuración automática de Spring Boot y el uso de anotaciones de JPA e Hibernate simplifican el desarrollo y la gestión de la persistencia de datos. Además, la adición de la clase `Audit` y las anotaciones de ciclo de vida proporcionan una manera eficiente de manejar la auditoría de las operaciones en las entidades.
