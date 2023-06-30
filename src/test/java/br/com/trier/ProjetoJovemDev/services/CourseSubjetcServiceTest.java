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
	CourseService courseService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	SubjectService subjectService;
	
	@Test
	@DisplayName("Teste buscar curso disciplina por id")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courseSubject.sql"})
	void findByIdTest() {
		var courseSubject = service.findById(1);
		assertThat(courseSubject).isNotNull();
		assertEquals(1, courseSubject.getId());
		assertEquals("Direito", courseSubject.getCourse().getName());
		assertEquals("Matemática", courseSubject.getSubject().getName());
	}
	
	@Test
	@DisplayName("Teste buscar curso por id inexistente")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courseSubject.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Curso 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os cursos")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courseSubject.sql"})
	void listAllTest() {
		List<CourseSubject> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar curso")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courseSubject.sql"})
	void alterTeacherTest() {
		var courseSubject = new CourseSubject(1, courseService.findById(1), subjectService.findById(1), statusService.findById(3));
		service.update(courseSubject);
		courseSubject = service.findById(1);
		assertThat(courseSubject).isNotNull();
		assertEquals(1, courseSubject.getId());
		assertEquals(1, courseSubject.getCourse().getId());
		assertEquals(1, courseSubject.getStatus().getId());
		assertEquals(3, courseSubject.getStatus().getId());

	}
	
	@Test
	@DisplayName("Teste alterar curso inexistente")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courseSubject.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Curso Disciplina 10 não encontrado", exception.getMessage());
		List<CourseSubject> lista = service.listAll();
		assertEquals(3, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar curso")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courseSubject.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<CourseSubject> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar curso inexistente")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/courseSubject.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Curso 10 não encontrado", exception.getMessage());
		List<CourseSubject> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar curso disciplina por curso")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findUserByNameStartsWithTest() {
		List<CourseSubject> lista = service.findByCourse(courseService.findById(1));
		assertEquals(1, lista.size());
		assertEquals("Direito", lista.get(0).getCourse().getName());
	}
	
	@Test
	@DisplayName("Teste buscar curso disciplina por disciplina")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		List<CourseSubject> lista = service.findBySubject(subjectService.findById(1));
		assertEquals(1, lista.size());
		assertEquals("Matemática", lista.get(0).getSubject().getName());
	}
	
	@Test
	@DisplayName("Teste inserir curso disciplina")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void insertTeacherTest() {
		CourseSubject courseSubject = new CourseSubject(1, courseService.findById(1), subjectService.findById(1), statusService.findById(1));
		service.insert(courseSubject);
		courseSubject = service.findById(1);
		assertThat(courseSubject).isNotNull();
		assertEquals(1, courseSubject.getId());
		assertEquals(1, courseSubject.getCourse().getId());
		assertEquals(1, courseSubject.getStatus().getId());
		assertEquals(1, courseSubject.getStatus().getId());
	}
	
	@Test
	@DisplayName("Teste buscar curso por status")
	@Sql({"classpath:/resources/sqls/course.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/status.sql"})
	@Sql({"classpath:/resources/sqls/coursesubjects.sql"})
	void findByStatusTest() {
		List<CourseSubject> lista = service.findByStatus(statusService.findById(3));
		assertEquals(2, lista.size());
	}

}
