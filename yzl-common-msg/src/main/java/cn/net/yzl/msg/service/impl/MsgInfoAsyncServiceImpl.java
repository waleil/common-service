package cn.net.yzl.msg.service.impl;

import cn.net.yzl.msg.mapper.MsgUserLinkMapper;
import cn.net.yzl.msg.model.vo.PersonMsgInfoInsertVo;
import cn.net.yzl.msg.service.MsgInfoAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/3/4 2:20
 * @title: PersonMsgInfoAsyncServiceImpl
 * @description:
 */
@Service
public class MsgInfoAsyncServiceImpl implements MsgInfoAsyncService {

    @Autowired
    private MsgUserLinkMapper msgUserLinkMapper;

    @Async
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void insertPersonMsgInfoAsync(List<PersonMsgInfoInsertVo> listVo) {
        msgUserLinkMapper.insertPersonMsgInfo(listVo);
    }

}
