package br.com.trier.ProjetoJovemDev.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.trier.ProjetoJovemDev.BaseTests;
import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class CourseServiceTest extends BaseTests{
	
	@Autowired
	CourseService service;
	
	@Autowired
	StatusService statusService;
	
	@Test
	@DisplayName("Teste buscar curso por id")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void findByIdTest() {
		var curso = service.findById(1);
		assertThat(curso).isNotNull();
		assertEquals(1, curso.getId());
		assertEquals("Direito", curso.getName());
	}
	
	@Test
	@DisplayName("Teste buscar curso por id inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Curso 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os cursos")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void listAllTest() {
		List<Course> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar curso")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void alterTeacherTest() {
		var course = new Course(1, "Direito",  7.0, statusService.findById(3));
		service.update(course);
		course = service.findById(1);
		assertThat(course).isNotNull();
		assertEquals(1, course.getId());
		assertEquals(7.0, course.getPassingGrade());
		assertEquals("Direito", course.getName());
		assertEquals(3, course.getStatus().getId());

	}
	
	@Test
	@DisplayName("Teste alterar curso inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Curso 10 não encontrado", exception.getMessage());
		List<Course> lista = service.listAll();
		assertEquals(3, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar curso")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<Course> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar curso inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Curso 10 não encontrado", exception.getMessage());
		List<Course> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar curso por nome que inicia com")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void findUserByNameStartsWithTest() {
		List<Course> lista = service.findByNameStartingWithIgnoreCase("D");
		assertEquals(1, lista.size());
	}
	
	@Test
	@DisplayName("Teste buscar curso por nome que inicia com inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findByNameStartingWithIgnoreCase("X"));
		assertEquals("Nenhum curso encontrado", exception.getMessage());
	}
	
	@Test
	@DisplayName("Teste inserir curso")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void insertTeacherTest() {
		Course course = new Course(1, "Direito",  7.0, statusService.findById(3));
		service.insert(course);
		course = service.findById(1);
		assertThat(course).isNotNull();
		assertEquals(1, course.getId());
		assertEquals(7.0, course.getPassingGrade());
		assertEquals("Direito", course.getName());
		assertEquals(3, course.getStatus().getId());

	}
	
	@Test
	@DisplayName("Teste buscar curso por status")
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void findByStatusTest() {
		List<Course> lista = service.findByStatus(statusService.findById(3));
		assertEquals(2, lista.size());
	}

}
