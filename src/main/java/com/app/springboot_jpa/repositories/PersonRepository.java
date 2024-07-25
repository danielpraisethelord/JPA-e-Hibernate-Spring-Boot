package com.app.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.springboot_jpa.dto.PersonDto;
import com.app.springboot_jpa.entities.Person;

/**
 * La clase CrudRepository es parte de Spring Data JPA y proporciona un conjunto
 * de métodos CRUD (Create, Read, Update, Delete) para la manipulación de
 * entidades persistentes en una base de datos. Esta interfaz genérica permite
 * que los repositorios específicos se beneficien de las operaciones CRUD sin
 * necesidad de implementarlas manualmente.
 * 
 * Uso de CrudRepository
 * Cuando defines un repositorio que extiende CrudRepository, obtienes
 * automáticamente una serie de métodos predefinidos para operar con la entidad
 * correspondiente. Esto hace que el desarrollo sea más eficiente y reduce la
 * cantidad de código repetitivo.
 * 
 * Genéricos en CrudRepository
 * El CrudRepository usa dos parámetros genéricos:
 * 
 * T: La entidad con la que trabajará el repositorio.
 * ID: El tipo de la clave primaria (o ID) de la entidad.
 * 
 * En este ejemplo:
 * 
 * PersonRepository es una interfaz que extiende CrudRepository.
 * T es Person, la entidad con la que trabajará el repositorio.
 * ID es Long, el tipo de la clave primaria de la entidad Person.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    /*
     * Como buena práctica cuando trabajamos con CrudRepository y la API de Spring
     * Boot con JPA, es mejor
     * utilizar el Optional, porque nos provee una envoltura del objeto del
     * resultado de la consulta y de esa forma saber si lo encontró, si está
     * presente o no
     * 
     * Esta query es la misma del método findById de CrudRepository, pero es una
     * manera de hacerlo con una Query personalizada envolviendo el objeto en un
     * Optional
     */
    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneByName(String name);

    @Query("select p from Person p where p.name like %:name%")
    Optional<Person> findOneLikeName(@Param("name") String name);

    Optional<Person> findByName(String name);

    Optional<Person> findByNameContaining(String name);

    /*
     * Consulta personalizada en base al nombre del atributo, siempre es importante
     * respetar el query method
     */
    List<Person> findByProgramingLanguage(String programingLanguage);

    /*
     * La anotación @Query en Spring Data JPA se utiliza para definir consultas SQL
     * personalizadas directamente en los métodos del repositorio. Esta anotación
     * permite escribir consultas JPQL (Java Persistence Query Language) o SQL
     * nativas, proporcionando flexibilidad adicional cuando las consultas generadas
     * automáticamente por Spring Data JPA no son suficientes.
     * 
     * Propósito de @Query
     * Consultas Personalizadas: Permite definir consultas personalizadas que no se
     * pueden expresar utilizando los métodos de consulta proporcionados por defecto
     * por Spring Data JPA.
     * JPQL o SQL Nativo: Puedes usar @Query para escribir consultas en JPQL o SQL
     * nativo según tus necesidades.
     * 
     * En este caso se realizó una consulta JPA, ya que se devuelve un Objeto de
     * tipo p o Person, ya que devuelve el Objeto Person, en el ejemplo
     * programingLanguage, es el atributo de la clase, no el campo de la tabla
     */
    @Query("select p from Person p where p.programingLanguage = ?1 and p.name = ?2")
    List<Person> buscarPorProgramingLanguage(String programingLanguage, String name);

    /*
     * Para este ejemplo la clase Person debe tener un constructor con esos
     * atributos (name, lastname)
     */
    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select new com.app.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    List<Person> findByProgramingLanguageAndName(String programingLanguage, String name);

    @Query("select p.name, p.programingLanguage from Person p")
    List<Object[]> obtenerPersonValues();

    @Query("select p, p.programingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select p.id, p.name, p.lastname, p.programingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programingLanguage from Person p where p.id = :id")
    Object obtenerPersonDataById(@Param("id") Long id);

    @Query("select p.name, p.programingLanguage from Person p where p.programingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonValues(String programingLanguage, String name);

    @Query("select p.name, p.programingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonValues(String name);

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select count(distinct(p.name)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();

    // @Query("select concat(p.name, ' ', p.lastname) from Person p")
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select upper(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(concat(p.name, ' ', p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc, p.lastname asc")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc, p.lastname asc")
    List<Person> findAllBetweenName(String c1, String c2);

    List<Person> findByIdBetweenOrderByNameDesc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDescLastnameAsc(String c1, String c2);

    @Query("select p from Person p order by p.name")
    List<Person> getAllOrderByName();

    List<Person> findAllByOrderByNameDescLastnameDesc();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    @Query("select p.name, length(p.name) from Person p where length(p.name) = (select min(length(p.name)) from Person p)")
    List<Object[]> getMinLengthNameAndLength();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();

    @Query("select p.name, length(p.name) from Person p where length(p.name) = (select max(length(p.name)) from Person p)")
    public List<Object[]> getLongestName();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    @Query("select p from Person p where p.id in :ids")
    public List<Person> getPersonsByIds(@Param("ids") List<Long> ids);
}