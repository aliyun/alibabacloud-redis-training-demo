package com.aliyun.tair.springbootexample.repo;

import com.aliyun.tair.springbootexample.model.HotArea;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class HotAreaRepository {
    private static final String EAREAKEY = "EAREA";
    private SetOperations setOperations;
    private RedisTemplate redisTemplate;

    public HotAreaRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.setOperations = redisTemplate.opsForSet();
    }

    public void save(HotArea area) {
        setOperations.add(EAREAKEY, area.getName());
    }

    public Boolean contains(String name) {
        return setOperations.isMember(EAREAKEY, name);
    }
}
