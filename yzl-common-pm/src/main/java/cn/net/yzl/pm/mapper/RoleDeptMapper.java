package cn.net.yzl.pm.mapper;

import cn.net.yzl.pm.entity.RoleDept;

public interface RoleDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleDept record);

    int insertSelective(RoleDept record);

    RoleDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleDept record);

    int updateByPrimaryKey(RoleDept record);
}