package cn.net.yzl.msg.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.msg.mapper.MsgInfoMapper;
import cn.net.yzl.msg.mapper.MsgToDoMapper;
import cn.net.yzl.msg.mapper.MsgUserLinkMapper;
import cn.net.yzl.msg.mapper.YMsgInfoMapper;
import cn.net.yzl.msg.model.dto.MsgToDoDto;
import cn.net.yzl.msg.model.dto.MsgToDoListDto;
import cn.net.yzl.msg.model.vo.*;
import cn.net.yzl.msg.service.MsgToDoService;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 11:45
 * @title: MsgToDoServiceImpl
 * @description:
 */
@Service
public class MsgToDoServiceImpl implements MsgToDoService {

    @Autowired
    private MsgToDoMapper msgToDoMapper;
    @Autowired
    private MsgInfoMapper msgInfoMapper;
    @Autowired
    private MsgUserLinkMapper msgUserLinkMapper;
    @Autowired
    private YMsgInfoMapper yMsgInfoMapper;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void insertMsgToDo(MsgToDoInsertVo msgToDoInsertVo) {
        String todoCode = IdUtil.simpleUUID();
        msgToDoInsertVo.setCode(todoCode);
        Date remindTime = msgToDoInsertVo.getRemindTime();
        //插入待办表
        msgToDoMapper.insertMsgToDo(msgToDoInsertVo);

        MsgInfoInsertVo msgInfoInsertVo = BeanUtils.instantiateClass(MsgInfoInsertVo.class);
        String code = IdUtil.simpleUUID();
        msgInfoInsertVo.setCode(code);
        msgInfoInsertVo.setTitle("待办提醒");
        msgInfoInsertVo.setContent(msgToDoInsertVo.getDetail());
        msgInfoInsertVo.setFileUrl("[]");
        msgInfoInsertVo.setNoticeMode(2);
        msgInfoInsertVo.setSendMode(2);
        msgInfoInsertVo.setSendStatus(0);
        msgInfoInsertVo.setTypeCode("1");
        msgInfoInsertVo.setTarget(1);
        msgInfoInsertVo.setSendUser("-1000");
        msgInfoInsertVo.setSendName("系统");
        msgInfoInsertVo.setSendTime(remindTime);
        //插入消息表
        msgInfoMapper.insertMsgInfo(msgInfoInsertVo);
        if(remindTime.after(new Date())){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Integer isRemind = msgToDoMapper.selectMsgToDoDetail(todoCode).getIsRemind();
                    //判断是否已提醒
                    if(isRemind==0){
                        List<PersonMsgInfoInsertVo> voList = new ArrayList<>();
                        PersonMsgInfoInsertVo vo = BeanUtils.instantiateClass(PersonMsgInfoInsertVo.class);
                        vo.setMsgCode(code);
                        vo.setUserCode(msgToDoInsertVo.getCreateUser());
                        vo.setCreator("Admin");
                        voList.add(vo);
                        //插入消息人员关联表
                        msgUserLinkMapper.insertPersonMsgInfo(voList);
                        yMsgInfoMapper.updateMsgStatus(code,null,1);
                        //修改待办为已提醒
                        msgToDoMapper.updateMsgToDoStatus(todoCode);
                    }
                    timer.cancel();
                }
            }, remindTime);
        }
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updateMsgToDo(MsgToDoUpdateVo msgToDoUpdateVo) {
        return msgToDoMapper.updateMsgToDo(msgToDoUpdateVo);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int deleteMsgToDo(String code) {
        return msgToDoMapper.deleteMsgToDo(code);
    }

    @Override
    public MsgToDoDto selectMsgToDoDetail(String code) {
        return msgToDoMapper.selectMsgToDoDetail(code);
    }

    @Override
    public Page<MsgToDoDto> selectMsgToDoList(MsgToDoSelectVo msgToDoSelectVo) {
        //开启分页
        Integer pageNo = msgToDoSelectVo.getPageNo();
        Integer pageSize = msgToDoSelectVo.getPageSize();
        PageHelper.startPage(pageNo==null?1:pageNo, pageSize==null?10:pageSize);
        List<MsgToDoDto> list = msgToDoMapper.selectMsgToDoList(msgToDoSelectVo);
        Page<MsgToDoDto> pageInfo = AssemblerResultUtil.resultAssembler(list);
        return pageInfo;
    }

    @Override
    public Page<MsgToDoListDto> selectMsgToDoMap(MsgToDoSelectVo msgToDoSelectVo) {
        List<MsgToDoListDto> lis = new ArrayList<>();
        Set<String> dateSet = new HashSet<>();
        List<MsgToDoDto> list = msgToDoMapper.selectMsgToDoList(msgToDoSelectVo);
        for (MsgToDoDto dto : list) {
            String date = dto.getRemindTime().substring(0,10);
            dateSet.add(date);
        }
        for (String str : dateSet) {
            MsgToDoListDto msgToDoListDto = BeanUtils.instantiateClass(MsgToDoListDto.class);
            msgToDoListDto.setDate(str);
            List<MsgToDoDto> li = new ArrayList<>();
            for (MsgToDoDto dto : list) {
                String date = dto.getRemindTime().substring(0,10);
                if(str.equals(date)){
                    li.add(dto);
                }
            }
            msgToDoListDto.setList(li);
            lis.add(msgToDoListDto);
        }
        //开启分页
        Integer pageNo = msgToDoSelectVo.getPageNo();
        Integer pageSize = msgToDoSelectVo.getPageSize();
        PageHelper.startPage(pageNo==null?1:pageNo, pageSize==null?10:pageSize);
        Page<MsgToDoListDto> pageInfo = AssemblerResultUtil.resultAssembler(lis);
        return pageInfo;
    }

}
