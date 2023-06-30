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
import br.com.trier.ProjetoJovemDev.domain.Activity;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;
import br.com.trier.ProjetoJovemDev.utils.DateUtils;
import jakarta.transaction.Transactional;

@Transactional
public class ActivityServiceTest extends BaseTests{
	
	@Autowired
	ActivityService service;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	ActivityKindService activityKindService;
	
	@Test
	@DisplayName("Teste buscar atividade por id")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void findByIdTest() {
		var activity = service.findById(1);
		assertThat(activity).isNotNull();
		assertEquals(1, activity.getId());
		assertEquals("Prova de matemática", activity.getDescription());
		assertEquals("30/06/2023", activity.getDeliveryDate());
		assertEquals(8.0, activity.getGrade());
	}
	
	@Test
	@DisplayName("Teste atividade curso por id inexistente")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void findByIdNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.findById(5));
		assertEquals("Atividade 5 não encontrada", exception.getMessage());
		
	}
	
	@Test
	@DisplayName("Listar todos os usuários curso")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void listAllTest() {
		List<Activity> lista = service.listAll();
		assertThat(lista).isNotNull();
		assertEquals(3, lista.size());
		assertEquals(1, lista.get(0).getId());

	}
	
	@Test
	@DisplayName("Teste alterar atividade")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void alterTest() {
		var activity = new Activity(1, "Prova de matemática", DateUtils.strToZonedDateTime("30/06/2023"), subjectService.findById(1),  
				8.0, activityKindService.findById(1));
		service.update(activity);
		activity = service.findById(1);
		assertThat(activity).isNotNull();
		assertEquals(1, activity.getId());
		assertEquals("Matemática", activity.getSubject().getName());

	}
	
	@Test
	@DisplayName("Teste alterar atividade inexistente")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void alterTeacherNotFoundTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Atividade 10 não encontrada", exception.getMessage());
	}
	
	
	@Test
	@DisplayName("Teste deletar atividade")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void deleteTeacherTest() {
		service.delete(1);
		List<Activity> lista = service.listAll();
		assertEquals(2, lista.size());

	}
	
	@Test
	@DisplayName("Teste deletar atividade inexistente")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void deleteNonExistingUserTest() {
		var exception = assertThrows(ObjectNotFound.class, () -> service.delete(10));
		assertEquals("Atividade 10 não encontrada", exception.getMessage());
		List<Activity> lista = service.listAll();
		assertEquals(3, lista.size());

	}
	
	@Test
	@DisplayName("Teste buscar atividade por disciplina")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void findByTeacherTest() {
		List<Activity> lista = service.findBySubject(subjectService.findById(1));
		assertEquals(1, lista.size());
		assertEquals("Matemática", lista.get(0).getSubject().getName());
	}
	
	@Test
	@DisplayName("Teste buscar atividade por tipo")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	@Sql({"classpath:/resources/sqls/activity.sql"})
	void findBySubjectTest() {
		List<Activity> lista = service.findByActivityKind(activityKindService.findById(1));
		assertEquals(1, lista.size());
		assertEquals("Prova", lista.get(0).getActivityKind().getName());
	}
	
	@Test
	@DisplayName("Teste inserir atividade")
	@Sql({"classpath:/resources/sqls/subjects.sql"})
	@Sql({"classpath:/resources/sqls/activityKind.sql"})
	void insertTeacherTest() {
		Activity activity = new Activity(1, "Prova de matemática", DateUtils.strToZonedDateTime("30/06/2023"), subjectService.findById(1),  
				8.0, activityKindService.findById(1));
		service.insert(activity);
		activity = service.findById(1);
		assertThat(activity).isNotNull();
		assertEquals(1, activity.getId());
		assertEquals("Matemática", activity.getSubject().getName());

	}

}
