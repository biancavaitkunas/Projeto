package br.com.trier.ProjetoJovemDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.domain.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer>{
	
	List<UserCourse> findByUser (User user);
	List<UserCourse> findByCourse (Course course);

}
