package br.com.trier.ProjetoJovemDev.services;

import java.util.List;
import java.util.Optional;

import br.com.trier.ProjetoJovemDev.domain.ActivityKind;

public interface ActivityKindService {
	
	ActivityKind insert (ActivityKind activityKind);
	List<ActivityKind> listAll();
	ActivityKind findById(Integer id);
	ActivityKind update(ActivityKind activityKind);
	void delete(Integer id);
	Optional<ActivityKind> findByNameStartingWithIgnoreCase (String name);

}
