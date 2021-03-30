package cn.net.yzl.msg.service;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.msg.model.dto.MsgInfoDto;
import cn.net.yzl.msg.model.dto.PartStaff;
import cn.net.yzl.msg.model.pojo.MsgTemplatePo;
import cn.net.yzl.msg.model.vo.MsgInfoPageVo;
import cn.net.yzl.msg.model.vo.MsgTemplateVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * cn.net.yzl.staff.service.msg
 * 2021/2/5 14:58
 *
 * @author yangxiaopeng
 */
public interface YMsgInfoService {
    ComResponse<Page<MsgInfoDto>> selectMsgInfoPageList(MsgInfoPageVo msgInfoPageVo);

    ComResponse rollback(String msgCode);

    ComResponse stopSending(String code);

    ComResponse<MsgInfoDto> selectMsgItem(String msgCode);

    ComResponse<cn.net.yzl.staff.dto.common.FileDto> uploadFile(MultipartFile file);

    void downloadFile(String str, HttpServletResponse response) throws IOException;

    ComResponse updateMsgStatus(String code, Integer msgStatus, Integer sendStatus);

    ComResponse<Page<PartStaff>> selectDepartStaff(Integer pageNo, Integer pageSize, String code);

    ComResponse<String> addMsg(MsgTemplatePo msgTemplatePo);

    ComResponse sendSysMsgInfo(MsgTemplateVo msgTemplateVo);

    ComResponse insertMsgTemplateBatch(List<MsgTemplateVo> msgTemplateVoList);
}
