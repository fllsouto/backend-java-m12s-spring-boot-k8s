package com.cdc.backend.userApi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.backend.shoppingClient.dto.UserDTO;
import com.cdc.backend.shoppingClient.exception.UserNotFoundException;
import com.cdc.backend.userApi.converter.DTOConverter;
import com.cdc.backend.userApi.model.User;
import com.cdc.backend.userApi.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream().map(DTOConverter::convert).toList();
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());
        userDTO.setDataCadastro(new Date());
        User user = User.convert(userDTO);
        user = userRepository.save(user);
        return DTOConverter.convert(user);
    }

    public Boolean delete(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    public UserDTO findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return DTOConverter.convert(user.get());
        }
        return null;
    }

    public UserDTO findByCpfAndKey(String cpf, String key) {
        Optional<User> user = userRepository.findByCpfAndKey(cpf, key);
        if (user.isPresent()) {
            return DTOConverter.convert(user.get());
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> searchByName(String nome) {
        System.out.println("Usando nome: " + nome);
        List<User> usuarios = userRepository.findByNomeContainingIgnoreCase(nome);
        return usuarios.stream().map(DTOConverter::convert).toList();
    }
}
