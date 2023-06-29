package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.repositories.ActivityKindRepository;
import br.com.trier.ProjetoJovemDev.services.ActivityKindService;
import br.com.trier.ProjetoJovemDev.services.exceptions.IntegrityViolation;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class ActivityKindServiceImpl implements ActivityKindService{
	
	@Autowired
	ActivityKindRepository repository;

	@Override
	public ActivityKind insert(ActivityKind activityKind) {
		return repository.save(activityKind);
	}

	@Override
	public List<ActivityKind> listAll() {
		if (repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum Tipo de Atividade encontrado");
		}
		return repository.findAll();
	}

	@Override
	public ActivityKind findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Tipo de atividade %s não encontrado".formatted(id)));
	}

	@Override
	public ActivityKind update(ActivityKind activityKind) {
		findById(activityKind.getId());
		return repository.save(activityKind);
	}

	@Override
	public void delete(Integer id) {
		ActivityKind activityKind = findById(id);
		repository.delete(activityKind);
		
	}

	@Override
	public List<ActivityKind> findByNameStartingWithIgnoreCase(String name) {
		if (repository.findByNameStartingWithIgnoreCase(name).size() == 0) {
			throw new ObjectNotFound("Nenhum Tipo de Atividade encontrado");
		}
		return repository.findByNameStartingWithIgnoreCase(name);
	}

}
