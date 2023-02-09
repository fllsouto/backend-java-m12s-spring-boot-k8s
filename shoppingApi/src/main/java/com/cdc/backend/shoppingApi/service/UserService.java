package com.cdc.backend.shoppingApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cdc.backend.shoppingClient.dto.UserDTO;
import com.cdc.backend.shoppingClient.exception.UserNotFoundException;


@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8080")
    private String userApiUrl;

    public UserDTO getUserByCpf(String cpf, String key) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(userApiUrl + "/users/cpf/" + cpf);
            builder.queryParam("key", key);

            ResponseEntity<UserDTO> response = restTemplate.getForEntity(builder.toString(), UserDTO.class);
            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
