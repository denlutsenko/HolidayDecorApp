package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.dto.UserDto;
import ua.com.hdcorp.hd.model.User;

import java.util.List;

public interface IUserService {
    User findUserByEmailAndPassword(String email, String password);

    List<User> getAllUsers();

    User findUserById(Long id);

    UserDto getUserDto(Long id);

    List<User> findActiveUsers();

    void deleteUser(Long id);

    User addNewUser(UserDto userDto);
    User updateUser(UserDto userDto);

}
