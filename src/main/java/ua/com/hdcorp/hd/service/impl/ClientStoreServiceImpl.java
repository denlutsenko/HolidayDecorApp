package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.Client;
import ua.com.hdcorp.hd.model.ClientStore;
import ua.com.hdcorp.hd.repository.ClientStoreRepository;
import ua.com.hdcorp.hd.service.interf.IClientStoreService;

@Service
public class ClientStoreServiceImpl implements IClientStoreService {
    private final ClientStoreRepository clientStoreRepository;

    @Autowired
    public ClientStoreServiceImpl(final ClientStoreRepository clientStoreRepository) {
        this.clientStoreRepository = clientStoreRepository;
    }


    @Override
    public ClientStore getStore(Long id) {
        return clientStoreRepository.getOne(id);
    }

    @Override
    public void deleteClientStore(Long id) {
        clientStoreRepository.updateActiveStatus(false, id);
    }

    @Override
    public ClientStore save(Client client, ClientStore clientStore) {
        clientStore.setClient(client);
        return clientStoreRepository.save(clientStore);
    }
}


