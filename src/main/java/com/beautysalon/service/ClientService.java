package com.beautysalon.service;

import com.beautysalon.controller.dto.ClientRequest;
import com.beautysalon.controller.dto.ClientResponse;
import com.beautysalon.repository.ClientRepository;
import com.beautysalon.repository.model.Client;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public void save(ClientRequest clientRequest) {
        if (clientRepository.existsByEmail(clientRequest.getEmail())) {
            throw new RuntimeException("Client with this email already exists!");
        }
        if (!isValidEmailFormat(clientRequest.getEmail())) {
            throw new IllegalArgumentException("Incorrect email format!");
        }
        if (clientRepository.existsByName(clientRequest.getName())) {
            throw new RuntimeException("Client with this name already exists!");
        }
        final Client client = clientMapper.map(clientRequest);
        clientRepository.save(client);
    }

    public ClientResponse findResponseByEmail(String email) {
        if(!clientRepository.existsByEmail(email)){
            throw new NullPointerException("Client with this email does not exists!");
        }
        final Client client = clientRepository.findByEmail(email);
        return clientMapper.map(client);
    }

    public ClientResponse findResponseById(Integer id) {
        final Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return clientMapper.map(client);
    }

    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }

    public ClientResponse findResponseByName(String name) {
        if(!clientRepository.existsByName(name)){
            throw new NullPointerException("Client with this name does not exist!");
        }
        final Client client = clientRepository.findByName(name);
        return clientMapper.map(client);
    }


    private boolean isValidEmailFormat(String email) {
        // Add your email format validation logic here
        // For simplicity, this example checks for a basic format
        return email != null && email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }
}
