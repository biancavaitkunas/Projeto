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
import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class ActivityKindServiceTest extends BaseTests{
	
	@Autowired
	ActivityKindService service;
	
	@Test
	@DisplayName("Teste buscar tipo de atividade por id")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void findByIdTest() {
		var activityKind = service.findById(1);
		assertThat(activityKind).isNotNull();
		assertEquals(1, activityKind.getId());
		assertEquals("Prova", activityKind.getName());
	}
	
	@Test
	@DisplayName("Teste buscar tipo de atividade por id inexistente")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Tipo de atividade 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os tipos de atividade")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void listAllTest() {
		List<ActivityKind> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals("Prova", lista.get(2).getName());

	}
	
	@Test
	@DisplayName("Teste alterar tipo de atividade")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void alterTeacherTest() {
		var activityKind = new ActivityKind(1, "Altera");
		service.update(activityKind);
		activityKind = service.findById(1);
		assertThat(activityKind).isNotNull();
		assertEquals(1, activityKind.getId());
		assertEquals("Altera", activityKind.getName());

	}
	
	@Test
	@DisplayName("Teste alterar tipo de atividade inexistente")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Tipo de atividade 10 não encontrado", exception.getMessage());
		List<ActivityKind> lista = service.listAll();
		assertEquals(3, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar tipo de atividade")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<ActivityKind> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar tipo de atividade inexistente")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Tipo de atividade 10 não encontrado", exception.getMessage());
		List<ActivityKind> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar tipo de atividade por nome que inicia com")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void findUserByNameStartsWithTest() {
		List<ActivityKind> lista = service.findByNameStartingWithIgnoreCase("p");
		assertEquals(1, lista.size());
	}
	
	@Test
	@DisplayName("Teste buscar tipo de atividade por nome que inicia com inexistente")
	@Sql({"classpath:/resources/sqls/activitykinds.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findByNameStartingWithIgnoreCase("X"));
		assertEquals("Nenhum Tipo de Atividade encontrado", exception.getMessage());
	}
	
	@Test
	@DisplayName("Teste inserir tipo de atividade")
	void insertTeacherTest() {
		ActivityKind activityKind = new ActivityKind(1, "Insere");
		service.insert(activityKind);
		activityKind = service.findById(1);
		assertThat(activityKind).isNotNull();
		assertEquals(1, activityKind.getId());
		assertEquals("Insere", activityKind.getName());

	}

}
