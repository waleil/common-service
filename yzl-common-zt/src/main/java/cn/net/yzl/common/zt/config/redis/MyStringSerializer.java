package cn.net.yzl.common.zt.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class MyStringSerializer extends StringRedisSerializer {


    private static Logger log = LoggerFactory.getLogger(MyStringSerializer.class);


    private  final String   keyPrefix = "bi:";


    @Override
    public String deserialize(byte[] bytes) {

        String saveKey = new String ( bytes );
        int indexOf = saveKey.indexOf ( keyPrefix );
        if (indexOf > 0) {
            log.info ( "key缺少前缀" );
        } else {
            saveKey = saveKey.substring ( indexOf );
        }
        log.info ( "saveKey:{}",saveKey);
        return (saveKey.getBytes () == null ? null : saveKey);
    }

    @Override
    public byte[] serialize(String string) {
        String key = keyPrefix + string;
        log.info ( "key:{}",key);
        return (key == null ? null : key.getBytes ());
    }


}
