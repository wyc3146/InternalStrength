package com.wyc.tips.structure.distributed.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis的setnx（set if not exist），在这个key不存在的时候才设置，且可以保证是原子的。
 * @author wangyongcan.
 * @date 2018/4/17.
 */
public class RedisBaseDistributedLock {

    JedisPool jedisPool = new JedisPool("localhost");

    private static final String DISTRIBUTED_LOCK_KEY = "distributed_lock_key";

    public boolean lock() {
        Long result = jedisPool.getResource().setnx(DISTRIBUTED_LOCK_KEY, "heheda");
        return result >= 1;
    }

    public void relese() {
        jedisPool.getResource().del(DISTRIBUTED_LOCK_KEY);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.del("key111");
//        System.out.println(jedis.setnx("key111", "value111"));

    }
}
