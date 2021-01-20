package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.RolePermissionDO;
import net.renfei.repository.entity.RolePermissionDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionDOMapper {
    long countByExample(RolePermissionDOExample example);

    int deleteByExample(RolePermissionDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RolePermissionDO record);

    int insertSelective(RolePermissionDO record);

    List<RolePermissionDO> selectByExample(RolePermissionDOExample example);

    RolePermissionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RolePermissionDO record, @Param("example") RolePermissionDOExample example);

    int updateByExample(@Param("record") RolePermissionDO record, @Param("example") RolePermissionDOExample example);

    int updateByPrimaryKeySelective(RolePermissionDO record);

    int updateByPrimaryKey(RolePermissionDO record);
}