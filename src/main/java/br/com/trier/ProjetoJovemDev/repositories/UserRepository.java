package br.com.trier.ProjetoJovemDev.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trier.ProjetoJovemDev.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List <User> findByNameStartingWithIgnoreCase(String name);
	Optional<User> findByEmail(String email);
	List<User> findByGender(String gender);

}
