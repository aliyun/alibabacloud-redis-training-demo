package com.aliyun.tair.springbootexample.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Repository;

@Repository
public class PositionRepository {
    private static final String POSITION_KEY = "CHINA";
    private RedisTemplate redisTemplate;

    public PositionRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object position(String position) {
        //RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //byte[][] args = new byte[3][];
        //args[0] = POSITION_KEY.getBytes();
        //args[1] = position.getBytes();
        //args[2] = "WITHOUTWKT".getBytes();
        //return connection.execute("GIS.CONTAINS", args);

        // we must use lua because Lettuce have some problem.
        // see https://github.com/spring-projects/spring-data-redis/issues/1979

        String scriptStr = "return redis.call('GIS.CONTAINS', KEYS[1], ARGV[1])";
        return redisTemplate.getConnectionFactory().getConnection().eval(scriptStr.getBytes(), ReturnType.MULTI, 1,
            POSITION_KEY.getBytes(), position.getBytes());
    }

    public void save(String field, String value) {
        String scriptStr = "return redis.call('GIS.ADD', KEYS[1], ARGV[1], ARGV[2])";
        redisTemplate.getConnectionFactory().getConnection().eval(scriptStr.getBytes(), ReturnType.MULTI, 1,
            POSITION_KEY.getBytes(), field.getBytes(), value.getBytes());
    }

    public Object intersect(String route) {
        String scriptStr = "return redis.call('GIS.INTERSECTS', KEYS[1], ARGV[1])";
        return redisTemplate.getConnectionFactory().getConnection().eval(scriptStr.getBytes(), ReturnType.MULTI, 1,
            POSITION_KEY.getBytes(), route.getBytes());
    }
}
