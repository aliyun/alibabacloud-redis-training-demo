package com.aliyun.tair.springbootexample.controller;

import com.aliyun.tair.springbootexample.model.HotArea;
import com.aliyun.tair.springbootexample.repo.HotAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
public class HotAreaController {
    @Autowired
    private HotAreaRepository hotAreaRepository;

    @PostMapping
    public HotArea save(@RequestBody HotArea area){
        hotAreaRepository.save(area);
        return area;
    }
}
