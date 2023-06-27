package br.com.trier.ProjetoJovemDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	
	List<Subject> findByName (String name);
	

}
