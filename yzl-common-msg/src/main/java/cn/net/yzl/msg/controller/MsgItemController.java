package cn.net.yzl.msg.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.msg.service.MsgItemToDoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * cn.net.yzl.msg.controller
 * 2021/2/23 13:10
 *
 * @author yangxiaopeng
 */
@RestController
@Slf4j
@Api(tags = "待办管理")
@RequestMapping("/msgItem")
public class MsgItemController {

    @Autowired
    private MsgItemToDoService msgItemToDoService;

    @GetMapping(value = "v1/addMsgToDoInfo")
    @ApiOperation("增加待办")
    public ComResponse<String> addMsgToDoInfo(@RequestParam(value = "code",required = true) String code,
                                              @RequestParam(value = "type",required = true) Integer type,
                                              @RequestParam(value = "systemCode",required = true) Integer systemCode,
                                              @RequestParam(value = "oprUser",required = true) String oprUser){
        try {
            msgItemToDoService.addMsgToDoInfo(code,type,systemCode,oprUser);
            return ComResponse.success();
        }catch(Exception e){
            log.error("增加待办失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.BIZ_ERROR_CODE.getCode(), "增加待办失败");
    }

    @GetMapping(value = "v1/subMsgToDoInfo")
    @ApiOperation("减去待办")
    public ComResponse subMsgToDoInfo(@RequestParam(value = "code",required = true) String code,
                                      @RequestParam(value = "type",required = true) Integer type,
                                      @RequestParam(value = "systemCode",required = true) Integer systemCode,
                                      @RequestParam(value = "oprUser",required = true) String oprUser){
        try {
            msgItemToDoService.subMsgToDoInfo(code,type,systemCode,oprUser);
            return ComResponse.success();
        }catch(Exception e){
            log.error("减去待办失败", e);
        }
        return ComResponse.fail(ResponseCodeEnums.BIZ_ERROR_CODE.getCode(), "减去待办失败");
    }

    @GetMapping(value = "v1/getMsgToDoItemCount")
    @ApiOperation("统计待办数量")
    public ComResponse<Integer> getMsgToDoItemCount( @RequestParam(value = "type",required = true) Integer type,
                                                     @RequestParam(value = "systemCode",required = true) Integer systemCode){
        Integer count = msgItemToDoService.getMsgToDoItemCount(type,systemCode);
        return ComResponse.success(count);
    }

}
