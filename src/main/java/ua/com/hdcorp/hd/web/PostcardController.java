package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.service.interf.IPostcardService;
import ua.com.hdcorp.hd.service.interf.IPostcardTypeService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PostcardController {
    private final IPostcardService postcardService;
    private final IPostcardTypeService pTypeService;

    @Autowired
    public PostcardController(final IPostcardService postcardService, final IPostcardTypeService pTypeService) {
        this.postcardService = postcardService;
        this.pTypeService = pTypeService;
    }

    //get active postcard with list of postcardCategories
    @GetMapping(value = "/account/administration/getPostcard/{id}")
    public ResponseEntity<?> getPostcardAndCategories(@PathVariable Long id) {
        Map<String, List<?>> objMap = new HashMap<>();
        try {
            objMap.put("postcard", postcardService.getActivePostcard(id));
            objMap.put("postcardTypes", pTypeService.getPostcardTypes());
            return ResponseEntity.ok().body(objMap);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Сервер недоступен"));
        }
    }

    //get all list of active postcards
    @GetMapping(value = "/account/administration/postcards")
    public ResponseEntity<?> getAllActivePostcard() {
        try {
            return ResponseEntity.ok().body(postcardService.getPostcardsWithRemain());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка сервера"));
        }
    }

    //add new postcard
    @PostMapping(value = "/account/administration/postcards/savePostcard")
    public ResponseEntity addPostcard(@RequestPart(value = "img", required = false) MultipartFile img,
                                      @RequestParam(value = "categoryId") Long categoryId,
                                      @RequestParam(value = "vendorCode") String vendorCode,
                                      @RequestParam(value = "price", required = false) BigDecimal price) {
        postcardService.saveNewPostcard(img, vendorCode, price, categoryId);
        try {

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка сервера"));
        }
    }

    //update postcard
    // TODO: add logic for updating postcardCategory and copy photo to another folder
    @PostMapping(value = "/account/administration/updatePostcards/updatePostcard")
    public ResponseEntity<?> saveUpdates(@RequestParam(value = "img", required = false) MultipartFile img,
                                         @RequestParam(value = "vendorCode", required = false) String vendorCode,
                                         @RequestParam(value = "price", required = false) BigDecimal price,
                                         @RequestParam(value = "categoryId", required = false) Long categoryId,
                                         @RequestParam(value = "postcardId") Long postcardId) {
        try {
        postcardService.updatePostcard(img, postcardId, vendorCode, price, categoryId);
        return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка сервера"));
       }
    }

    //delete postcard. set flag IsActive = false
    @DeleteMapping(value = "/account/administration/postcards/delete/{postcardId}")
    public ResponseEntity<?> deletePostcard(@PathVariable Long postcardId) {
        try {
            postcardService.deletePostcard(postcardId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Сервер недоступен"));
        }
    }
}



