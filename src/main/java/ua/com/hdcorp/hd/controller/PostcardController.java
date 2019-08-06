package ua.com.hdcorp.hd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Postcard;
import ua.com.hdcorp.hd.service.PostcardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "postcards")
public class PostcardController {

    private final PostcardService postcardService;

    @Autowired
    public PostcardController(PostcardService postcardService) {
        this.postcardService = postcardService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Postcard>> findPostcards() {
        return ResponseEntity.ok(postcardService.getPostcards());
    }

    @GetMapping(value = "/{postcardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Postcard> findPostcard(@PathVariable("postcardId") Long postcardId) {
        return ResponseEntity.ok(postcardService.findById(postcardId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Postcard> createPostcard(@RequestBody Postcard postcard) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postcardService.save(postcard));
    }

    @PatchMapping(value = "/{postcardId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Postcard> updatePostcard(@PathVariable("postcardId") Long postcardId, @RequestBody Map<String, String> postcardPatch) {
        return ResponseEntity.status(HttpStatus.OK).body(postcardService.update(postcardId, postcardPatch));
    }

    @DeleteMapping(value = "/{postcardId}")
    public ResponseEntity<Postcard> deletePostcard(@PathVariable("postcardId") Long postcardId) {
        return ResponseEntity.status(HttpStatus.OK).body(postcardService.deactivate(postcardId));
    }
}