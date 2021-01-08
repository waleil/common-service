package cn.net.yzl.pm.model.constant;

/**
 * redis的key前缀
 **/
public class RedisConstant {


    public static final long LONG_EXPIRE_TIME = 10*1000;

    /**
     *  缓存失效时间
     */
    public static final long EXPIRE_TIME = 12*60*60*1000;

    public static final long MINIUTE_EXPIRE_TIME = 60*1000;

    public static final long WEEK_EXPIRE_TIME = 7*24*60*60*1000;


    /**
     * 目标任务list
     */
    public static final String TASK_LIST = "taskList";


}
