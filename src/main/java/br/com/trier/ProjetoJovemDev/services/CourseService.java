package br.com.trier.ProjetoJovemDev.services;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.Status;

public interface CourseService {
	
	Course insert (Course course);
	List<Course> listAll();
	Course findById(Integer id);
	Course update(Course course);
	void delete(Integer id);
	List<Course> findByNameStartingWithIgnoreCase (String name);
	List<Course> findByStatus(Status status);

}
