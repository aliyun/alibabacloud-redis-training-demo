package com.aliyun.tair.springbootexample.controller;

import java.util.List;

import com.aliyun.tair.springbootexample.model.News;
import com.aliyun.tair.springbootexample.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @PostMapping("/save")
    public News save(@RequestBody News news) {
        newsRepository.save(news);
        return news;
    }

    @CrossOrigin
    @GetMapping
    public List<News> list() {
        return newsRepository.findAll();
    }

    @DeleteMapping("/delete")
    public void deleteUser(){
        newsRepository.delete();
    }
}
