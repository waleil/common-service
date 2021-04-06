package cn.net.yzl.msg.mapper;

import cn.net.yzl.msg.model.dto.PersonMsgInfoSelectDto;

import cn.net.yzl.msg.model.pojo.MsgInfoPo;
import cn.net.yzl.msg.model.vo.MsgInfoInsertVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoSelectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgInfoMapper {

    //消息新增
    int insertMsgInfo(MsgInfoInsertVo msgInfoVo);

    //个人中心消息列表
    List<PersonMsgInfoSelectDto> selectPersonMsgInfoList(PersonMsgInfoSelectVo personMsgInfoSelectVo);

    //个人中心消息详情
    PersonMsgInfoSelectDto selectPersonMsgInfoDetail(String msgCode);

    //查询定时未发送消息列表
    List<MsgInfoPo> selectMsgInfoTimeList();

}