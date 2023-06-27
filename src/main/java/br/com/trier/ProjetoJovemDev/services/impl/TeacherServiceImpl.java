package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Teacher;
import br.com.trier.ProjetoJovemDev.repositories.TeacherRepository;
import br.com.trier.ProjetoJovemDev.services.TeacherService;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherRepository repository;

	@Override
	public Teacher insert(Teacher teacher) {
		return repository.save(teacher);
	}

	@Override
	public List<Teacher> listAll() {
		return repository.findAll();
	}

	@Override
	public Teacher findById(Integer id) {
		Optional<Teacher> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFound("Usuário %s não encontrado".formatted(id)));
	}

	@Override
	public Teacher update(Teacher teacher) {
		return repository.save(teacher);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(findById(id));
	}

	@Override
	public List<Teacher> findByNameStartingWithIgnoreCase(String name) {
		if(repository.findByNameStartingWithIgnoreCase(name).isEmpty()) {
			throw new ObjectNotFound("Nenhum professor encontrado");			
		}
		return repository.findByNameStartingWithIgnoreCase(name);
	}

	
}
