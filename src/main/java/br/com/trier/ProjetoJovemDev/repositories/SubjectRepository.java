package br.com.trier.ProjetoJovemDev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	
	List<Subject> findByNameStartingWithIgnoreCase (String name);
	
	@Query("SELECT s, AVG(a.grade) FROM Subject s JOIN s.activities a GROUP BY s")
	List<Subject> calculateMediaGradesBySubject();
	//Double calculateMedia (List<Activity>activity);

}


    

