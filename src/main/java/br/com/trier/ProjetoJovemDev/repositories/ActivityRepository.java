package br.com.trier.ProjetoJovemDev.repositories;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.Activity;
import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.domain.Subject;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>{
	
	List<Activity> findByActivityKind(ActivityKind activityKind);
	List<Activity> findByDeliveryDate(ZonedDateTime deliveryDate);
	List<Activity> findByGrade(Double grade);
	List<Activity> findByDescription(String description);
	List<Activity> findBySubject(Subject subject);

}
