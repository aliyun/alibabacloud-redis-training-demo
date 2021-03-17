package com.aliyun.tair.springbootexample.repo;

import java.util.Set;

import com.aliyun.tair.springbootexample.model.Rank;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RankRepository {
    private static final String RANK_KEY = "RANK";
    private ZSetOperations zSetOperations;
    private RedisTemplate redisTemplate;

    public RankRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOperations = this.redisTemplate.opsForZSet();
    }

    public Set<Rank> findAll() {
        return zSetOperations.reverseRangeWithScores(RANK_KEY, 0, -1);
    }

    public void save(Rank rank) {
        zSetOperations.add(RANK_KEY, rank.getCity(), rank.getScore());
    }
}
