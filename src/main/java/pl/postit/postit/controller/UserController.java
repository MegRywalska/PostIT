package pl.postit.postit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.postit.postit.dto.UserDTO;
import pl.postit.postit.dto.UserRequestDTO;
import pl.postit.postit.model.User;
import pl.postit.postit.repository.UserRepository;
import pl.postit.postit.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {


    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("")
    public UserDTO putUser(@RequestBody UserRequestDTO requestDTO){
        return userService.createUser(requestDTO);
    }

    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable(name = "id") Long id, @RequestBody UserRequestDTO userRequestDTO){
        return userService.updateUserById(id, userRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void delateUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

}
