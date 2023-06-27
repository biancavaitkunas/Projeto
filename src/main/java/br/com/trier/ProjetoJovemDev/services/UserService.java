package br.com.trier.ProjetoJovemDev.services;

import java.time.ZonedDateTime;

import java.util.List;

import br.com.trier.ProjetoJovemDev.domain.User;


public interface UserService {
	
	User insert (User user);
	List<User> listAll();
	User findById(Integer id);
	User update(User user);
	void delete(Integer id);
	List <User> findByNameStartingWithIgnoreCase(String name);
	List<User> findByDateOfBirth(ZonedDateTime dateOfBirth);
	List<User> findByGender(String gender);

}
