package br.com.trier.ProjetoJovemDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	
	List<Teacher> findByName (String name);

}
