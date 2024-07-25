package com.app.springboot_jpa;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.app.springboot_jpa.dto.PersonDto;
import com.app.springboot_jpa.entities.Person;
import com.app.springboot_jpa.repositories.PersonRepository;

/**
 * En una aplicación de Spring Boot, implementar la interfaz CommandLineRunner
 * en la clase principal que está anotada con @SpringBootApplication tiene
 * varios propósitos y beneficios
 * 
 * . Ejecución de Código al Inicio de la Aplicación
 * La principal utilidad de CommandLineRunner es permitir la ejecución de código
 * específico justo después de que el contexto de la aplicación se ha iniciado y
 * la aplicación está lista para recibir solicitudes. Esto es útil para realizar
 * inicializaciones o configuraciones adicionales que no se pueden hacer
 * mediante las anotaciones de configuración estándar.
 * 
 * 2. Tareas de Inicialización
 * Puedes usar CommandLineRunner para realizar tareas de inicialización, como:
 * 
 * Precargar datos en una base de datos.
 * Configurar conexiones a servicios externos.
 * Realizar configuraciones específicas que necesitan estar listas antes de que
 * la aplicación procese cualquier solicitud.
 * 3. Uso en Aplicaciones de Línea de Comando
 * Para aplicaciones que están diseñadas para ejecutarse desde la línea de
 * comandos, CommandLineRunner permite capturar y procesar los argumentos
 * pasados en la línea de comandos y actuar en consecuencia.
 * 
 * Implementar CommandLineRunner es una forma poderosa y flexible de ejecutar
 * código al inicio de una aplicación Spring Boot, facilitando tareas de
 * inicialización y configuración personalizada.
 */
