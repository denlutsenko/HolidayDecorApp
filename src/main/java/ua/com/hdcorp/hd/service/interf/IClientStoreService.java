package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Client;
import ua.com.hdcorp.hd.model.ClientStore;

public interface IClientStoreService {
     ClientStore getStore(Long id);
     void deleteClientStore(Long id);

     ClientStore save(Client client, ClientStore clientStore);

}
