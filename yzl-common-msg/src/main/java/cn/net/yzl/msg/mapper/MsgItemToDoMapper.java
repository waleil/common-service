package cn.net.yzl.msg.mapper;

import cn.net.yzl.msg.model.vo.MsgItemCountVo;
import cn.net.yzl.msg.model.vo.MsgItemToDoVo;
import org.springframework.stereotype.Repository;

/**
 * cn.net.yzl.msg.mapper
 * 2021/2/23 11:50
 *
 * @author yangxiaopeng
 */
@Repository
public interface MsgItemToDoMapper {
    /**
     * 2020-2-23 新增待办
     * */
    int addMsgToDoInfo(MsgItemToDoVo msgItemToDoVo);

    /**
     * 2020-2-23 减去待办
     * */
    int subMsgToDoInfo(MsgItemToDoVo msgItemToDoVo);

    /**
     * 2020-2-23 统计待办数量
     * */
    int getMsgToDoItemCount(MsgItemCountVo msgItemCountVo);

}
