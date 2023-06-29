package br.com.trier.ProjetoJovemDev.services;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Status;

public interface StatusService {
	
	Status insert (Status status);
	List<Status> listAll();
	Status findById(Integer id);
	Status update(Status status);
	void delete(Integer id);
	List <Status> findByDescriptionStartingWithIgnoreCase(String description);

}
