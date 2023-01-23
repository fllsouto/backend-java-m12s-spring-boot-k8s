package com.cdc.backend.userApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdc.backend.userApi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCpf(String cpf);
	
	List<User> findByNomeStartingWith(String nome);
	
	List<User> findByNomeContainingIgnoreCase(String nome);
}
