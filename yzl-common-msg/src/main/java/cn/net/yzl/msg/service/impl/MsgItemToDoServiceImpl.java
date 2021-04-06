package cn.net.yzl.msg.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.msg.mapper.MsgItemToDoMapper;
import cn.net.yzl.msg.model.vo.MsgItemCountVo;
import cn.net.yzl.msg.model.vo.MsgItemToDoVo;
import cn.net.yzl.msg.service.MsgItemToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * cn.net.yzl.msg.service.impl
 * 2021/2/23 12:45
 *
 * @author yangxiaopeng
 */
@Service
@Slf4j
public class MsgItemToDoServiceImpl implements MsgItemToDoService {

    @Autowired
    private MsgItemToDoMapper msgItemToDoMapper;

    /**
     * 增加待办
     * @param code
     * @param type
     * @param systemCode
     * @param oprUser
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int addMsgToDoInfo(String code,Integer type,Integer systemCode,String oprUser) {
        log.info("增加待办入参参数：{}",String.format("code:%s,type:%d,systemCode:%d,oprUser%s",code,type,systemCode,oprUser));
        MsgItemToDoVo msgItemToDoVo = BeanUtils.instantiateClass(MsgItemToDoVo.class);
        msgItemToDoVo.setCode(code);
        msgItemToDoVo.setType(type);
        msgItemToDoVo.setSystemCode(systemCode);
        msgItemToDoVo.setOprUser(oprUser);
        log.info("【待办管理--增加待办】:{}",JsonUtil.toJsonStr(msgItemToDoVo));
        return msgItemToDoMapper.addMsgToDoInfo(msgItemToDoVo);
    }

    /**
     * 减去待办
     * @param code
     * @param type
     * @param systemCode
     * @param oprUser
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int subMsgToDoInfo(String code,Integer type,Integer systemCode,String oprUser) {
        log.info("减去待办入参参数：{}",String.format("code:%s,type:%d,systemCode:%d,oprUser%s",code,type,systemCode,oprUser));
        MsgItemToDoVo msgItemToDoVo = BeanUtils.instantiateClass(MsgItemToDoVo.class);
        msgItemToDoVo.setCode(code);
        msgItemToDoVo.setType(type);
        msgItemToDoVo.setSystemCode(systemCode);
        msgItemToDoVo.setOprUser(oprUser);
        log.info("【待办管理--减去待办】:{}",JsonUtil.toJsonStr(msgItemToDoVo));
        return msgItemToDoMapper.subMsgToDoInfo(msgItemToDoVo);
    }

    /**
     * 统计待办数量
     * @param type
     * @param systemCode
     * @return
     */
    @Override
    public int getMsgToDoItemCount(Integer type,Integer systemCode) {
        log.info("统计待办数量入参参数：{}",String.format("type:%d,systemCode:%d",type,systemCode));
        MsgItemCountVo msgItemCountVo = BeanUtils.instantiateClass(MsgItemCountVo.class);
        msgItemCountVo.setType(type);
        msgItemCountVo.setSystemCode(systemCode);
        log.info("【待办管理--减去待办】:{}", JsonUtil.toJsonStr(msgItemCountVo));
        return msgItemToDoMapper.getMsgToDoItemCount(msgItemCountVo);
    }

}
