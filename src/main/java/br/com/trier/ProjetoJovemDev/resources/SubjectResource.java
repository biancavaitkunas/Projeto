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

import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.services.SubjectService;

@RestController
@RequestMapping(value = "/subject")
public class SubjectResource {
	
	//TESTES POSTMAN OK
	
	@Autowired
	private SubjectService service;
	
	@PostMapping
	public ResponseEntity<Subject> insert (@RequestBody Subject subject) {
		return service.insert(subject) != null ? ResponseEntity.ok(service.insert(subject)) : ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Subject> findById (@PathVariable Integer id) {
		Subject subject = service.findById(id);
		return ResponseEntity.ok(subject);
	}
	
	@GetMapping
	public ResponseEntity<List<Subject>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Subject>> findByNameStartingWithIgnoreCase(@PathVariable String name){
		return ResponseEntity.ok(service.findByNameStartingWithIgnoreCase(name));
	}
	
	@GetMapping("/media/{id}")
	public ResponseEntity<List<Subject>> calculateMedia(@PathVariable Integer id){
		return ResponseEntity.ok(service.calculateMedia(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Subject> update (@PathVariable Integer id, @RequestBody Subject subject) {
		subject.setId(id);
		subject = service.update(subject);
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
