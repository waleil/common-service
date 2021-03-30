package cn.net.yzl.msg.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.msg.model.dto.PersonMsgInfoSelectDto;
import cn.net.yzl.msg.model.vo.MsgInfoInsertVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoEditVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoInsertVo;
import cn.net.yzl.msg.model.vo.PersonMsgInfoSelectVo;
import cn.net.yzl.msg.service.MsgInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 11:51
 * @title: MsgInfoController
 * @description:
 */
@RestController
@Slf4j
@Api(tags = "消息管理")
@RequestMapping("/msg")
public class MsgInfoController {

    @Autowired
    private MsgInfoService msgInfoService;

    @PostMapping(value = "v1/insertMsgInfo")
    @ApiOperation("新建消息发布")
    public ComResponse insertMsgInfo(@RequestBody MsgInfoInsertVo msgInfoVo,HttpServletRequest request) {
        String userNo = request.getHeader("userNo");
        log.info("新建消息发布userNo:{}",userNo);
        msgInfoVo.setSendUser(userNo);
        try {
            msgInfoService.insertMsgInfo(msgInfoVo);
            return ComResponse.success();
        }catch(Exception e){
            log.error("消息新增失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.API_ERROR_CODE.getCode(), "消息新增失败");
    }

    @PostMapping(value = "v1/selectPersonMsgInfoList")
    @ApiOperation("个人中心消息列表")
    public ComResponse<Page<PersonMsgInfoSelectDto>> selectPersonMsgInfoList(@RequestBody PersonMsgInfoSelectVo personMsgInfoSelectVo,HttpServletRequest request){
        String userNo = request.getHeader("userNo");
        log.info("个人中心消息列表userNo:{}",userNo);
        personMsgInfoSelectVo.setUserCode(userNo);
        Page<PersonMsgInfoSelectDto> pageDto = msgInfoService.selectPersonMsgInfoList(personMsgInfoSelectVo);
        return ComResponse.success(pageDto);
    }

    @GetMapping(value = "v1/selectPersonMsgInfoDetail")
    @ApiOperation("个人中心消息详情")
    public ComResponse<PersonMsgInfoSelectDto> selectPersonMsgInfoDetail(@RequestParam("msgCode") String msgCode){
        PersonMsgInfoSelectDto dto = msgInfoService.selectPersonMsgInfoDetail(msgCode);
        return ComResponse.success(dto);
    }

    @PostMapping(value = "v1/insertPersonMsgInfo")
    @ApiOperation("个人中心消息新增")
    public ComResponse insertPersonMsgInfo(@RequestBody List<PersonMsgInfoInsertVo> voList,HttpServletRequest request) {
        String userNo = request.getHeader("userNo");
        log.info("个人中心消息新增userNo:{}",userNo);
        for (PersonMsgInfoInsertVo vo : voList) {
            vo.setCreator(userNo);
        }
        try {
            msgInfoService.insertPersonMsgInfo(voList);
            return ComResponse.success();
        }catch(Exception e){
            log.error("个人中心消息新增失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.API_ERROR_CODE.getCode(), "个人中心消息新增失败");
    }

    @PostMapping(value = "v1/deletePersonMsgInfo")
    @ApiOperation("个人中心消息删除")
    public ComResponse deletePersonMsgInfo(@RequestBody List<String> msgCodeList,HttpServletRequest request) {
        String userNo = request.getHeader("userNo");
        log.info("个人中心消息删除userNo:{}",userNo);
        PersonMsgInfoEditVo vo = new PersonMsgInfoEditVo();
        vo.setMsgCodeList(msgCodeList);
        vo.setUserCode(userNo);
        try {
            msgInfoService.deletePersonMsgInfo(vo);
            return ComResponse.success();
        }catch(Exception e){
            log.error("个人中心消息删除失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.API_ERROR_CODE.getCode(), "个人中心消息删除失败");
    }

    @PostMapping(value = "v1/updatePersonMsgInfo")
    @ApiOperation("个人中心消息设为已读")
    public ComResponse updatePersonMsgInfo(@RequestBody List<String> msgCodeList,HttpServletRequest request) {
        String userNo = request.getHeader("userNo");
        log.info("个人中心消息设为已读userNo:{}",userNo);
        PersonMsgInfoEditVo vo = new PersonMsgInfoEditVo();
        vo.setMsgCodeList(msgCodeList);
        vo.setUserCode(userNo);
        try {
            msgInfoService.updatePersonMsgInfo(vo);
            return ComResponse.success();
        }catch(Exception e){
            log.error("个人中心消息设为已读失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.API_ERROR_CODE.getCode(), "个人中心消息设为已读失败");
    }

}
