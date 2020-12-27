package net.renfei.discuz.repository;

import java.util.List;
import net.renfei.discuz.repository.entity.DiscuzCommonUsergroupDO;
import net.renfei.discuz.repository.entity.DiscuzCommonUsergroupDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscuzCommonUsergroupDOMapper {
    long countByExample(DiscuzCommonUsergroupDOExample example);

    int deleteByExample(DiscuzCommonUsergroupDOExample example);

    int deleteByPrimaryKey(Short groupid);

    int insert(DiscuzCommonUsergroupDO record);

    int insertSelective(DiscuzCommonUsergroupDO record);

    List<DiscuzCommonUsergroupDO> selectByExample(DiscuzCommonUsergroupDOExample example);

    DiscuzCommonUsergroupDO selectByPrimaryKey(Short groupid);

    int updateByExampleSelective(@Param("record") DiscuzCommonUsergroupDO record, @Param("example") DiscuzCommonUsergroupDOExample example);

    int updateByExample(@Param("record") DiscuzCommonUsergroupDO record, @Param("example") DiscuzCommonUsergroupDOExample example);

    int updateByPrimaryKeySelective(DiscuzCommonUsergroupDO record);

    int updateByPrimaryKey(DiscuzCommonUsergroupDO record);
}