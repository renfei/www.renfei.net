package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.PermissionDO;
import net.renfei.repository.entity.PermissionDOExample;
import org.apache.ibatis.annotations.Param;

public interface PermissionDOMapper {
    long countByExample(PermissionDOExample example);

    int deleteByExample(PermissionDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PermissionDO record);

    int insertSelective(PermissionDO record);

    List<PermissionDO> selectByExample(PermissionDOExample example);

    PermissionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PermissionDO record, @Param("example") PermissionDOExample example);

    int updateByExample(@Param("record") PermissionDO record, @Param("example") PermissionDOExample example);

    int updateByPrimaryKeySelective(PermissionDO record);

    int updateByPrimaryKey(PermissionDO record);
}