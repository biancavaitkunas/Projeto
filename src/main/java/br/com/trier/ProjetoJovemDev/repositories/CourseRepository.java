package br.com.trier.ProjetoJovemDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.Status;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	List<Course> findByNameStartingWith(String name);
	List<Course> findByName(String name);
	List<Course> findByStatus(Status status);

}
