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
import br.com.trier.ProjetoJovemDev.domain.UserCourse;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
public class UserCourseServiceTest extends BaseTests{
	
	@Autowired
	UserCourseService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;
	
	@Test
	@DisplayName("Teste buscar usuário curso por id")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void findByIdTest() {
		var userCourse = service.findById(1);
		assertThat(userCourse).isNotNull();
		assertEquals(1, userCourse.getId());
		assertEquals("User 1", userCourse.getUser().getName());
	}
	
	@Test
	@DisplayName("Teste buscar usuário curso por id inexistente")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Usuário Curso 5 não encontrado", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os usuários curso")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void listAllTest() {
		List<UserCourse> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar usuário curso")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void alterTeacherTest() {
		var userCourse = new UserCourse(1, userService.findById(2), courseService.findById(1));
		service.update(userCourse);
		userCourse = service.findById(1);
		assertThat(userCourse).isNotNull();
		assertEquals(1, userCourse.getId());
		assertEquals("User 2", userCourse.getUser().getName());

	}
	
	@Test
	@DisplayName("Teste alterar usuário curso inexistente")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Usuário Curso 10 não encontrado", exception.getMessage());
	}
	
	
	@Test
	@DisplayName("Teste deletar usuário curso")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<UserCourse> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar usuário curso inexistente")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Usuário Curso 10 não encontrado", exception.getMessage());
		List<UserCourse> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar professor disciplina por professor")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void findByTeacherTest() {
		List<UserCourse> lista = service.findByUser(userService.findById(1));
		assertEquals(1, lista.size());
		assertEquals("User 1", lista.get(0).getUser().getName());
	}
	
	@Test
	@DisplayName("Teste buscar professor disciplina por disciplina")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	@Sql({"classpath:/resources/sqls/userCourse.sql"})
	void findBySubjectTest() {
		List<UserCourse> lista = service.findByCourse(courseService.findById(10));
		assertEquals(1, lista.size());
		assertEquals("Direito", lista.get(0).getCourse().getName());
	}
	
	@Test
	@DisplayName("Teste inserir professor disciplina")
	@Sql({"classpath:/resources/sqls/users.sql"})
	@Sql({"classpath:/resources/sqls/courses.sql"})
	void insertTeacherTest() {
		UserCourse userCourse = new UserCourse(1, userService.findById(2), courseService.findById(1));
		service.insert(userCourse);
		userCourse = service.findById(1);
		assertThat(userCourse).isNotNull();
		assertEquals(1, userCourse.getId());
		assertEquals("User 2", userCourse.getUser().getName());

	}

}
