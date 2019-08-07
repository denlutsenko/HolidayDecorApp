package ua.com.hdcorp.hd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.service.PostcardService;

import java.util.List;

@RestController
@RequestMapping(value = "postcards")
public class PostcardController {

    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Postcard>> getPostcards() {
        return ResponseEntity.ok(postcardService.getPostcards());
    }

    @PostMapping
    public ResponseEntity<Postcard> createPostcard(@RequestParam(value = "file", required = false) MultipartFile file,
                                                   @RequestParam(value = "vendorCode") String vendorCode,
                                                   @RequestParam(value = "postcardTypeId") Long postcardTypeId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postcardService.save(file, vendorCode, postcardTypeId));
    }
}