package cn.net.yzl.common.zt.api;

import cn.net.yzl.common.entity.ComResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 测试
 */
@Api(tags = {"test"}, description = "测试API")
public interface TestApi {

    /**
     * 测试
     *
     */
    @ApiOperation(value = "测试", nickname = "testceshi", notes = "测试,返回测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ceshi", value = "ceshi信息", dataType = "String", paramType = "query", example = "ceshi123"),

    })
    ComResponse testceshi(String ceshi);

}
