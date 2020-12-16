package cn.net.yzl.common.zt.config.redis;//package cn.net.yzl.bi.config.redis;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisSentinelPool;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//public class TestRedis {
//
//    public static void main(String[] args) {
//        //连接池配置
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(10);
//        jedisPoolConfig.setMaxIdle(5);
//        jedisPoolConfig.setMinIdle(5);
////哨兵信息
//        Set<String> sentinels = new HashSet<String>(Arrays.asList(
//                "192.168.32.33:26379",
//                "192.168.32.34:26379",
//                "192.168.32.35:26379"
//        ));
////创建连接池
////mymaster是我们配置给哨兵的服务名称
////sentinels是哨兵信息
////jedisPoolConfig是连接池配置
////abcdefg是连接Redis服务器的密码
//        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig, "YZL123456!");
////获取客户端
//        Jedis jedis = pool.getResource();
////执行两个命令
//        jedis.set("mykey", "myvalue");
//        String myvalue = jedis.get("mykey");
////打印信息
//        System.out.println(myvalue);
//    }
//}
