package cn.net.yzl.zt.model.constant;

/**
 * redis的key前缀
 **/
public class RedisConstant {


    public static final long LONG_EXPIRE_TIME = 10*1000;

    /**
     *  缓存失效时间
     */
    public static final long EXPIRE_TIME = 12*60*60*1000;

    public static final long SECOND_THREE_EXPIRE_TIME = 60*60*24*3;//三天过期时间

    public static final long SECOND_SEVEN_EXPIRE_TIME = 60*60*24*7;//七天过期时间

    public static final long MINIUTE_EXPIRE_TIME = 60*1000;

    public static final long WEEK_EXPIRE_TIME = 7*24*60*60*1000;



    /**
     * 街道list
     */
    public static final String STREET_LIST = "streetList";

    /**
     * 区域list
     */
    public static final String AREA_LIST = "areaList";

    /**
     * 城市list
     */
    public static final String CITY_LIST = "cityList";

    /**
     * 民族list
     */
    public static final String NATION_LIST = "nationList";

    /**
     * 省list
     */
    public static final String PROVINCE_LIST = "provinceList";

    /**
     * 大区list
     */
    public static final String REGION_LIST = "regionList";

    /**
     * 大区省list
     */
    public static final String REGIONPROVINCE_LIST = "regionProvinceList";

    /**
     * 国家list
     */
    public static final String COUNTRY_LIST = "countryList";
}
