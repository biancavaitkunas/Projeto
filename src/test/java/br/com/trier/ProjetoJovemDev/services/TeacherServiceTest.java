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
import br.com.trier.ProjetoJovemDev.domain.Teacher;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class TeacherServiceTest extends BaseTests{
	
	@Autowired
	TeacherService service;
	
	@Test
	@DisplayName("Teste buscar professor por id")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void findByIdTest() {
		var u = service.findById(1);
		assertThat(u).isNotNull();
		assertEquals(1, u.getId());
		assertEquals("Teacher 1", u.getName());
	}
	
	@Test
	@DisplayName("Teste buscar professor por id inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Professor 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os professores")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void listAllTest() {
		List<Teacher> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar professor")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void alterTeacherTest() {
		var teacher = new Teacher(1, "Teacher 1");
		service.update(teacher);
		teacher = service.findById(1);
		assertThat(teacher).isNotNull();
		assertEquals(1, teacher.getId());
		assertEquals("Teacher 1", teacher.getName());

	}
	
	@Test
	@DisplayName("Teste alterar professor inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Professor 10 não encontrado", exception.getMessage());
		List<Teacher> lista = service.listAll();
		assertEquals(3, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar professor")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<Teacher> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar professor inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Professor 10 não encontrado", exception.getMessage());
		List<Teacher> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar professor por nome que inicia com")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void findUserByNameStartsWithTest() {
		List<Teacher> lista = service.findByNameStartingWithIgnoreCase("T");
		assertEquals(3, lista.size());
	}
	
	@Test
	@DisplayName("Teste buscar professor por nome que inicia com inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findByNameStartingWithIgnoreCase("X"));
		assertEquals("Nenhum professor encontrado", exception.getMessage());
	}
	
	@Test
	@DisplayName("Teste inserir professor")
	void insertTeacherTest() {
		Teacher teacher = new Teacher(1, "Teacher 1");
		service.insert(teacher);
		teacher = service.findById(1);
		assertThat(teacher).isNotNull();
		assertEquals(1, teacher.getId());
		assertEquals("Teacher 1", teacher.getName());

	}

}
