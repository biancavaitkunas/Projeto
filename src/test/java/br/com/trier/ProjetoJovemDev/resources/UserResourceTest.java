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
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import br.com.trier.ProjetoJovemDev.ProjetoJovemDevApplication;
import br.com.trier.ProjetoJovemDev.domain.dto.UserDTO;


@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.ANY)
@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/resources/sqls/users.sql")
@Sql(executionPhase=ExecutionPhase.AFTER_TEST_METHOD,scripts="classpath:/resources/sqls/limpa_tabelas.sql")
@SpringBootTest(classes = ProjetoJovemDevApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTest {
	
	@Autowired
	protected TestRestTemplate rest;
	
	private ResponseEntity<UserDTO> getUser(String url) {
		return rest.getForEntity(url, UserDTO.class);
	}
	
	@SuppressWarnings("unused")
	private ResponseEntity<List<UserDTO>> getUsers(String url) {
		return rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {
		});
		
	}
	
	@Test
	@DisplayName("Buscar por id")
	public void testGetOk() {
		ResponseEntity<UserDTO> response = getUser("/user/1");
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		UserDTO user = response.getBody();
		assertEquals("User 1", user.getName());
	}

	@Test
	@DisplayName("Buscar por id inexistente")
	public void testGetNotFound() {
		ResponseEntity<UserDTO> response = getUser("/usuario/3");
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

	}
	
	@Test
	@DisplayName("Cadastrar usuário")
	@Sql({"classpath:/resources/sqls/limpa_tabelas.sql"})
	public void testCreateUser() {
		UserDTO dto = new UserDTO(null, "Nome", "nome@gmail.com", "nome", "genero", "21/08/2004");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> requestEntity = new HttpEntity<>(dto, headers);
		ResponseEntity<UserDTO> responseEntity = rest.exchange("/usuario", HttpMethod.POST, requestEntity, UserDTO.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		UserDTO user = responseEntity.getBody();
		assertEquals("Nome", user.getName());
	}
	
	@Test
	@DisplayName("Alterar usuário")
	public void testAlterUser() {
		UserDTO dto = new UserDTO(null, "altera", "altera@gmail.com", "altera", "feminino", "21/08/2004");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> requestEntity = new HttpEntity<>(dto, headers);
		ResponseEntity<UserDTO> response = this.rest.exchange("/user/1", HttpMethod.PUT, requestEntity, UserDTO.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		UserDTO user = response.getBody();
		assertEquals(1, user.getId());
		assertEquals("altera", user.getName());
	}
	
	@Test
	@DisplayName("Deletar usuário")
	@Sql({"classpath:/resources/sqls/limpa_tabelas.sql"})
	public void testDeleteUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Void> responseEntity = this.rest.exchange("/usuario/1", HttpMethod.DELETE, requestEntity, Void.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

}