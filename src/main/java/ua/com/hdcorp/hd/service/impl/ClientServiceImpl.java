package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.model.Client;
import ua.com.hdcorp.hd.model.ClientStore;
import ua.com.hdcorp.hd.repository.ClientRepository;
import ua.com.hdcorp.hd.service.interf.IClientService;
import ua.com.hdcorp.hd.service.interf.IClientStoreService;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;
    private final IClientStoreService clientStoreService;

    @Autowired
    public ClientServiceImpl(final ClientRepository clientRepository, final IClientStoreService clientStoreService) {
        this.clientRepository = clientRepository;
        this.clientStoreService = clientStoreService;
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findByActiveStatus(true);
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.getClientByIdAndActiveStatus(id, true);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.updateActiveStatus(false, clientId);
    }

    @Transactional
    @Override
    public void saveClientWithStores(Client cl) {
        cl.setActiveStatus(true);
        Client client = clientRepository.save(cl);
        for (ClientStore cStores : cl.getClientStores()) {
            clientStoreService.save(client, cStores);
        }
    }

    @Override
    public void saveClient(Client client) {
        client.setActiveStatus(true);
        clientRepository.save(client);
    }
}
