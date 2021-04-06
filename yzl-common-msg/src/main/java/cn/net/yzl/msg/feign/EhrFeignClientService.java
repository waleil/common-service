package cn.net.yzl.msg.feign;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;

import cn.net.yzl.staff.dto.StaffDetailsDto;
import cn.net.yzl.staff.dto.StaffListDto;
import cn.net.yzl.staff.vo.StaffParamsVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/24 20:20
 * @title: EhrFeignClientService
 * @description:
 */
@FeignClient(value = "staffDB",url = "${api.gateway.url}/staffDB")
public interface EhrFeignClientService {

    @ApiOperation(value = "模糊查询员工列表", notes = "模糊查询员工列表")
    @PostMapping(value = "/staff/getListByParams")
    ComResponse<Page<StaffListDto>> getListByParams(@RequestBody StaffParamsVO staffParamsVO);

    @ApiOperation(value = "根据staffNo查询用户详情", notes = "根据staffNo查询用户详情")
    @GetMapping("/staff/getDetailsByNo")
    ComResponse<StaffDetailsDto> getDetailsByNo(@RequestParam("staffNo") String  staffNo);

    @ApiOperation(value = "根据多个用户工号批量获取详情信息", notes = "根据多个用户工号批量获取详情信息")
    @PostMapping("/staff/getDetailsListByNo")
    ComResponse<List<StaffDetailsDto>> getDetailsListByNo(@RequestBody List<String> list);

}
