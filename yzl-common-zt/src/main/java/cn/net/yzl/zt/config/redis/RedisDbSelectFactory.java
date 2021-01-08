package cn.net.yzl.zt.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.InvocationTargetException;

/**
 * 其他选库实现 https://www.jianshu.com/p/9d78cd1c46b6
 *
 *
 * 本类用于创建restTemplate相同配置,但dbIndex不同的RestTemplate, 可以理解为选库
 *
 * @author TimFruit
 * @date 20-1-2 下午2:31
 */
public class RedisDbSelectFactory {


    /**
     * 创建restTemplate相同配置,但dbIndex不同的RestTemplate, 可以理解为选库
     *
     * @param redisTemplate
     * @param dbIndex redis库
     * @return
     */
    public static RedisTemplate selectDb(RedisTemplate redisTemplate, int dbIndex){
        try {
            RedisTemplate dbSelectRedisTemplate=redisTemplate.getClass().getConstructor().newInstance();
            BeanUtils.copyProperties(redisTemplate, dbSelectRedisTemplate);


            RedisConnectionFactory connectionFactory=dbSelectRedisTemplate.getConnectionFactory();

            RedisConnectionFactory dbSelectConnectionFactory=createDbSelectConnectionFactory(connectionFactory, dbIndex);

            dbSelectRedisTemplate.setConnectionFactory(dbSelectConnectionFactory);

            dbSelectRedisTemplate.afterPropertiesSet();
            return dbSelectRedisTemplate;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }


    protected static RedisConnectionFactory createDbSelectConnectionFactory(RedisConnectionFactory connectionFactory, int dbIndex){
        RedisConnectionFactory dbSelectConnectionFactory=null;
        if(connectionFactory instanceof LettuceConnectionFactory){
            dbSelectConnectionFactory= createLettuceDbSelectFactory((LettuceConnectionFactory)connectionFactory, dbIndex);
        }else {
            // 由于通过创建一个连接工厂比较复杂(BeanUtils复制属性有限制, 需要了解连接工厂内部构造), 暂不创建其他连接工厂
            throw new RuntimeException("不能识别类型: "+connectionFactory.getClass());
        }

        return dbSelectConnectionFactory;
    }




    // --------------------------------------
    // lettuceConnectionFactory, 创建后的connection在共享连接下不支持选择库 (connection#select),
    // 调用#setShareNativeConnection(false)后可以选库

    // !!! 注意事项: 使用BeanUtils复制属性, 属性必须添加set，get方法，否则拷贝不成功，但是不报错
    // 由于创建一个相同配置但dbIndex不同的方法比较复杂, 使用前需要仔细测试
    private static LettuceConnectionFactory createLettuceDbSelectFactory(LettuceConnectionFactory connectionFactory, int dbIndex){
        LettuceConnectionFactory dbSelectConnectionFactory=new LettuceDbSelectConnectionFactory(dbIndex);
        BeanUtils.copyProperties(connectionFactory, dbSelectConnectionFactory);

        //构造参数传入的属性(因为没有setter, BeanUtils不能复制的属性)
        final String[] constructProperties=new String[]{"clientConfiguration", "configuration"};
        MyBeanUtils.forceCopyProperties(connectionFactory, dbSelectConnectionFactory, constructProperties);

        dbSelectConnectionFactory.afterPropertiesSet();

        final String[] equalProperties=new String[]{"clientConfiguration", "configuration"};
        final String[] notEqualProperties=new String[]{"client","pool", "connectionProvider","reactiveConnectionProvider"};
        final String[] sameTypeProperties=new String[]{"connectionProvider","reactiveConnectionProvider"};

        MyBeanUtils.assertPropertiesEquals(connectionFactory, dbSelectConnectionFactory, equalProperties);
        MyBeanUtils.assertPropertiesNotEquals(connectionFactory, dbSelectConnectionFactory, notEqualProperties);
        MyBeanUtils.assertSameTypes(connectionFactory, dbSelectConnectionFactory, sameTypeProperties);


        return dbSelectConnectionFactory;
    }

    @Slf4j
    private static class LettuceDbSelectConnectionFactory extends LettuceConnectionFactory{

        private int pointDbIndex;

        public LettuceDbSelectConnectionFactory(int pointDbIndex) {
            this.pointDbIndex = pointDbIndex;
        }

        /**
         * 替换原配置的dbIndex
         * @return
         */
        @Override
        public int getDatabase() {
            log.debug("使用redis库{}",pointDbIndex);
            return pointDbIndex;
        }
    }



}
