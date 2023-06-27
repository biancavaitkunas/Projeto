package br.com.trier.ProjetoJovemDev.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.trier.ProjetoJovemDev.BaseTests;
import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.utils.DateUtils;
import jakarta.transaction.Transactional;

@Transactional
public class UserServiceTest extends BaseTests{
	
	@Autowired
	UserService service;
	
	@Test
	@DisplayName("Teste buscar usuário por id")
	@Sql({"classpath:br/com/trier/ProjetoJovemDev/resources/sqls/users.sql"})
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

}
