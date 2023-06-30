package br.com.trier.ProjetoJovemDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.CourseSubject;
import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.domain.Subject;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Integer>{
	
	//List<CourseSubject> findByDissaprovedSubjects(Course course);
	//List<CourseSubject> findByAprovedSubjects(Course course);
	List<CourseSubject> findBySubject(Subject subject);
	List<CourseSubject> findByCourse(Course course);
	List<CourseSubject> findByStatus(Status status);

}
