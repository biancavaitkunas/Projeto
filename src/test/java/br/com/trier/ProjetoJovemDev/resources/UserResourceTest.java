package br.com.trier.ProjetoJovemDev.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import br.com.trier.ProjetoJovemDev.ProjetoJovemDevApplication;
import br.com.trier.ProjetoJovemDev.config.jwt.LoginDTO;
import br.com.trier.ProjetoJovemDev.domain.dto.UserDTO;


@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.ANY)
@SpringBootTest(classes = ProjetoJovemDevApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTest {
	
	@Autowired
	protected TestRestTemplate rest;
	
	private HttpHeaders getHeaders(String email, String password) {
		LoginDTO loginDTO = new LoginDTO(email, password);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTO, headers);
		ResponseEntity<String> responseEntity = rest.exchange("/auth/token", HttpMethod.POST, requestEntity, String.class);
		
		String token = responseEntity.getBody();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(token);
		return headers;
	}
	
	private ResponseEntity<UserDTO> getUser(String url) {
		return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(getHeaders("user1@gmail", "user1")), UserDTO.class);
	}
	
	@SuppressWarnings("unused")
	private ResponseEntity<List<UserDTO>> getUsers(String url) {
		return rest.exchange(
				url, HttpMethod.GET, 
				new HttpEntity<>(getHeaders("user1@gmail", "user1")), 
				new ParameterizedTypeReference<List<UserDTO>>() {}
			);
	}
	
	
	@Test
	@DisplayName("Buscar por id")
	@Sql({"classpath:/resources/sqls/cleanTables.sql"})
	@Sql({"classpath:/resources/sqls/users.sql"})
	public void testGetOk() {
		ResponseEntity<UserDTO> response = getUser("/usuario/1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		UserDTO user = response.getBody();
		assertEquals("User 1", user.getName());
	}

	
	@Test
	@DisplayName("Buscar por id inexistente")
	@Sql({"classpath:/resources/sqls/cleanTables.sql"})
	@Sql({"classpath:/resources/sqls/users.sql"})
	public void testGetNotFound() {
		ResponseEntity<UserDTO> response = getUser("/usuario/6");
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}
	
	@Test
	@DisplayName("Cadastrar usuário")
	@Sql({"classpath:/resources/sqls/cleanTables.sql"})
	@Sql({"classpath:/resources/sqls/users.sql"})
	public void testCreateUser() {
		UserDTO dto = new UserDTO(null, "Nome", "nome@gmail.com", "nome", "genero", "ADMIN, USER");
		HttpHeaders headers = getHeaders("user1@gmail", "user1");
		HttpEntity<UserDTO> requestEntity = new HttpEntity<>(dto, headers);
		ResponseEntity<UserDTO> responseEntity = rest.exchange("/usuario", HttpMethod.POST, requestEntity, UserDTO.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		UserDTO user = responseEntity.getBody();
		assertEquals("Nome", user.getName());
	}
	
	
	@Test
	@DisplayName("Alterar usuário")
	@Sql({"classpath:/resources/sqls/cleanTables.sql"})
	@Sql({"classpath:/resources/sqls/users.sql"})
	public void testAlterUser() {
		UserDTO dto = new UserDTO(null,"altera", "altera@gmail.com", "altera", "feminino", "ADMIN, USER");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> requestEntity = new HttpEntity<>(dto, headers);
		ResponseEntity<UserDTO> response = this.rest.exchange("/usuario/1", HttpMethod.PUT, requestEntity, UserDTO.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		UserDTO user = response.getBody();
		assertEquals(1, user.getId());
		assertEquals("altera", user.getName());
	}
	
	
	@Test
	@DisplayName("Buscar por nome")
	@Sql({"classpath:/resources/sqls/cleanTables.sql"})
	@Sql({"classpath:/resources/sqls/users.sql"})
	public void findByNameTest() {
		ResponseEntity<List<UserDTO>> response = getUsers("/usuario/nome/User 1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(2, response.getBody().size());
	}
	
	@Test
	@DisplayName("Excluir usuário")
	@Sql({"classpath:/resources/sqls/cleanTables.sql"})
	@Sql({"classpath:/resources/sqls/users.sql"})
	public void testDeleteUser() {
		HttpHeaders headers = getHeaders("user1@gmail", "user1");
		HttpEntity<Void> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Void> responseEntity = rest.exchange("/users/4", HttpMethod.DELETE, requestEntity, Void.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Excluir usuário inexistente")
	@Sql({"classpath:/resources/sqls/cleanTables.sql"})
	@Sql({"classpath:/resources/sqls/users.sql"})
	public void testDeleteNonExistUser() {
		HttpHeaders headers = getHeaders("user1@gmail", "user1");
		HttpEntity<Void> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Void> responseEntity = rest.exchange("/users/100", HttpMethod.DELETE, requestEntity,	Void.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
	}

}