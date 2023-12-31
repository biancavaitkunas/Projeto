package br.com.trier.ProjetoJovemDev.services.impl;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trier.ProjetoJovemDev.domain.User;
import br.com.trier.ProjetoJovemDev.repositories.UserRepository;
import br.com.trier.ProjetoJovemDev.services.UserService;
import br.com.trier.ProjetoJovemDev.services.exceptions.IntegrityViolation;
import br.com.trier.ProjetoJovemDev.services.exceptions.ObjectNotFound;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;
	
	private void findByEmail(User obj) {
		Optional<User> user = repository.findByEmail(obj.getEmail());
		if (user != null && user.get().getId() != obj.getId()) {
			throw new IntegrityViolation("Email %s já existe".formatted(obj.getEmail()));
		}
	}

	@Override
	public User insert(User user) {
		findByEmail(user);
		return repository.save(user);
	}

	@Override
	public List<User> listAll() {
		if (repository.findAll().size() == 0) {
			throw new ObjectNotFound("Nenhum usuário encontrado");
		}
		return repository.findAll();
	}

	@Override
	public User findById(Integer id) {
		return repository.findById(id).orElseThrow(()-> new ObjectNotFound("Usuário %s não encontrado".formatted(id)));
	}

	@Override
	public User update(User user) {
		findByEmail(user);
		return repository.save(user);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(findById(id));
		
	}

	@Override
	public List<User> findByNameStartingWithIgnoreCase(String name) {
		if(repository.findByNameStartingWithIgnoreCase(name).size() == 0) {
			throw new ObjectNotFound("Nenhum usuário encontrado");			
		}
		return repository.findByNameStartingWithIgnoreCase(name);
	}

	@Override
	public List<User> findByGender(String gender) {
		if(repository.findByGender(gender).size() == 0) {
			throw new ObjectNotFound("Nenhum usuário encontrado");			
		}
		return repository.findByGender(gender);
	}
	
	

}
