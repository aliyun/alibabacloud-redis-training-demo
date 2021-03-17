package com.aliyun.tair.springbootexample.repo;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.aliyun.tair.springbootexample.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private static final String USER = "USER";

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public UserRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(User user) {
        hashOperations.put(USER, user.getId(), JSON.toJSONString(user));
    }

    public List<String> findAll() {
        return hashOperations.values(USER);
    }

    public String findById(String id) {
        return (String) hashOperations.get(USER, id);
    }

    public void delete(String id) {
        hashOperations.delete(USER, id);
    }

    public void multiGetUsers(List<String> userIds) {
        hashOperations.multiGet(USER, userIds);
    }
}
