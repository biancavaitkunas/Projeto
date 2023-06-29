package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.repositories.SubjectRepository;
import br.com.trier.ProjetoJovemDev.services.SubjectService;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	SubjectRepository repository;
	

	@Override
	public Subject insert(Subject subject) {
		return repository.save(subject);
	}

	@Override
	public List<Subject> listAll() {
		return repository.findAll();
	}

	@Override
	public Subject findById(Integer id) {
		return repository.findById(id).orElseThrow(()-> new ObjectNotFound("Disciplina %s não encontrada".formatted(id)));
	}

	@Override
	public Subject update(Subject subject) {
		findById(subject.getId());
		return repository.save(subject);
	}

	@Override
	public void delete(Integer id) {
		Subject subject = findById(id);
		repository.delete(subject);
	}

	@Override
	public List<Subject> findByNameStartingWithIgnoreCase(String name) {
		if(repository.findByNameStartingWithIgnoreCase(name).size() == 0) {
			throw new ObjectNotFound("Nenhuma disciplina encontrada");
		}
		return repository.findByNameStartingWithIgnoreCase(name);
	}


	/*public List<Object[]> calculateMediaGradesBySubject() {
		List<Object[]> result = repository.calculateMediaGradesBySubject();
		return repository.calculateMediaGradesBySubject();

	}*/

	@Override
	public List<Subject> calculateMedia(Integer id) {
		return repository.calculateMediaGradesBySubject();
	}
	

}
