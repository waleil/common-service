package cn.net.yzl.zt.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.zt.entity.RegionProvince;
import cn.net.yzl.zt.service.RegionProvinceService;

@RestController
@RequestMapping("/regionProvince")
public class RegionProvinceController {

	@Autowired
	private RegionProvinceService regionProvinceService;

	/**
	 * 查询大区省列表
	 * 
	 * @return
	 */
	// @OperateLog(operModule = "大区:查询大区列表", businessType = BusinessType.SELECT)
	@GetMapping("/getRegionProvinceList")
	public ComResponse<?> getRegionProvinceList() {

		List<RegionProvince> regionProvinceList = regionProvinceService.getRegionProvinceList();
		Map<String, List<RegionProvince>> regionProvinceListMap = regionProvinceList.stream()
				.collect(Collectors.groupingBy(RegionProvince::getRegionName, LinkedHashMap::new, Collectors.toList()));
		// 设置排序
		regionProvinceListMap.entrySet()
				.forEach(entry -> entry.getValue().sort((o1, o2) -> o1.getProvinceId() - o2.getProvinceId()));
		return ComResponse.success(regionProvinceListMap);
	}

}
