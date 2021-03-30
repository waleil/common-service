package cn.net.yzl.msg.service.impl;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.msg.mapper.MsgTemplateMapper;
import cn.net.yzl.msg.model.pojo.MsgTemplatePo;
import cn.net.yzl.msg.model.vo.MsgTemplateVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoInsertVo;
import cn.net.yzl.msg.service.MsgInfoService;
import cn.net.yzl.msg.service.MsgTemplateService;
import cn.net.yzl.msg.service.YMsgInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * cn.net.yzl.msg.service.impl
 * 2021/2/25 14:13
 *
 * @author yangxiaopeng
 */
@Service
@Slf4j
public class MsgTemplateServiceImpl implements MsgTemplateService {
    @Autowired
    private MsgInfoService msgInfoService;


    @Autowired
    private MsgTemplateMapper msgTemplateMapper;

    @Autowired
    private YMsgInfoService yMsgInfoService;


    /**
     *
     * @param code
     * @return
     */
    @Override
    public ComResponse selectMsgTemplate(String code) {
        MsgTemplatePo msgTemplatePo = msgTemplateMapper.selectMsgTemplate(code);
        if(msgTemplatePo != null)
            return ComResponse.success(msgTemplatePo);
        return ComResponse.fail(225001,"查询失败");
    }

    /**
     * 模板消息新增
     * @param msgTemplateVo
     * @return
     */
    @Override
    public ComResponse addMsgMsgTemplate(MsgTemplateVo msgTemplateVo) {
        if(msgTemplateVo == null && StringUtils.isNotEmpty(msgTemplateVo.getCode())){
            log.info("functionName:{},post:{},params:{}","【消息管理--模板消息新增】","service","msgTemplateVo 参数为空");
            return ComResponse.fail(225002,"参数格式有误，请核实");
        }
        //类型转换
        MsgTemplatePo msgTemplatePo = new MsgTemplatePo();
        BeanUtils.copyProperties(msgTemplateVo,msgTemplatePo);
        //查询模板,生成消息 版本
        MsgTemplatePo object = msgTemplateMapper.selectMsgTemplate(msgTemplateVo.getCode());
        log.info("【返回的消息模板】:{}", JsonUtil.toJsonStr(object));
        if(object==null)
            return ComResponse.fail(225006,"无此模板");
        String content = object.getContent();
        String format = MessageFormat.format(content, msgTemplatePo.getParams());
        msgTemplatePo.setContent(format);
        msgTemplatePo.setType(1);
        //添加入消息信息表
        ComResponse<String> addMsg = yMsgInfoService.addMsg(msgTemplatePo);
        if(addMsg==null)
            return ComResponse.fail(225005,"消息新增失败");
        String code = addMsg.getData();
        PersonMsgInfoInsertVo personMsgInfoInsertVo = new PersonMsgInfoInsertVo();
        personMsgInfoInsertVo.setCreator(msgTemplatePo.getCreator());
        personMsgInfoInsertVo.setMsgCode(code);
        personMsgInfoInsertVo.setUserCode(msgTemplatePo.getUserCode());
        personMsgInfoInsertVo.setSystemCode(msgTemplatePo.getSystemCode());
        List<PersonMsgInfoInsertVo> voList = new ArrayList<>();
        voList.add(personMsgInfoInsertVo);
        log.info("functionName:{},post:{},params:{}","用户信息关联表","service", JsonUtil.toJsonStr(voList));
        msgInfoService.insertPersonMsgInfo(voList);
        return ComResponse.success();

    }

    /**
     * 模板消息批量新增
     * @param msgTemplateVoList
     * @return
     */
    @Override
    public ComResponse insertMsgTemplateBatch(List<MsgTemplateVo> msgTemplateVoList) {
        log.info("functionName:{},post:{},params:{}","【消息管理--模板消息批量新增】","service", JsonUtil.toJsonStr(msgTemplateVoList));
        if(msgTemplateVoList.isEmpty())
            return ComResponse.fail(302001,"参数格式有误，请核实");
        for (MsgTemplateVo msgTemplateVo : msgTemplateVoList) {
            addMsgMsgTemplate(msgTemplateVo);
        }
        return ComResponse.success();
    }
}
