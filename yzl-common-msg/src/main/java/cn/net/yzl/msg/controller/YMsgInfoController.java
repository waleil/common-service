package cn.net.yzl.msg.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.msg.feign.EhrFeignClientService;
import cn.net.yzl.msg.model.dto.MsgInfoDto;
import cn.net.yzl.msg.model.dto.PartStaff;
import cn.net.yzl.msg.model.pojo.MsgTypePo;
import cn.net.yzl.msg.model.vo.MsgInfoPageVo;
import cn.net.yzl.msg.model.vo.MsgTemplateVo;
import cn.net.yzl.msg.model.vo.MsgTypeVo;
import cn.net.yzl.msg.service.MsgTypeService;
import cn.net.yzl.msg.service.YMsgInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * cn.net.yzl.staff.controller.msg
 * 2021/2/5 15:08
 *
 * @author yangxiaopeng
 */

@RestController
@RequestMapping("/msg")
@Slf4j
@Api(value = "消息配置", tags = {"消息配置"})
public class YMsgInfoController {
    @Autowired
    private YMsgInfoService ymsgInfoService;
    @Autowired
    private MsgTypeService msgTypeService;
    @Autowired
    private EhrFeignClientService ehrFeignClientService;

    /**
     * 消息列表=======================================================================
     * */

    @ApiOperation(value = "分页查询消息信息", notes = "分页查询消息信息")
    @PostMapping("/v1/selectMsgInfoPageList")
    public ComResponse<Page<MsgInfoDto>> selectMsgInfoPageList(@RequestBody MsgInfoPageVo msgInfoPageVo, HttpServletRequest request){
        String userNo = request.getHeader("userNo");
        log.info("userNo:{}",userNo);
        msgInfoPageVo.setUserNo(userNo);
        return ymsgInfoService.selectMsgInfoPageList(msgInfoPageVo);
    }

    @ApiOperation(value = "撤回", notes = "撤回")
    @GetMapping("/v1/rollback")
    public ComResponse rollback(@RequestParam(value = "code")String code){
        return ymsgInfoService.rollback(code);
    }

    @ApiOperation(value = "取消发送", notes = "取消发送")
    @GetMapping("/v1/stopSending")
    public ComResponse stopSending(@RequestParam(value = "msgCode")String msgCode){
        return ymsgInfoService.stopSending(msgCode);
    }

    @ApiOperation(value = "查看消息详情", notes = "查看消息详情")
    @GetMapping("/v1/selectMsgItem")
    public ComResponse<MsgInfoDto> selectMsgItem(@RequestParam(value = "msgCode")String msgCode) {
        return ymsgInfoService.selectMsgItem(msgCode);
    }

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("/v1/uploadFile")
    public ComResponse<cn.net.yzl.staff.dto.common.FileDto> uploadFile(@RequestParam("file") MultipartFile file) {
        return ymsgInfoService.uploadFile(file);
    }

    @ApiOperation(value = "下载文件", notes = "下载文件")
    @GetMapping("/v1/downloadFile")
    public void downloadFile(@RequestParam(value = "str")String str, HttpServletResponse response) throws IOException {
        ymsgInfoService.downloadFile(str,response);
    }

    @ApiOperation(value = "修改消息状态", notes = "修改消息状态")
    @GetMapping("/v1/updateMsgStatus")
    public ComResponse updateMsgStatus(@RequestParam(value = "code") String code,@RequestParam(value = "msgStatus",required = false) Integer msgStatus, @RequestParam(value = "sendStatus",required = false) Integer sendStatus){
        return ymsgInfoService.updateMsgStatus(code,msgStatus,sendStatus);
    }


    @ApiOperation(value = "发送方式-部分员工", notes = "发送方式-部分员工")
    @GetMapping("/v1/selectDepartStaff")
    public ComResponse<Page<PartStaff>> selectDepartStaff(@RequestParam(value = "pageNo")Integer pageNo, @RequestParam(value = "pageSize")Integer pageSize, @RequestParam(value = "code")String code){
        log.info("type：{},apiName：{}，url:{},request:{},response:{},functionName:{},params:{},post:{}","INFO","GET","msg/v1/selectDepartStaff",null,null,"【消息管理--发送方式-部分员工】", String.format("pageNo:{},pageSize:{}code:{}",pageNo,pageSize,code),"controller");
        return ymsgInfoService.selectDepartStaff(pageNo,pageSize,code);
    }


    /**
     * 消息类型=======================================================================
     * */

    @ApiOperation(value = "新增/编辑消息类型", notes = "新增/编辑消息类型")
    @PostMapping("/v1/insertAndUpdateMsgType")
    public ComResponse insertAndUpdateMsgType(@RequestBody List<MsgTypeVo> list,HttpServletRequest request){
        String userNo = request.getHeader("userNo");
        log.info("userNo:{}",userNo);
        for (MsgTypeVo msgTypeVo : list) {
            msgTypeVo.setCreator(userNo);
        }
        return msgTypeService.insertAndUpdateMsgType(list);
    }

    @ApiOperation(value = "删除消息类型", notes = "删除消息类型")
    @GetMapping("/v1/delMsgType")
    public ComResponse delMsgType(@RequestParam(value = "msgCode") String msgCode){
        return msgTypeService.delMsgType(msgCode);
    }

    @ApiOperation(value = "查看消息类型", notes = "查看消息类型")
    @GetMapping("/v1/selectMsgType")
    public ComResponse<List<MsgTypePo>> selectMsgType(){
        return msgTypeService.selectAllMsgType();
    }



    /**
     * 消息模板=======================================================================
     * */
    @ApiOperation(value = "模板消息新增", notes = "模板消息新增")
    @PostMapping("/v1/insertMsgTemplate")
    public ComResponse sendSysMsgInfo(@RequestBody MsgTemplateVo msgTemplateVo){
        return ymsgInfoService.sendSysMsgInfo(msgTemplateVo);
    }

    @ApiOperation(value = "模板消息批量新增", notes = "模板消息批量新增")
    @PostMapping("/v1/insertMsgTemplateBatch")
    public ComResponse insertMsgTemplateBatch(@RequestBody List<MsgTemplateVo> msgTemplateVoList){
        log.info("type：{},apiName：{}，url:{},request:{},response:{},functionName:{},params:{},post:{}","INFO","POST","msg/v1/insertMsgTemplateBatch",null,null,"【消息管理----模板消息批量新增】", JsonUtil.toJsonStr(msgTemplateVoList),"controller");
        return ymsgInfoService.insertMsgTemplateBatch(msgTemplateVoList);
    }


}
