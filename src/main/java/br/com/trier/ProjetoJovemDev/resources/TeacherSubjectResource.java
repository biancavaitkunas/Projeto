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

import br.com.trier.ProjetoJovemDev.domain.Subject;
import br.com.trier.ProjetoJovemDev.domain.Teacher;
import br.com.trier.ProjetoJovemDev.domain.TeacherSubject;
import br.com.trier.ProjetoJovemDev.services.TeacherSubjectService;

public class TeacherSubjectResource{
	
	@Autowired
	private TeacherSubjectService service;
	
	@PostMapping
	public ResponseEntity<TeacherSubject> insert (@RequestBody TeacherSubject teacherSubject) {
		return service.insert(teacherSubject) != null ? ResponseEntity.ok(service.insert(teacherSubject)) : ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TeacherSubject> findById (@PathVariable Integer id) {
		TeacherSubject teacherSubject = service.findById(id);
		return ResponseEntity.ok(teacherSubject);
	}
	
	@GetMapping
	public ResponseEntity<List<TeacherSubject>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/curso/{course}")
	public ResponseEntity<List<TeacherSubject>> findByTeacher(@PathVariable Teacher teacher){
		return ResponseEntity.ok(service.findByTeacher(teacher));
	}
	
	@GetMapping("/usuario/{user}")
	public ResponseEntity<List<TeacherSubject>> findBySubject(@PathVariable Subject subject){
		return ResponseEntity.ok(service.findBySubject(subject));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TeacherSubject> update (@PathVariable Integer id, @RequestBody TeacherSubject teacherSubject) {
		teacherSubject.setId(id);
		teacherSubject = service.update(teacherSubject);
		return teacherSubject != null ? ResponseEntity.ok(teacherSubject) : ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
