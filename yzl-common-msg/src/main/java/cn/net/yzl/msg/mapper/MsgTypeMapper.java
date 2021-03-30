package cn.net.yzl.msg.mapper;


import cn.net.yzl.msg.model.pojo.MsgTypePo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * cn.net.yzl.staff.mapper.msg
 * 2021/2/5 9:42
 *
 * @author yangxiaopeng
 */
@Repository
public interface MsgTypeMapper {
    //2020-2-05
    //新增消息类型
    int insertMsgType(MsgTypePo msgTypePo);
    //修改消息类型
    int updateMsgType(MsgTypePo msgTypePo);
    //根据名称查询消息类型
    MsgTypePo selectMsgType(String name);
    //查询所有的消息类型
    List<MsgTypePo> selectAllMsgType();
    //批量添加消息类型
    int insertMsgTypeList(List<MsgTypePo> insertList);
    //批量修改消息类型
    int updateMsgTypeList(List<MsgTypePo> updateList);

    //查询消息类型中最大的编号
    String selectMaxCode();


    //2020-2-07
    //删除消息类型
    int delMsgType(String msgCode);


}
