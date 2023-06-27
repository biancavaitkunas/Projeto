package br.com.trier.ProjetoJovemDev.services;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Teacher;

public interface TeacherService {
	
	Teacher insert (Teacher teacher);
	List<Teacher> listAll();
	Teacher findById(Integer id);
	Teacher update(Teacher teacher);
	void delete(Integer id);
	List<Teacher> findByNameStartingWithIgnoreCase(String name);

}
