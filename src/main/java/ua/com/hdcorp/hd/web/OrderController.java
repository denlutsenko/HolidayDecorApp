package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.service.interf.IClientService;
import ua.com.hdcorp.hd.service.interf.IPostcardService;
import ua.com.hdcorp.hd.service.interf.IPostcardTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {
    private final IPostcardService pService;
    private final IClientService clientService;
    private final IPostcardTypeService postcardTypeService;

    @Autowired
    public OrderController(final IPostcardService pService, final IClientService clientService,
                           final IPostcardTypeService postcardTypeService) {
        this.pService = pService;
        this.clientService = clientService;
        this.postcardTypeService = postcardTypeService;
    }

    /**
     * Method send list of postcards and clients to crate order on te next page.
     * @return list of postcards and orders
     */
    @GetMapping(value = "/account/orders")
    public ResponseEntity<?> getPostcardsAndClients() {
        Map<String, List<?>> objMap = new HashMap<>();
        objMap.put("postcards", pService.getPostcardsWithRemain());
        objMap.put("clients", clientService.getClients());
        objMap.put("postcardTypes", postcardTypeService.getPostcardTypes());
        try {
            return ResponseEntity.ok().body(objMap);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }
}
