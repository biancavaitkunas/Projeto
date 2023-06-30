package br.com.trier.ProjetoJovemDev.services;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.CourseSubject;
import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.domain.Subject;

public interface CourseSubjectService {
	
	CourseSubject insert (CourseSubject courseSubject);
	List<CourseSubject> listAll();
	CourseSubject findById(Integer id);
	CourseSubject update(CourseSubject courseSubject);
	void delete(Integer id);
	List<CourseSubject> findBySubject(Subject subject);
	List<CourseSubject> findByCourse(Course course);
	List<CourseSubject> findByStatus(Status status);
	//List<CourseSubject> findByAprovedSubjects(Course course);

}
