package com.aliyun.tair.springbootexample.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.tair.springbootexample.model.News;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository {
    private static final String STREAMKEY = "NEWS";
    private StreamOperations streamOperations;
    private RedisTemplate redisTemplate;

    public NewsRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.streamOperations = this.redisTemplate.opsForStream();
    }

    public void save(News news) {
        Map<String, String> fields = new HashMap<>();
        fields.put(news.getName(), news.getContent());
        StringRecord record = StreamRecords.string(fields).withStreamKey(STREAMKEY);
        streamOperations.add(record);
    }

    public List<News> findAll() {
        List<News> news = new ArrayList<>();
        List<MapRecord> range = streamOperations.reverseRange(STREAMKEY, Range.open("+", "-"));
        for (MapRecord mr : range) {
            Map<String, String> map = (Map) mr.getValue();
            for (Map.Entry<String, String> entry : map.entrySet())
            news.add(new News(entry.getKey(), entry.getValue()));
        }
        return news;
    }

    public void delete() {
        redisTemplate.delete(STREAMKEY);
    }
}
