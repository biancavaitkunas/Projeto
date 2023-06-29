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
import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class StatusServiceTest extends BaseTests{
	
	@Autowired
	StatusService service;
	
	@Test
	@DisplayName("Teste buscar status por id")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void findByIdTest() {
		var u = service.findById(1);
		assertThat(u).isNotNull();
		assertEquals(1, u.getId());
		assertEquals("Aprovado", u.getDescription());
	}
	
	@Test
	@DisplayName("Teste buscar status por id inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Status 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os status")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void listAllTest() {
		List<Status> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(4, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar status")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void alterTeacherTest() {
		var status = new Status(1, "Aprovado");
		service.update(status);
		status = service.findById(1);
		assertThat(status).isNotNull();
		assertEquals(1, status.getId());
		assertEquals("Aprovado", status.getDescription());

	}
	
	@Test
	@DisplayName("Teste alterar status inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Status 10 não encontrado", exception.getMessage());
		List<Status> lista = service.listAll();
		assertEquals(4, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar status")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<Status> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar status inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Status 10 não encontrado", exception.getMessage());
		List<Status> lista = service.listAll();
		assertEquals(4, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar status por nome que inicia com")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void findUserByNameStartsWithTest() {
		List<Status> status = service.findByDescriptionStartingWithIgnoreCase("A");
		assertEquals(1, status.size());
	}
	
	@Test
	@DisplayName("Teste buscar status por nome que inicia com inexistente")
	@Sql({"classpath:/resources/sqls/status.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findByDescriptionStartingWithIgnoreCase("X"));
		assertEquals("Nenhum status encontrado", exception.getMessage());
	}
	
	@Test
	@DisplayName("Teste inserir status")
	void insertTeacherTest() {
		Status status = new Status(1, "Aprovado");
		service.insert(status);
		status = service.findById(1);
		assertThat(status).isNotNull();
		assertEquals(1, status.getId());
		assertEquals("Aprovado", status.getDescription());

	}

}
