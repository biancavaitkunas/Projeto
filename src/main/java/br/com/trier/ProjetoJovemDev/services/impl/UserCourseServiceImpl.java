package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.domain.UserCourse;
import br.com.trier.ProjetoJovemDev.repositories.CourseRepository;
import br.com.trier.ProjetoJovemDev.repositories.UserCourseRepository;
import br.com.trier.ProjetoJovemDev.repositories.UserRepository;
import br.com.trier.ProjetoJovemDev.services.UserCourseService;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class UserCourseServiceImpl implements UserCourseService{
	
	@Autowired
	UserCourseRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@Override
	public UserCourse insert(UserCourse userCourse) {
		return repository.save(userCourse);
	}

	@Override
	public List<UserCourse> listAll() {
		if (repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum Usuário Curso encontrado");
		}
		return repository.findAll();
	}

	@Override
	public UserCourse findById(Integer id) {
		Optional<UserCourse> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFound("Usuário Curso %s não encontrado".formatted(id)));
	}

	@Override
	public UserCourse update(UserCourse userCourse) {
		findById(userCourse.getId());
		return repository.save(userCourse);
	}

	@Override
	public void delete(Integer id) {
		UserCourse userCourse = findById(id);
		repository.delete(userCourse);
		
	}

	@Override
	public List<UserCourse> findByUser(User user) {
		return repository.findByUser(userRepository.findById(user.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhum usuário para o Usuário Curso %s".formatted(user))));
	}

	@Override
	public List<UserCourse> findByCourse(Course course) {
		return repository.findByCourse(courseRepository.findById(course.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhum curso para o Usuário Curso %s".formatted(course))));
	}

}
