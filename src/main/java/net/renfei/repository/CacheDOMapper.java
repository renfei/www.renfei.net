package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.CacheDO;
import net.renfei.repository.entity.CacheDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheDOMapper {
    long countByExample(CacheDOExample example);

    int deleteByExample(CacheDOExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(CacheDO record);

    int insertSelective(CacheDO record);

    List<CacheDO> selectByExampleWithBLOBs(CacheDOExample example);

    List<CacheDO> selectByExample(CacheDOExample example);

    CacheDO selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") CacheDO record, @Param("example") CacheDOExample example);

    int updateByExampleWithBLOBs(@Param("record") CacheDO record, @Param("example") CacheDOExample example);

    int updateByExample(@Param("record") CacheDO record, @Param("example") CacheDOExample example);

    int updateByPrimaryKeySelective(CacheDO record);

    int updateByPrimaryKeyWithBLOBs(CacheDO record);

    int updateByPrimaryKey(CacheDO record);
}