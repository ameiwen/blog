package com.blog.cache;

import com.blog.utils.Misc;
import com.blog.utils.PropertyReader;
import redis.clients.jedis.*;

import java.util.*;


public abstract class RedisCache {
    private static final String hosts = PropertyReader.get("cache.redis.host", "cache.properties");
    private static final boolean enableCluster = "1".equals(PropertyReader.get("cache.redis.enableCluster", "cache.properties"));
    public static final boolean enableCache = "1".equals(PropertyReader.get("cache.enable", "cache.properties"));
    protected static int maxTotal = 1000;
    protected static int maxIdle = 10;
    protected static int maxWaitMillis = 3000;
    protected static int maxRedirections = 1000;
    protected static JedisPool pool = null;
    protected static JedisCluster jedisCluster = null;
    protected static RedisCache redisCache = new RedisEmptyCache();

    public RedisCache() {
    }

    public static RedisCache getInstance() {
        return redisCache;
    }

    public abstract boolean existsCache(String var1);

    public abstract boolean existsHashCache(String var1, String var2);

    public abstract void addCache(String var1, String var2);

    public abstract void addCache(String var1, String var2, String var3);

    public abstract void addCacheWithExpire(String var1, String var2, int var3);

    public abstract void addCache(String var1, Map<String, Object> var2);

    public abstract void addCacheForObject(String var1, Object var2);

    public abstract void addCacheForObject(String var1, Object var2, int var3);

    public abstract String getCache(String var1);

    public abstract Object getCacheForObject(String var1);

    public abstract String getCache(String var1, String var2);

    public abstract List<String> getCache(String var1, String... var2);

    public abstract Map<String, String> getHashAllFieldCache(String var1);

    public abstract Set<String> getAllKeys();

    public abstract Long increaseBy(String var1, long var2);

    public abstract Double increaseBy(String var1, double var2);

    public abstract Long decreaseBy(String var1, long var2);

    public abstract Long increase(String var1);

    public abstract Long decrease(String var1);

    public abstract void expireKey(String var1, int var2);

    public abstract void delCache(String... var1);

    public abstract void delCacheObject(String var1);

    public abstract void delHashCache(String var1, String... var2);

    public abstract void publish(String var1, String var2);

    public abstract void publish(byte[] var1, byte[] var2);

    public abstract void subscribe(JedisPubSub var1, String var2);

    public abstract void closeCacheClient();

    public static void main(String[] args) {
        RedisCache redisCache = getInstance();
        Set<String> keys = redisCache.getAllKeys();
        Iterator iterator = keys.iterator();

        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            System.out.println(key);
            System.out.println(redisCache.getHashAllFieldCache(key));
            System.out.println();
        }

    }

    static {
        String s = PropertyReader.get("cache.redis.pool.maxTotal", "cache.properties");
        if (!Misc.isStringEmpty(s)) {
            try {
                maxTotal = Integer.valueOf(s).intValue();
            } catch (NumberFormatException var14) {
                ;
            }
        }

        s = PropertyReader.get("cache.redis.pool.maxIdle", "cache.properties");
        if (!Misc.isStringEmpty(s)) {
            try {
                maxIdle = Integer.valueOf(s).intValue();
            } catch (NumberFormatException var13) {
                ;
            }
        }

        s = PropertyReader.get("cache.redis.pool.maxWaitMillis", "cache.properties");
        if (!Misc.isStringEmpty(s)) {
            try {
                maxWaitMillis = Integer.valueOf(s).intValue();
            } catch (NumberFormatException var12) {
                ;
            }
        }

        s = PropertyReader.get("cache.redis.pool.maxRedirections", "cache.properties");
        if (!Misc.isStringEmpty(s)) {
            try {
                maxRedirections = Integer.valueOf(s).intValue();
            } catch (NumberFormatException var11) {
                ;
            }
        }

        if (enableCache) {
            String[] hostArray;
            JedisPoolConfig config;
            if (enableCluster) {
                if (!Misc.isStringEmpty(hosts)) {
                    hostArray = hosts.split(",");
                    Set<HostAndPort> jedisClusterNodes = new HashSet();
                    config = new JedisPoolConfig();
                    config.setMaxTotal(maxTotal);
                    config.setMaxIdle(maxIdle);
                    config.setMaxWaitMillis((long)maxWaitMillis);
                    config.setTestOnBorrow(true);
                    String[] arr$ = hostArray;
                    int len$ = hostArray.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        String hostAndPort = arr$[i$];
                        String[] hp = hostAndPort.split(":");
                        String host = hp[0];
                        int port = Integer.valueOf(hp[1]).intValue();
                        jedisClusterNodes.add(new HostAndPort(host, port));
                        jedisCluster = new JedisCluster(jedisClusterNodes, maxWaitMillis, maxRedirections, config);
                    }
                }

                redisCache = new RedisCacheWithCluster();
            } else {
                hostArray = hosts.split(",");
                String[] hp = hostArray[0].split(":");
                config = new JedisPoolConfig();
                config.setMaxTotal(maxTotal);
                config.setMaxIdle(maxIdle);
                config.setMaxWaitMillis((long)maxWaitMillis);
                config.setTestOnBorrow(true);
                pool = new JedisPool(config, hp[0], Integer.valueOf(hp[1]).intValue());
                redisCache = new RedisCacheWithoutCluster();
            }
        }

    }
}
