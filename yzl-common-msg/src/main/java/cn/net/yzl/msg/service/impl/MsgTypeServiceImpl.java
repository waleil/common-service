package cn.net.yzl.msg.service.impl;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.msg.mapper.MsgTypeMapper;
import cn.net.yzl.msg.model.pojo.MsgTypePo;
import cn.net.yzl.msg.model.vo.MsgTypeVo;
import cn.net.yzl.msg.service.MsgTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * cn.net.yzl.staff.service.impl.msg
 * 2021/2/5 9:55
 *
 * @author yangxiaopeng
 */
@Service
@Slf4j
public class MsgTypeServiceImpl implements MsgTypeService {
    @Autowired
    private MsgTypeMapper msgTypeMapper;


    /**
     * 新增消息类型
     * @param msgTypeVo
     * @return
     */
    @Override
    public ComResponse insertMsgType(MsgTypeVo msgTypeVo) {
        if(msgTypeVo==null)
            return ComResponse.fail(ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getCode(), ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getMessage());
        MsgTypePo msgTypePo = new MsgTypePo();
        BeanUtils.copyProperties(msgTypeVo,msgTypePo);
        log.info("【新增消息类型--入参】",JsonUtil.toJsonStr(msgTypePo));
        MsgTypePo object = msgTypeMapper.selectMsgType(msgTypePo.getName());
        log.info("【新增消息类型--查询消息类型是否存在】",JsonUtil.toJsonStr(object));
        if(object!=null)
            return ComResponse.fail(23001,"当前信息类型已存在");
        String maxCode = msgTypeMapper.selectMaxCode();
        int msgMaxCode = maxCode == null ? 0 :Integer.parseInt(maxCode) + 1;
        msgTypePo.setCode(String.valueOf(msgMaxCode));
        log.info("【新增消息类型】:{}",JsonUtil.toJsonStr(msgTypePo));
        msgTypeMapper.insertMsgType(msgTypePo);
        return ComResponse.success();
    }

    /**
     * 更新消息类型
     * @param msgTypeVo
     * @return
     */
    @Override
    public ComResponse updateMsgType(MsgTypeVo msgTypeVo) {
        if(msgTypeVo==null)
            return ComResponse.fail(ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getCode(), ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getMessage());
        MsgTypePo msgTypePo = new MsgTypePo();
        BeanUtils.copyProperties(msgTypeVo,msgTypePo);
        log.info("【更新消息类型--入参】",JsonUtil.toJsonStr(msgTypePo));
        MsgTypePo object = msgTypeMapper.selectMsgType(msgTypePo.getName());
        log.info("【更新消息类型】--查询名称是否存在",JsonUtil.toJsonStr(object));
        if(object!=null){
            if(object.getCode()==msgTypePo.getCode()){
                return ComResponse.success();
            }else{
                return ComResponse.fail(23001,"当前信息类型已存在");
            }

        }
        Date date = new Date();
        msgTypePo.setUpdateTime(date);
        log.info("【更新消息类型】",JsonUtil.toJsonStr(msgTypePo));
        msgTypeMapper.updateMsgType(msgTypePo);
        return ComResponse.success();
    }

    /**
     * 查询单个消息类型
     * @param name
     * @return
     */
    @Override
    public ComResponse<MsgTypePo> selectMsgType(String name) {
        MsgTypePo msgTypePo = msgTypeMapper.selectMsgType(name);
        log.info("【查询单个消息类型入参参数】name:{}，【根据名称查询查询单个消息类型】msgTypePo:{}",JsonUtil.toJsonStr(msgTypePo),name);
        if(msgTypePo==null)
            return ComResponse.fail(ResponseCodeEnums.NO_DATA_CODE.getCode(), ResponseCodeEnums.NO_DATA_CODE.getMessage());
        return ComResponse.success(msgTypePo);
    }

    /**
     * 查询所有消息类型
     * @return
     */
    @Override
    public ComResponse<List<MsgTypePo>> selectAllMsgType() {
        List<MsgTypePo> msgTypePos = msgTypeMapper.selectAllMsgType();
        log.info("【查询所有消息类型--返回集合】",JsonUtil.toJsonStr(msgTypePos));
        if(msgTypePos.isEmpty())
            return ComResponse.success();
        return ComResponse.success(msgTypePos);
    }

