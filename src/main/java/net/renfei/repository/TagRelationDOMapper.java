package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.TagRelationDO;
import net.renfei.repository.entity.TagRelationDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRelationDOMapper {
    long countByExample(TagRelationDOExample example);

    int deleteByExample(TagRelationDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TagRelationDO record);

    int insertSelective(TagRelationDO record);

    List<TagRelationDO> selectByExample(TagRelationDOExample example);

    TagRelationDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TagRelationDO record, @Param("example") TagRelationDOExample example);

    int updateByExample(@Param("record") TagRelationDO record, @Param("example") TagRelationDOExample example);

    int updateByPrimaryKeySelective(TagRelationDO record);

    int updateByPrimaryKey(TagRelationDO record);
}