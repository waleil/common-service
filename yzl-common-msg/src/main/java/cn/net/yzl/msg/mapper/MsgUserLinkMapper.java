package cn.net.yzl.msg.mapper;

import cn.net.yzl.msg.model.vo.PersonMsgInfoEditVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoInsertVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgUserLinkMapper {

    //个人中心消息新增
    int insertPersonMsgInfo(List<PersonMsgInfoInsertVo> listVo);

    //个人中心消息删除
    int deletePersonMsgInfo(PersonMsgInfoEditVo vo);

    //个人中心消息已读
    int updatePersonMsgInfo(PersonMsgInfoEditVo vo);

    //查询所有员工号
    //List<String> selectStaffList();

}