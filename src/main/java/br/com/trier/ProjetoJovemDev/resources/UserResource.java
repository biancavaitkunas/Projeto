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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.domain.dto.UserDTO;
import br.com.trier.ProjetoJovemDev.services.UserService;
import br.com.trier.ProjetoJovemDev.utils.DateUtils;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<UserDTO> insert (@RequestBody UserDTO userDTO) {
		//User newUser = service.insert(new User(userDTO));
		return service.insert(new User(userDTO)) != null ? ResponseEntity.ok(service.insert(new User(userDTO)).ToDto()) : 
			ResponseEntity.badRequest().build();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById (@PathVariable Integer id) {
		User user = service.findById(id);
		return ResponseEntity.ok(user.ToDto());
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> listAll(){
		return ResponseEntity.ok(service.listAll().stream().map((user) -> user.ToDto()).toList());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<UserDTO>> findByNameStartingWithIgnoreCase(@PathVariable String name){
		return ResponseEntity.ok(service.findByNameStartingWithIgnoreCase(name).stream().map((user) -> user.ToDto()).toList());
	}
	
	@GetMapping("/data")
	public ResponseEntity<List<UserDTO>> findByDateOfBirth(@RequestParam("dateOfBirth")String date){
		return ResponseEntity.ok(service.findByDateOfBirth(DateUtils.strToZonedDateTime(date)).stream().map((user) -> user.ToDto()).toList());
	}
	
	@GetMapping("/sexo/{gender}")
	public ResponseEntity<List<UserDTO>> findByGender(@PathVariable String gender){
		return ResponseEntity.ok(service.findByGender(gender).stream().map((user) -> user.ToDto()).toList());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update (@PathVariable Integer id, @RequestBody UserDTO userDTO) {
		User user = new User(userDTO);
		user.setId(id);
		user = service.update(user);
		return user != null ? ResponseEntity.ok(user.ToDto()) : ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
