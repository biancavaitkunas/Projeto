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

import br.com.trier.ProjetoJovemDev.domain.Course;
import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.services.CourseService;
import br.com.trier.ProjetoJovemDev.services.StatusService;

@RestController
@RequestMapping(value = "/curso")
public class CourseResource {
	
	//TESTES POSTMAN OK
	
	@Autowired
	private CourseService service;
	
	@Autowired
	private StatusService statusService;
	
	@PostMapping
	public ResponseEntity<Course> insert (@RequestBody Course course) {
		return service.insert(course) != null ? ResponseEntity.ok(service.insert(course)) : ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> findById (@PathVariable Integer id) {
		Course course = service.findById(id);
		return ResponseEntity.ok(course);
	}
	
	@GetMapping
	public ResponseEntity<List<Course>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Course>> findByNameStartingWithIgnoreCase(@PathVariable String name){
		return ResponseEntity.ok(service.findByNameStartingWithIgnoreCase(name));
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Course>> findByStatus(@PathVariable Status status){
		return ResponseEntity.ok(service.findByStatus(statusService.findById(status.getId())));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> update (@PathVariable Integer id, @RequestBody Course course) {
		course.setId(id);
		course = service.update(course);
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
