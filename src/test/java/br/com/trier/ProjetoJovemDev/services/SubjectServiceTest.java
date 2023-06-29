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
import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class SubjectServiceTest extends BaseTests{
	
	@Autowired
	SubjectService service;
	
	@Test
	@DisplayName("Teste buscar disciplina por id")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void findByIdTest() {
		var subject = service.findById(1);
		assertThat(subject).isNotNull();
		assertEquals(1, subject.getId());
		assertEquals("Matemática", subject.getName());
	}
	
	@Test
	@DisplayName("Teste buscar disciplina por id inexistente")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Disciplina 5 não encontrada", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos as disciplinas")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void listAllTest() {
		List<Subject> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar disciplina")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void alterTeacherTest() {
		var subject = new Subject(1, "Altera");
		service.update(subject);
		subject = service.findById(1);
		assertThat(subject).isNotNull();
		assertEquals(1, subject.getId());
		assertEquals("Altera", subject.getName());

	}
	
	@Test
	@DisplayName("Teste alterar disciplina inexistente")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Disciplina 10 não encontrada", exception.getMessage());
		List<Subject> lista = service.listAll();
		assertEquals(3, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar disciplina")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<Subject> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar disciplina inexistente")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Disciplina 10 não encontrada", exception.getMessage());
		List<Subject> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar disciplina por nome que inicia com")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void findUserByNameStartsWithTest() {
		List<Subject> subject = service.findByNameStartingWithIgnoreCase("M");
		assertEquals(1, subject.size());
	}
	
	@Test
	@DisplayName("Teste buscar disciplina por nome que inicia com inexistente")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findByNameStartingWithIgnoreCase("X"));
		assertEquals("Nenhuma disciplina encontrada", exception.getMessage());
	}
	
	@Test
	@DisplayName("Teste inserir disciplina")
	void insertTeacherTest() {
		Subject subject = new Subject(1, "Insere");
		service.insert(subject);
		subject = service.findById(1);
		assertThat(subject).isNotNull();
		assertEquals(1, subject.getId());
		assertEquals("Insere", subject.getName());

	}

}
