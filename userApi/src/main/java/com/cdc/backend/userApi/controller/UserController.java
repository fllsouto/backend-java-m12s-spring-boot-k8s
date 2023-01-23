package com.cdc.backend.userApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdc.backend.userApi.dto.UserDTO;
import com.cdc.backend.userApi.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String getMensagem() {
		return "Spring boot is working";
	}

	@GetMapping("/users")
	public List<UserDTO> getUsers() {
		return userService.getAll();
	}

	@GetMapping("/users/{id}")
	public UserDTO getUser(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PostMapping("/users")
	public UserDTO addUser(@RequestBody UserDTO userDTO) {
		return userService.save(userDTO);
	}
	
	@DeleteMapping("/users/{id}")
	public Boolean removeUser(@PathVariable Long id) {
		return userService.delete(id);
	}

	@GetMapping("/users/search")
	public List<UserDTO> searchByName(@RequestParam(name="nome", required=true) String nome) {
		return userService.searchByName(nome);
	}
	
	
}
