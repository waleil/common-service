package cn.net.yzl.msg.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.msg.feign.EhrFeignClientService;
import cn.net.yzl.msg.mapper.MsgInfoMapper;
import cn.net.yzl.msg.mapper.MsgUserLinkMapper;
import cn.net.yzl.msg.mapper.YMsgInfoMapper;
import cn.net.yzl.msg.model.dto.PersonMsgInfoSelectDto;
import cn.net.yzl.msg.model.vo.MsgInfoInsertVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoEditVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoInsertVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoSelectVo;
import cn.net.yzl.msg.service.MsgInfoService;
import cn.net.yzl.msg.service.MsgInfoAsyncService;
import cn.net.yzl.staff.dto.StaffDetailsDto;
import cn.net.yzl.staff.dto.StaffListDto;
import cn.net.yzl.staff.vo.StaffParamsVO;
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
 * @title: MsgInfoServiceImpl
 * @description:
 */
@Service
public class MsgInfoServiceImpl implements MsgInfoService {

    @Autowired
    private MsgInfoMapper msgInfoMapper;
    @Autowired
    private MsgUserLinkMapper msgUserLinkMapper;
    @Autowired
    private YMsgInfoMapper yMsgInfoMapper;
    @Autowired
    private EhrFeignClientService ehrFeignClientService;
    @Autowired
    private MsgInfoAsyncService msgInfoAsyncService;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void insertMsgInfo(MsgInfoInsertVo msgInfoVo) {
        List<String> staffNoList = getAllStaffNoList();
        //获取当前操作人名称
        ComResponse<StaffDetailsDto> comDto = ehrFeignClientService.getDetailsByNo(msgInfoVo.getSendUser());
        String sendName = comDto.getData().getName();

        Integer target = msgInfoVo.getTarget();
        Integer sendMode = msgInfoVo.getSendMode();
        String createUser = msgInfoVo.getSendUser();
        Date sendTime = msgInfoVo.getSendTime();

        String code = IdUtil.simpleUUID();
        msgInfoVo.setCode(code);
        msgInfoVo.setSendName(sendName);

        List<String> list = new ArrayList<>();
        List<PersonMsgInfoInsertVo> voList = new ArrayList<>();
        //1:实时 2:定时
        if(sendMode==1){
            msgInfoVo.setSendTime(new Date());
            //0:全部员工 1:部分员工
            if(target==0){
                list = staffNoList;
            }else if(target==1){
                list = msgInfoVo.getUserCodeList();
                msgInfoVo.setTargetList(JsonUtil.toJsonStr(msgInfoVo.getUserCodeList()));
            }
            for (String str : list) {
                PersonMsgInfoInsertVo vo = BeanUtils.instantiateClass(PersonMsgInfoInsertVo.class);
                vo.setMsgCode(code);
                vo.setUserCode(str);
                vo.setCreator(createUser);
                voList.add(vo);
            }
            //插入消息表
            msgInfoMapper.insertMsgInfo(msgInfoVo);
            //插入消息人员关联表
            msgInfoAsyncService.insertPersonMsgInfoAsync(voList);
        }else if(sendMode==2){
            //0:全部员工 1:部分员工
            if(target==0){
                list = staffNoList;
            }else if(target==1){
                list = msgInfoVo.getUserCodeList();
                msgInfoVo.setTargetList(JsonUtil.toJsonStr(msgInfoVo.getUserCodeList()));
            }
            for (String str : list) {
                PersonMsgInfoInsertVo vo = BeanUtils.instantiateClass(PersonMsgInfoInsertVo.class);
                vo.setMsgCode(code);
                vo.setUserCode(str);
                vo.setCreator(createUser);
                voList.add(vo);
            }
            //插入消息表
            msgInfoMapper.insertMsgInfo(msgInfoVo);
            if(sendTime.after(new Date())){
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Integer sendStatus = yMsgInfoMapper.selectMsgOne(code).getSendStatus();
                        //判断发送状态是否待发送
                        if(sendStatus==0){
                            //插入消息人员关联表
                            msgInfoAsyncService.insertPersonMsgInfoAsync(voList);
                            yMsgInfoMapper.updateMsgStatus(code,null,1);
                        }
                        timer.cancel();
                    }
                }, sendTime);
            }
        }
    }

    @Override
    public Page<PersonMsgInfoSelectDto> selectPersonMsgInfoList(PersonMsgInfoSelectVo personMsgInfoSelectVo) {
        //开启分页
        Integer pageNo = personMsgInfoSelectVo.getPageNo();
        Integer pageSize = personMsgInfoSelectVo.getPageSize();
        PageHelper.startPage(pageNo==null?1:pageNo, pageSize==null?10:pageSize);
        List<PersonMsgInfoSelectDto> list = msgInfoMapper.selectPersonMsgInfoList(personMsgInfoSelectVo);
        Page<PersonMsgInfoSelectDto> pageInfo = AssemblerResultUtil.resultAssembler(list);
        return pageInfo;
    }

    @Override
    public PersonMsgInfoSelectDto selectPersonMsgInfoDetail(String msgCode) {
        return msgInfoMapper.selectPersonMsgInfoDetail(msgCode);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void insertPersonMsgInfo(List<PersonMsgInfoInsertVo> listVo) {
        msgUserLinkMapper.insertPersonMsgInfo(listVo);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int deletePersonMsgInfo(PersonMsgInfoEditVo vo) {
        return msgUserLinkMapper.deletePersonMsgInfo(vo);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updatePersonMsgInfo(PersonMsgInfoEditVo vo) {
        return msgUserLinkMapper.updatePersonMsgInfo(vo);
    }

    @Override
    public List<String> getAllStaffNoList(){
        //获取在职的全部员工编号
        List<String> staffNoList = new ArrayList<>();
        StaffParamsVO staffParamsVO = new StaffParamsVO();
        staffParamsVO.setPageNo(1);
        staffParamsVO.setPageSize(50000);
        staffParamsVO.setWorkStatusCode(1);//在职
        ComResponse<Page<StaffListDto>> com = ehrFeignClientService.getListByParams(staffParamsVO);
        List<StaffListDto> dtoList = com.getData().getItems();
        for (StaffListDto dto : dtoList) {
            staffNoList.add(dto.getStaffNo());
        }
        return staffNoList;
    }

}
