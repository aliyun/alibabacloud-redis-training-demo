package com.aliyun.tair.springbootexample;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("ping redis: " + jedis.ping());
        jedis.set("key", "value");
        System.out.println(jedis.get("key"));
    }
}
