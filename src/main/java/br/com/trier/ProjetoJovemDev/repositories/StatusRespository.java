package br.com.trier.ProjetoJovemDev.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Status;

@Repository
public interface StatusRespository extends JpaRepository<Status, Integer>{
	
	Status findByDescriptionStartingWithIgnoreCase(String description);
	Status findByDescription(String description);

}
