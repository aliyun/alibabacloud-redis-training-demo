package com.aliyun.tair.springbootexample.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.aliyun.tair.springbootexample.model.User;
import com.aliyun.tair.springbootexample.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User save(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @CrossOrigin
    @GetMapping
    public List<String> list(){
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return JSON.parseObject(userRepository.findById(id), User.class);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        userRepository.delete(id);
        return id;
    }
}
