package cn.net.yzl.msg.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.msg.model.dto.MsgToDoDto;
import cn.net.yzl.msg.model.dto.MsgToDoListDto;
import cn.net.yzl.msg.model.vo.MsgToDoInsertVo;
import cn.net.yzl.msg.model.vo.MsgToDoSelectVo;
import cn.net.yzl.msg.model.vo.MsgToDoUpdateVo;
import cn.net.yzl.msg.service.MsgToDoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 11:51
 * @title: MsgToDoController
 * @description:
 */
@RestController
@Slf4j
@Api(tags = "待办提醒")
@RequestMapping("/msg")
public class MsgToDoController {

    @Autowired
    private MsgToDoService msgToDoService;

    @PostMapping(value = "v1/insertMsgToDo")
    @ApiOperation("新建待办提醒")
    public ComResponse insertMsgToDo(@RequestBody MsgToDoInsertVo msgToDoInsertVo,HttpServletRequest request) {
        String userNo = request.getHeader("userNo");
        log.info("新建待办提醒userNo:{}",userNo);
        msgToDoInsertVo.setCreateUser(userNo);
        try {
            msgToDoService.insertMsgToDo(msgToDoInsertVo);
            return ComResponse.success();
        }catch(Exception e){
            log.error("待办提醒新增失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.API_ERROR_CODE.getCode(), "待办提醒新增失败");
    }

    @PostMapping(value = "v1/updateMsgToDo")
    @ApiOperation("修改待办提醒")
    public ComResponse updateMsgToDo(@RequestBody MsgToDoUpdateVo msgToDoUpdateVo,HttpServletRequest request) {
        String userNo = request.getHeader("userNo");
        log.info("修改待办提醒userNo:{}",userNo);
        msgToDoUpdateVo.setUpdator(userNo);
        try {
            msgToDoService.updateMsgToDo(msgToDoUpdateVo);
            return ComResponse.success();
        }catch(Exception e){
            log.error("待办提醒修改失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.API_ERROR_CODE.getCode(), "待办提醒修改失败");
    }

    @GetMapping(value = "v1/deleteMsgToDo")
    @ApiOperation("删除待办提醒")
    public ComResponse deleteMsgToDo(@RequestParam("code") String code) {
        try {
            msgToDoService.deleteMsgToDo(code);
            return ComResponse.success();
        }catch(Exception e){
            log.error("待办提醒删除失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.API_ERROR_CODE.getCode(), "待办提醒删除失败");
    }

    @PostMapping(value = "v1/selectMsgToDoList")
    @ApiOperation("待办提醒列表")
    public ComResponse<Page<MsgToDoDto>> selectMsgToDoList(@RequestBody MsgToDoSelectVo msgToDoSelectVo,HttpServletRequest request){
        String userNo = request.getHeader("userNo");
        log.info("待办提醒列表userNo:{}",userNo);
        msgToDoSelectVo.setUserCode(userNo);
        Page<MsgToDoDto> pageDto = msgToDoService.selectMsgToDoList(msgToDoSelectVo);
        return ComResponse.success(pageDto);
    }

    @PostMapping(value = "v1/selectMsgToDoMap")
    @ApiOperation("待办提醒列表(日期格式)")
    public ComResponse<Page<MsgToDoListDto>> selectMsgToDoMap(@RequestBody MsgToDoSelectVo msgToDoSelectVo,HttpServletRequest request){
        String userNo = request.getHeader("userNo");
        log.info("待办提醒列表(日期格式)userNo:{}",userNo);
        msgToDoSelectVo.setUserCode(userNo);
        Page<MsgToDoListDto> pageDto = msgToDoService.selectMsgToDoMap(msgToDoSelectVo);
        return ComResponse.success(pageDto);
    }

    @GetMapping(value = "v1/selectMsgToDoDetail")
    @ApiOperation("待办提醒详情")
    public ComResponse<MsgToDoDto> selectMsgToDoDetail(@RequestParam("code") String code){
        MsgToDoDto dto = msgToDoService.selectMsgToDoDetail(code);
        return ComResponse.success(dto);
    }

}
