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

import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.services.StatusService;

@RestController
@RequestMapping(value = "/status")
public class StatusResource {
	
	@Autowired
	StatusService service;
	
	@PostMapping
	public ResponseEntity<Status> insert (@RequestBody Status status) {
		return service.insert(status) != null ? ResponseEntity.ok(service.insert(status)) : 
			ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Status> findById (@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Status>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Status> findByDescriptionStartingWithIgnoreCase(@PathVariable String description){
		return ResponseEntity.ok(service.findByDescriptionStartingWithIgnoreCase(description));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Status> update (@PathVariable Integer id, @RequestBody Status status) {
		status.setId(id);
		status = service.update(status);
		return status != null ? ResponseEntity.ok(status) : ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}


}
