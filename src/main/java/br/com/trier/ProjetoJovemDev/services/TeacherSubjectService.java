package br.com.trier.ProjetoJovemDev.services;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.domain.Teacher;
import br.com.trier.ProjetoJovemDev.domain.TeacherSubject;

public interface TeacherSubjectService {
	
	TeacherSubject insert (TeacherSubject teacherSubject);
	List<TeacherSubject> listAll();
	TeacherSubject findById(Integer id);
	TeacherSubject update(TeacherSubject teacherSubject);
	void delete(Integer id);
	List<TeacherSubject> findByTeacher(Teacher teacher);
	List<TeacherSubject> findBySubject(Subject subject);

}
