package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.RegionDO;
import net.renfei.repository.entity.RegionDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDOMapper {
    long countByExample(RegionDOExample example);

    int deleteByExample(RegionDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RegionDO record);

    int insertSelective(RegionDO record);

    List<RegionDO> selectByExample(RegionDOExample example);

    RegionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RegionDO record, @Param("example") RegionDOExample example);

    int updateByExample(@Param("record") RegionDO record, @Param("example") RegionDOExample example);

    int updateByPrimaryKeySelective(RegionDO record);

    int updateByPrimaryKey(RegionDO record);
}