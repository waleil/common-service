package cn.net.yzl.msg.service;

import cn.net.yzl.common.entity.Page;
import cn.net.yzl.msg.model.dto.MsgToDoDto;
import cn.net.yzl.msg.model.dto.MsgToDoListDto;
import cn.net.yzl.msg.model.vo.MsgToDoInsertVo;
import cn.net.yzl.msg.model.vo.MsgToDoSelectVo;
import cn.net.yzl.msg.model.vo.MsgToDoUpdateVo;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 11:47
 * @title: MsgToDoService
 * @description:
 */
public interface MsgToDoService {

    //待办新增
    void insertMsgToDo(MsgToDoInsertVo msgToDoInsertVo);

    //待办修改
    int updateMsgToDo(MsgToDoUpdateVo msgToDoUpdateVo);

    //待办新增
    int deleteMsgToDo(String code);

    //待办列表
    Page<MsgToDoDto> selectMsgToDoList(MsgToDoSelectVo msgToDoSelectVo);

    //待办列表(日期格式)
    Page<MsgToDoListDto> selectMsgToDoMap(MsgToDoSelectVo msgToDoSelectVo);

    //待办详情
    MsgToDoDto selectMsgToDoDetail(String code);

}
