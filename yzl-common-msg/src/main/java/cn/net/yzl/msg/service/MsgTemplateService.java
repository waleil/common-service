package cn.net.yzl.msg.service;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.msg.model.pojo.MsgTemplatePo;
import cn.net.yzl.msg.model.vo.MsgTemplateVo;

import java.util.List;

/**
 * cn.net.yzl.msg.service
 * 2021/2/25 14:08
 *
 * @author yangxiaopeng
 */
public interface MsgTemplateService {
    //查询模板
    ComResponse<MsgTemplatePo> selectMsgTemplate(String code);

    //插入模板
    ComResponse addMsgMsgTemplate(MsgTemplateVo msgTemplateVo);

    //2020-3-02
    ComResponse insertMsgTemplateBatch(List<MsgTemplateVo> msgTemplateVoList);
}
