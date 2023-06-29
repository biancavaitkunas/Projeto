package br.com.trier.ProjetoJovemDev.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.trier.ProjetoJovemDev.domain.CourseSubject;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class CourseSubjetcServiceTest {
	
	@Autowired
	CourseSubjectService service;
	
	@Autowired
	CourseService courseservice;
	
	@Autowired
	SubjectService subjectService;
	
	@Test
	@DisplayName("Teste buscar curso disciplina por id")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findByIdTest() {
		var courseSubject = service.findById(1);
		assertThat(courseSubject).isNotNull();
		assertEquals(1, courseSubject.getId());
		assertEquals("Direito", courseSubject.getCourse().getName());
		assertEquals("Matemática", courseSubject.getSubject().getName());
	}
	
	@Test
	@DisplayName("Teste buscar curso por id inexistente")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Curso 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os cursos")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void listAllTest() {
		List<CourseSubject> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	/*@Test
	@DisplayName("Teste alterar curso")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void alterTeacherTest() {
		var courseSubject = new CourseSubject(1, "Direito",  7.0, statusService.findById(3));
		service.update(courseSubject);
		courseSubject = service.findById(1);
		assertThat(courseSubject).isNotNull();
		assertEquals(1, courseSubject.getId());
		assertEquals(7.0, courseSubject.getPassingGrade());
		assertEquals("Direito", courseSubject.getName());
		assertEquals(3, courseSubject.getStatus().getId());

	}
	
	@Test
	@DisplayName("Teste alterar curso inexistente")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Curso 10 não encontrado", exception.getMessage());
		List<CourseSubject> lista = service.listAll();
		assertEquals(3, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar curso")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<CourseSubject> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar curso inexistente")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Curso 10 não encontrado", exception.getMessage());
		List<CourseSubject> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar curso por nome que inicia com")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findUserByNameStartsWithTest() {
		List<CourseSubject> lista = service.findByNameStartingWithIgnoreCase("D");
		assertEquals(1, lista.size());
	}
	
	@Test
	@DisplayName("Teste buscar curso por nome que inicia com inexistente")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findByNameStartingWithIgnoreCase("X"));
		assertEquals("Nenhum curso encontrado", exception.getMessage());
	}*/
	
	@Test
	@DisplayName("Teste inserir curso")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void insertTeacherTest() {
		CourseSubject courseSubject = new CourseSubject(1, "Direito",  7.0, statusService.findById(3));
		service.insert(courseSubject);
		courseSubject = service.findById(1);
		assertThat(course).isNotNull();
		assertEquals(1, course.getId());
		assertEquals(7.0, course.getPassingGrade());
		assertEquals("Direito", course.getName());
		assertEquals(3, course.getStatus().getId());

	}
	
	@Test
	@DisplayName("Teste buscar curso por status")
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findByStatusTest() {
		List<CourseSubject> lista = service.findByStatus(statusService.findById(3));
		assertEquals(2, lista.size());
	}*/

}
