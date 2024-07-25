package com.app.springboot_jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * La anotación @Entity de Jakarta (anteriormente conocida como
 * javax.persistence.Entity) se utiliza en el contexto de Java Persistence API
 * (JPA) para marcar una clase como una entidad que debe ser gestionada por el
 * sistema de persistencia. Es decir, una clase anotada con @Entity representa
 * una tabla en la base de datos y cada instancia de esta clase corresponde a
 * una fila de dicha tabla.
 * 
 * Propósitos y Beneficios de @Entity
 * Manejo de Datos Persistentes: Marca una clase como una entidad JPA, lo que
 * significa que los objetos de esta clase pueden ser almacenados y recuperados
 * de una base de datos.
 * Mapeo a una Tabla de Base de Datos: Define una clase como una tabla en la
 * base de datos. Los campos de la clase se mapean a las columnas de la tabla.
 * Compatibilidad con el Contexto de Persistencia: Permite que el EntityManager
 * de JPA gestione la persistencia, recuperación, actualización y eliminación de
 * instancias de la entidad.
 * 
 * Beneficios
 * Automatización de Operaciones CRUD: Las entidades JPA permiten que el
 * EntityManager gestione automáticamente las operaciones de Create, Read,
 * Update y Delete en la base de datos.
 * Abstracción de la Base de Datos: Facilita la interacción con la base de datos
 * sin necesidad de escribir SQL directamente, lo que reduce el riesgo de
 * errores y mejora la mantenibilidad del código.
 * Integración con Herramientas y Frameworks: Se integra perfectamente con
 * frameworks de Java como Spring Data JPA, facilitando la creación de
 * repositorios y servicios para la gestión de datos.
 * 
 * La anotación @Entity es esencial en JPA para definir una clase como una
 * entidad persistente, permitiendo el mapeo de objetos Java a registros de una
 * base de datos relacional. Esto habilita un manejo eficiente y automático de
 * datos persistentes dentro de aplicaciones Java, mejorando la productividad y
 * la mantenibilidad del desarrollo de software.
 */
@Entity
/*
 * La anotación @Table es opcional, si no se agrega la clase va a estar mapeada
 * a la tabla en la BDD que tenga el mismo nombre que la clase
 */
@Table(name = "persons")
public class Person {

    /*
     * @GeneratedValue
     * La anotación @GeneratedValue se usa en JPA para especificar cómo debe ser
     * generado el valor de la clave primaria (o ID) de una entidad. Esto es
     * particularmente útil cuando deseas que la base de datos se encargue de la
     * generación de este valor automáticamente.
     * 
     * GenerationType
     * El GenerationType es una enumeración que define las diferentes estrategias
     * que JPA puede usar para generar los valores de las claves primarias. JPA
     * soporta cuatro estrategias principales:
     * 
     * IDENTITY
     * SEQUENCE
     * TABLE
     * AUTO
     * 
     * 1. GenerationType.IDENTITY
     * Uso: Esta estrategia indica que los valores de las claves primarias serán
     * generados automáticamente por la base de datos mediante una columna
     * auto-incremental.
     * Funcionamiento: La base de datos se encarga de generar un valor único cada
     * vez que se inserta una nueva fila. Es común en bases de datos como MySQL, SQL
     * Server y PostgreSQL.
     * Ventaja: Es simple y directo, especialmente para bases de datos que soportan
     * columnas auto-incrementales.
     * Desventaja: Puede no ser la mejor opción para bases de datos que no soportan
     * nativamente el auto-incremento.
     * 
     * 2. GenerationType.SEQUENCE
     * Uso: Esta estrategia utiliza una secuencia de la base de datos para generar
     * valores únicos para las claves primarias.
     * Funcionamiento: Una secuencia es un objeto de base de datos que genera
     * números en un orden secuencial. Cada vez que se necesita un nuevo valor para
     * una clave primaria, JPA obtiene el siguiente valor de la secuencia.
     * Ventaja: Muy eficiente en términos de generación de valores únicos. Ideal
     * para bases de datos como Oracle, PostgreSQL y DB2 que soportan secuencias.
     * Desventaja: Puede requerir configuración adicional en la base de datos para
     * definir la secuencia.
     * 
     * 3. GenerationType.TABLE
     * Uso: Esta estrategia utiliza una tabla específica en la base de datos para
     * generar valores únicos para las claves primarias.
     * Funcionamiento: JPA utiliza una tabla especial que mantiene los valores
     * generados y proporciona un nuevo valor cada vez que se necesita una clave
     * primaria.
     * Ventaja: Funciona en cualquier base de datos ya que solo requiere una tabla
     * adicional.
     * Desventaja: Menos eficiente que IDENTITY o SEQUENCE debido a la necesidad de
     * acceder a una tabla adicional cada vez que se genera un valor.
     * 
     * 4. GenerationType.AUTO
     * Uso: Esta estrategia permite que JPA elija automáticamente la mejor
     * estrategia de generación de claves primaria según el proveedor de
     * persistencia y la base de datos.
     * Funcionamiento: El proveedor de JPA decide cuál estrategia usar (IDENTITY,
     * SEQUENCE o TABLE) basándose en las capacidades de la base de datos.
     * Ventaja: Es flexible y conveniente, ya que no necesitas especificar la
     * estrategia exacta.
     * Desventaja: Puede no ser óptima si tienes requisitos específicos sobre cómo
     * deben ser generados los valores de las claves primarias.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @Column(name = "programing_language")
    private String programingLanguage;

    /**
     * Si tenemos un constructor personalizado donde pasaremos parámetros, estamos
     * obligados siempre a tener un constructor vacío, ya que eso lo maneja JPA, es
     * decir, JPA va a utilizar el constructor vacío para crear la instancia,
     * Hibernate y JPA utilizan un constructor vacío para poblar los datos de la
     * tabla al objeto, etc
     */
    public Person() {

    }

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Person(Long id, String name, String lastname, String programingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programingLanguage = programingLanguage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProgramingLanguage() {
        return programingLanguage;
    }

    public void setProgramingLanguage(String programingLanguage) {
        this.programingLanguage = programingLanguage;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", programingLanguage='" + getProgramingLanguage() + "'" +
                "}";
    }
}
