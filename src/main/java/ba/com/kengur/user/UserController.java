package ba.com.kengur.user;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ba.com.kengur.error.EntityNotFound;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

    private UserRepository repository;

    private UserMapper userMapper;

    private PasswordEncoder passEncoder;

    // Find
    @GetMapping("/users")
    List<User> findAll() {
        return userMapper.entitestoDtos(repository.findAll());
    }

    // Save
    @PostMapping("/users")
    // return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    UserEntity createNewUser(@RequestBody User newUser) {
        UserEntity userEnt = userMapper.dtoToEntity(newUser);
        userEnt.setPassword(passEncoder.encode(newUser.getPassword()));
        return repository.save(userEnt);
    }

    // Find
    @GetMapping("/users/{id}")
    UserEntity findOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }

    // Save or update
    @PutMapping("/users/{id}")
    UserEntity saveOrUpdate(@RequestBody UserEntity newUser, @PathVariable Long id) {
        return newUser;

    }

    // update author only
    @PatchMapping("/users/{id}")
    UserEntity patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return null;

    }

    @DeleteMapping("/users/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
