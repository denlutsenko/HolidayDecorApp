package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.model.Client;
import ua.com.hdcorp.hd.service.interf.IClientService;

@RestController
@CrossOrigin
public class ClientController {
    private final IClientService clientService;

    @Autowired
    public ClientController(final IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/account/administration/clients")
    public ResponseEntity<?> getAllClients(){
        try{
            return ResponseEntity.ok().body(clientService.getClients());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }

    @GetMapping(value = "/account/administration/clients/client/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable Long clientId){
        try{
            return ResponseEntity.ok().body(clientService.getClient(clientId));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }

    @GetMapping(value = "/account/administration/clients/client/delete/{clientId}")
    public ResponseEntity<?> deleteById(@PathVariable Long clientId){
        try{
            clientService.deleteClient(clientId);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }

    // save new clients with stores. Also update clients with stores.
    @PostMapping(value = "/account/administration/clients/saveClientAndStores")
    public ResponseEntity<?> addNewClientAndStores(@RequestBody Client client){
        try{
            clientService.saveClientWithStores(client);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }

    // save new client without stores.
    @PostMapping(value = "/account/administration/clients/saveClient")
    public ResponseEntity<?> saveClient(@RequestBody Client client){
        try{
            clientService.saveClient(client);
            return ResponseEntity.ok().build();
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }
}
