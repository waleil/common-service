package cn.net.yzl.operatelogger.enums;

/**
 * @ClassName BusinessType
 * @Descripion 业务操作类型 增删改等
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
public enum BusinessType
{
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 查询
     */
    SELECT,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,
    
    /**
     * 清空
     */
    CLEAN,
}
