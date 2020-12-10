package cn.net.yzl.operatelogger.service;


import cn.net.yzl.operatelogger.common.IpUtil;
import cn.net.yzl.operatelogger.model.OperateLogModel;
import cn.net.yzl.operatelogger.model.UserModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName OperateLogService
 * @Descripion 需要实现的接口
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
public interface OperateLogService {
    /**
     * 转化用户行为日志(一般存储到数据库可视化展示) 必须重写
     * @param operateLogModel
     * @return
     */
    int covertOperateLog(OperateLogModel operateLogModel);

    /**
     * 打印操作日志 必须重写
     * @param operateLogModel
     */
    default void printOperateLog(OperateLogModel operateLogModel){
        System.out.println(operateLogModel);
    };

    /**
     * 获取用户
     * @param params 选择参数传递  必须重写
     * @return
     */
    UserModel getUser(String ... params);

    /**
     * 默认实现request获取IP方法 建议重写
     * @param request
     * @return
     */
    default String getIp(HttpServletRequest request){
        return IpUtil.getRemoteIpByServletRequest(request , false);
    }
}
