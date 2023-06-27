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

import br.com.trier.ProjetoJovemDev.domain.Teacher;
import br.com.trier.ProjetoJovemDev.domain.dto.UserDTO;
import br.com.trier.ProjetoJovemDev.services.TeacherService;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherResource {
	
	@Autowired
	TeacherService service;
	
	@PostMapping
	public ResponseEntity<Teacher> insert (@RequestBody Teacher teacher) {
		return service.insert(teacher) != null ? ResponseEntity.ok(service.insert(teacher)) : 
			ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Teacher> findById (@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Teacher>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Teacher>> findByNameStartingWithIgnoreCase(@PathVariable String name){
		return ResponseEntity.ok(service.findByNameStartingWithIgnoreCase(name));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Teacher> update (@PathVariable Integer id, @RequestBody Teacher teacher) {
		teacher.setId(id);
		teacher = service.update(teacher);
		return teacher != null ? ResponseEntity.ok(teacher) : ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
