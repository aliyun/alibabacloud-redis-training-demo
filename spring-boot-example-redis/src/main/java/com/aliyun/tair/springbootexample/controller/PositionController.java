package com.aliyun.tair.springbootexample.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.aliyun.tair.springbootexample.model.Position;
import com.aliyun.tair.springbootexample.model.User;
import com.aliyun.tair.springbootexample.repo.HotAreaRepository;
import com.aliyun.tair.springbootexample.repo.PositionRepository;
import com.aliyun.tair.springbootexample.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotAreaRepository hotAreaRepository;

    @CrossOrigin
    @GetMapping("/{id}")
    public Position getPosition(@PathVariable String id) throws UnsupportedEncodingException {
        String userStr = userRepository.findById(id);
        if (userStr == null || userStr.isEmpty()) {
            return new Position();
        }
        User user = JSON.parseObject(userStr, User.class);
        Object obj = positionRepository.position(user.getPosition());
        List<Object> result = (List<Object>)obj;
        if (null == result || 0 == result.size()) {
            return new Position();
        } else {
            List<byte[]> rawResults = (List)result.get(1);
            List<String> provinces = new ArrayList<String>();
            for (byte[] raw : rawResults) {
                provinces.add(new String(raw, "UTF-8"));
            }
            return new Position("name", provinces.get(0));
        }
    }

    @CrossOrigin
    @GetMapping("/code/{id}")
    public Position getCode(@PathVariable String id) throws UnsupportedEncodingException {
        int red = 0;
        String userStr = userRepository.findById(id);
        if (userStr == null || userStr.isEmpty()) {
            return new Position(red);
        }
        User user = JSON.parseObject(userStr, User.class);
        Object obj = positionRepository.intersect(user.getRoute());
        List<Object> result = (List<Object>)obj;
        if (null == result || 0 == result.size()) {
            return new Position(red);
        } else {
            List<byte[]> rawResults = (List)result.get(1);
            for (byte[] raw : rawResults) {
                if (hotAreaRepository.contains(new String(raw, "UTF-8"))) {
                    red = 1;
                }
            }
            return new Position(red);
        }
    }
}