    /**
     * 新增/编辑消息类型
     * @param list
     * @return
     */
    @Override
    public ComResponse insertAndUpdateMsgType(List<MsgTypeVo> list) {
        //判断参数为空
        if(list.isEmpty())
            return ComResponse.success();
        //转化数据类型
        List<MsgTypePo> msgList = new ArrayList<>();
        for (MsgTypeVo msgTypeVo : list) {
            MsgTypePo msgTypePo = new MsgTypePo();
            BeanUtils.copyProperties(msgTypeVo,msgTypePo);
            msgList.add(msgTypePo);
        }
        log.info("functionName:{},post:{},params:{}","【新增/编辑消息类型入参参数】","service", JsonUtil.toJsonStr(list));
        //创建新增和修改队列
        List<MsgTypePo> insertList = new ArrayList<>();
        List<MsgTypePo> updateList = new ArrayList<>();
        //创建Set集合
        Set<String> setList = new HashSet<>();
        //创建时间
        Date date = new Date();

        //遍历结合
        for (MsgTypePo msgTypePo : msgList) {
            MsgTypePo object = msgTypeMapper.selectMsgType(msgTypePo.getName());
            log.info("【新增/编辑消息类型-查询类型名称】:{}",JsonUtil.toJsonStr(object));
            if(object!=null){
                if(msgTypePo.getId()!=null && msgTypePo.getId()==object.getId()){
                    if(msgTypePo.getName().isEmpty())
                        return ComResponse.fail(ResponseCodeEnums.REPEAT_STORE_CODE_ERROR.getCode(), ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getMessage());
                    msgTypePo.setUpdateTime(date);
                    updateList.add(msgTypePo);
                }else{
                    return ComResponse.fail(23002,"当前类型已存在，请重新输入");
                }
            }else{
                if(msgTypePo.getId()!=null){
                    if(msgTypePo.getName().isEmpty())
                        return ComResponse.fail(ResponseCodeEnums.REPEAT_STORE_CODE_ERROR.getCode(), ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getMessage());
                    msgTypePo.setUpdateTime(date);
                    updateList.add(msgTypePo);
                }else{
                    if(msgTypePo.getName().isEmpty())
                        return ComResponse.fail(ResponseCodeEnums.REPEAT_STORE_CODE_ERROR.getCode(), ResponseCodeEnums.PARAMS_EMPTY_ERROR_CODE.getMessage());
                    String maxCode = msgTypeMapper.selectMaxCode();
                    int msgMaxCode = 0;
                    if(insertList.size()>0){
                        msgMaxCode = StringUtils.isEmpty(maxCode) ? 1 :Integer.parseInt(maxCode) + insertList.size()+1;
                    }else{
                        msgMaxCode = StringUtils.isEmpty(maxCode) ? 1 :Integer.parseInt(maxCode) + 1;
                    }

                    msgTypePo.setCode(String.valueOf(msgMaxCode));
                    insertList.add(msgTypePo);
                }
            }
            setList.add(msgTypePo.getName());
        }
        log.info("集合状态比较params:{}",String.format("【新增/编辑消息类型】SET集合list长度:%d,msgList集合长度:%d",setList.size(),msgList.size()));
        if(setList.size()==msgList.size()){
            if(insertList.size()>0){
                log.info("【新增/编辑消息类型--新增消息类型入参；{}】","service", JsonUtil.toJsonStr(insertList));
                msgTypeMapper.insertMsgTypeList(insertList);
            }
            if(updateList.size()>0){
                log.info("【新增/编辑消息类型--更新消息类型入参；{}】","service", JsonUtil.toJsonStr(insertList));
                msgTypeMapper.updateMsgTypeList(updateList);
            }
        }else{
            return ComResponse.fail(23003,"编辑和新增中存在重复类型，请重新输入");
        }
        return ComResponse.success();
    }


    /**
     * 删除消息类型
     * @param msgCode
     * @return
     */
    @Override
    public ComResponse delMsgType(String msgCode) {
        int count = msgTypeMapper.selectCount(msgCode);
        if(count>0)
            return ComResponse.fail(23004,"该类型已被使用，无法删除！");
        int num = msgTypeMapper.delMsgType(msgCode);
        log.info("【删除消息类型入参参数以及返回值结果】:{}",String.format("入参msgCode:%s,删除结果num:%d",msgCode,num));
        if(num > 0)
            return ComResponse.success();
        return ComResponse.fail(23005,"删除失败");
    }
}
