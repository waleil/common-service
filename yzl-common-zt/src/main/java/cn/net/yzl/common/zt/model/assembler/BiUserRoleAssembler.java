package cn.net.yzl.common.zt.model.assembler;

import cn.net.yzl.common.zt.model.vo.userRole.BiUserRoleVO;
import cn.net.yzl.common.util.YDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BiUserRoleAssembler {

    public static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    public Map<String, Object> toDTO(BiUserRoleVO vo) {
        if (vo == null) {
            return null;
        }
        Map<String, Object> dto = new HashMap<>();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }

    public List<Map<String, Object>> toDTOList(List<BiUserRoleVO> voList) {
        if (voList == null) {
            return null;
        }
        List<Map<String, Object>> dtoList = voList.stream().map(dto -> {
            return toDTO(dto);
        }).collect(Collectors.toList());
        return dtoList;
    }

    public BiUserRoleVO toVO(Map<String, Object> dto) {
        if (dto == null) {
            return null;
        }
        BiUserRoleVO vo = new BiUserRoleVO();
        BeanUtils.copyProperties(dto, vo);

        return vo;
    }

    public List<BiUserRoleVO> toVOList(List<Map<String, Object>> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return null;
        }

        List<BiUserRoleVO> voList = dtoList.stream().map(i -> {
            BiUserRoleVO dto = new BiUserRoleVO();

            if (!StringUtils.isEmpty(i.get("userRoleId"))) {
                dto.setUserRoleId(Long.valueOf(String.valueOf(i.get("userRoleId"))));//主键id
            }
            if (!StringUtils.isEmpty(i.get("userId"))) {
                dto.setUserId(Long.valueOf(String.valueOf(i.get("userId"))));//用户ID
            }
            if (!StringUtils.isEmpty(i.get("userName"))) {
                dto.setUserName(String.valueOf(i.get("userName")));//用户名称
            }
            if (!StringUtils.isEmpty(i.get("roleId"))) {
                dto.setRoleId(Integer.valueOf(String.valueOf(i.get("roleId"))));//角色ID
            }
            if (!StringUtils.isEmpty(i.get("deptId"))) {
                dto.setDeptId(Integer.valueOf(String.valueOf(i.get("deptId"))));//部门ID
            }
            if (!StringUtils.isEmpty(i.get("deptName"))) {
                dto.setDeptName(String.valueOf(i.get("deptName")));//部门名称
            }
            if (!StringUtils.isEmpty(i.get("isDel"))) {
                dto.setIsDel(Integer.valueOf(String.valueOf(i.get("isDel"))));//是否删除 (1:是 0:否)
            }
            if (!StringUtils.isEmpty(i.get("delSource"))) {
                dto.setDelSource(Integer.valueOf(String.valueOf(i.get("delSource"))));//删除来源 (1:BI 2:EHR)
            }
            if (!StringUtils.isEmpty(i.get("createTime"))) {
                Date createTime = YDateUtil.StringToDate(String.valueOf(i.get("createTime")), FORMAT_STR);
                dto.setCreateTime(createTime);//创建人时间
            }
            if (!StringUtils.isEmpty(i.get("createCode"))) {
                dto.setCreateCode(String.valueOf(i.get("createCode")));//创建人编号
            }

            return dto;
        }).collect(Collectors.toList());
        return voList;

    }
}
