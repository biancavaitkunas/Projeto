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
	
	private void validateStatus (Status obj) {
		if (repository.findByDescription(obj.getDescription()) != null) {
			throw new IntegrityViolation("Status não pode ser nulo");
		}
		if(repository.findByDescription(obj.getDescription()).getId() != obj.getId()) {
			throw new IntegrityViolation("Status %s já cadastrado".formatted(obj.getDescription()));
		}
	}

	@Override
	public Status insert(Status status) {
		validateStatus(status);
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
		validateStatus(status);
		return repository.save(status);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(findById(id));
	}

	@Override
	public Status findByDescriptionStartingWithIgnoreCase(String description) {
		if(repository.findByDescriptionStartingWithIgnoreCase(description) != null) {
			throw new ObjectNotFound("Nenhum status encontrado");
		}
		return repository.findByDescription(description);
		//FIXME
	}

}
