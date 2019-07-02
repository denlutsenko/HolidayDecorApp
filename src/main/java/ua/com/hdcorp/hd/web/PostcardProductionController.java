package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.dto.PostcardProductionDto;
import ua.com.hdcorp.hd.dto.UserDto;
import ua.com.hdcorp.hd.service.interf.IPostcardProductionService;
import ua.com.hdcorp.hd.service.interf.IPostcardService;
import ua.com.hdcorp.hd.service.interf.IPostcardTypeService;
import ua.com.hdcorp.hd.service.interf.IUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostcardProductionController {
    private final IPostcardService pService;
    private final IUserService uService;
    private final IPostcardProductionService postcardProduction;
    private final IPostcardTypeService postcardTypeService;

    @Autowired
    public PostcardProductionController(IPostcardService pService,
                                        IUserService uService,
                                        IPostcardProductionService postcardProduction,
                                        IPostcardTypeService postcardTypeService) {
        this.pService = pService;
        this.uService = uService;
        this.postcardProduction = postcardProduction;
        this.postcardTypeService = postcardTypeService;
    }

    /**
     * method allow to see whole list of postcards and users
     *
     * @return list of users and list of postcards
     */
    @CrossOrigin
    @GetMapping(value = "/account/postcardProduction")
    public ResponseEntity<?> getPostcardsAndUsers() {
        Map<String, List<?>> objMap = new HashMap<>();
        List<UserDto> usersDto = new ArrayList<>();
        uService.getAllUsers().forEach((a) -> usersDto.add(new UserDto(a.getId(), a.getFirstName(), a.getLastName())));
        objMap.put("users", usersDto);
        objMap.put("postcardTypes", postcardTypeService.getPostcardTypes());
        objMap.put("postcards", pService.getPostcardsWithRemain());
        try {
            return ResponseEntity.ok().body(objMap);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(400, "Ошибка запроса"));
        }
    }

    /**
     * @param productionDtoList - receive list with produced postcards.
     * @return if Ok - status 200 else - status 400
     */
    @CrossOrigin
    @PostMapping(value = "/account/postcardProduction/add")
    public ResponseEntity<?> addPostcardProductionAndUpdateRemains(@RequestBody PostcardProductionDto productionDtoList) {
        try {
            postcardProduction.updatePostcardProduction(productionDtoList);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(400, "Не удается сохранить."));
        }
    }

}
