package br.com.trier.ProjetoJovemDev.services;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.domain.UserCourse;

public interface UserCourseService {
	
	UserCourse insert (UserCourse userCourse);
	List<UserCourse> listAll();
	UserCourse findById(Integer id);
	UserCourse update(UserCourse userCourse);
	void delete(Integer id);
	List<UserCourse> findByUser (User user);
	List<UserCourse> findByCourse (Course course);

}
