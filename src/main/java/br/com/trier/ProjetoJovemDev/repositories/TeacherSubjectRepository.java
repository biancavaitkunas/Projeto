package br.com.trier.ProjetoJovemDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.domain.Teacher;
import br.com.trier.ProjetoJovemDev.domain.TeacherSubject;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Integer>{
	
	List<TeacherSubject> findByTeacher(Teacher teacher);
	List<TeacherSubject> findBySubject(Subject subject);

}
