package com.aliyun.tair.springbootexample.controller;

import java.util.Set;

import com.aliyun.tair.springbootexample.model.Rank;
import com.aliyun.tair.springbootexample.repo.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankRepository rankRepository;

    @PostMapping
    public Rank save(@RequestBody Rank rank){
        rankRepository.save(rank);
        return rank;
    }

    @CrossOrigin
    @GetMapping
    public Set<Rank> list(){
        return rankRepository.findAll();
    }
}
