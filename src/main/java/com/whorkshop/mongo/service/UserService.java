package com.whorkshop.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whorkshop.mongo.domain.User;
import com.whorkshop.mongo.dto.UserDTO;
import com.whorkshop.mongo.repository.UserRepository;
import com.whorkshop.mongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public User insert(UserDTO objDto) {
		return repo.save(fromDTO(objDto));
	}
}
