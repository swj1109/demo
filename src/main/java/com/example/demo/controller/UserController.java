package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.resp.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author mark
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    //swj add start
    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userRepository.addUser(user.getUsername());
    }
    //swj add end

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //swj add start
    @DeleteMapping("/del/{id}")
    public void delUser(@PathVariable("id") Integer userId) {
        userRepository.delUser(userId);
    }
    //swj add end

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer userId) {
        userRepository.deleteById(userId);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Integer userId, @RequestBody User user) {
        user.setId(userId);
        return userRepository.saveAndFlush(user);
    }

    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") Integer userId) {
        Optional<User> optional = userRepository.findById(userId);
        User user = optional.orElseGet(User::new);

        // add HATEOAS link
        user.add(linkTo(methodOn(UserController.class).getUserInfo(userId)).withSelfRel());
        return user;
    }

    @GetMapping("/list")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }
}
