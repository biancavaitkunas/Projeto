package br.com.trier.ProjetoJovemDev.repositories;

import java.time.ZonedDateTime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List <User> findByNameStartingWithIgnoreCase(String name);
	User findByEmail(String email);
	List<User> findByDateOfBirth(ZonedDateTime dateOfBirth);
	List<User> findByGender(String gender);

}
