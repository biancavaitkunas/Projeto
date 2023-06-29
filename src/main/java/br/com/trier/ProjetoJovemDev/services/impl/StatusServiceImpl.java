package br.com.trier.ProjetoJovemDev.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.repositories.StatusRespository;
import br.com.trier.ProjetoJovemDev.services.StatusService;
import br.com.trier.ProjetoJovemDev.services.exceptions.IntegrityViolation;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusRespository repository;
	
	private void findByDescription (Status obj) {
		Status status = repository.findByDescription(obj.getDescription());
		if (status != null && status.getId() != obj.getId()) {
			throw new IntegrityViolation("Status não pode ser nulo");
		}
	}

	@Override
	public Status insert(Status status) {
		findByDescription(status);
		return repository.save(status);
	}

	@Override
	public List<Status> listAll() {
		if(repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum status encontrado");
		}
		return repository.findAll();
	}

	@Override
	public Status findById(Integer id) {
		Optional<Status> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFound("Status %s não encontrado".formatted(id)));
	}

	@Override
	public Status update(Status status) {
		findByDescription(status);
		return repository.save(status);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(findById(id));
	}

	@Override
	public List<Status> findByDescriptionStartingWithIgnoreCase(String description) {
		if(repository.findByDescriptionStartingWithIgnoreCase(description).size() == 0) {
			throw new ObjectNotFound("Nenhum status encontrado");
		}
		return repository.findByDescriptionStartingWithIgnoreCase(description);
	}

}
