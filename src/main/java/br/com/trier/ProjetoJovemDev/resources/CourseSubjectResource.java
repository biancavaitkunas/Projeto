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
import br.com.trier.ProjetoJovemDev.domain.CourseSubject;
import br.com.trier.ProjetoJovemDev.domain.Status;
import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.services.CourseSubjectService;

@RestController
@RequestMapping(value = "/courseSubject")
public class CourseSubjectResource {
	
	@Autowired
	private CourseSubjectService service;
	
	@PostMapping
	public ResponseEntity<CourseSubject> insert (@RequestBody CourseSubject courseSubject) {
		return service.insert(courseSubject) != null ? ResponseEntity.ok(service.insert(courseSubject)) : ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseSubject> findById (@PathVariable Integer id) {
		CourseSubject courseSubject = service.findById(id);
		return ResponseEntity.ok(courseSubject);
	}
	
	@GetMapping
	public ResponseEntity<List<CourseSubject>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/curso/{course}")
	public ResponseEntity<List<CourseSubject>> findByCourse(@PathVariable Course course){
		return ResponseEntity.ok(service.findByCourse(course));
	}
	
	@GetMapping("/disciplina/{subject}")
	public ResponseEntity<List<CourseSubject>> findBySubject(@PathVariable Subject subject){
		return ResponseEntity.ok(service.findBySubject(subject));
	}
	
	@GetMapping("/disciplina/{subject}")
	public ResponseEntity<List<CourseSubject>> findByStatus(@PathVariable Status status){
		return ResponseEntity.ok(service.findByStatus(status));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CourseSubject> update (@PathVariable Integer id, @RequestBody CourseSubject courseSubject) {
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
