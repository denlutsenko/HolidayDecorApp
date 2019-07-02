package ua.com.hdcorp.hd.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.service.interf.IPostcardTypeService;

@CrossOrigin
@RestController
public class PostcardTypeController {
    private final IPostcardTypeService postcardTypeService;

    public PostcardTypeController(final IPostcardTypeService postcardTypeService){
        this.postcardTypeService = postcardTypeService;
    }

    @CrossOrigin
    @GetMapping(value = "account/administration/postcardTypes")
    public ResponseEntity<?> getPostcardTypes(){
        try {
            return ResponseEntity.ok().body(postcardTypeService.getPostcardTypes());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка запроса"));
        }
    }
}