@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// list();
		// findOne();
		// create();
		// update();
		// delete();
		// delete2();
		// personalizedQueries2();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperAndLowerCase();
		// personalizedQueriesBetween();
		// queriesFunctionAggregation();
		// subQueries();
		whereIn();
	}

	/**
	 * En Spring Boot, la anotación @Transactional se utiliza para manejar
	 * transacciones en un contexto de base de datos. Es parte del módulo de
	 * transacciones de Spring y se usa para garantizar que las operaciones de base
	 * de datos se completen de manera atómica, consistente, aislada y duradera
	 * (propiedades ACID).
	 * 
	 * ¿Qué es una Transacción?
	 * Una transacción es un conjunto de operaciones que se ejecutan como una sola
	 * unidad lógica. Todas las operaciones dentro de una transacción deben
	 * completarse con éxito; de lo contrario, todas deben revertirse (rollback)
	 * para mantener la consistencia de los datos.
	 * 
	 * Funcionamiento de @Transactional
	 * Cuando un método está anotado con @Transactional, Spring gestiona
	 * automáticamente el inicio y el final de una transacción al comienzo y al
	 * final del método. Si el método se completa con éxito, Spring confirma
	 * (commit) la transacción; si ocurre una excepción, Spring realiza una
	 * reversión (rollback).
	 * 
	 * Propiedades Comunes de @Transactional
	 * propagation: Define el comportamiento de la transacción si ya existe una
	 * transacción activa. Algunos valores comunes son:
	 * 
	 * REQUIRED (por defecto): Usa la transacción actual o crea una nueva si no
	 * existe.
	 * REQUIRES_NEW: Siempre crea una nueva transacción, suspendiendo la actual si
	 * existe.
	 * MANDATORY: Debe ejecutarse dentro de una transacción existente; si no hay
	 * ninguna, lanza una excepción.
	 * SUPPORTS: Si hay una transacción existente, usa esa transacción; si no, se
	 * ejecuta sin transacción.
	 * NOT_SUPPORTED: Siempre se ejecuta sin transacción, suspendiendo cualquier
	 * transacción existente.
	 * NEVER: Nunca debe ejecutarse dentro de una transacción; si hay una
	 * transacción existente, lanza una excepción.
	 * NESTED: Ejecuta dentro de una transacción anidada si hay una transacción
	 * existente.
	 * isolation: Define el nivel de aislamiento de la transacción, controlando cómo
	 * las operaciones de la transacción son visibles para otras transacciones
	 * concurrentes:
	 * 
	 * DEFAULT: Usa el nivel de aislamiento por defecto de la base de datos.
	 * READ_UNCOMMITTED: Permite leer datos no confirmados.
	 * READ_COMMITTED: Permite leer datos confirmados, previene lecturas sucias.
	 * REPEATABLE_READ: Evita lecturas no repetibles.
	 * SERIALIZABLE: El nivel de aislamiento más alto, evita lecturas fantasmas.
	 * timeout: Especifica el tiempo máximo (en segundos) que la transacción puede
	 * estar activa antes de ser forzada a una reversión.
	 * 
	 * readOnly: Indica si la transacción es solo de lectura, optimizando las
	 * operaciones para solo leer datos sin permitir modificaciones.
	 * 
	 * rollbackFor y noRollbackFor: Especifica las excepciones que deben causar (o
	 * no) una reversión.
	 * 
	 * la anotación @Transactional proporciona una manera declarativa de manejar
	 * transacciones en Spring Boot, permitiendo controlar aspectos cruciales como
	 * la propagación, el aislamiento, los tiempos de espera y el comportamiento de
	 * reversión.
	 */
	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String name = scanner.next();
		System.out.println("Ingrese el apellido");
		String lastName = scanner.next();
		System.out.println("Ingrese el lenguaje de programación");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastName, programmingLanguage);
		Person personCreatedInDB = repository.save(person);
		System.out.println(personCreatedInDB);

		System.out.println("Usuario creado");
		repository.findById(personCreatedInDB.getId()).ifPresent(System.out::println);
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona");
		Long id = scanner.nextLong();
		Optional<Person> personOptional = repository.findById(id);
		personOptional.ifPresent(p -> {
			System.out.println(p);
			System.out.println("Ingrese el nuevo lenguaje de programación:");
			String programmingLanguage = scanner.next();
			p.setProgramingLanguage(programmingLanguage);
			Person personUpdate = repository.save(p);
			System.out.println(personUpdate);
		});
		scanner.close();
	}

	@Transactional
	public void delete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona a eliminar:");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void delete2() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona a eliminar:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(repository::delete,
				() -> System.out.println("No existe una persona con el ID ingresado"));

		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {

		Scanner scanner = new Scanner(System.in);
		// System.out.println("====================== consulta solo el nombre por el id
		// ======================");
		// System.out.println("Ingrese el ID de la persona a buscar");
		// Long id = scanner.nextLong();
		// String name = repository.getNameById(id);
		// System.out.println(name);

		// System.out.println("====================== consultar nombre completo por el
		// id ======================");
		// System.out.println("Ingrese el ID de la persona a buscar");
		// Long id2 = scanner.nextLong();
		// String fullName = repository.getFullNameById(id2);
		// System.out.println(fullName);

		System.out
				.println("====================== consultas por campos personalizados por el id ======================");

		System.out.println("Ingrese el ID de la persona a buscar");
		Long id3 = scanner.nextLong();
		Object[] personReg = (Object[]) repository.obtenerPersonDataById(id3);
		System.out.println("id=" + personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2]
				+ ", lenguaje=" + personReg[3]);

		System.out.println("====================== consulta por lista ======================");
		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(p -> {
			System.out.println("id=" + p[0] + ", nombre=" + p[1] + ", apellido=" + p[2]
					+ ", lenguaje=" + p[3]);
		});

		scanner.close();
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println("========== consulta por objeto persona y lenguaje de programacion ==========");
		List<Object[]> personRegs = repository.findAllMixPerson();
		personRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println(
				"========== consulta que puebla y devuelve objeto entity de una instancia personalizada ==========");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out
				.println("========== consulta que puebla y devuelve objeto dto de una clase personalizada ==========");
		List<PersonDto> personDtos = repository.findAllPersonDto();
		personDtos.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("========== consultas con nombres de personas ==========");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("========== consultas con nombres unicos de personas ==========");
		List<String> names2 = repository.findAllNamesDistinct();
		names2.forEach(System.out::println);

		System.out.println("========== cantidad de lenguajes de programación ==========");
		Long totalLanguage = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println("Total: " + totalLanguage);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("========== consulta de nombres y apellidos de personas ==========");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);
		System.out.println("========== Upper Case ==========");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);
		System.out.println("========== Lower Case ==========");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out.println("========== consultas por rangos ==========");
		List<Person> person = repository.findByIdBetweenOrderByNameDesc(2L, 5L);
		person.forEach(System.out::println);

		person = repository.findByNameBetweenOrderByNameDescLastnameAsc("J", "Z");
		person.forEach(System.out::println);

		person = repository.getAllOrderByName();
		person.forEach(System.out::println);

		person = repository.findAllByOrderByNameDescLastnameDesc();
		person.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {
		System.out.println("========== consulta con el total de registros de la tabla persona ==========");
		Long count = repository.getTotalPerson();
		System.out.println(count);
		System.out.println("========== consulta con el valor minimo del id ==========");
		Long min = repository.getMinId();
		System.out.println(min);
		System.out.println("========== consulta con el valor maximo del id ==========");
		Long max = repository.getMaxId();
		System.out.println(max);
		System.out.println("========== consulta de el nombre y su largo de caracteres ==========");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name: " + name + ", length: " + length);
		});
		System.out.println("========== consulta de el nombre mas corto ==========");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println(minLengthName);
		System.out.println("========== consulta de el nombre mas largo ==========");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println(maxLengthName);
		System.out.println("========== consulta de el nombre mas corto con su cantidad de caracteres ==========");
		regs = repository.getMinLengthNameAndLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Name: " + name + ", Length: " + length);
		});
		System.out.println(
				"========== consultas resumen de funciones de agregacion min, max, sum, avg, count ==========");
		Object[] resumeReg = (Object[]) repository.getResumeAggregationFunction();
		System.out.println("min=" + resumeReg[0] + ", max=" + resumeReg[1] + ", sum=" + resumeReg[2] + ", avg="
				+ resumeReg[3] + ", count=" + resumeReg[4]);

	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("========== consulta por el nombre mas corto y su largo ==========");
		List<Object[]> regs = repository.getLongestName();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("========== consulta para obtener el ultimo registro de Persona ==========");
		Optional<Person> optionalPerson = repository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("========== consulta where in ==========");
		List<Person> person = repository.getPersonsByIds(Arrays.asList(1L, 2L, 3L));
		person.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne() {
		// Person person = repository.findById(1L).orElse(null);
		// System.out.println(person);
		repository.findById(1L).ifPresent(System.out::println);
		repository.findOne(2L).ifPresent(System.out::println);
		repository.findOneByName("Barry").ifPresent(System.out::println);
		repository.findOneLikeName("nel").ifPresent(System.out::println);
		repository.findByName("Lionel").ifPresent(System.out::println);
		repository.findByNameContaining("anio").ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void list() {
		List<Person> persons = (List<Person>) repository.findAll();
		persons.stream().forEach(person -> System.out.println(person));
		System.out.println("--------------------------------");

		List<Person> persons2 = (List<Person>) repository.findByProgramingLanguage("Kotlin");
		persons2.stream().forEach(person -> System.out.println(person));

		System.out.println("--------------------------------");
		List<Person> persons3 = (List<Person>) repository.buscarPorProgramingLanguage("Python", "Cristianio");
		persons3.stream().forEach(p -> System.out.println(p));

		System.out.println("-----------------------------------");
		List<Person> persons4 = (List<Person>) repository.findByProgramingLanguageAndName("JavaScript", "Barry");
		persons4.stream().forEach(p -> System.out.println(p));

		System.out.println("---------------------------------------");
		List<Object[]> personValues = (List<Object[]>) repository.obtenerPersonValues();
		personValues.stream().forEach(p -> System.out.println(p[0] + " es experto en " + p[1]));
	}
}
