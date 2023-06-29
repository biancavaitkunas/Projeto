package br.com.trier.ProjetoJovemDev.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.ActivityKind;

@Repository
public interface ActivityKindRepository extends JpaRepository<ActivityKind, Integer>{
	
	List <ActivityKind> findByNameStartingWithIgnoreCase (String name);

}
