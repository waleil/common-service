package cn.net.yzl.msg.mapper;

import cn.net.yzl.msg.model.pojo.MsgTemplatePo;
import org.springframework.stereotype.Repository;

/**
 * cn.net.yzl.msg.mapper
 * 2021/2/25 13:35
 *
 * @author yangxiaopeng
 */
@Repository
public interface MsgTemplateMapper {
    //2020-2-25
    //查询模板
    MsgTemplatePo selectMsgTemplate(String code);

    //插入模板
    int addMsgMsgTemplate(MsgTemplatePo msgTemplatePo);

}
