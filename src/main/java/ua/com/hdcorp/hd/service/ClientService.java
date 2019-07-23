package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.com.hdcorp.hd.model.Client;
import ua.com.hdcorp.hd.repository.ClientRepository;

import javax.validation.Valid;

@Service
@Validated
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(@Valid Client client) {
        Client savedClient = clientRepository.save(client);
        clientRepository.refresh(savedClient);
        return savedClient;
    }
}