package cn.net.yzl.msg.service;

/**
 * cn.net.yzl.msg.service
 * 2021/2/23 12:44
 *
 * @author yangxiaopeng
 */
public interface MsgItemToDoService {
    /**
     * 2020-2-23 新增待办
     * */
    int addMsgToDoInfo(String code,Integer type,Integer systemCode,String oprUser);

    /**
     * 2020-2-23 减去待办
     * */
    int subMsgToDoInfo(String code,Integer type,Integer systemCode,String oprUser);


    /**
     * 2020-2-23 统计待办数量
     * */
    int getMsgToDoItemCount(Integer type,Integer systemCode);
}
