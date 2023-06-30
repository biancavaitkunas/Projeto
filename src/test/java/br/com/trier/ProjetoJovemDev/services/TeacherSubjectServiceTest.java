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
import br.com.trier.ProjetoJovemDev.domain.TeacherSubject;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class TeacherSubjectServiceTest extends BaseTests{
	
	@Autowired
	TeacherSubjectService service;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	SubjectService subjectService;
	
	@Test
	@DisplayName("Teste buscar professor disciplina por id")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void findByIdTest() {
		var teacherSubject = service.findById(1);
		assertThat(teacherSubject).isNotNull();
		assertEquals(1, teacherSubject.getId());
		assertEquals("Teacher 1", teacherSubject.getTeacher().getName());
	}
	
	@Test
	@DisplayName("Teste buscar professor disciplina por id inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Professor Disciplina 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os professores disciplina")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void listAllTest() {
		List<TeacherSubject> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar professor disciplina")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void alterTeacherTest() {
		var teacherSubject = new TeacherSubject(1, subjectService.findById(2), teacherService.findById(1));
		service.update(teacherSubject);
		teacherSubject = service.findById(1);
		assertThat(teacherSubject).isNotNull();
		assertEquals(1, teacherSubject.getId());
		assertEquals("História", teacherSubject.getSubject().getName());

	}
	
	@Test
	@DisplayName("Teste alterar professor disciplina inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Professor Disciplina 10 não encontrado", exception.getMessage());
	}
	
	
	@Test
	@DisplayName("Teste deletar professor disciplina")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<TeacherSubject> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar professor disciplina inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Professor Disciplina 10 não encontrado", exception.getMessage());
		List<TeacherSubject> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar professor disciplina por professor")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void findByTeacherTest() {
		List<TeacherSubject> lista = service.findByTeacher(teacherService.findById(1));
		assertEquals(1, lista.size());
		assertEquals("Teacher 1", lista.get(0).getTeacher().getName());
	}
	
	@Test
	@DisplayName("Teste buscar professor disciplina por disciplina")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/teachersubjects.sql"})
	void findBySubjectTest() {
		List<TeacherSubject> lista = service.findBySubject(subjectService.findById(3));
		assertEquals(1, lista.size());
		assertEquals("Matemática", lista.get(0).getTeacher().getName());
	}
	
	@Test
	@DisplayName("Teste inserir professor disciplina")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void insertTeacherTest() {
		TeacherSubject teacherSubject = new TeacherSubject(1, subjectService.findById(2), teacherService.findById(1));
		service.insert(teacherSubject);
		teacherSubject = service.findById(1);
		assertThat(teacherSubject).isNotNull();
		assertEquals(1, teacherSubject.getId());
		assertEquals("História", teacherSubject.getSubject().getName());

	}

}
