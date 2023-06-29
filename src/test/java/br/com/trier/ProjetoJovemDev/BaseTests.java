package br.com.trier.ProjetoJovemDev;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import br.com.trier.ProjetoJovemDev.services.ActivityKindService;
import br.com.trier.ProjetoJovemDev.services.ActivityService;
import br.com.trier.ProjetoJovemDev.services.CourseService;
import br.com.trier.ProjetoJovemDev.services.CourseSubjectService;
import br.com.trier.ProjetoJovemDev.services.StatusService;
import br.com.trier.ProjetoJovemDev.services.SubjectService;
import br.com.trier.ProjetoJovemDev.services.TeacherService;
import br.com.trier.ProjetoJovemDev.services.TeacherSubjectService;
import br.com.trier.ProjetoJovemDev.services.UserCourseService;
import br.com.trier.ProjetoJovemDev.services.UserService;
import br.com.trier.ProjetoJovemDev.services.impl.ActivityKindServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.ActivityServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.CourseServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.CourseSubjectServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.StatusServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.SubjectServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.TeacherServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.TeacherSubjectServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.UserCourseServiceImpl;
import br.com.trier.ProjetoJovemDev.services.impl.UserServiceImpl;

@TestConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class BaseTests {
	
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public TeacherService teacherService() {
		return new TeacherServiceImpl();
	}
	
	@Bean
	public StatusService statusService() {
		return new StatusServiceImpl();
	}
	
	@Bean
	public SubjectService subjectService() {
		return new SubjectServiceImpl();
	}
	
	@Bean
	public CourseService courseService() {
		return new CourseServiceImpl();
	}
	
	@Bean
	public ActivityService activityService() {
		return new ActivityServiceImpl();
	}
	
	@Bean
	public ActivityKindService activityKindService() {
		return new ActivityKindServiceImpl();
	}
	
	@Bean
	public CourseSubjectService courseSubjectService() {
		return new CourseSubjectServiceImpl();
	}
	
	@Bean
	public TeacherSubjectService teacherSubjectService() {
		return new TeacherSubjectServiceImpl();
	}
	
	@Bean
	public UserCourseService userCourseService() {
		return new UserCourseServiceImpl();
	}

}
