package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.CourseSubject;
import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.repositories.CourseRepository;
import br.com.trier.ProjetoJovemDev.repositories.CourseSubjectRepository;
import br.com.trier.ProjetoJovemDev.repositories.StatusRespository;
import br.com.trier.ProjetoJovemDev.repositories.SubjectRepository;
import br.com.trier.ProjetoJovemDev.services.CourseSubjectService;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class CourseSubjectServiceImpl implements CourseSubjectService{
	
	@Autowired
	CourseSubjectRepository repository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StatusRespository statusRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Override
	public CourseSubject insert(CourseSubject courseSubject) {
		return repository.save(courseSubject);
	}
	
	@Override
	public CourseSubject findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFound("CursoDisciplina %s não encontrado".formatted(id)));
	}

	@Override
	public List<CourseSubject> findBySubject(Subject subject) {
		return repository.findBySubject(subjectRepository.findById(subject.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhum CursoDisciplina para a disciplina %s".formatted(subject))));
	}

	@Override
	public List<CourseSubject> findByCourse(Course course) {
		return repository.findByCourse(courseRepository.findById(course.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhum CursoDisciplina para o curso %s".formatted(course))));
	}

	@Override
	public CourseSubject update(CourseSubject courseSubject) {
		findById(courseSubject.getId());
		return repository.save(courseSubject);
	}

	@Override
	public List<CourseSubject> listAll() {
		if (repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum CursoDisciplina encontrado");
		}
		return repository.findAll();
	}

	@Override
	public void delete(Integer id) {
		CourseSubject courseSubject = findById(id);
		repository.delete(courseSubject);
		
	}

	@Override
	public List<CourseSubject> findByStatus(Status status) {
		return repository.findByStatus(statusRepository.findById(status.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhum CursoDisciplina para o curso %s".formatted(status))));
		
	}

	/*@Override
	public List<CourseSubject> findByAprovedSubjects(Course course) {
		List<CourseSubject> list = new ArrayList<CourseSubject>();
		//for (CourseSubject courseSubject : list) {
			//if (courseSubject.getMedia() > )
		//}
		return null;
	}*/

}
