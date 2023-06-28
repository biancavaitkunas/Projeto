package br.com.trier.ProjetoJovemDev.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.ProjetoJovemDev.domain.Activity;
import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.domain.dto.ActivityDTO;
import br.com.trier.ProjetoJovemDev.services.ActivityKindService;
import br.com.trier.ProjetoJovemDev.services.ActivityService;
import br.com.trier.ProjetoJovemDev.services.SubjectService;

@RestController
@RequestMapping(value = "/atividade")
public class ActivityResource {
	
	@Autowired
	private ActivityService service;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ActivityKindService activityKindService;
	
	@PostMapping
	public ResponseEntity<ActivityDTO> insert (@RequestBody ActivityDTO activity) {
		return service.insert(new Activity(activity)) != null ? ResponseEntity.ok(service.insert(new Activity(activity)).ToDto()) : ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ActivityDTO> findById (@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id).ToDto());
	}
	
	@GetMapping
	public ResponseEntity<List<ActivityDTO>> listAll(){
		return ResponseEntity.ok(service.listAll().stream().map((user) -> user.ToDto()).toList());
	}
	
	@GetMapping("/tipoAtividade/{activityKind}")
	public ResponseEntity<List<ActivityDTO>> findByActivityKind(@PathVariable Integer activityKindId){
		return ResponseEntity.ok(service.findByActivityKind(activityKindService.findById(activityKindId)).stream().map((piloto) -> piloto.toDTO().toList());
	}
	
	@GetMapping("/disciplina/{subject}")
	public ResponseEntity<List<ActivityDTO>> findBySubject(@PathVariable Subject subject){
		return ResponseEntity.ok(service.findBySubject(subject));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ActivityDTO> update (@PathVariable Integer id, @RequestBody ActivityDTO activityDTO) {
		activity.setId(id);
		activity = service.update(activity);
		return activity != null ? ResponseEntity.ok(activity) : ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
