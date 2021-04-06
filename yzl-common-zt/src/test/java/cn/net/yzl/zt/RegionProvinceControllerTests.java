package cn.net.yzl.zt;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.net.yzl.zt.controller.RegionProvinceController;

/**
 * 单元测试类
 * 
 * @author zhangweiwei
 * @date 2021年3月19日,上午1:31:32
 */
@SpringBootTest
public class RegionProvinceControllerTests {
	@Resource
	private RegionProvinceController controller;
	@Resource
	private ObjectMapper objectMapper;

	@Test
	public void testGetRegionProvinceList() {
		try {
			System.err.println(this.objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(this.controller.getRegionProvinceList()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
