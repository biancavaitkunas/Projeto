package br.com.trier.ProjetoJovemDev.services;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Subject;

public interface SubjectService {
	
	Subject insert (Subject subject);
	List<Subject> listAll();
	Subject findById(Integer id);
	Subject update(Subject subject);
	void delete(Integer id);
	List<Subject> findByNameStartingWithIgnoreCase (String name);
	//Double findAvgGradeById(Integer id);

}
