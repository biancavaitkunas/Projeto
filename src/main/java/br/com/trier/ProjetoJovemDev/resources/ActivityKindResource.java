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

import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.services.ActivityKindService;

@RestController
@RequestMapping(value = "/tipoAtividade")
public class ActivityKindResource {
	
	//TESTES POSTMAN OK
	
	@Autowired
	private ActivityKindService service;
	
	@PostMapping
	public ResponseEntity<ActivityKind> insert (@RequestBody ActivityKind activityKind) {
		return service.insert(activityKind) != null ? ResponseEntity.ok(service.insert(activityKind)) : ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ActivityKind> findById (@PathVariable Integer id) {
		ActivityKind activityKind = service.findById(id);
		return ResponseEntity.ok(activityKind);
	}
	
	@GetMapping
	public ResponseEntity<List<ActivityKind>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/nome/{name}")
	public ResponseEntity<List<ActivityKind>> findByNameStartingWithIgnoreCase(@PathVariable String name){
		return ResponseEntity.ok(service.findByNameStartingWithIgnoreCase(name));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ActivityKind> update (@PathVariable Integer id, @RequestBody ActivityKind courseSubject) {
		courseSubject.setId(id);
		courseSubject = service.update(courseSubject);
		return courseSubject != null ? ResponseEntity.ok(courseSubject) : ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
