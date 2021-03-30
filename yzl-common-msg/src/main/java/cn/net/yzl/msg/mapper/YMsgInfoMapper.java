package cn.net.yzl.msg.mapper;

import cn.net.yzl.msg.model.dto.MsgInfoDto;
import cn.net.yzl.msg.model.pojo.MsgInfoPo;
import cn.net.yzl.msg.model.pojo.MsgTemplatePo;
import cn.net.yzl.msg.model.vo.MsgInfoPageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * cn.net.yzl.staff.mapper.msg
 * 2021/2/5 14:28
 *
 * @author yangxiaopeng
 */
@Repository
public interface YMsgInfoMapper {
    //2020-2-05
    //分页查询
    List<MsgInfoPo> selectMsgInfoPageList(MsgInfoPageVo msgInfoPageVo);

    //撤回
    int rollback(@Param(value = "code") String code);
    //查询消息员工关联表总数
    int selectMsgCount(@Param(value = "msgCode") String msgCode);
    //查询未读取消息的员工数
    int selectMsgCountIsRead(@Param(value = "msgCode") String msgCode);

    //取消发送
    int stopSending(@Param(value = "code") String code);

    //查询消息详情
    MsgInfoDto selectMsgItem(@Param(value = "code") String code);

    //2020-2-07
    //修改消息状态
    int updateMsgStatus(@Param(value = "code") String code, @Param(value = "msgStatus") Integer msgStatus, @Param(value = "sendStatus") Integer sendStatus);

    MsgInfoPo selectMsgOne(@Param(value = "code") String code);

    //查询部分未读用户的在用户消息关系表中的id
    List<Integer> selectMsgUserIsNotRead(String code);

    //点击撤回，删除部分用户未读的消息
    int deleteMsgUserPartNotRead(@Param(value = "notReadList")List<Integer> notReadList);

    //2020-02-25
    int addMsg(MsgTemplatePo msgTemplatePo);

}
