package cn.net.yzl.msg.service;

import cn.net.yzl.common.entity.Page;
import cn.net.yzl.msg.model.dto.PersonMsgInfoSelectDto;
import cn.net.yzl.msg.model.vo.MsgInfoInsertVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoEditVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoInsertVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoSelectVo;

import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 11:47
 * @title: MsgInfoService
 * @description:
 */
public interface MsgInfoService {

    //消息新增
    void insertMsgInfo(MsgInfoInsertVo msgInfoVo);

    //个人中心消息列表
    Page<PersonMsgInfoSelectDto> selectPersonMsgInfoList(PersonMsgInfoSelectVo personMsgInfoSelectVo);

    //个人中心消息详情
    PersonMsgInfoSelectDto selectPersonMsgInfoDetail(String msgCode);

    //个人中心消息新增
    void insertPersonMsgInfo(List<PersonMsgInfoInsertVo> listVo);

    //个人中心消息删除
    int deletePersonMsgInfo(PersonMsgInfoEditVo vo);

    //个人中心消息已读
    int updatePersonMsgInfo(PersonMsgInfoEditVo vo);

    //获取在职的全部员工编号
    List<String> getAllStaffNoList();

}
