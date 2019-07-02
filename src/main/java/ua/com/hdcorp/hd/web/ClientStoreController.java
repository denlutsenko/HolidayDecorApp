package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.service.interf.IClientStoreService;

@RestController
public class ClientStoreController {

    private final IClientStoreService clientStoreService;

    @Autowired
    public ClientStoreController(final IClientStoreService clientStoreService) {
        this.clientStoreService = clientStoreService;
    }


    @GetMapping(value = "/account/administration/clients/client/deleteStore/{id}")
    public ResponseEntity<?> deleteClientStore(@PathVariable Long id){
        try{
            clientStoreService.deleteClientStore(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }

}
