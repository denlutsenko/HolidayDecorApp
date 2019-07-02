package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.dto.UserDto;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.model.User;
import ua.com.hdcorp.hd.repository.UserRepository;
import ua.com.hdcorp.hd.service.interf.IRoleService;
import ua.com.hdcorp.hd.service.interf.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final IRoleService roleService;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final IRoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByActiveStatus(true);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findByIdAndActiveStatus(id, true);
    }

    @Override
    public List<User> findActiveUsers() {
        return userRepository.findByActiveStatus(true);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.updateActiveStatus(false, id);
    }

    @Override
    public User addNewUser(UserDto userDto) {
        Role role = roleService.getRoleById(userDto.getRole().getId());
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(),
                userDto.getPhone(), userDto.getAddress(), role, true);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDto userDto){
        Role role = roleService.getRoleById(userDto.getRole().getId());
        User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(),
                userDto.getPhone(), userDto.getAddress(), role, true);
        return userRepository.save(user);
    }

    @Override
    public UserDto getUserDto(Long id){
        User user = userRepository.findByIdAndActiveStatus(id, true);
        if(user != null) {
            return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),
                    user.getAddress(), user.isActiveStatus(), user.getRole());
        }else{
            return null;
        }
    }
}
