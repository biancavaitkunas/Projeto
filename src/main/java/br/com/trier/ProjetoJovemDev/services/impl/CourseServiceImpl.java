package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.repositories.CourseRepository;
import br.com.trier.ProjetoJovemDev.services.CourseService;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseRepository repository;

	@Override
	public Course insert(Course course) {
		return repository.save(course);
	}

	@Override
	public List<Course> listAll() {
		if (repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum curso encontrado");
		}
		return repository.findAll();
	}

	@Override
	public Course findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Curso %s não encontrado".formatted(id)));
	}

	@Override
	public Course update(Course course) {
		findById(course.getId());
		return repository.save(course);
	}

	@Override
	public void delete(Integer id) {
		Course course = findById(id);
		repository.delete(course);
	}

	@Override
	public List<Course> findByNameStartingWithIgnoreCase(String name) {
		if(repository.findByNameStartingWithIgnoreCase(name).size() == 0) {
			throw new ObjectNotFound("Nenhum curso encontrado");
		}
		return repository.findByNameStartingWithIgnoreCase(name);
	}

	@Override
	public List<Course> findByStatus(Status status) {
		if (repository.findByStatus(status).size() == 0) {
			throw new ObjectNotFound("Nenhum curso com o status %s".formatted(status));
		}
		return repository.findByStatus(status);
	}

}
