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
import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.domain.UserCourse;
import br.com.trier.ProjetoJovemDev.services.UserCourseService;

@RestController
@RequestMapping(value = "/usuarioCurso")
public class UserCourseResource {
	
	@Autowired
	private UserCourseService service;
	
	@PostMapping
	public ResponseEntity<UserCourse> insert (@RequestBody UserCourse userCourse) {
		return service.insert(userCourse) != null ? ResponseEntity.ok(service.insert(userCourse)) : ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserCourse> findById (@PathVariable Integer id) {
		UserCourse userCourse = service.findById(id);
		return ResponseEntity.ok(userCourse);
	}
	
	@GetMapping
	public ResponseEntity<List<UserCourse>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/curso/{course}")
	public ResponseEntity<List<UserCourse>> findByCourse(@PathVariable Course course){
		return ResponseEntity.ok(service.findByCourse(course));
	}
	
	@GetMapping("/usuario/{user}")
	public ResponseEntity<List<UserCourse>> findByUser(@PathVariable User user){
		return ResponseEntity.ok(service.findByUser(user));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserCourse> update (@PathVariable Integer id, @RequestBody UserCourse userCourse) {
		userCourse.setId(id);
		userCourse = service.update(userCourse);
		return userCourse != null ? ResponseEntity.ok(userCourse) : ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
