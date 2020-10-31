package net.renfei.repository;

import java.util.List;
import net.renfei.repository.entity.TagDO;
import net.renfei.repository.entity.TagDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDOMapper {
    long countByExample(TagDOExample example);

    int deleteByExample(TagDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TagDO record);

    int insertSelective(TagDO record);

    List<TagDO> selectByExampleWithBLOBs(TagDOExample example);

    List<TagDO> selectByExample(TagDOExample example);

    TagDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TagDO record, @Param("example") TagDOExample example);

    int updateByExampleWithBLOBs(@Param("record") TagDO record, @Param("example") TagDOExample example);

    int updateByExample(@Param("record") TagDO record, @Param("example") TagDOExample example);

    int updateByPrimaryKeySelective(TagDO record);

    int updateByPrimaryKeyWithBLOBs(TagDO record);

    int updateByPrimaryKey(TagDO record);
}