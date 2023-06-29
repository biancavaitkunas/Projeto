package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.domain.Teacher;
import br.com.trier.ProjetoJovemDev.domain.TeacherSubject;
import br.com.trier.ProjetoJovemDev.repositories.SubjectRepository;
import br.com.trier.ProjetoJovemDev.repositories.TeacherRepository;
import br.com.trier.ProjetoJovemDev.repositories.TeacherSubjectRepository;
import br.com.trier.ProjetoJovemDev.services.TeacherSubjectService;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	
	@Autowired
	TeacherSubjectRepository repository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	SubjectRepository subjectRepository;

	@Override
	public TeacherSubject insert(TeacherSubject teacherSubject) {
		return repository.save(teacherSubject);
	}

	@Override
	public List<TeacherSubject> listAll() {
		if (repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum Professor Disciplina encontrado");
		}
		return repository.findAll();
	}

	@Override
	public TeacherSubject findById(Integer id) {
		Optional<TeacherSubject> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFound("Professor Disciplina %s não encontrado".formatted(id)));
	}

	@Override
	public TeacherSubject update(TeacherSubject teacherSubject) {
		findById(teacherSubject.getId());
		return repository.save(teacherSubject);
	}

	@Override
	public void delete(Integer id) {
		TeacherSubject teacherSubject = findById(id);
		repository.delete(teacherSubject);
		
	}

	@Override
	public List<TeacherSubject> findByTeacher(Teacher teacher) {
		return repository.findByTeacher(teacherRepository.findById(teacher.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhum professor para o Professor Disciplina %s".formatted(teacher))));
	}

	@Override
	public List<TeacherSubject> findBySubject(Subject subject) {
		return repository.findBySubject(subjectRepository.findById(subject.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhuma disciplina para o Professor Disciplina %s".formatted(subject))));
	}

}
