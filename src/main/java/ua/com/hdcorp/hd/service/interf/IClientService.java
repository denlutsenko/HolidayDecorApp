package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Client;

import java.util.List;

public interface IClientService {

    List<Client> getClients();

    Client getClient(Long id);

    void deleteClient(Long clientId);

    void saveClientWithStores(Client client);

    void saveClient(Client client);

}
