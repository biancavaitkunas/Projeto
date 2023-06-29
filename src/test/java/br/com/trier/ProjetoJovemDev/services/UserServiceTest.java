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
import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import br.com.trier.ProjetoJovemDev.utils.DateUtils;
import jakarta.transaction.Transactional;

@Transactional
public class UserServiceTest extends BaseTests{
	
	@Autowired
	UserService service;
	
	@Test
	@DisplayName("Teste buscar usuário por id")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void findByIdTest() {
		var u = service.findById(1);
		assertThat(u).isNotNull();
		assertEquals(1, u.getId());
		assertEquals("User 1", u.getName());
		assertEquals("user1@gmail", u.getEmail());
		assertEquals("user1", u.getPassword());
	}
	
	@Test
	@DisplayName("Teste inserir usuário")
	void insertUserTest() {
		User u = new User(1, "Bianca", "bianca@gmail.com", "bianca123", "feminino", DateUtils.strToZonedDateTime("21/08/2004"));
		service.insert(u);
		u = service.findById(1);
		assertThat(u).isNotNull();
		assertEquals(1, u.getId());
		assertEquals("Bianca", u.getName());
		assertEquals("bianca@gmail.com", u.getEmail());
		assertEquals("bianca123", u.getPassword());

	}
	
	@Test
	@DisplayName("Teste buscar usuário por id inexistente")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(10));
		assertEquals("Usuário 10 não encontrado", exception.getMessage());
	}
	
	
	@Test
	@DisplayName("Listar todos os usuários")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void listAllTest() {
		List<User> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(1, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar usuários")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void alterUserTest() {
		var user = new User(1, "altera", "altera@gmail.com", "altera", "feminino", DateUtils.strToZonedDateTime("21/08/2004"));
		service.update(user);
		user = service.findById(1);
		assertThat(user).isNotNull();
		assertEquals(1, user.getId());
		assertEquals("altera", user.getName());
		assertEquals("altera@gmail.com", user.getEmail());
		assertEquals("feminino", user.getGender());
		assertEquals("altera", user.getPassword());

	}
	
	@Test
	@DisplayName("Teste alterar usuário inexistente")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void alterUserNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Usuário 10 não encontrado", exception.getMessage());
		List<User> lista = service.listAll();
		assertEquals(1, lista.size());
	}
	
	
	@Test
	@DisplayName("Teste deletar usuário")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void deleteUserTest() {
		service.delete(1);
		List<User> lista = service.listAll();
		assertEquals(0, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar usuário inexistente")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Usuário 10 não encontrado", exception.getMessage());
		List<User> lista = service.listAll();
		assertEquals(1, lista.size());

	}
	
	@Test
	@DisplayName("Teste usuário por nome que inicia com")
	@Sql({"classpath:/resources/sqls/users.sql"})
	void findUserByNameStartsWithTest() {
		List<User> lista = service.findByNameStartingWithIgnoreCase("U");
		assertEquals(1, lista.size());
	}
	
	@Test
	@DisplayName("Teste buscar usuário por nome que inicia com inexistente")
	@Sql({"classpath:/resources/sqls/teachers.sql"})
	void findUserByNameStartsWithNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findByNameStartingWithIgnoreCase("X"));
		assertEquals("Nenhum usuário encontrado", exception.getMessage());
	}

}
