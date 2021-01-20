package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.RoleDO;
import net.renfei.repository.entity.RoleDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDOMapper {
    long countByExample(RoleDOExample example);

    int deleteByExample(RoleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleDO record);

    int insertSelective(RoleDO record);

    List<RoleDO> selectByExample(RoleDOExample example);

    RoleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleDO record, @Param("example") RoleDOExample example);

    int updateByExample(@Param("record") RoleDO record, @Param("example") RoleDOExample example);

    int updateByPrimaryKeySelective(RoleDO record);

    int updateByPrimaryKey(RoleDO record);
}