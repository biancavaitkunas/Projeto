package br.com.trier.ProjetoJovemDev.services;

import java.time.ZonedDateTime;
import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.Activity;
import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.domain.Subject;

public interface ActivityService {
	
	Activity insert (Activity activity);
	List<Activity> listAll();
	Activity findById(Integer id);
	Activity update(Activity activity);
	void delete(Integer id);
	List<Activity> findByActivityKind(ActivityKind activityKind);
	List<Activity> findByDeliveryDate(ZonedDateTime deliveryDate);
	List<Activity> findByGrade(Double grade);
	List<Activity> findByDescription(String description);
	List<Activity> findBySubject(Subject subject);

}
