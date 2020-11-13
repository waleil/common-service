package cn.net.yzl.common.util;

import java.util.Calendar;
import java.util.UUID;

/**
 *
 * UUID 工具类
 **/
public class UUIDGenerator {
    /**
     *   时间戳生成
     * @return
     */
    public static String getTimeStr(){
        Calendar cal = Calendar.getInstance();
        String timw = cal.get(Calendar.YEAR)+""
                +(cal.get(Calendar.MONTH)+1)+""
                +cal.get(Calendar.DAY_OF_MONTH)+""
                +cal.get(Calendar.HOUR_OF_DAY)+""
                +cal.get(Calendar.MINUTE)+""
                +cal.get(Calendar.SECOND)+"";
        return timw;
    }


    /**
     * 自动生成32位的UUid，对应数据库的主键code进行插入用。
     * @return
     */
    public static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }


}
