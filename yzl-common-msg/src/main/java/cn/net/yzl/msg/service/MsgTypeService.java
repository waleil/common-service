package cn.net.yzl.msg.service;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.msg.model.pojo.MsgTypePo;
import cn.net.yzl.msg.model.vo.MsgTypeVo;

import java.util.List;

/**
 * cn.net.yzl.staff.service.msg
 * 2021/2/5 9:54
 *
 * @author yangxiaopeng
 */
public interface MsgTypeService {
    //新增消息类型
    ComResponse insertMsgType(MsgTypeVo msgTypeVo);
    //修改消息类型
    ComResponse updateMsgType(MsgTypeVo msgTypeVo);

    //根据名称查询消息类型
    ComResponse<MsgTypePo> selectMsgType(String name);

    //查询/编辑消息类型
    ComResponse<List<MsgTypePo>> selectAllMsgType();
    //消息类型的编辑
    ComResponse insertAndUpdateMsgType(List<MsgTypeVo> list);

    //删除消息类型
    ComResponse delMsgType(String msgCode);


}
