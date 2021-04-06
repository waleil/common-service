package cn.net.yzl.msg.service;

import cn.net.yzl.msg.model.vo.PersonMsgInfoInsertVo;

import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/3/4 2:18
 * @title: PersonMsgInfoAsyncService
 * @description:
 */
public interface MsgInfoAsyncService {

    //个人中心消息新增
    void insertPersonMsgInfoAsync(List<PersonMsgInfoInsertVo> listVo);
}
