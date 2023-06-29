package br.com.trier.ProjetoJovemDev.services.impl;

import java.time.ZonedDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Activity;
import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.repositories.ActivityKindRepository;
import br.com.trier.ProjetoJovemDev.repositories.ActivityRepository;
import br.com.trier.ProjetoJovemDev.repositories.SubjectRepository;
import br.com.trier.ProjetoJovemDev.services.ActivityService;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	ActivityRepository repository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	ActivityKindRepository activityKindRepository;

	@Override
	public Activity insert(Activity activity) {
		return repository.save(activity);
	}

	@Override
	public List<Activity> listAll() {
		if (repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum CursoDisciplina encontrado");
		}
		return repository.findAll();
	}

	@Override
	public Activity findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Atividade %s não encontrada".formatted(id)));

	}

	@Override
	public Activity update(Activity activity) {
		findById(activity.getId());
		return repository.save(activity);
	}

	@Override
	public void delete(Integer id) {
		Activity activity = findById(id);
		repository.delete(activity);
		
	}

	@Override
	public List<Activity> findByActivityKind(ActivityKind activityKind) {
		return repository.findByActivityKind(activityKindRepository.findById(activityKind.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhuma atividade para o tipo %s".formatted(activityKind))));

	}

	@Override
	public List<Activity> findByDeliveryDate(ZonedDateTime deliveryDate) {
		if (repository.findByDeliveryDate(deliveryDate).size() == 0) {
			throw new ObjectNotFound("Nenhuma atividade para a data %s".formatted(deliveryDate));
		}
		return repository.findByDeliveryDate(deliveryDate);
	}

	@Override
	public List<Activity> findByGrade(Double grade) {
		if (repository.findByGrade(grade).size() == 0) {
			throw new ObjectNotFound("Nenhuma atividade com a nota %s".formatted(grade));
		}
		return repository.findByGrade(grade);
	}

	@Override
	public List<Activity> findByDescription(String description) {
		if (repository.findByDescription(description).size() == 0) {
			throw new ObjectNotFound("Nenhuma atividade %s encontrada".formatted(description));
		}
		return repository.findByDescription(description);
	}

	@Override
	public List<Activity> findBySubject(Subject subject) {
		return repository.findBySubject(subjectRepository.findById(subject.getId()).orElseThrow(() -> 
		new ObjectNotFound("Nenhuma atividade para a disciplina %s".formatted(subject))));
	}

}
