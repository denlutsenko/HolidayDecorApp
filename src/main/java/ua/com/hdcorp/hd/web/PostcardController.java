package ua.com.hdcorp.hd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.model.Production;
import ua.com.hdcorp.hd.model.dto.ProductionDto;
import ua.com.hdcorp.hd.service.interf.PostcardService;
import ua.com.hdcorp.hd.service.interf.ProductionService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/postcards")
public class PostcardController {

    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping
    public ResponseEntity<List<Postcard>> getAllActivePostcards() {
        return ResponseEntity.ok(postcardService.findAllActivePostcards());
    }
}
