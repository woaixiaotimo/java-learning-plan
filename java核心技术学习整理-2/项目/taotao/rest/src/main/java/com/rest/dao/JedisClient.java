package com.rest.dao;

public interface JedisClient {

    String get(String key);
    String set(String key, String value);
    //返回给定字段的值。如果给定的字段或 key 不存在时，返回 nil 。
    String hget(String hkey, String key);
    /*
    * Redis Hset 命令用于为哈希表中的字段赋值 。
    * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
    * 如果字段已经存在于哈希表中，旧值将被覆盖。
    * */
    long hset(String hkey, String key, String value);
    long del(String key);
    long hdel(String hkey, String key);
    /*
    * 命令将 key 中储存的数字值增一。
    * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
    * */
    long incr(String key);
    /*
    * 用于设置 key 的过期时间。key 过期后将不再可用。
    * */
    long expire(String key, int second);
    /*
    * 命令以秒为单位返回 key 的剩余过期时间。
    * */
    long ttl(String key);

}
