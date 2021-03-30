package cn.net.yzl.msg.mapper;

import cn.net.yzl.msg.model.dto.MsgToDoDto;
import cn.net.yzl.msg.model.vo.MsgToDoInsertVo;
import cn.net.yzl.msg.model.vo.MsgToDoSelectVo;
import cn.net.yzl.msg.model.vo.MsgToDoUpdateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgToDoMapper {

    //待办新增
    int insertMsgToDo(MsgToDoInsertVo msgToDoInsertVo);

    //待办修改
    int updateMsgToDo(MsgToDoUpdateVo msgToDoUpdateVo);

    //待办删除
    int deleteMsgToDo(@Param("code") String code);

    //待办列表
    List<MsgToDoDto> selectMsgToDoList(MsgToDoSelectVo msgToDoSelectVo);

    //待办详情
    MsgToDoDto selectMsgToDoDetail(@Param("code") String code);

    //标记为已提醒
    int updateMsgToDoStatus(@Param("code") String code);

}