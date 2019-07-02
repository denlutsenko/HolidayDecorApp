package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.data.Error;
import ua.com.hdcorp.hd.dto.UserDto;
import ua.com.hdcorp.hd.model.User;
import ua.com.hdcorp.hd.service.interf.IRoleService;
import ua.com.hdcorp.hd.service.interf.IUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    private IUserService userService;
    private IRoleService roleService;

    @Autowired
    public UserController(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/account")
    public ResponseEntity<?> auth(@RequestBody UserDto userDto) {
        try {
            User user = userService.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
            UserDto usrDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName());
            return ResponseEntity.ok().body(usrDto);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(new Error(403, "Ошибка авторизации"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Сервер недоступен"));
        }
    }

    //redirect to page to open form for new employee
    @RequestMapping(method = RequestMethod.GET, value = "/account/administration/users/newUser")
    public ResponseEntity<?> getInfoForNewUser() {
        try {
            return ResponseEntity.ok().body(roleService.getAllRoles());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка запроса"));
        }
    }

    //save user.
    @RequestMapping(method = RequestMethod.POST, value = "/account/administration/users/addNewUser")
    public ResponseEntity<?> saveNewUser(@RequestBody UserDto userDto) {
        try {
            userService.addNewUser(userDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Не удалось сохранить сотрудника. Проверьте данные"));
        }
    }


    //get all list of users.
    @GetMapping(value = "/account/administration/users/all")
    public ResponseEntity<?> editUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        Map<String, List<?>> objMap = new HashMap();

        try {
            userService.getAllUsers().forEach((a) -> usersDto.add(new UserDto(a.getId(), a.getFirstName(), a.getLastName(),
                    a.getEmail(), a.getPhone(), a.getAddress(), a.isActiveStatus(), a.getRole())));
            objMap.put("users", usersDto);
            objMap.put("roles", roleService.getAllRoles());
            return ResponseEntity.ok().body(objMap);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка сервера"));
        }
    }

    //delete user by user id
    @DeleteMapping(value = "/account/administration/users/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        try {
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка сервера"));
        }
    }

    // update user
    @PostMapping(value = "/account/administration/users/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        try {
            userService.updateUser(userDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка сервера"));
        }
    }

    // update user
    @GetMapping(value = "/account/administration/users/getUser/{userId}")
    public ResponseEntity<?> getUserByID(@PathVariable Long userId){
        try{
            return ResponseEntity.ok(userService.getUserDto(userId));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new Error(500, "Ошибка сервера"));
        }
    }
}



